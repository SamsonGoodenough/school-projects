# Huffman Encoder / Decoder
Huffman project uses the [Huffman coding] methods to compress text files.  

#### Building for source
Build example using gcc:
```sh
$ gcc *.c -o huffman -lm
```

### Usage
```sh
USAGE: ./huffman [encode | decode <source file>] <input file> <output file>
```
```sh
$ ./huffman encode example.txt encoded.dat
$ ./huffman decode example.txt encoded.dat decoded.txt 
```

### Credit

| Author | File(s) |
| ------ | ------ |
| Brian Ha | initialize.h |
| Samson Goodenough | huffman.c, encode.h |
| Eric Wildfong | decode.h |
| Alex Lau | huffman.c, freeTree.h |

[//]:#
   [Huffman coding]: <https://en.wikipedia.org/wiki/Huffman_coding>