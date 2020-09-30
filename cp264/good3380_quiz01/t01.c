#include <stdio.h> //i686-w64-mingw32-gcc main.c -o main.exe
#include <stdlib.h>

int main(int argc, char *argv[]) {
    setbuf(stdout, NULL); // turns standard output buffering off

    // prompt for input from user
    printf("Enter the first number: ");
    int first_num;
    scanf("%d", &first_num);

    // prompt for input from user
    printf("Enter the second number: ");
    int second_num;
    scanf("%d", &second_num);

    printf("%d + %d = %d\n", first_num, second_num, first_num + second_num); // addition
    printf("%d - %d = %d\n", first_num, second_num, first_num - second_num); // subtraction
    printf("%d × %d = %d\n", first_num, second_num, first_num * second_num); // multiplication
    printf("%d / %d = %d (Integer Division)\n", first_num, second_num, first_num / second_num); // integer devision
    printf("%d %% %d = %d (Modulo Division)\n", first_num, second_num, first_num % second_num); // modulos division
    printf("%d / %d = %.2f (Normal Division)\n", first_num, second_num, first_num / (float)second_num); // normal division (cast to float to bypass)

    return (0);
}