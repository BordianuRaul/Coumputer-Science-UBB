bits 32

global find_max_byte

segment code use32 class = code public

find_max_byte:
    
    mov ESI, 0
    mov EDX, [ESP + 12]
    mov ecx, [esp + 8]
    
    mov bl, [edx + esi]
    inc esi
    dec ecx
    
    parse_string:
    
    cmp ecx, dword 0
    je break
    
    mov AL, [edx + esi]
    
    cmp AL, BL
    
    jle not_max
    
    mov BL, AL
    
    not_max:
    
    dec ecx
    inc esi
    
    jmp parse_string
    
    break:
    
    mov AL, BL
    cbw
    cwde
    mov [ESP + 4], EAX
    
    ret