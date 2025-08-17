
#include <stdio.h>
#include <string.h>

int convert_decimal(char*, int);

int main()
{
    
    char string[100];
    int len_string;
    char delim[] = ",";
    
    printf("Enter the string: ");
    scanf("%s", string);
    len_string = strlen(string);
    
    char* ptr = strtok(string, delim);
    int len_ptr = strlen(ptr);
        
    int decimal_value;
    decimal_value = convert_decimal(ptr, len_ptr);
    printf("The value of %s decimal: %d\n", ptr, decimal_value);
    
    while (ptr != NULL){
        
        
        ptr = strtok(NULL, delim);
        len_ptr = strlen(ptr);
        
        decimal_value = convert_decimal(ptr, len_ptr);
        printf("The value of %s decimal: %d\n", ptr, decimal_value);
        
    }

    return 0;
}
