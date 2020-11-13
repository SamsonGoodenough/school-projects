/*
 -------------------------------------------------------
 int_int.h
 Arbitrary integer int for ADT testing.
 -------------------------------------------------------
 Author:       David Brown
 ID:           987654321
 Email:        dbrown@wlu.ca
 Version:      2019-06-22
 -------------------------------------------------------
 */
#ifndef INT_DATA_H_
#define INT_DATA_H_

/**
 * Destroys dynamically allocated int. source is freed and
 * set to NULL.
 * @param source pointer to source int
 */
void int_destroy(int **source);

/**
 * Returns a string version of int.
 * @param string pointer to a string to contain int
 * @param string_size maximum size of string
 * @param source pointer to source int
 * @return pointer to string
 */
char *int_to_string(      char *string,
		                  int  string_size,
					const int  *source);

/**
 * Compares two int items. Returns:
 *     -1 if source is less than target
 *     1 if source is greater than target
 *     0 if source equals target
 * @param target int to compare to
 * @param source int to be compared
 * @return result of comparison
 */
int int_compare(const int *target,
		        const int *source);

/**
 * Copies contents of source to target. target is initialized by copy.
 * @param source pointer to source of copy
 * @return pointer to target
 */
int *int_copy(const int *source);

#endif /* INT_DATA_H_ */
