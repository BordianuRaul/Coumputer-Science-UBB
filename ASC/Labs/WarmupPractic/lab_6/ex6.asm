bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; 
         
    s DD 12345678h, 1A2B3C4Dh, 1E98DC76h
    
    len_s equ $ - s
    
    d times len_s db 0
    
    ten db 10
    
    four db 4

; our code starts here
segment code use32 class=code
    start:
        ; A string of doublewords is given. Compute the string formed by the high bytes of the low words from the elements of the doubleword string and these bytes should be multiple of 10.
        
        
        mov EAX, len_s
        div  byte [four]
        mov ECX, EAX
        
        mov EDI, 0
        
        mov ESI, s
        mov EDI, d
        
        loop_s:
        
            lodsd
            
            mov EBX, EAX
            
            mov AL, BH
            mov AH, 0
            
            div byte [ten]
            
            cmp AH, 0
            
            jne is_not_multiple_of_10
            
            mov AL, BH
            
            stosb
            
            is_not_multiple_of_10:
            
        loop loop_s
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
