bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf         ; add printf as extern function            
import exit msvcrt.dll    
import printf msvcrt.dll    ; tell the assembler that function printf can be found in library msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    
    s1 db 'a','c','e','e'
    
    len_s1 equ $ - s1
    
    s2 db 'b','d','z'
    
    len_s2 equ $ - s2
    
    result times len_s1 + len_s2  db 0
    
    len_result equ len_s1 + len_s2
    
    format db "The string: %s", 0
    
    
    
; our code starts here
segment code use32 class=code
    start:
        ; Being given two alphabetical ordered strings of characters, s1 and s2, build using merge sort the ordered string of bytes that contain all characters from s1 and s2.
        
        
        mov ESI, s1 ; index for s1
        
        mov EDI, 0 ; index for s2
        
        mov EDX, 0 ; index for result
        
        mov BX, len_s1
        
        mov CX, len_s2
        
        lodsb ; AL = first element from s1
        
        ; In the first step we will go trough s1 and s2 simultaneously, and compare elements from each string, the lower one
        ; will be added in the "final" string and the bigger one will be saved for the next comparison
        
        while:
            
            cmp AL, [s2 + EDI] ; compare the elements from s1 and s2
            
            JL if_less ;if the element from s1 is lower, we will jump over
            
                mov AH, 0 ; we conver AL, to AX so we can push it on the stack
                
                push AX ; we save the element from s1 on the stack such that we can use the register AX as an auxiliary for the
                        ;element from s2
                
                mov AL, [s2 + EDI] ; we move the element from s2 in AL
                
                mov [result + EDX], AL ; we add in the string "result" an element from s2
                
                inc EDX ; we increment EDX
                
                inc EDI ; we increment EDI, so we move to the next element from s2
                
                dec CX ; we decrement CX
                
                pop AX ; we save in AX the element from s1 which we pushed on the stack so we can use it at the next comparison
            
                cmp CX, 0 ; if we added in result all the string s2, then we exit the while
                
                je exit_while
            
            if_less:
                
                mov [result + EDX], AL ; we add into "result" the element from s1
                
                inc EDX ; we increment EDX
                
                dec BX ; we decrement BX
                
                cmp BX, 0 ; if we added in result all the string s1, then we exit the while
                
                je exit_while
                
                lodsb ; AL takes the next element from s1
                
        
        jmp while
        
        
        exit_while:
        
        
        cmp BX, 0 ; if we added all the elements from s1, we will skip the next while
        
        je skip_s1
        
                
            continue_while_s1: ; if s1 still has elements which were not added in "result" then they must be the most bigger ones
                               ; so we will add in "result" all the elements left in s1
                
                mov [result + EDX], AL ; we add in the string "result" an element from s1
                    
                inc EDX ; we increment EDX
                
                dec BX ; we decrement BX
                
                cmp BX, 0
                
                je final
                
                lodsb ; AL = next element from s2
                
            
            jmp continue_while_s1
        
        
        skip_s1:
        
        cmp CX, 0 ; if we added all the elements from s1, we will skip the next while
        
        je skip_s2
        
            continue_while_s2: ; if s2 still has elements which were not added in "result" then they must be the most bigger ones
                               ; so we will add in "result" all the elements left in s2
                
                mov AL, [s2 + EDI]
                
                mov [result + EDX], AL
                
                inc EDX
                
                inc EDI
                
                dec CX
            
                cmp CX, 0
                
                je final
            
            jmp continue_while_s2
        
        skip_s2:
        
        
        final:
        
        push result
        
        push format
        
        call [printf]
        
        add esp, 4 * 2
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
