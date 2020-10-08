/*
-------------------------------------
File:    decode_message.c
Project: 202001_CP264_Lab03
Purpose: 2D Arrays and String Handling
         =============================

Author:  Rick Magnotta
ID:      xxxxxxxxx
Email:   rmagnotta@wlu.ca
Version  2020-01-20

Program Description:
===================
   Given a 2D character array, called "scrambled", filled with ad hoc characters, this program uses the [row, col]
pair values stored in the 2D integer array, called "key", to retrieve characters from the "scrambled" array.
The retrieved individual characters are concatenated, left to right, and in the order that they are retrieved, to
form the complete decoded message. The message is then output.
------------------------------------------------------------------------------------------
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//===================== GLOBAL MACRO DEFINITIONS ==========================================================

#define  cNUL     '\0'          // NULL character
#define  sNUL     "\0"          // NULL String
#define  cBlank   ' '           // Blank character
#define  sBlank   " "           // Blank String
#define  cUScore  '_'           // Underscore character
#define  sUScore  "_"           // Underscore string.

                                // Define machine-independent TRUE and FALSE values
#ifdef TRUE
   #undef TRUE
   #undef FALSE
#endif
#define TRUE  (1==1)
#define FALSE (0==1)
                                // ======================== Statement Function Definitions.
#define  F_MIN(v1,v2) (((v1) <  (v2))? (v1):(v2))        // Return the less    of v1 and v2
#define  F_MAX(v1,v2) (((v1) >  (v2))? (v1):(v2))        // Return the greater of v1 and v2
#define ZF_MIN(v1,v2) (F_MAX(0, (F_MIN((v1),(v2)))))     // Like F_MIN, but lower bounds the result at ZERO
#define ZF(v)         (F_MAX(0, (v)))                    // Lower bounds the value "v" at ZERO.
#define  F_NOT(v)     (((v) == TRUE)? FALSE:TRUE)        // Logical Negation.
#define  F_ABS(v)     (((v) >= 0   )? (v):(-(v)))        // Absolute value

//====================== GLOBAL CONSTANTS ==================================================================
#define MAX_NUM_ROWS     5           // Max. number of rows.
#define MAX_NUM_COLS     6           // Max. number of columns.
#define MAX_KEY_PAIRS   12           // Max. number of [row, col] key pairs.

int main(int argc, char *argv[]) {
//==============================
    setbuf(stdout, NULL);            // Turns standard output buffering off

    char solved[MAX_KEY_PAIRS+4];

                                     // "scrambled" - 2D Character Array storing adhoc characters.
    char scrambled[MAX_NUM_ROWS][MAX_NUM_COLS] = {{"zevk83"},
                                                  {"Ju96Tp"},
                                                  {"b2o7If"},
                                                  {"lx0D4U"},
                                                  {"p_Q_cR"}
                                                 };
                                     // "Key" - 2D Integer Array storing the message key pairs [row, col].
                                     //         Note: The range of the row, col pair values stored in the "key"
                                     //               array is as shown below:
                                     //               [row, col] ==> [1...5, 1...6], not [0...4, 0...5]!!!
    int  key[MAX_KEY_PAIRS][2] = {{3,5},
                                  {5,2},
                                  {4,1},
                                  {3,3},
                                  {1,3},
                                  {1,2},
                                  {5,4},
                                  {5,5},
                                  {2,6},
                                  {3,2},
                                  {2,4},
                                  {4,5}
                                 };

    for (int i = 0; i < sizeof(key)/sizeof(key[0]); i++){ // loop through all the key pairs
      if (scrambled[key[i][0]-1][key[i][1]-1] == cUScore){ // check if its an underscore
        strcat(solved, sBlank); // insert space
      } else {
        strncat(solved, &scrambled[key[i][0]-1][key[i][1]-1], 1); // otherwise insert chars
      }
    }

    printf("Decoded Message: %s", solved); // print final message

    return 0;
}