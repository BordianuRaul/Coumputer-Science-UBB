bits 32
global concat

segment code use32 class = code public
concat:
    
    mov ESI, [ESP + 20]
    mov EDI, [ESP + 4]
    
    parse_s1:
        
        lodsb
        mov BL, 0
        cmp AL, BL
        je break_parse_s1
        stosb
    
    jmp parse_s1
    
    break_parse_s1:
    
    mov ESI, [ESP + 12]
    
    parse_s2:
    
        lodsb
        mov BL,0
        cmp BL, AL
        je break_parse_s2
        stosb
    jmp parse_s2
    
    break_parse_s2:
    
    ret