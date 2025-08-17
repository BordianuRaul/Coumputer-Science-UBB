bits 32

global start

extern exit
import exit msvcrt.dll

extern printf
import printf msvcrt.dll

extern find_max_byte
extern find_min_byte

segment data use32 class = data public
    
    string dw 1111111111111111b, 1111111100001111b
    
    len_str equ $ - string
    
    result dd 0
    max_byte db 0
    min_byte db 0
    format db "catul = %x restul = %x",0
    one db -1
    
segment code use32 class = code public

start:
    
    push dword string
    push dword len_str
    push dword result
    call find_max_byte
    
    pop EAX
    add ESP, 4 * 2
    
    mov [max_byte], AL
    
    push dword string
    push dword len_str
    push dword result
    call find_min_byte
    
    pop EAX
    add ESP, 4 * 2
    
    mov [min_byte], AL
    
    mov AL, [max_byte]
    cbw 
    mov BL, [min_byte]
    
    idiv BL
    
    mov BL, AL
    
    mov AL, AH
    imul byte [one]
    cwde
    push EAX
    
    mov AL,BL
    imul byte [one]
    cwde
    push EAX
    
    push dword format
    call [printf]
    
    add ESP, 4 * 3
    
    push dword 0
    call [exit]
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    