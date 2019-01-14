//
//  main.c
//  M3E4RandomNumber5to25
//
//  Created by Iana Kalinichenko on M/2/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme has one function and provides a random number between 5 and 25.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int random_number(void);

int main() {
    
    int number = random_number();
    printf("%i", number);
    return 0;
}

int
random_number()
{
    srand(time(NULL));
    return (rand() % 20 +6);
}