/*
 * CP 264 Huffman Coding
 *
 * This file contains the main program, the structure of the Huffman node
 * and user interface for running your Huffman Encoder/Decoder. 
 * 
 * [huffman.c] programmed by Samson Goodenough (190723380) and Alex Lau (190786790)
 * Other headers made by other members (check their comments for credit)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "initialize.h" // by: Brian Ha
#include "encode.h"     // by: Samson Goodenough
#include "decode.h"     // by: Eric Wildfong
#include "freeTree.h"   // by: Alex Lau

int main(int argc, char **argv) {
  
  if (argc != 4 && argc != 5) {
    fprintf(stderr,
        "USAGE: ./huffman [encode | decode <source file>] "
          "<input file> <output file>\n");
    return 0;
  }

  FILE *f = fopen(argv[2], "rb");
  if (f){ // fopen is valid
    fseek(f, 0, SEEK_END); // find END OF FILE
    long f_size = ftell(f); // get EOF position
    rewind(f); // rewind the stream

    char *string = malloc(f_size+1); // allocate space for 0-terminated character
    fread(string, 1, f_size, f); // read file into string
    string[f_size] = '\0'; // add 0-terminated character
    fclose(f); // close the file

    letterNode *root = initialize(string); // construct tree

    if (strcmp(argv[1], "encode") == 0){
      encode(root, argv[3], string);
    } else if (strcmp(argv[1], "decode") == 0){
      decode(root, argv[3], argv[4]);
    }

    free_memory(root);
    root = NULL;

  } else { // fopen failed (Segmentation Fault)
    fprintf(stderr,
      "INVALID INPUT FILE: cannot find file '%s'\n", argv[2]);
    
    return 0;
  }

  return 0;
}
