/*
-------------------------------------------------------
l06_t01.s
Using the code from sub_demo.s, update the PrintString 
subroutine so that it prints an end of line character at 
the end of the string it sends to the UART. (Reminder: 
the enter character is 0x0A. Save the resulting program as l06_t01.s.
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    3/3/2021
-------------------------------------------------------
*/
.org    0x1000    // Start at memory location 1000
.text  // Code section
.global _start
_start:

LDR R4, =First
BL  PrintString
LDR R4, =Second
BL  PrintString
LDR R4, =Third
BL  PrintString
LDR R4, =Last
BL  PrintString

_stop:
B    _stop

// Subroutine constants
.equ UART_BASE, 0xff201000     // UART base address
.equ EOL_CHAR, 0x0a

PrintString:
/*
-------------------------------------------------------
Prints a null terminated string.
-------------------------------------------------------
Parameters:
  R4 - address of string
Uses:
  R0 - holds character to print
  R1 - address of UART
-------------------------------------------------------
*/
STMFD  SP!, {R0-R1, R4, LR}
LDR R1, =UART_BASE

psLOOP:
LDRB R0, [R4], #1   // load a single byte from the string
CMP  R0, #0
BEQ  _PrintString   // stop when the null character is found
STR  R0, [R1]       // copy the character to the UART DATA field
B    psLOOP
_PrintString:
LDR R0, =EOL_CHAR
STR  R0, [R1]
LDMFD  SP!, {R0-R1, R4, PC}

.data
.align
// The list of strings
First:
.asciz  "First string"
Second:
.asciz  "Second string"
Third:
.asciz  "Third string"
Last:
.asciz  "Last string"
_Last:    // End of list address

.end