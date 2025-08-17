bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; a - byte, b - word, c - double word, d - qword - Signed representation
    
    a db 10
    
    c dd -100
    
    d dq -50

; our code starts here
segment code use32 class=code
    start:
        ; (d-a)-(a-c)-d
        
        mov EBX, dword [d + 0]
        mov ECX, dword [d + 4] ; ECX:EBX = d = -50
        
        mov AL, [a] ; AL = a = 10
        
        cbw ; AX = a = 10
        
        cwde ; EAX = a = 10
        
        cdq ; EDX:EAX = a = 10
        
        sub EBX, EAX ; EBX = EBX - EAX = -50 -10 = -60
        sbb ECX, EDX ; ECX = ECX - EDX
                     ; ECX:EBX = ECX:EBX - EDX:EAX = d - a = -60
                     
        mov AL, [a] ; AL = a = 10
        
        mov EDX, [c] ; ECX = c = -100
        
        cbw ; AX = a = 10
        
        cwde ; EAX = a = 10
        
        sub EAX, EDX ; EAX = EAX - EDX = a-c = 10 + 100 = 110
      
        ; ECX:EBX = d - a = -60
        ; EAX = a - c = 110
        
        cdq ; EDX:EAX = a - c = 110
        
        sub EBX, EAX ; EBX = EBX - EAX =  -60 - 110 = -170
        sbb ECX, EDX ; ECX = ECX - EDX
                     ; ECX:EBX = (d-a) - (a-c) = -170
                     
        mov EAX, dword [d + 0] ; EBX = -50
        mov EDX, dword [d + 4] ; ECX:EBX = d = -50
        
        sub EBX, EAX ; EBX = EBX - EAX = -170 - (-50) = -120
        sbb ECX, EDX ; ECX:EBX = (d-a) - (a-c) - d = -120
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
