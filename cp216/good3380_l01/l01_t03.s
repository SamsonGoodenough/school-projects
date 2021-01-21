/*
-------------------------------------------------------
l01.s
Assign to and add contents of registers.
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    2021-01-22
-------------------------------------------------------
*/
.org    0x1000  // Start at memory location 1000
.text  // Code section
.global _start
_start:

MOV R0, #9       // Store decimal 9 in register R0
MOV R1, #14     // Store hex E (decimal 14) in register R1
ADD R2, R1, R0  // Add the contents of R0 and R1 and put result in R2

MOV R3, #8
ADD R3, R3, R3

MOV R5, #4
ADD R4, R5, #5

// End program
_stop:
B   _stop

/*
Q&A

1. Doesn't change the behaviour in any way
2. Yes it is legal to run `ADD R3, R3, R3`
3. It is illegal to run `ADD R4, #4, #5` but legal with `ADD R4, R5, #5`
*/