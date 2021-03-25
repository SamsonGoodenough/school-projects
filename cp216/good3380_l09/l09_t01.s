/*
-------------------------------------------------------
swap_array.s
Working with stack frames and local variables.
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    03-25-2021
---------------------------------
*/
.org    0x1000    // Start at memory location 1000
.text  // Code section
.global _start
_start:

// Swap the array data
MOV    R3, #1
STMFD  SP!, {R3}     // Push j
MOV    R3, #7
STMFD  SP!, {R3}     // Push i
LDR    R3, =Data
STMFD  SP!, {R3}     // Push a
BL     swap_array
ADD    SP, SP, #12

_stop:
B      _stop

//-------------------------------------------------------
swap_array:
/*
-------------------------------------------------------
Swaps location of two values in list.
Equivalent of: swap(*a, i, j)
-------------------------------------------------------
Parameters:
  a - address of list
  i - index of first value
  j - index of second value
Local variable
  temp (4 bytes)
Uses:
  R0 - address of list
  R1 - i
  R2 - j
  R3 - value to swap
-------------------------------------------------------
*/

// your code here - get parameters, set aside local storage, preserve registers
STMFD   SP!, {FP, LR}  // push frame pointer and link register onto the stack
MOV     FP, SP         // save current stack top to frame pointer
SUB     SP, SP, #4     // set aside space for local variable temp
STMFD   SP!, {R0-R3}   // preserve other registers

LDR     R0, [FP, #8]   // get address of list
LDR     R1, [FP, #12]  // get address of i
LDR     R2, [FP, #16]  // get address of j

// offsets must be multiplied by 4 to get proper number of bytes
LSL     R1, R1, #2     // multiple first offset by 4 (shift left 2 bits)
LSL     R2, R2, #2     // multiply second offset by 4 (shift left 2 bits)

LDR     R3, [R0, R1]   // get value at first offset
STR     R3, [FP, #-4]  // copy value to temp

LDR     R3, [R0, R2]   // get value at second offset
STR     R3, [R0, R1]   // store value in first offset

LDR     R3, [FP, #-4]  // get temp
STR     R3, [R0, R2]   // store temp in second offset

// your code here - pop registers, remove local storage, reset program counter
LDMFD   SP!, {R0-R3}   // pop preserved registers
ADD     SP, SP, #4     // remove local storage
LDMFD   SP!, {FP, PC}  // pop frame pointer and program counter

//-------------------------------------------------------
.data
Data:
.word  4,5,-9,0,3,0,8,-7,12    // The list of data
_Data:    // End of list address

.end