bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    a db 10
    e dw 50
    f dw 20
    

; our code starts here
segment code use32 class=code
    start:
        ; a*a-(e+f)
        
        
        mov AL, [a] ; AL = a = 10
        mul AL ; AX = AL * AL = a * a = 10 * 10 = 100
        
        mov BX, [e] ; BX = e = 50   
        mov CX, [f] ; CX = f = 20
        
        add BX, CX ; BX = BX + CX = e + f = 50 + 20 = 70

        sub AX, BX ; AX = AX - BX = a*a-(e+f) = 100 - 70 = 30
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
