---
title: CP386 | Chapter 10 | Virtual Memory
created: '2022-03-31T17:23:40.948Z'
modified: '2022-03-31T17:24:23.684Z'
---

# CP386 | Chapter 10 | Virtual Memory
## Stack alogrithm
If the last snapshot is 5,4,3, when when the size increases, 5,4,3 must be present

## LRU Approimation Algorithms
- LRU needs special hardware and still slow
- Many systems provide some help, however, in the form of reference bit
  - ref bit = 0 -> replace it
  - ref bit = 1 -> (second chance) 
    - leave page in memory
    - arrival time reset to current time
    - set ref bit = 0

## Additional Reference Bits Algorithm
We can gain additional ordering info by recording the ref bits at regular intervals
- An 8-bit byte for each page in a table in memory
- When reference bit is set to 1, once that regular time interval finishes, a subroutine is run moving it to the most significant bit in binary
  - On following runs, all bits move down to make space
- The lowest value of the binary represents that which is the Least Recently Used

## Enhanced Sencond Change Algorithm
Improve algo by using the ref but and modify bit (if available) in concert
- Take ordered pair (ref, mod)
  - (0, 0) neither recently used nor modified - best page to replace
  - (0, 1) not recently used but modified - not quite as good, must write out before replacement
  - (1, 0) recently used but clean - probably will be used again soon
  - (1, 1) recently used and modified - probably will be used again soon and need to write out before replacement
- When page replacement is called for, use the clock scheme but use the four classes replace the page in the lowest priorety

## Counting Algorithms
Keep a counter of the number of references that have been made to each page
- (not common)
- *Least Frequently Used (LFU)*
  - replaces page with the smallest count
- *Most Frequently Used (MFU)*
  - based on the argument that the page with the smallest count was probably just brought in and has yet to be used

## Fixed Allocation
Equal Allocation
- ex. if there are 100 frames (after allocating frames for the OS) and 5 processes, give each process 20 frames
  - *keep some as free frame buffer pool*

Propotional allocation
- Allocate according to the size of process
  - Dynamic as degree of multiprogramming, process sizes change

## Global vs Local Allocation
Global replacement
- process selects a replacement frame from the set of all frames; one process can take a frame from another
  - but then process excecution time can vary greatly
  - but greater throughput so more common
Local replacement
- each process selects from only its set of allocated frames
  - more consistent per-process preformance
  - but possibly underutilized memory

