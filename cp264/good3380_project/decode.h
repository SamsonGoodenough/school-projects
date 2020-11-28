/*
-----------------------------------------
 Author:             Eric Wildfong
 ID:                 190559940
 Email:              wild9940@mylaurier.ca
 Version             2020-11-27
 -----------------------------------------
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
----------------------------------------------------------
Decodes a given binary file into a text output file
Use: encode(dict_tree, input_file_name, output_file_name);
----------------------------------------------------------
Parameters:
  letterNode *root    - tree used to decode codes
  char *input_name    - string holding input file's name
  char *output_name   - string holding output file's name
Returns:
  true if decoded successfully
----------------------------------------------------------
*/
int decode(letterNode *root, char *input_name, char *output_name){
	int i; // Counter for looping through the string
	int j; // Counter for getting the bits
	int bit; // Current Bit we are looking at
	letterNode *curr; // Current node
	FILE *w; // Output File Handle
	FILE *f; // Input File Handle
	char *string; // Input file contents
	int start; // start point for last character byte
	long f_size;

	f = fopen(input_name, "rb");
	if (f){ // fopen is valid
		fseek(f, 0, SEEK_END); // find END OF FILE
		f_size = ftell(f); // get EOF position
		rewind(f); // rewind the stream

		string = malloc(f_size+1); // allocate space for 0-terminated character
		fread(string, 1, f_size, f); // read file into string
		string[f_size] = '\0'; // add 0-terminated character
		fclose(f); // close the file
	}
	else{
		fprintf(stderr, "INVALID INPUT FILE: cannot find file '%s'\n", input_name);
		return 0; // Can't decode from a non existent file
	}


	w = fopen(output_name, "wb"); // open the output file

	if (!w){ // check if file opened successfully
		fprintf(stderr, "INVALID OUTPUT FILE: cannot find file '%s'\n", output_name);
		return 0; // Can't decode to a non existent file
	}

	curr = root; // Start at the top of the tree

	start = (int)string[f_size-1];
	for(i = 0;i < f_size-1;i++){
		for(j = 7; 0 <= j;j--){
			if(i == f_size-2){
				if(j <= 7-start){
					bit = (string[i] >> j) & 0x01; // Get one bit at a time from the char
					// We are traversing the tree
					if(bit == 0){
						// Enter the left subtree
						curr = curr->left;
					}
					else if(bit == 1){
						// Enter the right subtree
						curr = curr->right;
					}
					if(curr->left == NULL && curr->right == NULL){
						// Leaf Node Reached
						// Output the character to the file
						fputc(curr->letter,w);
						// Return to the start of the tree
						curr = root;
					}
				}
			}
			else{
				bit = (string[i] >> j) & 0x01; // Get one bit at a time from the char
				// We are traversing the tree
				if(bit == 0){
					// Enter the left subtree
					curr = curr->left;
				}
				else if(bit == 1){
					// Enter the right subtree
					curr = curr->right;
				}
				if(curr->left == NULL && curr->right == NULL){
					// Leaf Node Reached
					// Output the character to the file
					fputc(curr->letter,w);
					// Return to the start of the tree
					curr = root;
				}
			}
		}
	}
	// Close the output file
	fclose(w);
	// Decoding was successful
	return 1;
}