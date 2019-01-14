//  Menufunctions.c
//  Lab1_test
//  Created by Iana Kalinichenko on M/10/15.
//  Copyright (c) 2015 Iana Kalinichenko. All rights reserved.

// 0. Shows welcoming instructions on the screen
void print_instructions(void)
{
    printf("\nWelcome to fMatrix 1.0!\n");
    printf("In this program you can generate a matrix of 100 random unique numbers between (and including) 100 and 900.\n");
    printf("Afterwards you can sort those generated numbers from the smallest one to the biggest one or find out the sum of numbers in each row, each column, and total sum of all the numbers in the matrix.\n");
    printf("After the matrix is sorted, you can find out which number is the smallest and the biggest in the matrix as well as what are the median number and the mean number of the matrix.\n");
    printf("You can also search for a specific number in the sorted matrix.\n");
    printf("If you need any detailed information, please choose 'Help' from the menu.\n");
    printf("If you would like to exit the program, please choose 'Exit' from the menu.\n");
    printf("In order to choose from the menu, type in the number of the menu option (available numbers are 1, 2, 3, 4, 5, 6, 7; for example, you type 1 for generating a new matrix) and click 'ENTER' on your keyboard.\n");
    printf("\nWe wish you a positive experience while using fMatrix 1.0!\n");
}

//  1. Shows menu and lets user to choose from it
int run_menu(void)
{
    int menuChoice;
    
    printf("\nMenu:\n");
    printf("1.Generate New Matrix\n");
    printf("2.Sort Generated Matrix\n");
    printf("3.Calculate Values In Sorted Matrix\n");
    printf("4.Search In Sorted Matrix\n");
    printf("5.Calculate Sums in Generated Matrix\n");
    printf("6.Help\n");
    printf("7.Exit\n");
    printf("\nPlease choose from the menu above:\n");
    scanf("%i", &menuChoice);

    return(menuChoice);
}



// 2. Generates a matrix of 100 random unique number from (and including) 100 to (and including) 900
void generate_matrix(int *array, int num)
{

// first a local matrix is generated, then it is copied as a matrix of random numbers in the main function
    
// i - counter of places in the local matrix; c - counter of already taken places in the local matrix; number - randomly generated number
 int matrix [num], i, c, number;
 const int MAX = 801;
 int used; // 1 when the number was used, 0 when the number was not used
 
// generate local matrix
 matrix[0] = rand() % MAX + 100; // first number is generated separately, since there are no other numbers in the matrix to compare it to
 for (i = 1; i < 100; i++)
 {
     do
     {
         number = rand() % MAX + 100;
         used = 0; // we assume that generated number is unique, otherwise we risk to have infinite loop
         for (c = 0; c < i; c++)
         {
             if (number == matrix[c])
             {
                 used = 1;
             }
         }
     } while (used == 1);
     matrix[i] = number;
 }


 // copy local matrix as a generated matrix in the main function
    for (i = 0; i < num; i++)
    {
        *array = matrix[i];
        array++;
    }
} // end of function

// 3. Prints a matrix in 10 rows and 10 columns
void print_matrix(int *array, int num)
{
    int i;
    for (i = 0; i < num; i++)
    {
        printf("%2i\t", *array);
        array++;
        if(((i+1)%10)==0) //break to the new row after each 10th number
        {
            printf("\n");;
        }
    }
}

// 4. Sorts a matrix
void sort_matrix(int *array1, int num1, int *array2, int num2)
{
    // this functions first copies the generated matrix of random numbers from the main function to the local matrix, then sorts the local matrix, and in the ends copies local matrix to the sorted matrix in the main function
    
    // i - counter of places in the local matrix; c - counter of compared places in the local matrix; temp - temporary stored number
    int matrix[num1], i, c, temp;
    
    // copy the generated matrix to the local matrix
    for (i = 0; i < num1; i++)
    {
        matrix[i] = *array1;
        array1++;
    }
    
    // sort local matrix using bubble sort
    for (i = 0; i < num1; i++)
    {
        for (c = (num1 - 1); c > i; c--)
        {
            if (matrix[c-1] > matrix[c])
            {
                temp = matrix[c-1];
                matrix[c-1] = matrix[c];
                matrix[c] = temp;
            }
        }
    }
    
    // copy local matrix to the sorted matrix in the main function
    for (i = 0; i < num2; i++)
    {
        *array2 = matrix[i];
        array2++;
    }

} // end of function

