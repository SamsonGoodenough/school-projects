/*
-------------------------------------------------------
min_max.s
Working with stack frames.
-------------------------------------------------------
Author:  Samson Goodenough
ID:      190723380
Email:   good3380@mylaurier.ca
Date:    3/18/2021
-------------------------------------------------------
*/
.org    0x1000    // Start at memory location 1000
.text  // Code section
.global _start
_start:

// push min
LDR R3, =Min
STMFD SP!, {R3}

// push max
LDR R3, =Max
STMFD SP!,{R3}

LDR R3, =_Data
STMFD SP!, {R3}

LDR R3, =Data
STMFD SP!, {R3}

BL 	MinMax
ADD SP, SP, #16

_stop:
B    _stop

//-------------------------------------------------------
MinMax:
/*
-------------------------------------------------------
Finds the minimum and maximum values in a list.
Equivalent of: MinMax(*start, *end, *min, *max)
Passes addresses of list, end of list, max, and min as parameters.
-------------------------------------------------------
Parameters:
  start - start address of list
  end - end address of list
  min - address of minimum result
  max - address of maximum result
Uses:
  R0 - address of start of list
  R1 - address of end of list
  R2 - minimum value so far
  R3 - maximum value so far
  R4 - address of value to process
-------------------------------------------------------
*/
STMFD  SP!, {FP, LR}  // push frame pointer and link register onto the stack
MOV    FP, SP         // Save current stack top to frame pointer
// allocate local storage (none)
STMFD  SP!, {R0-R4}   // preserve other registers

LDR    R0, [FP, #8]   // Get address of start of list
LDR    R2, [R0]       // store first value as minimum
LDR    R3, [R0], #4   // store first value as maximum
LDR    R1, [FP, #12]  // get address of end of list

MinMaxLoop:
CMP    R0, R1   // Compare addresses
BEQ    _MinMax
LDR    R4, [R0], #4
CMP    R4, R2
MOVLT  R2, R4
CMP    R4, R3
MOVGT  R3, R4
B      MinMaxLoop

_MinMax:
// Store results to address parameters
LDR    R0, [FP, #16]
STR    R3, [R0]
LDR    R0, [FP, #20]
STR    R2, [R0]

LDMFD  SP!, {R0-R4}   // pop preserved registers
// deallocate local storage (none was allocated)
LDMFD  SP!, {FP, PC}  // pop frame pointer and program counter

//-------------------------------------------------------
.data  // Data section
.align
Data:
.word    4,5,-9,0,3,0,8,-7,12    // The list of data
_Data:    // End of list address
Min:
.space 4
Max:
.space 4

.end