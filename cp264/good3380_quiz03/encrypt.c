#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isValid(char string[]);

int main(int argc, char *argv[]) {
  char string[100];
  printf("Enter a string: ");
  gets(string); // grab string input

  int key;
  printf("Enter a encoding key: ");
  scanf("%d", &key); // grab key input
  key %= 26; // keep key within the shifting range

  if(strlen(string) > 0 && isValid(string)){
    for(int i = 0; i < strlen(string); i++){
      if(string[i] != ' '){
        if((string[i]+key >= 'a' && string[i]+key <= 'z') || (string[i]+key >= 'A' && string[i]+key <= 'Z')){
          string[i] += key;
        } else {
          string[i] += key - 26;
        }
      }
      
    }
    printf("Encrypted text: %s\n", string);
  } else {
    printf("\033[1;31mEntered text is not valid.\033[0m\n");
  }

  return (0);
}

int isValid(char string[]){
  for(int i = 0; i < strlen(string); i++){
    if(!(string[i] >= 'a' && string[i] <= 'z') && !(string[i] >= 'A' && string[i] <= 'Z') && !(string[i] == ' ')){
      return 0;
    }
  }
  return 1;
}