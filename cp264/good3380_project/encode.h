#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//number of possible characters in ascii table
#define MAX 127
void getCodes(letterNode *root, char letters[], unsigned int codes[], int arr[], int i);

int encode(letterNode *root, char output_name[]){
  //store letters in letter array
  //store codes in codes array
  char letters[MAX];
  unsigned int codes[MAX];
  unsigned int buffer = 0;
  int arr[MAX];
  int index = 0;

  getCodes(root, letters, codes, arr, index);

  // FILE *w;
  // w = fopen(output_name, "wb");
  // fwrite(buffer, sizeof(buffer), 1, w);

  //loop through string and replace each character with its code while writing to file

}

void getCodes(letterNode *root, char letters[], unsigned int codes[], int arr[], int i){
  if (root->left){
    arr[i]=0;
    getCodes(root->left, letters, codes, arr, i + 1);
  }

  if (root->right){
    arr[i]=1;
    getCodes(root->right, letters, codes, arr, i + 1);
  }

  if(!root->left && !root->right){
    printf("%c: ", root->letter);
    letters

    for (int n = 0; n < i; n++){
      printf("%d", arr[n]);
    }

    printf("\n");
  } else {
    printf("uh oh: %c", root->letter);
  }
}