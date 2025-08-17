bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; A byte string S of length l is given. Obtain the string D of length l-1 so that the elements of D represent the difference between every two consecutive elements of S
    
    S db 5, 10, 20, 40, 80, 160
    len_S equ $ - S
    D times len_S - 1 db 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        mov ECX, len_S
        mov ESI, 0
        mov EDI, 0
        
        dec ECX
        
        loop_S:
        
            mov AL, [S + ESI + 1]
            mov BL, [S + ESI]
            
            sub AL, BL
            
            mov [D + EDI], AL
            
            inc ESI
            inc EDI
        
        loop loop_S
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
