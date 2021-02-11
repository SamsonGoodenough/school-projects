/*
-------------------------------------------------------
l04_t02.s
Reads and writes into memory until enter
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    2/11/2021
-------------------------------------------------------
*/
// Constants            
.equ UART_BASE, 0xff201000     // UART base address
.equ SIZE, 80        // Size of string buffer storage (bytes)
.equ VALID, 0x8000   // Valid data in UART mask
.org    0x1000       // Start at memory location 1000
.text  // Code section
.global _start
_start:

// read a string from the UART
LDR  R1, =UART_BASE
LDR  R4, =READ_STRING
ADD  R5, R4, #SIZE 	// store address of end of buffer
        
LOOP:
LDRB R0, [R1]  		// read the UART data register
TST R0, #VALID    	// check if there is new data

CMP R0, #0x0a
BEQ  _stop

STRB R0, [R4]      	// store the character in memory
ADD R4, R4, #1    	// move to next byte in storage buffer

CMP R4, R5 			//check to see if memory buffer is full
BEQ _stop

B    LOOP

_stop:
B    _stop

.data
	READ_STRING:
		.space SIZE

.end

// you can run the program again and it will continue from after the ENTER key, but will overwrite any memory