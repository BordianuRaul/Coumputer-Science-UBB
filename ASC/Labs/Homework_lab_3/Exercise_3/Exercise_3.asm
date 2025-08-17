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
    
    x dq 10000
    a dw 50
    b db 10
    c dw 100
    d db 20
    e dd 100
    

; our code starts here
segment code use32 class=code
    start:
        ; x/2+100*(a+b)-3/(c+d)+e*e (Unsigned)
        
        ; x / 2
        
        mov EAX, [x + 0]
        mov EDX, [x + 4] ; EDX:EAX = x = 10.000
        
        mov ECX, 2 ; ECX = 2
        
        div ECX ;  EAX = EDX:EAX / ECX = 10.000 / 2 = 5000 = x/2
                ;  EDX = EDX:EAX % ECX = 10.000 % 2 = 0
                
        mov ECX, EAX ; ECX = x / 2 = 5000
        
        ;100 * (a + b)
        
        mov AX, [a] ; AX = a = 50
        
        mov BL, [b] ; BL = b = 10
        mov BH, 0 ; BX = b = 10
        
        add AX, BX ; AX = AX + BX = a + b = 50 + 10 = 60
        
        mov BX, 100 ; BX = 100
        
        mul BX ; DX:AX = AX * BX = (a + b) * 100 = 6000
        
        push DX
        
        push AX
        
        pop EBX ; EBX = DX:AX = (a + b) * 100 = 6000
        
        add ECX, EBX ; ECX = x/2 + 100*(a+b) = 1100
        
        ; 3/(c + d)
        
        mov AX, [c] ; AX = c = 100
        
        mov BL, [d] ; BL = d = 20
        mov BH, 0 ; BX = d = 20
        
        add BX, AX ; BX = BX + AX = d + c = 20 + 100 = 120
        
        mov AX, 3 ; AX = 3
        
        mov DX, 0 ; DX:AX = 3
        
        div BX ; AX = DX:AX / BX = 3 / 120 = 0
               ; DX = DX:AX % BX = 3 % 120 = 3
        
        mov DX, 0 ; DX:AX = 0 = 3/(c+d)
        
        push DX
        
        push AX
        
        pop EAX ; EAX = DX:AX = 3/(c+d) = 0
        
        sub ECX, EAX ; ECX = x/2 + 100*(a+b) - 3/(c+d) = 11000
        
        ; e*e
        
        mov EAX, [e] ; EAX = e = 100
        
        mul EAX ; EDX:EAX = EAX * EAX = 10.000
        
        ; x/2 + 100*(a+b) - 3/(c+d) + e*e
        
        mov EBX, ECX ; EAX = ECX = x/2 + 100*(a+b) - 3/(c+d) = 11000
        
        mov ECX, 0 ; ECX:EBX = x/2 + 100*(a+b) - 3/(c+d) = 11000
        
        add EBX, EAX ;
        adc ECX, EDX ;  ECX:EBX = ECX:EBX + EDX:EAX = x/2 + 100*(a+b) - 3/(c+d) + e*e = 21.000
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
