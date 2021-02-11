/*
-------------------------------------------------------
l03_t01.s
A simple count down program (BGT)
-------------------------------------------------------
Author:  David Brown
ID:      999999999
Email:   dbrown@wlu.ca
Date:    2020-12-14
-------------------------------------------------------
*/
.org	0x1000	// Start at memory location 1000
.text  // Code section
.global _start
_start:

.text	// code section
// Store data in registers
MOV	R3, #5		// Initialize a countdown value
	
TOP:
SUB	R3, R3, #1	// Decrement the countdown value
CMP	R3, #0		// Compare the countdown value to 0
BGE	TOP	        // Branch to TOP if > 0

_stop:
B	_stop

.end

// R3 = ffffffff, -1
