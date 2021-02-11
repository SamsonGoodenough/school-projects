/*
-------------------------------------------------------
l03_t03.s
An infinite loop program with a timer delay and
LED display.
-------------------------------------------------------
Author:  David Brown
ID:      999999999
Email:   dbrown@wlu.ca
Date:    2020-12-14
-------------------------------------------------------
*/
// Constants
.equ TIMER, 0xfffec600
.equ LEDS,  0xff200000
.org	0x1000	// Start at memory location 1000
.text  // Code section
.global _start
_start:
LDR R0, =LED_BITS	// use R0 as address loader
LDR R3, [R0]		// value to set LEDs
LDR R0, =DELAY_TIME
LDR R4, [R0]		// timeout = 1/(200 MHz) x 200x10^6 = 1 sec

LDR R1, =LEDS		// LEDs base address
LDR R2, =TIMER		// private timer base address

STR R4, [R2]		// write timeout to timer load register
MOV R4, #0b011		// set bits: mode = 1 (auto), enable = 1
STR R4, [R2, #0x8]	// write to timer control register
LOOP:
STR R3, [R1]		// load the LEDs
WAIT:
LDR R4, [R2, #0xC]	// read timer status
CMP R4, #0
BEQ WAIT			// wait for timer to expire
STR R4, [R2, #0xC]	// reset timer flag bit
ROR	R3, #1			// rotate the LED bits
B LOOP

.data
	LED_BITS:
		.word 0xF0F0F0F0	// changes led pattern
	DELAY_TIME:
		.word 100000000		// changes how slow the program loops

.end
