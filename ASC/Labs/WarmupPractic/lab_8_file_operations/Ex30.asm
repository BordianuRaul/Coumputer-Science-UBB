bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fclose, scanf, fprintf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import scanf msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import fprintf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; A file name (defined in data segment) is given. Create a file with the given name, then read words from the keyboard until character '$' is read from the keyboard. Write only the words that contain at least one digit to file.
    
    filename db "output.txt",0
    file_descriptor dd -1
    acces_mode db "w",0
    format_read db "%s",0
    format_print db "%s",0
    string resb 100
    
    

; our code starts here
segment code use32 class=code
    start:
        ; 
        
        ;OPEN FILE
        
        push dword acces_mode
        push dword filename
        call [fopen]
        add ESP, 4 * 2

        
        cmp EAX, 0
        
        mov [file_descriptor], EAX
        
        je final
        
        ;OPEN FILE
        
        while:
            
            push dword string
            push dword format_read
            call [scanf]
            add ESP, 4 * 2
            
            mov ESI, 0
            
            mov AL, [string + ESI]
            
            cmp AL, "$"
            
            je break
            
            parse_word:
                
                mov AL, [string + ESI]
                
                cmp AL, 0
                
                je break_parse
                
                mov BL, "0"
                
                cmp AL, BL:
                
                jb dont_add
                
                mov BL, "9"
                
                cmp AL, BL:
                
                ja dont_add
                
                push dword string
                push dword format_print
                push dword [file_descriptor]
                call [fprintf]
                add ESP, 4 * 3
                
                jmp break_parse
                
                
                dont_add:
                
                inc ESI
                
            jmp parse_word
            
            break_parse:
        
        jmp while
        
        break:
        
        ;CLOSE FILE
        
        push dword [file_descriptor]
        call [fclose]
        add ESP, 4
        
        ;CLOSE FILE
        
        final:
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
