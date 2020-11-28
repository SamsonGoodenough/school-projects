/*
-------------------------------------
File:    main_int.c
Project: 202009_CP264_Lab09_Hashing
file description
-------------------------------------
Author:  Rick Magnotta/Heider Ali
ID:      xxxxxxxxx
Email:   rmagnotta@wlu.ca/heali@wlu.ca
Version  2020-11-22
-------------------------------------
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>


#include "My_Definitions.h"
#include "hashing_int.h"
                                             //===== GLOBAL DEFINITIONS ===================================================
#define MAX_INPUT_LINE_LNG   81                   // Define an 80 char input line +1 byte for terminating null!
#define MAX_HASH_TABLE_SIZE  10                   // Max size of Hash table ==> 10 slots!

#define my_isdigit(v) (isdigit(v) != 0)           // isdigit(v)    ==> returns Non-Zero if DIGIT, otherwise 0.
                                                  // my_isdigit(v) ==> TRUE if digit, FALSE otherwise. Much simpler!

int main(int argc, char *argv[])
//==============================
{
	bool DONE;                                    // Program processing flag.
	char input_buff[MAX_INPUT_LINE_LNG];          // User input storage.
	int  input_choice;                            // User input selection:: 1, 2, 3, or 4

	int  hash_table[MAX_HASH_TABLE_SIZE];         // Hash Table - implemented as a 1D array of INT of size HASH_TABLE_SIZE.

	setbuf(stdout, NULL);

    initialize_hash_table(hash_table, MAX_HASH_TABLE_SIZE);   // Lets get set up to process HASHING!

	DONE = FALSE;
	while ( !DONE ) {                             //===== Keep processing input until user elects to STOP!
	   printf("\n");
	   printf("Enter:: 1) Insert \t 2) Display \t 3) Search \t 4) Exit: \n");
	                                              //===== Must do some INPUT scrubbing/validation here to catch invalid input!
	   gets(input_buff);                               // Read entire input line, but only keep the first two chars.
	   input_buff[MAX_INPUT_LINE_LNG -1] = '\0';       // Force a Null at last byte to ensure input buffer is NULL terminated (in
	                                                   // case user enters more than MAX_INPUT_LINE_LNG chars).

	   input_choice = -1;                              // Assume an invalid input.
	   if (strlen(input_buff) == 1) {                  // Valid input (i.e. "input_choice") must have a length of 1-byte.
	      if ( my_isdigit(*input_buff) ) input_choice = atoi( input_buff );     // Check if input byte is a digit, and if so, convert
	   }

	   switch ( input_choice ) {                       // Carry out the user's request.
	      case 1:
	    	 printf("::: [1] ==> Insert:\n");
	         insert_hash_table(hash_table, MAX_HASH_TABLE_SIZE);
	         break;
	      case 2:
	    	 printf("::: [2] ==> Display:\n");
	         display_hash_table(hash_table, MAX_HASH_TABLE_SIZE);
	         break;
	      case 3:
	    	 printf("::: [3] ==> Search:\n");
	         search_hash_table(hash_table, MAX_HASH_TABLE_SIZE);
	         break;
	      case 4:
	    	 printf("::: [4] ==> Exit:\n");
	         DONE = TRUE;
	         break;
	      default:
	         printf(">>> ERR: Invalid Input: [%s]\n", input_buff);
	         break;
	   }
	}
	printf("\n::: Program Terminated...till next time.\n");
	exit( SUCCESS );
}
