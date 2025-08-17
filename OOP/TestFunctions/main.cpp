#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char* str = (char*)malloc(sizeof(char) * 100);

    printf("Enter a string: ");
    fgets(str, 100, stdin);

    // remove newline character at the end of the string, if any
    if (str[strlen(str) - 1] == '\n') {
        str[strlen(str) - 1] = '\0';
    }

    // check if string is empty

    if (strlen(str) == 0) {
        printf("You entered an empty string.\n");
    } else {
        printf("You entered: %s\n", str);
    }

    free(str);

    return 0;
}





