/*
-------------------------------------
File:    max_heap_int.c
Project: 202009_CP264_Lab09_Heap
Heap Source Code
 -------------------------------------
Author:  Rick Magnotta/Heider Ali
ID:      xxxxxxxxx
Email:   rmagnotta@wlu.ca/heali@wlu.ca
Version  2020-11-22
			 Created max_heap_int.c from max_heap.c
----------------------------------------------------
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <assert.h>

#include "max_heap_int.h"

// Macros
//#define LESS_THAN(x,y)          compare((x), (y)) < 0
//#define LESS_THAN_EQUAL(x,y)    compare((x), (y)) <= 0
//#define GREATER_THAN(x,y)       compare((x), (y)) > 0
//#define GREATER_THAN_EQUAL(x,y) compare((x), (y)) >= 0
//#define EQUALS(x,y)             compare((x), (y)) == 0
//#define NOT_EQUALS(x,y)         compare((x), (y)) != 0
//#define COMPARE(x,y)            compare((x), (y))

// ===================================================== local functions
max_heap* max_heap_initialize()
//=============================
{
	max_heap *source;

	source = malloc(sizeof *source);                // Allocate storage for the "source" heap "header".
	assert(source != NULL);
																	 // Allocate storage for the "values" array of integers to store the "heap".
	source->values = (int *) malloc(MAX_HEAP_SIZE * (sizeof *source->values));
	assert(source->values != NULL);
																	 // Initialize heap header values.
	source->capacity = MAX_HEAP_SIZE;
	source->size     = 0;

	 #ifdef DEBUG_LEVEL_01
		if ( source->values != NULL ) {
			printf("MAX_HEAP_SIZE          = [%d]      \n", MAX_HEAP_SIZE);
			printf("sizeof *source->values = [%ld]      \n", (sizeof *source->values));
			printf("Size of Values array   = [%ld] bytes\n", MAX_HEAP_SIZE * (sizeof *source->values));
		 }
	 #endif
	return ( source );
}

bool max_heap_empty(const max_heap *source)
//=========================================
// Heap is EMPTY if size of heap is ZERO. Otherwise, heap is NOT empty.
{
	bool empty_status;

	if ( source->size == 0 ) empty_status = TRUE;
	else                     empty_status = FALSE;
	return ( empty_status );
}

bool max_heap_full(const max_heap *source)
//========================================
// TRUE if Heap is full, that is, if capacity = size
{
	bool full_status;
	if ( source->size == source->capacity ) full_status = TRUE;
	else                                    full_status = FALSE;
	return ( full_status );
}

int max_heap_size(const max_heap *source)
//=======================================
{
	return (source->size);
}

int max_heap_peek(const max_heap *source)
//=======================================
{
	int return_val;
	assert(source->size > 0);                                  // Cannot peek on an empty heap.

	return_val = -999999;                                      // Have to return something, if heap is EMPTY!
	if ( source->size > 0 ) return_val = *source->values;      // values[0]

	return ( return_val );
}

bool max_heapify(max_heap *source,
					int       keys[],
				 int       num_keys)
//==================================
{
	bool heapify_status;

	if ( num_keys > source->capacity ) {
	  heapify_status = FAILURE;

	}else {
		for (int i = 0; i < num_keys; i++) {
		  max_heap_insert(source, keys[i]);
		}
		heapify_status = SUCCESS;
	}
	return ( heapify_status );
}

bool max_heap_insert(      max_heap *source,
						 const int       value )
//==========================================
{
	bool insert_status;

	insert_status = FAILURE;

	if (!max_heap_full(source)){
		source->values[source->size] = value;
		source->size++;

		if (value > source->values[source->size - 2]){
			heapify_up(source);
    }

		insert_status = SUCCESS;
	}

	return ( insert_status );
}


void heapify_up(max_heap *source)
//======================================
// Moves a value from location index up the heap until
// it is in its correct location in the heap.
{
	int end_idx;
	int child_idx;
	int parent_idx;
	int child_value;
	int parent_value;

	#ifdef DEBUG_LEVEL_01
		printf(">>> In heapify_up()\n");
	 #endif

	end_idx = source->size - 1;              // Index of last position in heap array. New item start
														  // position is always at end of Heap Array.
	child_idx  = end_idx;
	parent_idx = (child_idx - 1) / 2;        // Get the location of the parent of the value at index.

	child_value  = source->values[child_idx ];
	 parent_value = source->values[parent_idx];

	 #ifdef DEBUG_LEVEL_01
		 printf("source->size = [%d]\n", source->size);
		 printf("child_idx    = [%d]\n", child_idx   );
		 printf("parent_idx   = [%d]\n", parent_idx  );
		 printf("child_value  = [%d]\n", child_value );
		 printf("parent_value = [%d]\n", parent_value);
	 #endif

	 while ( (child_idx > 0) && ( parent_value < child_value) ) {

		 heap_swap( &source->values[child_idx], &source->values[parent_idx] );

		  #ifdef DEBUG_LEVEL_02
			 printf("Back from heap_swap =============\n");
			 printf("child_value  = [%d]\n", source->values[child_idx ]);
			 printf("parent_value = [%d]\n", source->values[parent_idx]);
		  #endif
														  // Update Child and Parent Indexes and Values, in preparation for next go round.
		 child_idx  = parent_idx;             // Child now becomes its parent.
		 parent_idx = (child_idx - 1) / 2;    // Obtain new Parent.

		 child_value  = source->values[child_idx ];
		  parent_value = source->values[parent_idx];

		  #ifdef DEBUG_LEVEL_02
			  printf("=================================\n");
			  printf("source->size = [%d]\n", source->size);
			  printf("child_idx    = [%d]\n", child_idx   );
			  printf("parent_idx   = [%d]\n", parent_idx  );

			  printf("child_value  = [%d]\n", child_value );
			  printf("parent_value = [%d]\n", parent_value);
		  #endif
	 }

	 #ifdef DEBUG_LEVEL_01
		 printf("<<< Returning from heapify_up()\n");
	 #endif

	 return;
}

/**
  * Moves a value down a heap to its correct position.
 * @param source pointer to a heap.
 */
