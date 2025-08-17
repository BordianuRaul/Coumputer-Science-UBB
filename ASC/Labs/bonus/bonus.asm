bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fread, fclose, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; A text file is given. Read the content of the file, count the number of vowels and display the result on the screen. The name of text file is defined in the data segment.
    
    nr_vowels dd 0
    format_print_result db "Nr of vowels = %d", 0
    filename db "data.txt",0
    acces_mod db "r",0
    file_descriptor dd -1
    chars_read dd 0
    max_apperences dd 0
    len equ 100
    buffer resb len

; our code starts here
segment code use32 class=code
    start:
        ; 
        
        ;OPEM FILE
        push dword acces_mod
        push dword filename
        call [fopen]
        add ESP, 4 * 2
        ;OPEN FILE
        
        ;Chech if the program created the file succesfully, EAX != 0
        cmp EAX, 0
        je final
        
        mov [file_descriptor], EAX ; we save in EAX, the file descriptor
        
        while:
            ;Reads in buffer the first 100, chars
            
            push dword [file_descriptor]
            push dword len
            push dword 1
            push dword buffer
            call [fread]
            add ESP, 4 * 4
            
            ;Reads in buffer the first 100, chars
            
            ;In EAX we have the number of chars read
            cmp EAX, 100
            
            je procces_data
            
            cmp EAX, 0
            
            je cleanup
                 
            mov [buffer + EAX + 1], byte 0
            
            procces_data:
            
            mov [chars_read], EAX
            
            ;Instruction for data procces here
            
            mov ESI, 0
            while_buffer:
                mov AL, [buffer + ESI]
                
                cmp AL, byte 0
                je break
                
            jmp while_buffer
            
            break:
           
        
        jmp while
        
        cleanup:
        ;Close file
        
        push dword [file_descriptor]
        call [fclose]
        add ESP, 4
       
        
        ;Close file
        
        
        final:
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
