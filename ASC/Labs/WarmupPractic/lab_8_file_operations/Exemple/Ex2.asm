bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen, fclose, fread, scanf, printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import fopen msvcrt.dll
import fclose msvcrt.dll
import fread msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; 2.Se citeste de la tastatura un numar si din fisier un cuvant. Daca numarul este par se cere criptarea cuvantului prin adunarea la fiecare caracter a numarului 20. Daca numarul este impar se cere criptarea cuvantului prin adaugarea dupa fiecare vocala a gruparii "p"+vocala.Se cere afisarea cuvantului criptat.
    
    n dd 0
    format_print_n db "n = ",0
    format_read_n db "%d",0
    
    filename db "cuvant.txt",0
    file_descriptor dd -1
    access_mode db "r",0
    
    format_read_string db "%s",0
    format_print_string db "%s",0
    len equ 100
    string resb len
    
    

; our code starts here
segment code use32 class=code
    start:
        ; 
        
        push dword format_print_n
        call [printf]
        add esp, 4
        
        push n
        push dword format_read_n
        call esp, 4 * 2
        
        ;OPEN FILE
        
        push dword access_mode
        push dword filename
        call [fopen]
        add esp, 4 * 2
        
        cmp eax, 0
        je final
        
        mov [file_descriptor], eax
        
        ;OPEN FILE
        
        while:
            push dword [file_descriptor]
            push dword len
            push dword 1
            push dword string
            call [fread]
            add esp, 4 * 4
            
            cmp eax, 0
            
            je break
            
            cmp eax, 100
            
            je not_last_read
            
            mov [string + eax], 0
            
            not_last_read:
            
            ;PROBLEMA IN SINE
                
                mov AX, [n]
                
                div byte 2
                
                cmp AH, 0
                
                jne not_even
                
                ;for n even
                
                mov ESI, 0
                
                parse_string:
                
                    mov AL, [string + ESI]
                    
                    cmp AL, 0
                    
                    je break_parse
                    
                    add AL, 20
                    
                    mov [string + ESI], AL
                    
                    inc ESI
                
                jmp parse_string
                
                ;for n even
                
                not_even:
                
                ; for n odd
                
                    mov ESI, 0
                
                    parse_string_1:
                    
                        mov AL, [string + ESI]
                        
                        cmp AL, 0
                        
                        je break
                        
                        mov BL, "a"
                        
                        cmp AL, BL
                        
                        jne not_vowel
                        
                        mov BL, "e"
                        
                        cmp AL, BL
                        
                        jne not_vowel
                        
                        mov BL, "i"
                        
                        cmp AL, BL
                        
                        jne not_vowel
                        
                        mov BL, "o"
                        
                        cmp AL, BL
                        
                        jne not_vowel
                        
                        mov BL, "u"
                        
                        cmp AL, BL
                        
                        jne not_vowel
                        
                        
                        mov EDI, ESI
                        inc EDI
                        
                        loop_string:
                        
                            mov BL, [string + EDI]
                            
                            cmp BL, 0
                            
                            je stop_parse
                            
                            inc EDI
                            
                            mov [string + EDI], BL
                            
                            mov BL, [string + EDI]
                            
                            cmp BL, 0
                            
                            je stop_parse
                            
                            inc EDI
                            
                            mov [string + EDI], BL 
                          
                            
                        jmp loop_string
                        
                        stop_parse_string:
                            
                            mov BL, [string + EDI - 1]
                            
                            mov [string + EDI], BL
                            mov [string + EDI + 1], byte 0
                            
                            mov BL, "p"
                            
                            mov [string + ESI], BL
                            inc ESI
                            mov [string + ESI], AL
                        
                        not_vowel:
                        
                    
                    jmp parse_string_1
                    
                    break_parse:
                
                ; for n odd
                
                
            
            ;PROBLEMA IN SINE
            
            
        jmp while
        
        break:
        
        ;CLOSE FILE
        
        push dword [file_descriptor]
        call dword [fclose]
        add esp, 4
        
        ;CLOSE FILE
        
        push dword string
        push dword format_print_string
        call [printf]
        add esp, 4 * 2
        
        final: 
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
