/*
-------------------------------------------------------
l04_t01.s
Reads and writes until enter
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
LDR R1, =UART_BASE
        
LOOP:
LDRB R0, [R1]		// read the UART data register

STR R0, [R1]		// write to UART
CMP R0, #0x0a
BEQ _stop

B	LOOP

_stop:
B    _stop

.end

// if you don't use enter there is a FIFO overflow