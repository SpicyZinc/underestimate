/**
idea:
what is difference between '\0' and ' '
' ' is space
'\0' is a string end sign
another idea different from Java version
from left to right to walk the string
meeting a space, update count to be 1

this problem is just space and char, play with space and char

whenever meeting a char which is not space, it is time for count to be 1
"   Hello World"

*/
#include <stdio.h>

int LengthOfLastWord(const char *s) {
    if(*s == '\0')
		return 0;

    int count = *s == ' ' ? 0 : 1;
    while(*(++s) != '\0') {
        if (*s != ' ') {
            if (*(s-1) == ' ')
                count = 1;
            else
                ++count;
		}
    }

    return count;
}

int main(int argc, char** argv) {
    char a[] = "hello world";
    char b[] = "How are you ";

    printf("%d\n", LengthOfLastWord(a));
    printf("%d\n", LengthOfLastWord(b));
    
    return 0;
}