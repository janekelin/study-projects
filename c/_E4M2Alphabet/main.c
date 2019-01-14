//
//  main.c
//  E4M2Alphabet
//
//  Created by Iana Kalinichenko on A/16/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  Programme prints alphabet in normal and rewind order
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    
    char letter; // defines alphabet letter
    
    printf("\nAlphabet order:\n");

    for (
        letter = 'A';
        letter <= 'Z';
        ++letter)
        printf("%c ", letter);
    
    printf("\n\nRewind alphabet order:\n");
    
    for (
         letter = 'Z';
         letter >= 'A';
         --letter)
        printf("%c ", letter);
    
    return 0;
}
