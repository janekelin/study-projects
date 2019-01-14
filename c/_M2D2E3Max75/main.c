//
//  main.c
//  M2D2E3Max75
//
//  Created by Iana Kalinichenko on A/25/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This program counts the sum of uneven numbers until the sum is more than 75 and prints the seum and the last added number.
//

#include <stdio.h>
#include <stdlib.h>
/* Variation that I think is correct
int main() {
    
    int num, sum; // defines last added number for sum of numbers that is not bigger than 75
    
    num = 1;
    sum = num;
    
    while (sum <=75){
        num = num + 2;
        sum = sum + num;
    }
    
    sum = sum - num; // the sum after loop's end is bigger than 100, so we rewind one number back
    num = num - 2;
    printf("\nThe last added number was: %d\n", num);
    printf("\nThe sum of numbers is: %d\n", sum);
    return 0;
}
*/

//Impossible to get 18 and 90 as outputs!!!
int main() {
    int num = 1; // last added number in sum
    int sum = num;
    
    while (sum <75){
        num = num + 2;
        sum = sum + num;
    }
    
    printf("\nDet senast inlesta talet er: %d\n", num);
    printf("\nSumman av alla inlesta tal er: %d\n", sum);
    printf("Press ENTER to continue.");
    return 0;
}