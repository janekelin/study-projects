//
//  main.c
//  M2D2E6DividedBy4
//
//  Created by Iana Kalinichenko on A/25/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  This programme checks if the number can be divided by four.
//

int main() {
    
    int num, rem; // defines input number with initial value of 1, and a remainder after division by 4
    
    do {
        
        printf("\nSkriv ett heltal. 0 for att avsluta: ");
        scanf("%i", &num);
        
        if (num != 0){
            rem = num % 4;
            if(rem == 0){
                printf("Talet er jemnt delbart med 4\n");
            } else
                printf("Talet er inte jemnt delbart med 4. Resten er: %d\n", rem);
        }
        
    }
    
    while (num != 0);
    
    
    return 0;
}

