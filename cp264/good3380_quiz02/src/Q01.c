#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {

  char string[64];
  printf("Enter a string: ");
  scanf("%s", string); // grab input

  char *pString; // create pointer to first value
  pString = string;

  int characters['~'-' ' + 1] = {0}; // create array of each char indexed on their decimal ascii value mapped to 0

  while(*pString != '\0'){ // traverse string and count each occurance
    characters[*pString - ' '] += 1;
    pString++;
  }

  for(int i = 0; i < strlen(string); i++){ // print each count associated with each letter
    if(characters[string[i] - ' '] != 0){
      printf("%c | %d\n", string[i], characters[string[i] - ' ']);
      if(characters[string[i] - ' '] > 1){ // eliminate duplicates 
        characters[string[i] - ' '] = 0;
      }
    }
  }

  return (0);
}