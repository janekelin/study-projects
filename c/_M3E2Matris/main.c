//
//  main.c
//  M3E1Lotto
//
//  Created by Iana Kalinichenko on M/2/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This program prints a matris of random numbers between 0 and 99.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main() {
    
    int matrix [100], i, c, number;
    const int MAX = 99;
    srand(time(NULL));
    int used; // 1 for true meaning the number was used, 0 for false

    matrix[0] = rand() % (MAX + 1);
    
    for (i = 1; i < 100; i++){
        do {
            number = rand() % (MAX + 1);
            used = 0;
            for (c = 0; c < i; c++){
                if (number == matrix[c]) {
                    used = 1;
                }
            }
        } while (used == 1);
        matrix[i] = number;
    }
    

    
    for (i = 0; i != 100; i+=10){
        printf("%2i\t%2i\t%2i\t%2i\t%2i\t%2i\t%2i\t%2i\t%2i\t%2i\t\n", matrix[i], matrix[i+1], matrix[i+2], matrix[i+3], matrix[i+4], matrix[i+5], matrix[i+6], matrix[i+7], matrix[i+8], matrix[i+9]);
    
    }
    
    return 0;
}
