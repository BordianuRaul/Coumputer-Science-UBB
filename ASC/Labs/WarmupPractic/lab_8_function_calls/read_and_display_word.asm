bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    format_read db "%s",0
    format_print db "Word read: %s", 0
    string times 100 db 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword string
        push dword format_read
        call [scanf]
        add ESP, 4 * 2
        
        mov ESI, 0
        mov ECX, 0
        
        while:
        
            mov AL, [string + ESI]
            
            cmp AL, byte 0
            
            je break
            
            inc ECX
            inc ESI
            
            
        jmp while
        
        break: ; ECX has the number of chars in the word
        
        push dword string
        push dword format_print
        call [printf]
        add ESP, 4 * 2
         
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
