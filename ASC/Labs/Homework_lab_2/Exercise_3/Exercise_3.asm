bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a dw 10
    b dw 50
    c dw 150
    d dw 100

; our code starts here
segment code use32 class=code
    start:
        ; (a+b+b)+(c-d)
        
        mov AX, [a] ; AX = a = 10
        
        mov BX, [b] ; BX = b = 50
        
        add AX, BX ; AX = AX + BX = a + b = 10 + 50 = 60
        
        add AX, BX ; AX = AX + BX = a + b + b = 60 + 50 = 110
        
        mov CX, [c] ; CX = c = 150
        
        mov DX, [d] ; DX = d = 100
        
        sub CX, DX ; CX = CX - DX = c - d = 150 - 100 = 50
        
        add AX, CX ; AX = AX + CX = (a + b + b) + (c - d) = 110 + 50 = 160
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
