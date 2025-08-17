bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    A dw 1011001111010001b
    B dw 0001010110010001b
    C dw 0

; our code starts here
segment code use32 class=code
    start:
        ; Given the words A and B, compute the doubleword C as follows:
        ;the bits 0-2 of C have the value 0
        ;the bits 3-5 of C have the value 1
        ;the bits 6-9 of C are the same as the bits 11-14 of A
        ;the bits 10-15 of C are the same as the bits 1-6 of B
        ;the bits 16-31 of C have the value 1
        
        mov AX, [C]
        mov DX, [C + 2] 
        
        ;DX:AX = C
        
        or AL, 00111000b
        
        mov BX, [A]
        
        ror BX, 5
        
        and BX, 0000001111000000b
        
        or AL, BL
        or AH, BH
        
        mov BX, [B]
        
        ror BX, 7
        and BX, 1111110000000000b
        
        or AX, BX
        
        or DX, 1111111111111111b
        
        mov [C], DX
        mov [C + 2], AX
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
