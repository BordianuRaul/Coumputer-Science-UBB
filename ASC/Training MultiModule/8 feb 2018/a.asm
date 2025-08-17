bits 32
global start

extern exit, printf
import exit msvcrt.dll
import printf msvcrt.dll

extern get_rank

segment data use32 class = data public

    string dd 1234a678h, 12345678h, 1ac3b47dh, 0fedc9876h
    len_str equ $ - string
    format db "String of byte ranks %s, sum = %d",0
    ranks times len_str db 0
    sum dd 0


segment code use32 class = code public
start:

    push dword string
    push dword len_str
    push dword ranks
    push dword sum
    call get_rank
    add esp, 4 * 4
    
    push dword sum
    push dword ranks
    push dword format
    call [printf]
    add esp, 4 * 3

    push dword 0
    call [exit]