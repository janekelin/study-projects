//  main.c
//  Lab1_test
//  Created by Iana Kalinichenko on M/10/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.

#include "Header.h"

int main()
{
    int userChoice;
    int menuCounter = 0;
    int randomMatrix[100], sortedMatrix[100]; // matrix of random and unique numbers, and matrix of sorted numbers
    int numberOfMatrixElements = 100;
    
    // define the starting point for the sequence of random numbers
    srand(time(NULL));
    
    // welcome user and explain what the program does
    print_instructions();
    
    // run the program
    do
    {
        userChoice = run_menu();
        switch (userChoice)
        {
            case 1: // 1.Generate New Matrix
                menuCounter = 1;
                printf("\n");
                generate_matrix(&randomMatrix, numberOfMatrixElements);
                print_matrix(&randomMatrix, numberOfMatrixElements);
                printf("\n");
                break;
            case 2: // 2.Sort Generated Matrix
                if(menuCounter != 1)
                {
                    printf("\nThis command cannot be performed, because there is no matrix of numbers to sort. You should generate a matrix first. Please try again and choose 1 from the menu.\n");
                }
                else
                {
                    menuCounter = 2;
                    printf("\n");
                    sort_matrix(&randomMatrix, numberOfMatrixElements, &sortedMatrix, numberOfMatrixElements);
                    print_matrix(&sortedMatrix, numberOfMatrixElements);
                    printf("\n");
                }
                break;
            case 3: // 3.Calculate Values In Sorted Matrix
                if(menuCounter != 2)
                {
                    printf("\nThis command cannot be performed, because the matrix either is not sorted or is not generated at all. You should sort the generated matrix first. Please try again and choose 2 from the menu if you have already generated a matrix or choose 1 and then 2 if you have not generated a matrix yet.\n");
                }
                else
                {
                    printf("\n");
                    show_values(&sortedMatrix, numberOfMatrixElements);
                    printf("\n");
                }
                break;
            case 4: // 4.Search In Sorted Matrix
                if(menuCounter != 2)
                {
                    printf("\nThis command cannot be performed, because the matrix either is not sorted or is not generated at all. You should sort the generated matrix first. Please try again and choose 2 from the menu if you have already generated a matrix or choose 1 and then 2 if you have not generated a matrix yet.\n");
                }
                else
                {
                    printf("\n");
                    search_in_matrix(&sortedMatrix, numberOfMatrixElements);
                    printf("\n");
                }
                break;
            case 5: // 5.Calculate Sums in Generated Matrix
                if(menuCounter == 0)
                {
                    printf("\nThis command cannot be performed, because there is no matrix of numbers to perform calculations on. You should generate a matrix first. Please try again and choose 1 from the menu.\n");
                }
                else
                {
                    printf("\n");
                    sums(&randomMatrix, numberOfMatrixElements);
                    printf("\n");
                }
                break;
            case 6: // 6.Help
                printf("\n");
                show_help();
                printf("\n");
                break;
            case 7: // 7.Exit
                end_program();
                break;
            default: // Error - no valid menu option is chosen
                error_message();
                break;
        } //end switch
    } while (userChoice != 7); // end of do-while loop
    
    return 0;
} // end of main function

