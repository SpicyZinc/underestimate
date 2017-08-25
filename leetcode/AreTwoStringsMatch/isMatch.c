#include<stdio.h>
#include <assert.h>

#define TRUE 1
#define FALSE 0

typedef int boolean;

	boolean isMatch(const char *s, const char *p){
/**
The argument expression can be anything you want to test--a variable or any C expression. 
If expression evaluates to TRUE, assert() does nothing. 
If expression evaluates to FALSE, assert() displays an error message on stderr and aborts program execution.

*/
		assert(s && p);
		if (*p == '\0') 
			return *s == '\0';

		// next char is not '*': must match current character
		if (*(p+1) != '*'){
			assert(*p != '*');
			return ((*p == *s) || (*p == '.' && *s != '\0')) && isMatch(s+1, p+1);
		}
		// next char is '*'
		while ((*p == *s) || (*p == '.' && *s != '\0')){
			if (isMatch(s, p+2)) 
				return TRUE;
			s++;
		}
		return isMatch(s, p+2);
	}

	int main(){
		char s[] = "abcbcd";
		char p[] = "a.*c.*d";
		printf("Are two strings match? %d", isMatch(s, p));
	}
/**
Algorithms go hand in hand with data structures¡ªschemes for organizing data
/