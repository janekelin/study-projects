//  Header.h
//  Lab1_test
//  Created by Iana Kalinichenko on M/10/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.

#ifndef Lab1_test_Header_h
#define Lab1_test_Header_h

// list of included libraries
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// list of included functions
void print_instructions(void);
int run_menu(void);
void generate_matrix(int *array, int num); // where to store generated matrix and what is the maximum number of elements in it
void print_matrix(int *array, int num); // which matrix to print and what is the maximum number of elements in it
void sort_matrix(int *array1, int num1, int *array2, int num2); // which matrix to use for sorting and what is the maximum number of elements in it; where to store sorted matrix and what is the maximum number of elements in it
void show_values(int *array, int num); // from which matrix numbers should be calculated and what is the maximum number of elements in it
void search_in_matrix(int *array, int num); // which matrix should be searched through and what is the maximum number of elements in it
void sums (int *array, int num); // which matrix to perform calculations on and what is the maximum number of elements in it
void show_help(void);
void end_program(void);
void error_message(void);



#endif
