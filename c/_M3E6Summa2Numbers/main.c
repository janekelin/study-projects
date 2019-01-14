//
//  main.c
//  M3E6Summa2Numbers
//
//  Created by Iana Kalinichenko on M/2/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme has 3 functions and adds up 2 numbers.
//

#include <stdio.h>
#include <stdlib.h>

int lasEtt(void);
int lasTva(void);
void resultat(int num1, int num2);

int main() {
    
    int number1, number2;
    number1 = lasEtt();
    number2 = lasTva();
    resultat(number1, number2);
    
    return 0;
}

int
lasEtt(void)
{
    int n1;
    printf("Type in the first number:\n");
    scanf("%i", &n1);
    return(n1);
}

int
lasTva(void)
{
    int n2;
    printf("Type in the second number:\n");
    scanf("%i", &n2);
    return(n2);
}

void
resultat(int num1, int num2)
{
    printf("\nThe sum is: %i.\n", num1 + num2);
}