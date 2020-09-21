#include <stdio.h>
#include <stdlib.h>

// declare function prototypes (avoids compiler error)
int is_digit(char ch);
int is_upper(char ch);
int is_lower(char ch);

int main(int argc, char *argv[]) {
  // prompt for input from user
  printf("Enter any character: ");
  char c;
  scanf("%c", &c);

  if(is_digit(c)){ // check if input is a digit
    printf("Number was entered\n");
  } else {
    if(is_upper(c)){ // check if input is an uppercase letter
      printf("Upper case character was entered\n");
    } else if (is_lower(c)){ // check if input is a lowercase letter
      printf("Lower case character was entered\n");
    } else { // none of the above
      printf("Non-Alphanumeric character was entered\n");
    }
  }

  return (0);
}

int is_digit(char ch){
  if (ch >= '0' && ch <= '9'){ // check if ascii values fit numbers
    return 1;
  }
  return 0;
}

int is_upper(char ch){
  if (ch >= 'A' && ch <= 'Z'){ // check if ascii values fit uppers
    return 1;
  }
  return 0;
}

int is_lower(char ch){
  if (ch >= 'a' && ch <= 'z'){ // check if ascii values fit lowers
    return 1;
  }
  return 0;
}