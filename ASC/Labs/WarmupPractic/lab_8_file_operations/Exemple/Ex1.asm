bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fclose, fprintf, printf, scanf                ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll   ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; 1.Sa se citeasca de la tastatura un numar n, apoi sa se citeasca mai multe cuvinte, pana cand se citeste cuvantul/caracterul "#". Sa se scrie intr-un fisier text toate cuvintele citite care incep si se termina cu aceeasi litera si au cel putin n litere.
    n dd 0
    format_print_n db "n = ",0
    format_read_n db "%d",0
    filename db "output.txt",0
    file_descriptor dd -1
    access_mode db "w",0
    format_read_string db "%s",0
    format_print_string db "%s", 0
    string resb 100
    
; our code starts here
segment code use32 class=code
    start:
        ; 
        
        push dword format_print_n
        call [printf]
        add esp, 4
        
        push dword n
        push dword format_read_n
        call [scanf]
        add esp, 4 * 2
        
        ;Open FILE
        
        push dword access_mode
        push dword filename
        call [fopen]
        add esp, 4 * 2
        
        cmp eax, 0
        je final
        
        mov [file_descriptor], eax
        
        while:
        
            push dword string
            push dword format_read_string
            call [scanf]
            add esp, 4 * 2
            
            mov ESI, 0
            
            mov AL, [string + esi]
            mov BL, "#"
            
            cmp AL, BL
            
            je break
            
            mov DL, [string + esi]
            
            mov ECX, 1
            
            parse_string:
                
                mov DH, [string + esi]
                
                inc esi
                
                mov AL, [string + esi]
                
                cmp AL, 0
                
                je break_parse
                
                inc ECX
                
            jmp parse_string
            
            break_parse:
            
            cmp ECX, [n]
            
            jne dont_add
            
            cmp DL, DH
            
            jne dont_add
            
            push dword string
            push dword format_print_string
            push dword [file_descriptor]
            call [fprintf]
            add esp, 4 * 3
            
            dont_add:
            
        jmp while
        
        break:
        
        ;Close file
        
        push dword [file_descriptor]
        call [fclose]
        add esp, 4
        
        ;Close file
        
        final:
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
