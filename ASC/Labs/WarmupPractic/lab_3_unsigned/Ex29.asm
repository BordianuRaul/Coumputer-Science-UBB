bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; (a+b)/(c-2)-d+2-x; a,b,c-byte; d-doubleword; x-qword UNSIGNED
    
    a db 50
    b db 100
    c db 3
    d dd 5
    x dq 50
    

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        mov AL, [a]
        mov BL, [b]
        
        add AL,BL
        
        mov CL, [c]
        sub CL, byte 2
        
        mov AH, 0
        
        div CL
        
        ;AL = (a+b)/(c-2)
        
        mov AH, 0
        mov DX, 0
        
        push DX
        push AX
        pop EAX
        
        mov EDX, [d]
        
        sub EAX, EDX
        sub EAX, dword 2
        mov EDX, 0
        
        mov EBX, [x]
        mov ECX, [x + 4]
        
        sbb EDX, ECX
        sbb EAX, EBX
        
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
