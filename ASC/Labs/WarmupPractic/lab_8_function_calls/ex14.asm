bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll   ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ;
    
    a dd 0
    b dd 0
    format_read_number db "%x", 0
    format_print db "a + b = %d", 0

; our code starts here
segment code use32 class=code
    start:
        ; Read two numbers a and b (in base 16) from the keyboard and calculate a+b. Display the result in base 10
        
        push a
        push format_read_number
        call [scanf]
        add ESP, 4 * 2
        
        push b
        push format_read_number
        call [scanf]
        add ESP, 4 * 2
        
        mov EAX, [a]
        mov EBX, [b]
        mov ECX, EAX
        add ECX, EBX
        
        push ECX
        push format_print
        call [printf]
        add ESP, 4 * 2
        
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
