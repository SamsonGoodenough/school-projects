/*
 * CP 264 Huffman Coding
 *
 * This file contains the main program,the structure of the Huffman node
 * and user interface for running your Huffman Encoder/Decoder. 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "initialize.h"
#include "encode.h"

int main(int argc, char **argv) {
  argv[1] = "encode";
  argv[2] = "example.txt";
  argv[3] = "example.txt";
  // if (argc != 4) {
  //   fprintf(stderr,
  //       "USAGE: ./huffman [encode | decode] "
  //       "<input file> <output file>\n");
  //   return 0;
  // }

  FILE *f = fopen(argv[2], "r");
  if (f){ // fopen is valid
    fseek(f, 0, SEEK_END); // find END OF FILE
    long f_size = ftell(f); // get EOF position
    rewind(f); // rewind the stream

    char *string = malloc(f_size+1); // allocate space for 0-terminated character
    fread(string, 1, f_size, f); // read file into string
    string[f_size] = '\0'; // add 0-terminated character
    fclose(f); // close the file

    // FILE *w;
    // w = fopen(output_name, "wb");
    // fwrite(buffer, sizeof(buffer), 1, w);

    //loop through string and replace each character with its code while writing to file

      letterNode *root = initialize(string);
    printf("%c, %d\n", root->letter, root->freq);

    if (strcmp(argv[1], "encode") == 0)
      encode(root, argv[3]);
    else if (strcmp(argv[1], "decode") == 0)
      // decode(root, argv[2], argv[3]);
      printf("uncomment decode\n");
    else
      fprintf(stderr,
          "USAGE: ./huffman [encode | decode] "
          "<input file> <output file>\n");

    // uncomment when coded
    // free_memory();

  } else { // fopen failed (Segmentation Fault)
    fprintf(stderr,
      "INVALID INPUT FILE: cannot find file '%s'\n", argv[2]);
    return 0;
  }

  return 0;
}