// 5. Shows the value of the smallest number in the matrix, the biggest number in the matrix, the mean value of numbers in the matrix, and the median in the matrix
void show_values(int *array, int num)
{
    int matrix[num], i;
    int small, big;
    double mean, median;
    
    // copy the sorted matrix from the main function to the local matrix
    for (i = 0; i < num; i++)
    {
        matrix[i] = *array;
        array++;
    }
    
    // identify the smallest number, the biggest number
    small = matrix[0];
    big = matrix[num-1];
    
    // calculate median
    if ((num % 2) == 0) // if number of elements in matrix is even two numbers in the middle should be taken into consideration
    {
        median = ((double)(matrix[num/2] + matrix[(num/2)-1])) / 2; // since median is a float, the sum of numbers in the middle has to be converted to float as well before dividing, otherwise integer division occur
    }
    else
    {
        median = matrix[num/2]; // if number of elements in matrix is odd the number in the middle is the median
    }
    
    // calculate the mean number
    mean = small;
    for(i = 1; i < num; i++)
    {
        mean = mean + matrix[i];
    }
    mean = mean / num;
    
    // print the values
    printf("\nThe smallest number in the matrix is:\t%6i\n", small);
    printf("\nThe biggest number in the matrix is:\t%6i\n", big);
    printf("\nThe median number of the matrix is:\t\t%.2f\n", median);
    printf("\nThe mean number of the matrix is:\t\t%.2f\n", mean);
    
} // end of function

// 6. Performs binary search

void search_in_matrix(int *array, int num)
{
    int matrix[num], i;
    int start = 0, end = num, middle, searchItem;
    int row, column; // place of the item if it is found in the matrix
    
    // copy the sorted matrix from the main function to the local matrix
    for (i = 0; i < num; i++)
    {
        matrix[i] = *array;
        array++;
    }
    
    printf("\nEnter the number you would like to search for:\n");
    scanf("%i", &searchItem);
    
    // binary search
    while ((end - start) > 1)
    {
        middle = (start + end) / 2;
        if(searchItem >= matrix[middle])
        {
            start = middle;
        }
        else
        {
            end = middle;
        }
    }
    if (searchItem == matrix[start])
    {
        if (start < 10)
        {
            row = 1;
            column = start + 1;
        }
        else
        {
            row = (start / 10) + 1;
            column = (start % 10) + 1;
        }
        printf("\nNumber %i has been found in the matrix. Its position is:\n", searchItem);
        printf("\nRow:\t%2i\nColumn:\t%2i\n", row, column);
    }
    else
    {
        printf("\nThere is no such number in the matrix. Please try again or choose another option from the menu.\n");
    }
} // end of function

// 7. Calculates sums of numbers in each row, each column and a total sum
void sums (int *array, int num)
{
    int matrix[num], i, c;
    int rowSum = 0, columnSum = 0, totalSum = 0;
    int numColumn = 10; // signifies that we have 10 columns in the matrix
    
    // copy the generated matrix from the main function to the local matrix
    for (i = 0; i < num; i++)
    {
        matrix[i] = *array;
        array++;
    }
    
    // print explanation
    printf("\nThe sums of numbers for each row and for each column as well as the sum of all the numbers is calculated for the generated matrix of random numbers. \nOn the screen you see a matrix with 11 rows and 11 columns:\nlast column shows the sum of numbers in the respective row;\nlast row shows the sum of numbers in the respective column;\nthe last number in 11th row and 11th column shows the sum of all the numbers in 10x10 matrix.\n\n");
    
    // sums of rows are calculated and printed simultaneously while matrix is printed
    for (i = 0; i < num; i++)
    {
        printf("%5i\t", matrix[i]);
        rowSum = rowSum + matrix[i];
        if(((i+1)%10)==0) //break to the new row after each 10th number
        {
            printf("%5i\t", rowSum);
            rowSum = 0;
            printf("\n");
        }
    }
    
    // sums of columns are calculated and printed afterwards together with total sum which is calculated as sum of sums of all columns
    for (c = 0; c < numColumn; c++)
    {
        for (i = c; i < num; i += 10)
        {
            columnSum = columnSum + matrix[i];
        }
        printf("%5i\t", columnSum);
        totalSum = totalSum + columnSum;
        columnSum = 0;
    }
    printf("%5i\t", totalSum);
    
} // end of function

