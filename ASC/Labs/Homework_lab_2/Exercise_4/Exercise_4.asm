bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    a db 8
    b db 4
    c db 2
    

; our code starts here
segment code use32 class=code
    start:
        ;EX 16: (a+b)/2 + (10-a/c)+b/4
    
       mov AL, [a] ; AL = a = 8
       
       add AL, [b] ; AL = a + b = 8 + 4 = 12
       
       mov AH, 0 ; AX = a + b = 12
       
       mov BL, 2
       
       div BL ; AH = 0, AL = (a+b)/2 = 6
       
       mov BL, AL ; BL = (a+b)/2 = 6
       
       mov AL, [a] ; AL = a = 8
       
       mov AH, 0 ; AX = a = 8
       
       div BYTE [c] ; AH = 0, AL = a/c = 4
       
       mov CL, 10 ; CL = 10
       
       sub CL, AL ; CL = 10-a/c = 10 - 4 = 6
       
       mov AL, [b] ; AL = b = 4
       
       mov AH, 0 ; AX = b = 4
       
       mov DL, 4 ; DL = 4
       
       div DL ; AH = 0, AL = b/4 = 1
       
       add CL, AL ; CL = (10-a/c)+b/4 =  7
       
       add BL, CL ; BL = (a+b)/2 + (10-a/c)+b/4 = 6 + 7 = 13
              
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
