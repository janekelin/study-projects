//
//  main.c
//  E7M2CapitalLetters
//
//  Created by Iana Kalinichenko on A/17/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programmes counts capital letters in an input string
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    
    char line[102]; // input - line of string
    int i, capital = 0; // pointer to characters in line and counter for capital letters
  
    printf("\nPlease enter a string of up to 100 characters:\n");
    fgets(line, 102, stdin);
    
    for(
        i = 0;
        line[i] != '\0';
        ++i){
        if (line[i] >= 'A' && line[i] <= 'Z'){
            capital++; }
    }
    
    printf("\nThe amount of capital letter in your string is %d.\n", capital);
    return 0;
}
