bits 32
global find_maximum

;| return address |
;|----------------|
;| maximum value  |
;|----------------|
;| current value  |
;|----------------|

;input Values , integers:
;EDX holds the value of the current number in decimal
;EBX holds the maximum value in decimal


;the EDX,EAX,EBX registers will be modified
;returns the value from EAX, which is 0 if the current number is less or equal to the maximum value else the current value

find_maximum:


    mov EDX,[ESP+8]
    mov EBX,[ESP+4]
    mov EAX,0
    
    cmp EDX,EBX
    
    jbe not_a_bigger_value
    
    ;This number is a new max
    mov EAX,EDX
    
    not_a_bigger_value:
    
    ret
    