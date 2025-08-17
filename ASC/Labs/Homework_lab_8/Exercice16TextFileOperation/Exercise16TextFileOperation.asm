
bits 32

global start

; declare external functions needed by our program
extern exit, fopen, fread, fclose, printf
import exit msvcrt.dll
import fopen msvcrt.dll
import fread msvcrt.dll
import fclose msvcrt.dll
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    
    format db "The number of y's = %d and of z's = %d",0
    file_name db "data.txt", 0   
    access_mode db "r", 0       
                                
    file_descriptor dd -1       
    len equ 100                 
    text times len db 0         
    
    nr_of_char_read dd 0
    
    nr_of_y dd 0
    nr_of_z dd 0
    
    
    

; our code starts here
segment code use32 class=code

    start:
    
        ; call fopen() to create the file
        ; fopen() will return a file descriptor in the EAX or 0 in case of error
        ; eax = fopen(file_name, access_mode)
        push dword access_mode     
        push dword file_name
        call [fopen]
        add esp, 4*2                ; clean-up the stack

        mov [file_descriptor], eax  ; store the file descriptor returned by fopen

        ; check if fopen() has successfully opened the file (EAX != 0)
        cmp eax, 0
        je final
        
        mov EBX, 0; we will store the number of chars in EBX

        read_from_file:
        
            push dword [file_descriptor]
            push dword len
            push dword 1
            push dword text        
            call [fread]
            add esp, 4*4
            
            cmp eax, 100
            
            je dont_add_0
            
            
                cmp eax, 0
                
                je final
                
                mov [text + eax], byte 0
            
            dont_add_0:
            
            mov [nr_of_char_read], EAX ; we save in EBX the length of the string read from the file
            
            mov ECX, [nr_of_char_read] ; we move to ECX, the length of "text"
    
            mov ESI, 0 ; we use ESI, as index for parsing the string "text"
            
            mov EAX, 0 ; we use EAX for counting the letters "y"
            
            mov EBX, 0 ; we use EBX, for counting the letters "z"
            
            repeat_loop:
                
                mov DL, [text + ESI]
                
                cmp DL, "y"
                
                je y_found ; if the character is an "y" increase EAX
                
                cmp DL, "z"
                
                je z_found ; if the character is an "z" increase EBX
                
                jmp no_y_or_z_found ; if the character was not an "y" or an "z" don't increase anything
                
                y_found:
                
                    inc EAX
                    
                    jmp no_y_or_z_found ;
                    
                z_found:
                    
                    inc EBX
                    
                no_y_or_z_found: 
                
                inc ESI ; we increase the index for "text"
        
                loop repeat_loop
                
                mov EDX, 0
                
                mov EDX, [nr_of_y]
                
                add EDX, EAX
                
                mov [nr_of_y], EDX ; we put in number of y's into "nr_of_y"
                
                mov EDX, [nr_of_z]
                
                add EDX, EBX
                
                mov [nr_of_z], EDX
            
        jmp read_from_file
    
    final:
    
    ; call fclose() to close the file
    ; fclose(file_descriptor)
    push dword [file_descriptor]
    call [fclose]
    add esp, 4

    
    ; format db "The number of y's = %d and of z's = %d",0
    push dword [nr_of_z]
    push dword [nr_of_y]
    push dword format
    call [printf]
    add ESP, 4 * 3
    

    ; exit(0)
    push dword 0
    call [exit]