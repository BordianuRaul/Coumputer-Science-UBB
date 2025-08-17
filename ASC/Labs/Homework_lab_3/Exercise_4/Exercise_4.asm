bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; a,c- word
    ; b,d- byte;
    ; e- doubleword
    ; x- qword
    
     x dq -1000
     a dw -50
     b db 10
     c dw -25
     d db -5
     e dd -10
    

; our code starts here
segment code use32 class=code
    start:
        ; x/2+100*(a+b)-3/(c+d)+e*e (Signed)
         
        ; x / 2

        mov EAX, [x + 0]
        mov EDX, [x + 4]
        
        mov ECX, 2
        
        idiv ECX ; EAX = EDX:EAX / ECX = -1000 / 2 = -500 = x/2
                 ; EDX = EDX:EAX % ECX = -1000 % 2 = 0
                 
        mov ECX, EAX ; ECX = x/2 = -500
        
        ; 100*(a+b)
        
        mov BX, [a] ; BX = a = -50
        
        mov AL, [b] ; AL = b = 10
        cbw ; AX = b = 10
        
        add AX, BX ; AX = b + a = 10 - 50 = -40
        
        mov BX, 100 ; BX = 100
        
        imul BX ; DX:AX = AX * BX = 100*(a+b) = -40 * 100 = -4000
        
        ;x/2 + 100*(a+b)
        
        push DX
        
        push AX
        
        pop EAX; EAX = DX:AX = -4000
        
        add ECX, EAX ; ECX = x/2 + 100*(a+b) = -4500
        
        ;3/(c+d)
        
        mov BX, [c] ; BX = c = -25
        
        mov AL, [d] ; AL = d = -5
        
        cbw ; AX = D = - 5
        
        ADD BX, AX ; AX = c + d = -25 + (-5) = -30
        
        mov AX, 3 ; AX = 3
        
        cwd ;  DX:AX = 3
        
        idiv BX ; AX = DX:AX / BX = 3 / -30 = 0 = 3/(c+d)
               ; DX = DX:AX % BX = 3 % -30 = 3
               
        ; x/2 + 100*(a+b) - 3/(c+d)
        
        cwde ; EAX = 3/(c+d) = 0
        
        sub ECX, EAX ; ECX = EAX - ECX = -4500 = x/2 + 100*(a+b) - 3/(c+d)
        
        ; e*e
        
        mov EAX, [e] ; EAX = e = -10
        
        imul EAX ; EDX:EAX = EAX * EAX = -10 * -10 = 100
        
        ;x/2 + 100*(a+b) - 3/(c+d) + e*e
        
        push EAX
        
        push EDX
        
        mov EAX, ECX ; ECX = EAX = x/2 + 100*(a+b) - 3/(c+d) = -4500
        
        cdq ; EDX:EAX = x/2 + 100*(a+b) - 3/(c+d) = -4500
        
        pop ECX ; ECX = EDX(stiva)
        
        pop EBX ; EBX = EAX(stiva)
        
        add EAX, EBX ; EAX = EAX + EBX
        adc EDX, ECX ; EDX = EDX + ECX
                     ; EDX:EAX = EDX:ECX + ECX:EBX = x/2 + 100*(a+b) - 3/(c+d) + e*e = -4400  
       
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
