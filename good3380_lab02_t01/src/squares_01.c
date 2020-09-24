/*
------------------------------------------------------------------------------------------
File:    squares_01.c
Project: rmagnotta_lab02_t01
Purpose: Array traversal by "ARRAY INDEXING"
         ===================================

Program Description:
	This program populates 1D integers arrays with integer values.
The "values" 1D array is populated with consecutive integer values in the range [1 to 10].
The "squares" 1D array is populated with the corresponding square values.
This version of the program access these two arrays by using the ARRAY INDEXING method.
------------------------------------------------------------------------------------------
Author:  Rick Magnotta
ID:      xxxxxxxxx
Email:   rmagnotta@wlu.ca
Version  2020-01-17
-------------------------------------
*/

#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 10

int main(int argc, char *argv[]) {
//==============================
    setbuf(stdout, NULL);           // turns standard output buffering off

    int i;                          // Loop counter/index.

    int      values [MAX_SIZE];     // Integer Array of size 10
    long int squares[MAX_SIZE];     // Integer Array of size 10.

                                    // Populate the "values" array with consecutive integers
                                    // [1,2,3, ..., 10]
    for (i=0; i<MAX_SIZE; i++) {
    	values[i] = i+1;
    }
                                    // Populate each element of the "squares" array with the
                                    // square value of its corresponding integer value stored in
                                    // the "values" array.
    for (i=0; i<MAX_SIZE; i++) {
    	squares[i] = values[i] * values[i];
    }
                                    // Print the table of values
    printf("\n");
    printf("Array traversal by \"ARRAY INDEXING\"\n");
    printf("===================================\n");
    printf("\n");
    printf("Value       Square  \n");
    printf("=====     ==========\n");

    for (i=0; i<MAX_SIZE; i++) {
    	printf("%5d     %10ld\n", values[i], squares[i]);
    }

    printf(":::\n");
    printf("::: Program Terminated.\n");
    printf(":::\n");

    return 0;
}

