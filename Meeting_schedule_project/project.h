#ifndef _PROJECT__H_
#define _PROJECT__H_

// Define the struct for a meeting
typedef struct Meeting {
    char* description;
    int month;
    int day;
    int hour;
    struct Meeting* next;
} Meeting;

void add_meeting(char* description, int month, int day, int hour);

void print_meetings();

void delete_meeting(int month, int day, int hour);

void free_meetings();

void write_meetings_to_file(char* filename, Meeting* head);

void load_from_file(const char* filename);

int main(void);

#endif //! _PROJECT__H_