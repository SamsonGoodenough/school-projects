/*
-------------------------------------------------------
l06_t03.s
Write a program that combines the two previous tasks
it should read strings from the UART and write those
strings both to memory and back to the UART. Save the
resulting program as l06_t03.s. (Copy/paste is your friend.)
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    3/3/2021
-------------------------------------------------------
*/
// Constants
.equ SIZE, 20    	// Size of string buffer storage (bytes)
.text  // Code section
.org	0x1000	// Start at memory location 1000
.global _start
_start:

MOV    R5, #SIZE
LDR    R4, =First
BL	   ReadString
LDR    R4, =Second
BL	   ReadString
LDR    R4, =Third
BL     ReadString
LDR    R4, =Last
BL     ReadString
    
_stop:
B	_stop

// Subroutine constants
.equ UART_BASE, 0xff201000     // UART base address
.equ VALID, 0x8000	// Valid data in UART mask
.equ DATA, 0x00FF	// Actual data in UART mask
.equ ENTER, 0x0A	// End of line character

ReadString:
/*
-------------------------------------------------------
Reads an ENTER terminated string from the UART.
-------------------------------------------------------
Parameters:
  R4 - address of string buffer
  R5 - size of string buffer
Uses:
  R0 - holds character to print
  R1 - address of UART
-------------------------------------------------------
*/
STMFD  SP!, {R0-R1, R4-R5, LR}

// read a string from the UART
LDR  R1, =UART_BASE
ADD  R5, R4 // store address of end of buffer
        
LOOP:
LDRB  R0, [R1]  // read the UART data register
TST  R0, #VALID    // check if there is new data
STR R0, [R1]

CMP  R0, #0x0A
BEQ  _ReadString

STRB R0, [R4]      // store the character in memory
ADD  R4, R4, #1    // move to next byte in storage buffer

CMP  R4, R5        // end branch if buffer full
BEQ  _ReadString
B    LOOP

_ReadString:
LDMFD  SP!, {R0-R1, R4-R5, PC}
	
.data
.align
// The list of strings
First:
.space  SIZE
Second:
.space	SIZE
Third:
.space	SIZE
Last:
.space	SIZE
_Last:    // End of list address

.end