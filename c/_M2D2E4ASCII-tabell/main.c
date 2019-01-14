//
//  main.c
//  M2D2E4ASCII-tabell
//
//  Created by Iana Kalinichenko on A/25/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme provides a tabell of characters and their ASCII-numbers
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    
    char letter; // defines alphabet letter
    int number; // defines the ASCII-number for respective letters
    
    for (letter = 'A'; letter <= 'Z'; ++letter)
        printf("  %c  ", letter);
    
    printf("\n");
    
    for (
         number = 'A';
         number <= 'Z';
         ++number)
        printf("%3d  ", number);
    
    printf("\n");
    
    for (
         letter = 'a';
         letter <= 'z';
         ++letter)
        printf("  %c  ", letter);
    
    printf("\n");
    
    for (
         number = 'a';
         number <= 'z';
         ++number)
        printf("%3d  ", number);
    
    printf("\n");
    
    return 0;
}

