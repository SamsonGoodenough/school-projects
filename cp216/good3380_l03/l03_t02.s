/*
-------------------------------------------------------
l03_t02.s
A simple count down program (BGE)
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

// Store data in registers
LDR R0, =COUNTER
LDR	R3, [R0]	// Initialize a countdown value
	
TOP:
SUB	R3, R3, #1	// Decrement the countdown value
CMP	R3, #0		// Compare the countdown value to 0
BGE	TOP			// Branch to top under certain conditions
	
_stop:
B	_stop

.data	// Initialized data section
COUNTER:
.word	5

.end

// address of COUNTER is 00001020