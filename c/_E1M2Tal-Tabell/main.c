/*
//
//  main.c
//  E1M2Tal-Tabell
//
//  Created by Iana Kalinichenko on A/16/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.
//
//  This programmes produces a table of
//  number number*number number*number*number
//  with the highest value of "number" provided as an input
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    int num, num2, num3; //defines columns of the table: initial number, square, and cube
    int num_max; // input: the highest value of "number"
    printf("\nThis programme will show you the table of numbers with their square numbers and cubes. Please provide the highest number after 1 that should be calculated (note that if you provide a number lower than 1, the programme would not calculate anything):\n");
    scanf("%i", &num_max);
    printf("\ntal   tal*tal   tal*tal*tal\n");
    printf("---   -------   ------------\n");
    
    //starts with 1 and continues unless is equal to input highest value of "number"
    for (num = 1;
         num <= num_max;
         ++num){
        num2 = num * num;
        num3 = num * num * num;
        printf("%i        %i          %i\n", num, num2, num3);
    }
    return 0;
}
 */

//
//  main.c
//  ISGA04Utskick4
//
//  Created by Johan Öfverberg on 2015-04-30.
//  Copyright (c) 2015 Johan. All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>
typedef struct postTyp
{
    char namn[20];
    int alder;
}postTyp;
FILE *openfil(FILE *fp, char namn[]);
int main(int argc, const char * argv[]) {
    FILE *fp=NULL;
    char filnamn[] = "reg.dat";
    postTyp post;
    fp = openfil(fp, filnamn);
    fseek(fp, 0, SEEK_END);
    printf("Ange namn: ");
    gets(post.namn);
    printf("Ange ålder: ");
    scanf("%i",&post.alder);
    fwrite(&post, sizeof(postTyp), 1, fp);
    fseek(fp, 0, SEEK_SET);
    while (fread(&post, sizeof(postTyp), 1, fp) != 0)
        printf("%s %i\n", post.namn, post.alder);
    fclose(fp);
    return 0; }
FILE *openfil(FILE *fp, char namn[])
{
    if ((fp = fopen(namn, "r+b")) == NULL)
        if ((fp = fopen(namn, "w+b")) == NULL)
        {
            printf("fel\n");
            exit(0); }
    return fp; }