void heapify_down(max_heap *source)
//=================================
{

	 // your code here

	return;
}


void heap_swap(int *a,
				 int *b)
//====================
{
	int  temp;

	temp = *a;
	*a   = *b;
	*b   = temp;
	return;
}

void heap_nice_print(max_heap *source)
//====================================
{
	int max_lvl;                             // Max. level of heap for given heap size: [0,1,2,3,...]
	int lvl;                                 // Current level
	int Lidx;                                // Left-Index of heap array in current level.
	int Ridx;                                // Right-Index of heap array in current level.
	double epsilon;                          // Rounding error compensation value.
	epsilon = 0.00000001;

	printf("\n");
	printf("Heap Structure:\n");
	printf("============== \n");

	if (source->size == 0) {
	  printf("::: Heap is EMPTY!\n");
	}else {
														  // Calculate maximul level of heap, for given heap size, taking care to
														  // incorporate a fix for the rounding error in log2 calculation.
		max_lvl = (int)(log(source->size)/log(2.0) + epsilon);

		for (lvl = 0; lvl <= max_lvl; lvl++) { // Loop through all levels, lvl being the current level of heap.

			Lidx = (int)pow(2.0, lvl) - 1;                     // Calculate the Left index and the Right index for the current heap level.
			Ridx = (int)fmin( 2*Lidx, source->size -1);

			for (int i = Lidx; i <= Ridx; i++) {               // Print all array values of the current level, on the same output line.
			  printf("%d  ", source->values[i]);
			}
			printf("\n");
		}
		printf("\n");
	}
	return;
}


