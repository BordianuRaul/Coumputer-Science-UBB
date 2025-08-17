bits 32

global find_min_byte

segment code use32 class = code public

find_min_byte:

    mov EDX, [ESP + 12]
    mov ESI, 0
    mov ECX, [ESP + 8]
    
    mov BL, [EDX + ESI]
    dec ecx
    inc esi
    
    parse_string:
    
        cmp ecx, dword 0
        je break
        
        mov AL, [edx + esi]
        cmp AL, BL
        
        jge not_min
            
        mov BL, AL    
            
        not_min:
        
        inc ESI
        dec ECX
        
    jmp parse_string
        
    break:    
     
    mov AL, BL
    cbw
    cwde
    
    mov [ESP + 4], EAX
    
    ret
      