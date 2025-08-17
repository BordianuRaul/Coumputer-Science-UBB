bits 32 

global start        


extern exit,printf,scanf,fopen,fprintf,fclose,find_maximum
import exit msvcrt.dll    
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fprintf msvcrt.dll
import fclose msvcrt.dll
                          


segment data use32 class=data

    ;Exercise data
    input_message db "How many numbers do you want to read: ",0
    format_read_number db "%d",0
    number_of_elemets dd 0
    current_number dd 0
    max_number dd 0
    
    ;File operation data
    file_name db "maximum.txt",0
    acces_mod db "w",0
    print_message db "The maximum value which was read, represented in hexa is: %x",0
    descrpitor_fis dd -1
    aux dd 0
    
    
segment code use32 class=code
    start:
        ;Print input message, "How many numbers to you want to read?"
        push dword input_message
        call [printf]
        add ESP,4
        
        ;Reads how many numbers the user wants to introduce
        push dword number_of_elemets
        push dword format_read_number
        call [scanf]
        add ESP,4*2
        
        mov ECX,[number_of_elemets]
        
        loop_ECX:
        mov dword [aux],ECX
        
            ;We move into current_number the input from the user
            push dword current_number
            push dword format_read_number
            call [scanf]
            add ESP,4*2
             
            
            mov EAX,[current_number] ; we move into EAX, the value that was last introduced by the user
            mov EBX,[max_number] ; we move into EBX the maximum value that was introduced until the now
            
            push EAX ; we push EAX to the stack
            push EBX ; we push EBX to the stack  
            call find_maximum ; we call the find_maximum module
            add ESP,4 * 2
            
            cmp EAX,0 ; if EAX is 0, it means that the current value is less or equal than the maximum value
            je dont_change_max
            
            ;If the jump was not executed, then the current value is the new maximum value
            mov dword [max_number],EAX ; we save into maximum, the maximum value read until now
            
            dont_change_max:
            
            mov ECX,dword [aux] ; because the value of ECX was altered when we called "scanf" we must move the value saved in aux, which was the                   initial value of ECX
        loop loop_ECX
        
        ;Write into a file the maximum value read, in hexa
        
        ;Open the file
        push dword acces_mod
        push dword file_name
        call [fopen]
        add ESP,4*2
        
        mov [descrpitor_fis],EAX
        cmp EAX,0 
        je final
        
        ;Write the maximum value which was read in the file, in hexa representation
        push dword [max_number]
        push dword print_message
        push dword [descrpitor_fis]
        call [fprintf]
        add ESP,4*3
        
        ;Close the file:
        push dword [descrpitor_fis]
        call [fclose]
        add esp,4
        
        final:
        push    dword 0      
        call    [exit]       
