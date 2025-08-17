bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ;a - byte, b - word, c - double word, d - qword - Unsigned representation
    
    a db 10
    b dw 20
    c dd 100
    

; our code starts here
segment code use32 class=code
    start:
        ; c-a-(b+a)+c
        
        mov ECX, [c] ; EAX = c = 100
        
        mov AL, [a] ; AL = a = 10
        
        mov AH, 0; AX = a = 10
        
        mov DX, 0; DX:AX = a
        
        push DX 
        
        push AX
        
        pop EAX ; EAX = DX:AX = a
        
        sub ECX, EAX ; ECX = c - a = 100 - 10 = 90
        
        mov BX, [b] ; BX = b = 20
        
        mov AL, [a] ; AL = a = 10
        
        mov AH, 0 ; AX = a = 10
        
        add BX, AX ; BX = a + b = 10 + 20 = 30
        
        mov AX, BX ; AX = a + b = 30
        
        mov DX, 0 ; DX = 0
        
        push DX
        
        push AX
        
        pop EAX ; EAX = a + b = 30
        
        sub ECX, EAX ; ECX = c - a - (a + b) = 90 - 30 = 60
        
        add ECX, dword [c] ; ECX = c - a - (a + b) + c = 60 + 100 = 160
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
