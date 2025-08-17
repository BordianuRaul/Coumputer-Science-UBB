bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; A character string is given (defined in the data segment). Read one character from the keyboard, then count the number of occurences of that character in the given string and display the character along with its number of occurences.
    
    s db "hello world",0
    len_s equ $ - s

    format_read db "%c",0

    
    format_print_char db "char = ",0
    format_print_result db "The character %c appeard %d times in the string",0
    
        
    char db 0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        push dword format_print_char
        call [printf]
        add esp, 4
        
        push dword char
        push dword format_read
        call [scanf]
        add esp, 4 * 2
        
        mov ECX, len_s
        mov ESI, 0
        mov EBX, 0
        
        loop_s:
            
            mov AL, [s + ESI]
            
            mov AH, [char]
            
            cmp AL, AH
            
            jne dont_add
            
            inc EBX
            
            dont_add:
            
            inc ESI
            
        loop loop_s
        mov EAX, dword [char]
        
        push EBX
        push EAX
        push format_print_result
        call [printf]
        add ESP, 4 * 3
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
