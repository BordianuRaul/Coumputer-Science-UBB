bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    a db 20
    b db 30
    c db 10
    d db 50
    

; our code starts here
segment code use32 class=code
    start:
        ; a+13-c+d-7+b
        
        
        mov AL, [a] ; AL = a = 20
        
        add AL, 13 ; AL = a + 13 = 20 + 13 = 33
        
        mov CL, [c] ; CL = c = 40
        
        sub AL, CL ; AL = a + 13 - c = 33 - 10 = 23
        
        mov DL, [d] ; DL = d = 50
        
        add AL, DL ; AL = AL + DL = a + 13 - c + d = 23 + 50 = 73
        
        sub AL, 7 ; AL = AL - 7 = a + 13 - c + d - 7 = 73 - 7 = 66
        
        mov BL, [b] ; BL = b = 30
        
        add AL, BL ; AL = AL + BL = a + 13 - c + d - 7 + b = 66 + 30 = 96
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
