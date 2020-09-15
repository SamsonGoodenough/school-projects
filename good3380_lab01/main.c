#include <stdio.h> //i686-w64-mingw32-gcc main.c -o main.exe
#include <stdlib.h>

int main(int argc, char *argv[]) {
    setbuf(stdout, NULL); // turns standard output buffering off
    int number = 0;
    printf("Enter a number: ");
    scanf("%d", &number);
    printf("The number you entered is %d\n", number);
    return (0);
}