#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

int isValid(char c);

int main(int argc, char *argv[]) {
  int DECRYPT_KEY = 5;
  
  FILE *file;
  file = fopen("./encMessage.txt", "r"); // open the file

  if (file == NULL){ // check if file opened properly
    printf("%s\n", strerror(errno));
  } else {
    char body[256];
    fscanf(file, "%[^\a]", &body); // scans until first '\a' (end of file since '\a' is not valid)

    for (int i = 0; i < strlen(body); i++){ // run through each character
      if (body[i] != ' ' && isValid(body[i])){ // check it can be decoded
        if (isValid(body[i]-DECRYPT_KEY)){ 
          body[i] -= DECRYPT_KEY; // decode
        } else {
          body[i] -= DECRYPT_KEY - 26; // decode and map it within the letters range
        }
      }
    }

    puts(body); // print
  }

  return 0;
}

// checks if character is within valid decoding standards (alpha)
int isValid(char c){
  if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
    return 1;
  }
  return 0;
}