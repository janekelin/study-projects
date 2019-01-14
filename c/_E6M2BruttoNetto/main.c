//
//  main.c
//  E6M2BruttoNetto
//
//  Created by Iana Kalinichenko on A/16/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//  Programme calculates netto income for brutto income
//

#include <stdio.h>
#include <stdlib.h>
#define SKATT_LOW 0.3 // skattesatsen laag foer brutto <=204000
#define SKATT_HIGH 0.5 // skattesatsen hoeg foer brutto >204000
#define BRUTTO_HIGH 204000 // troeskelvaerde

int main() {
    
    double brutto, netto; // defines brutto income as input, and netto income as output
    
        
        printf("\nPlease print the brutto income and click <enter>.\n");
        scanf("%lf", &brutto);
    
            if(brutto <= BRUTTO_HIGH){
                netto = brutto - (brutto * SKATT_LOW);
            } else {
                netto = brutto - (BRUTTO_HIGH * SKATT_LOW + (brutto - BRUTTO_HIGH) * SKATT_HIGH);
            }

    printf("\nThe netto income is: %.2lf\n", netto);
        
    
    
    return 0;
}