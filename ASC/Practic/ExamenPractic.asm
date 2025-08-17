bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf, scanf, fprintf, fopen, fclose               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific     in
import scanf msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll
import fopen msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; Read a 100 characters string from the keyboard. The string contains a hidden message.
    ;The message was hidden by swapping the 2nd and 2nd to last letter and turning each letter in its ASCII code (in base 16).
    ;Write in a file the original message in a file. The original message does not contain spaces or digits.
    
    result times 100 db 0
    filename db "output.txt",0
    format_fprint db "%s",0
    file_descriptor dd -1
    access_mode db "w",0
    
    
    message_print db "string = ",0
    format_read db "%s",0
    
    nr_chars dd 0
    
    
    len equ 100
    string resb len


; our code starts here
segment code use32 class=code
    start:
        ; 
        
        push dword message_print
        call [printf]
        add ESP, 4
        
        push dword string
        push dword format_read
        call [scanf]
        add esp, 4 * 2
        
        
        mov ECX, 0
        mov ESI, 0
        mov EDI, 0
        
        get_nr_chars:
        
            mov AL, [string + ESI]
            
            cmp AL, byte 0
            
            je break_get_nr_chars
            
            inc ECX
            inc ESI
        
        jmp get_nr_chars
        
        break_get_nr_chars:
        
        mov [nr_chars], ECX
        
        ;SWAP 2nd and 2nd to last letter
        
        ;2, 3 cu n - 4, 2 <-> -4 3 <-> -3
        
        mov ESI, 2
        
        mov EDI, [nr_chars]
        sub EDI, 4
        
        mov AL, [string + ESI]
        mov BL, [string + EDI]
        
        mov [string + ESI], BL
        mov [string + EDI], AL
        
        inc ESI
        inc EDI
        
        
        mov AL, [string + ESI]
        mov BL, [string + EDI]
        
        mov [string + ESI], BL
        mov [string + EDI], AL
        
        
        ;SWAP 2nd and 2nd to last letter
        
        ;convert hexa to ascii
        
        mov ESI, 0
        mov EDX, 0
        
        convert_hexa_while:
            
            mov AL, [string + ESI]
            
            cmp AL, "0"
            
            jne dont_move_0
            
            mov DL, 0
            
            dont_move_0:
            
            cmp AL, "1"
            
            jne dont_move_1
            
            mov DL, 1
            
            dont_move_1:
            
            cmp AL, "2"
            
            jne dont_move_2
            
            mov DL, 2
            
            dont_move_2:
            
            cmp AL, "3"
            
            jne dont_move_3
            
            mov DL, 3
            
            dont_move_3:
            
            cmp AL, "4"
            
            jne dont_move_4
            
            mov DL, 4
            
            dont_move_4:
            
            cmp AL, "5"
            
            jne dont_move_5
            
            mov DL, 5
            
            dont_move_5:
            
            cmp AL, "6"
            
            jne dont_move_6
            
            mov DL, 6
            
            dont_move_6:
            
            cmp AL, "7"
            
            jne dont_move
            
            mov DL, 7
            
            dont_move_7:
            
            cmp AL, "8"
            
            jne dont_move_8
            
            mov DL, 8
            
            dont_move_8:
            
            cmp AL, "9"
            
            jne dont_move_9
            
            mov DL, 9
            
            dont_move_9:
            
            cmp AL, "A"
            
            jne dont_move
            
            mov DL, 10
            
            cmp AL, "B"
            
            jne dont_move
            
            mov DL, 11
            
            cmp AL, "C"
            
            jne dont_move
            
            mov DL, 12
            
            cmp AL, "D"
            
            jne dont_move
            
            mov DL, 13
            
            cmp AL, "E"
            
            jne dont_move
            
            mov DL, 14
            
            cmp AL, "F"
            
            jne dont_move
            
            mov DL, 15
            
            dont_move:
            
            mov ECX, [nr_chars]
            
            cmp ESI, ECX
            jge break_hexa_while
            
            mov CL, 0
            add CL, DL
            
            mov [result + EDI], CL
            
            inc EDI
            inc ESI
        
        jmp convert_hexa_while
        
        break_hexa_while:
        
        ;convert hexa to ascii
        
        
        ;open file
        push dword access_mode
        push dword filename
        call [fopen]
        add esp, 4 * 2
        
        cmp eax, 0
        je final
        
        mov [file_descriptor], eax
        ;open file
        
        ;WRITE TO FILE
        
        push dword result
        push dword format_fprint
        push dword [file_descriptor]
        call [fprintf]
        add esp, 4 * 3
        
        ;WRITE TO FILE 
        
        ;close file
        push dword [file_descriptor]
        call [fclose]
        add esp, 4
        ;close file
        
        final:
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
