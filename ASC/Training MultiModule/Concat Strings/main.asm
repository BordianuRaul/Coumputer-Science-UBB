bits 32
global start
extern concat

extern printf
import printf msvcrt.dll

extern exit
import exit msvcrt.dll

segment data use32 class = data public
    
    s1 db "Ana",0
    len_s1 equ $ - s1
    
    s2 db " are mere",0
    len_s2 equ $ - s2
    
    result resb len_s1 + len_s2
    format db '%s',0
    
segment code use32 class = code public
start:
    push dword s1
    
    push dword len_s1
    
    push dword s2
    
    push dword len_s2
    
    push dword result
    
    call concat
    
    pop dword EAX
    
    add ESP, 4 * 4
    
    push dword EAX
    push dword format
    call [printf]
    add esp, 4 * 2
    
    push dword 0
    call [exit]
    
