//
//  main.c
//  M3E3FunctionEevenOdd
//
//  Created by Iana Kalinichenko on M/2/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme contains 3 functions and identifies if a number is even or odd.
//

#include <stdio.h>
#include <stdlib.h>

int even_or_odd(int num);
void print_even(void);
void print_odd(void);

int main() {
    
    int number;
    
    printf("Type a number:\n");
    scanf("%i", &number);
    even_or_odd(number);
    if (even_or_odd(number) == 0){
        print_even();
    }
    else print_odd();
    
    
    return 0;
}


int
even_or_odd(int num)
{

    if (!(num%2)){
        return 0;
    }
    else return 1;
}


void
print_even(void)
{
    printf("\nThe number is even.\n");
}

void
print_odd(void)
{
    printf("\nThe number is odd.\n");
}