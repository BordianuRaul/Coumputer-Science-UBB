bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; Two character strings S1 and S2 are given. Obtain the string D by concatenating the elements found on odd positions in S2 and the elements found on even positions in S1.
    
    s1 db 'a', 'b', 'c', 'b', 'e', 'f'
    
    l_s1 equ $ - s1
    
    s2 db '1', '2', '3', '4', '5'
    
    l_s2 equ $ - s2
    
    d times (l_s1 + l_s2 )/2  db 0
    
    two dw 2
    

; our code starts here
segment code use32 class=code
    start:
        
        ; First we concatenate the elements found on odd positions from s2 in d
        
        mov ECX, l_s2 ; ECX = l_s2, We move in the register ECX the length of the string s2
        
        mov ESI, 0 ; we use ESI as an index, and we set it to the first element from the string
        
        mov EDI, 0 ; we use EDI as index for the string d
        
        jecxz End_loop_1 ; we jump over the loop if l_s2 = 0
        
        repeat_loop_1:
            
            mov EAX, ESI ; we make a copy of the index ESI
            
            mov EDX, 0 ; we convert EAX to EDX:EAX
            
            div word [two] ; we devide EDX:EAX such that EDX have the reminder of the value from ESI / 2
            
            cmp EDX, 0 ; we compare the result from EDX, which represents the remainder from ESI / 2, with 0
                       ; such that if the index is even we jump over and skip that element from being added in d
            
            je if_even
            
            mov AL, [s2 + ESI] ; we move into AL the element from position ESI in s2
            
            mov [d + EDI], AL ; then we concatenate into d the element from AL
            
            inc EDI ; we added an element into d so we will increase it afterwards
            
            if_even:
            
            inc ESI ; we increase ESI
        
        loop repeat_loop_1
        
        End_loop_1:
        
        ; Secondly we concatenate in "d" the elements from even positions from s1
        
        mov ECX, l_s1 ; ECX = l_s1, which is the length of s1
        
        mov ESI, 0 ; reset the index to 0, because we will use it as an index for s1
        
        jecxz end_loop_2 ; jump over the loop if l_s1 = 0
        
        repeat_loop_2:
        
            mov EAX, ESI ; we make a copy of the index ESI
            
            mov EDX, 0 ; we convert EAX to EDX:EAX
            
            div word [two] ; we devide EDX:EAX such that EDX have the reminder of the value from ESI / 2
            
            cmp EDX, 0 ; we compare the result from EDX, which represents the remainder from ESI / 2, with 0
                       ; such that if the index is even we jump over and skip that element from being added in d
            
            jne if_odd
            
            mov AL, [s1 + ESI] ; we move into AL the element from position ESI in s2
            
            mov [d + EDI], AL ; then we concatenate into d the element from AL
            
            inc EDI ; we added an element into d so we will increase it afterwards
            
            if_odd:
            
            inc ESI ; we increase ESI
        
        loop repeat_loop_2
        
        end_loop_2:
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