// 8. Shows Help
void show_help(void)
{
    printf("\n\tHelp\t\n");
    printf("\t====\t\n\n");
    printf("\nWelcome to fMatrix 1.0 Help!\n");
    printf("\nCredits – fMatrix has been written by Iana Kalinichenko in May 2015 as a part of ISGA04 course at the Karlstad University (Sweden).\n\n");
    printf("\nMatrix with Sorted Numbers – after a matrix of random unique numbers is generated, you can sort numbers in the matrix from the smallest one to the biggest one. Choose menu option 2 for it. This function uses bubble sort algorithm.\n\n");
    printf("\nMean – is the average value of given numbers. It is calculated as the sum of all the numbers divided by their amount.\n\n");
    printf("\nMedian - is the number in the ‘middle’ of the list of numbers. In order to find a median, one has to have a list of numbers in numerical order, therefore it is important that you choose menu option 2 (sort matrix) before choosing menu option 3 (calculate median). For list of numbers with even amount of elements median is calculated as a sum of two ‘middle’ numbers divided by two.\n\n");
    printf("\nMenu options – it is possible to choose from the menu options only by typing in numbers from (and including) 1 to (and including) 7. If you type in any other number, you will get an error message and will be able to make a choice again. If you type in anything else than a number, you will get an error message and the program will loop. You would have to force quit the program and start it again if you wish to continue working with it. You would loose all your previous progress. Note that some menu options cannot be chosen from the beginning. If you wish to work with a new matrix, you have to generate it first by choosing menu option 1. You cannot sort numbers in a matrix (menu option 2) or calculate sums in a matrix (menu option 5) if you have not generated a matrix first (menu option 1). Menu options 3 and 4 are not available until you sort the numbers in the matrix (menu option 2).\n");
    printf("\nNew Matrix – each new matrix is an array of 100 random unique numbers between (and including) 100 and 900. It is printed on the screen in 10 rows and 10 columns. Choose menu option 1 in order to generate a new matrix.\n\n");
    printf("\nSearch – you can search for a specific number in the matrix by choosing menu option 4. This program uses binary search, which requires a list of searchable numbers in numerical order so that it can function properly. Therefore it is important that you choose menu option 2 (sort matrix) before choosing menu option 4 (search in matrix). If the search number is in the matrix, you will get information about its position in rows and columns of the matrix. Otherwise, you will get a message that the number was not found in the matrix. If you would try to search for anything else than a number, you will get an error message and the program will loop. You would have to force quit the program and start it again if you wish to continue working with it. You would loose all your previous progress.\n\n");
    printf("\nVersion Of The Program - 1.0\n\n");
    printf("\nIf you have any question or comments, you are welcome to contact program support through janekelin@gmail.com\n\n");
    printf("\n=========================================================================================\n\n");
}

// 9. Shows message on the exit of the program
void end_program(void)
{
    printf("\nThank you for using fMatrix! We hope you had a positive experience.\n");
}

// 10. Shows error message
void error_message(void)
{
    printf("\nThis command cannot be recognized. Please try again and choose a desired option from the menu by typing in the corresponding number between (and including) 1 and 7.\n");
}
