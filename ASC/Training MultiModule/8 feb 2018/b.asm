bits 32
global get_rank

segment code use32 class=code public

get_rank:
    
    current dd 0
    max db 0
    rank db 0
    
    mov ECX, [esp + 12]
    mov esi, [esp + 16]
    mov edi, 0
    parse_string:
    
        lodsd
        mov [current], EAX
        mov DX, [current]
        mov AX, [current + 2]
        
        mov [max], DH
        mov bl, 1
        mov [rank], bl
        
        cmp dl, [max]
        jae not_dl
        
        mov [max],dl
        mov bl, 2
        mov [rank],bl
        
        not_dl:
        
        cmp ah, [max]
        jae not_ah
        
        mov [max], ah
        mov bl, 3
        mov [rank], bl
        
        not_ah:
        
        cmp [max], al
        jae not_al
        
        mov [max], AL
        mov bl, 4
        mov [rank], bl
        
        not_al:
        
        mov bl, [rank]
        mov EAX,[ESP + 8]
        mov [EAX + EDI], bl
        
        mov eax, [esp + 4]
        mov bl,0
        mov bh,0
        push word 0
        pop EBX
        
        add EAX, EBX
        mov [esp + 4], eax
        inc edi
        dec ecx
        
    jmp parse_string
        
    ret
        
        
        
        
        
        
        
        
        
        
        
        
        
        