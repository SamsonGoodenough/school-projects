/*
-----------------------------------------
 Author:             Alex Lau
 ID:                 190786790
 Email:              laux6790@mylaurier.ca
 Version             2020-11-24
 -----------------------------------------
*/

#include <stdio.h>
#include <stdlib.h>

/*
----------------------------------------------------------
Frees the whole huffman tree by going through the whole tree recursively and freeing the roots
Use: free_memory(root)
----------------------------------------------------------
Parameters:
  letterNode *root    - tree used to free the roots
Returns:
  None
----------------------------------------------------------
*/

void free_memory(letterNode *root) {
    if (root == NULL) return; //Do nothing if passed a non-existent root

    //Recursively go through the tree
    free_memory(root->left);
    free_memory(root->right);
    free(root); //Free the root
}
