bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
                          
import printf msvcrt.dll    
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    a dd 0
    message_a db "a=",0
    b dd 0
    message_b db "b=",0
    format db "%d",0
    
    two dd 2
    
    format_hexa db "a+b/2 = %x",0

; our code starts here
segment code use32 class=code
    start:
        ; Read two numbers a and b (in base 10) from the keyboard. Calculate and print their arithmetic average in base 16
        
        push dword message_a
        call [printf]
        add ESP, 4 ; we print the message "a="
        
        push dword a
        push format
        call [scanf]
        add ESP, 4 * 2 ; we take the input for a
        
        push dword message_b
        call [printf]
        add ESP, 4 * 2 ; we print the message "b="
        
        push dword b
        push format
        call [scanf]
        add ESP, 4 * 2 ; we take the input for b
        
        mov EAX, [a]
        mov EBX, [b]
        
        add EAX, EBX
        mov EDX, 0 ;we compute the arithmetic average for a and b 
        
        mov EBX, [two]
        div EBX
        
       push EAX
       push format_hexa
       call [printf]
       add ESP, 4 * 2 ;we print the result from EAX, in hexa format
        
       ; exit(0)
       push    dword 0      ; push the parameter for exit onto the stack
       call    [exit]       ; call exit to terminate the program
