bits 32

global _convert_decimal

segment data public data use32
    result dd 0
    power dd 1
    two dw 2
    

segment code public code use32

_convert_decimal:

    ;we have one parameter which is an adress of a string
    
    mov eax, 0
    mov [result], eax
    mov eax, 1
    mov [power], eax
    
    push ebp
    mov ebp, esp
    
    mov ebx, [ebp + 8] ; EAX will contain the adress of the string
    mov ecx, [ebp + 12] ; ECX will contain the length of the string
    dec ecx
    
    mov al, [ebx + ecx]
    mov ah, "0"
    cmp ah, al
    je skip_first_element
    
    mov eax, [power]
    mov [result], eax
    
    mul word [two]
    mov [power], eax
    
    dec ecx
    
    skip_first_element:
    
    std
    loop_string:
        
        mov dl,[ebx + ecx]
        mov dh, "1"
        cmp dl, dh
        jne dont_add
        
        mov edx, [result]
        mov eax, [power]
        add edx, eax
        
        mov [result], edx
        
        dont_add:
        
        mov eax, [power]
        mul word [two]
        mov [power], eax
    
    loop loop_string
    
    mov dl,[ebx + ecx]
    mov dh, "1"
    cmp dl, dh
    jne final
    
    mov edx, [result]
    mov eax, [power]
    add edx, eax
    
    mov [result], edx
    
    final:
    
    mov eax, [result]
    mov esp,ebp
    pop ebp
    
    ret
    