//
//  main.c
//  M3E5TwoRandomNumbers
//
//  Created by Iana Kalinichenko on M/2/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This program has a function that incremenates by one two random numbers
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void increment_by_one (int *num1, int *num2);

int main() {
    
    int number1, number2;
    srand(time(NULL));
    
    number1 = rand();
    number2 = rand();
    
    printf("%i, %i\n", number1, number2);
    
    increment_by_one(&number1, &number2);
    
    printf("%i, %i", number1, number2);
    return 0;
}

void
increment_by_one(int *num1, int *num2)
{
    *num1 = *num1 + 1;
    *num2 = *num2 + 1;
}