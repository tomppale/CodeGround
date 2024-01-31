#include "project.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the head of the linked list
Meeting* head = NULL;
unsigned int numOfMeetings = 0;

// Function to add a new meeting
void add_meeting(char* description, int month, int day, int hour) {
    // Allocate memory for the new meeting
    Meeting* newMeeting = malloc(sizeof(Meeting));
    if (newMeeting == NULL) {
        printf("Error: Memory allocation failed.\n");
        return;
    }
    
    // Initialize the new meeting
    newMeeting->description = malloc(strlen(description) + 1);
    if (newMeeting->description == NULL) {
        printf("Error: Memory allocation failed.\n");
        free(newMeeting);
        return;
    }
    strcpy(newMeeting->description, description);
    newMeeting->day = day;
    newMeeting->month = month;
    newMeeting->hour = hour;
    
    // Insert the new meeting in sorted order
    Meeting* current = head;
    Meeting* previous = NULL;
    while (current != NULL) {
        if (current->month > month || (current->month == month && current->day > day) || (current->month == month && current->day == day && current->hour > hour)) {
            break;
        }
        previous = current;
        current = current->next;
    }
    
    if (previous == NULL) {
        newMeeting->next = head;
        head = newMeeting;
    } else {
        newMeeting->next = previous->next;
        previous->next = newMeeting;
    }
    
    numOfMeetings++;
    
}

// Function to print all the meetings
void print_meetings() {
    Meeting* current = head;
    while (current != NULL) {
        printf("%s %02d.%02d at %02d\n", current->description, current->day, current->month, current->hour);
        current = current->next;
    }
}

// Function to delete a meeting
void delete_meeting(int month, int day, int hour) {
    // Find the meeting to be deleted
    Meeting* current = head;
    Meeting* previous = NULL;
    while (current != NULL) {
        if (current->month == month && current->day == day && current->hour == hour) {
            break;
        }
        previous = current;
        current = current->next;
    }
    
    // If the meeting is not found, print an error message
    if (current == NULL) {
        printf("The time slot %02d.%02d at %02d is not in the calendar.\n", day, month, hour);
        return;
    }
    
    // Remove the meeting from the linked list
    if (previous == NULL) {
        head = current->next;
    } else {
        previous->next = current->next;
    }
    
    // Free memory
    free(current->description);
    free(current);
    numOfMeetings--;
    printf("SUCCESS\n");

}

// Function to free all the meetings
void free_meetings() {

    Meeting* current = head;
    Meeting* next;

    while (current != NULL) {
        next = current->next;
        free(current->description);
        free(current);
        current = next;
    }

    head = NULL;
    numOfMeetings = 0;
}


void write_meetings_to_file(char* filename, Meeting* head) {
    FILE* fp = fopen(filename, "w");
    if (fp == NULL) {
        printf("Error: could not open file %s for writing\n", filename);
        return;
    }

    Meeting* current = head;
    while (current != NULL) {
        fprintf(fp, "%s %02d.%02d at %02d\n", current->description, current->day, current->month, current->hour);
        current = current->next;
    }

    fclose(fp);
    printf("SUCCESS\n");
}

#define MAX_INPUT_LENGTH 1000

void load_from_file(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (!file) {
        printf("Cannot open file %s for reading.\n", filename);
        return;
    }

    // As we want to replace the current meeting, let's clear them first
    free_meetings();

    char line[MAX_INPUT_LENGTH];

    // Let's read the lines in a file and require that it has a correct format --> description, day, month, hour
    while (fgets(line, MAX_INPUT_LENGTH, file)) {
        char desc[100];
        int day, month, hour;
        if (sscanf(line, "%s %d.%d at %d", desc, &day, &month, &hour) == 4) {
            add_meeting(desc, month, day, hour);
        } else {
            // If the file is corrupted, print error message. Although load_from_file is used for files created by write_meetings_to_file
            printf("Error: Invalid format in file '%s'\n", filename);
            fclose(file);
            return;
        }
    }

    fclose(file);
    printf("SUCCESS\n");
}



int main(void) {
    // Global variable for linked list of meetings
    char input[MAX_INPUT_LENGTH];
    char filename[MAX_INPUT_LENGTH];
    char command;
    int month, day, time;
    char desc[MAX_INPUT_LENGTH];


    while (1) {
        // read input from user
        fgets(input, MAX_INPUT_LENGTH, stdin);
        sscanf(input, "%c", &command);

        // process user command
        switch (command) {

            case 'A': 
                if ((sscanf(input, "A %s %d %d %d", desc, &month, &day, &time)) != 4){
                    printf("A should be followed by exactly 4 arguments.\n");
                    break;
                }
                
                else if (day < 1 || day > 31){
                    printf("Day cannot be less than 1 or greater than 31.\n");
                    break;
                }
                else if (month < 1 || month > 12){
                    printf("Month cannot be less than 1 or greater than 12.\n");
                    break;
                }
                else if (time < 0 || time > 23){
                    printf("Hour cannot be negative or greater than 23.\n");
                    break;
                }
                add_meeting(desc, month, day, time);
                printf("SUCCESS\n");
                break;
                   
            
            case 'D': 
                // Check that right amount of inputs hold and they correspond to a structure: "D int int int"
                if ((sscanf(input, "D %d %d %d", &month, &day, &time)) != 3){
                    printf("Error: Invalid input in dates\n");
                    break;
                }
                
                else if (day < 1 || day > 31){
                    printf("Day cannot be less than 1 or greater than 31.\n");
                    break;
                }
                else if (month < 1 || month > 12){
                    printf("Month cannot be less than 1 or greater than 12.\n");
                    break;
                }
                else if (time < 0 || time > 23){
                    printf("Hour cannot be negative or greater than 23.\n");
                    break;
                }

                delete_meeting(month, day, time);
                // "SUCCESS" from function call
                break;
            
            case 'L': 
                // Checkif any meetings have been scheduled
                if (numOfMeetings == 0){
                    printf("No meetings have been scheduled\n");
                    break;
                }
                print_meetings();
                printf("SUCCESS\n");
                 break;


            case 'W':
                // Require that the name of a file is not empty
                if (sscanf(input, "W %s", filename) != 1) {
                    printf("Error: No file name given\n");
                    break;
                }
                else if (numOfMeetings == 0){
                    printf("No meetings to be saved in a file\n");
                    break;
                }
                else{
                write_meetings_to_file(filename, head);
                // "SUCCESS" from function call
                break;
                }
            

            case 'O': 
                // Require that the name of a file is not empty
                if (sscanf(input, "O %s", filename) != 1) {
                        printf("Error: No file name given\n");
                        break;
                }
                sscanf(input, "O %s", filename);
                load_from_file(filename);
                // "SUCCESS" from function call
                break;
            

            case 'Q':
                free_meetings();
                printf("SUCCESS\n");
                return 0;
            
                
            default: 
                // invalid command, print error message
                printf("Invalid command %c\n", input[0]);
        }
    }
}