bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; Given the byte A and the word B, compute the doubleword C as follows:
    
    a db 11010011b
    b dw 0110111000100111b
    c dd 0

; our code starts here
segment code use32 class=code
    start:
        
        ; the bits 0-7 of C have the value 1
        ; the bits 8-11 of C are the same as the bits 4-7 of A
        ; the bits 12-19 are the same as the bits 2-9 of B
        ; the bits 20-23 are the same as the bits 0-3 of A
        ; the bits 24-31 are the same as the high byte of B
        
        
        ; the bits 0-7 of C have the value 1
        ; We will compute the final result in EBX
        
        mov EBX, 00000000000000000000000011111111b ; We force bits 0-7 to be equal to 1
        
        ; the bits 8-11 of C are the same as the bits 4-7 of A
        
        ; first we need to convert the value of a which is a byte to a doubleword
        mov AL, [a] ; AL = a
        
        mov AH, 0 ; We convert AL to AX
        
        and AX, 0000000011110000b ; we isolate the bits 4-7 from a
        
        mov CL, 4
        
        rol AX, CL ; we rotate 4 positions to the left, AX = 0000 1101 0000 0000b = 3328
        
        mov DX, 0 ; convert AX to DX:AX
        
        push DX
        
        push AX
        
        pop EAX
        
        or EBX, EAX ; we put the bits into the result EBX = 0000 0000 0000 0000 0000 1101 1111 1111b = 3583
        
        
        ; the bits 12-19 are the same as the bits 2-9 of B
        
        mov AX, [b] ; AX = b = 0110111000100111b = 28199
        
        and AX, 0000001111111100b ; we isolate the bits 2-9 of B
        
        mov DX, 0 ; convert AX to DX:AX
        
        push DX
        
        push AX
        
        pop EAX ;  we convert DX:AX to EAX
        
        mov CL, 10
        
        rol EAX, cl ; we rotate 10 positions to the left
        
        or EBX, EAX ; we put the bits into the result, EBX = 0000 0000 0000 1000 1001 1101 1111 1111b = 564735
        
        ; the bits 20-23 are the same as the bits 0-3 of A
        
        mov AL, [a] ; AL = A
        
        and AL, 00001111b ;  we isolate the bits 0-3 of A
        
        mov AH, 0 ; convert AL to AX
        
        mov DX, 0 ; convert AX to DX:AX
        
        push DX
        
        push AX
        
        pop EAX ; convert DX:AX to EAX
        
        mov CL, 20
        
        rol EAX, cl ; rotate 20 positions to left
        
        or EBX, EAX ; we put the bits into the result, EBX = 0000 0000 0011 1000 1001 1101 1111 1111b = 3710463
        
        ; the bits 24-31 are the same as the high byte of B
        
        mov AX, [b] ; AX = B
        
        and AX, 1111111100000000b ; we isolate the high byte of B
        
        mov DX, 0 ; convert AX to DX:AX
        
        push DX
        
        push AX 
        
        pop EAX ; convert DX:AX to EAX
        
        mov CL, 16
        
        rol EAX, CL ; we rotate 20 positions to left
        
        or EBX, EAX ; we put the bits into the result, EBX = 0110 1110 0011 1000 1001 1101 1111 1111b = 1849204223
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
