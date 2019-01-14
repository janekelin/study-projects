//
//  main.c
//  E2M2SumSquareSum
//
//  Created by Iana Kalinichenko on A/16/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme provide the sum and the square sum of input numbers.
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    
    double num, sum = 0, sum2 = 0; // defines input number, output sum of numbers and square sum of numbers
    
    printf("\nThis programme will calculate the sum and the square sum of provided numbers. To stop adding more numbers, print '0'. \n");
    
    do{
        printf("\nPrint the next number you want to add and click <enter>.\n");
        scanf("%lf", &num);
        if (num != 0) {
            sum = sum + num;
            sum2 = sum2 + (num * num);
        }
    }
    while (num != 0);
    printf("\nThe sum and the square sum of provided numbers are the following numbers - \n");
    printf("\nThe sum: %.2f     The square sum: %.2f\n", sum, sum2);
    return 0;
}
