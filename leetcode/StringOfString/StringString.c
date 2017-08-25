/**
The strstr function locates a specified substring within a source string. 
Strstr returns a pointer to the first occurrence of the substring in the source. 
If the substring is not found, strstr returns a null pointer.

*/
#include <stdio.h>

char* StrStr(char *source, char *target) {
	if (!*target) 
		return source;
		
	char *p1 = (char*)source;
	while (*p1) {
		char *p1Begin = p1;
		char *p2 = (char*)target;
		while (*p1 && *p2 && *p1 == *p2) {
			p1++;
			p2++;
		}
		// finish looping the target string
		if (!*p2)
			return p1Begin;
		// otherwise p1 moves to the next char in the string
		p1 = p1Begin + 1;
	}
	return NULL;
}
/**
Because after looping more than N-M+1 times, the string has less than M characters to be matched with the substring. 
*/
char* StrStr_Improve(char *source, char *target) {
	if (!*target) 
		return source;
		
	char *p1 = (char*)source;
	char *p2 = (char*)target;
	char *p1Adv = (char*)source;
	
	while (*++p2)
		p1Adv++;
	// only need to iterate N-M+1 times
	// because 10-6+1 if only 5 characters left, no need to iterate any more.
	// get p1Adv first by loop the length M of target string
	// then N-M+1 times loop can be done by the loop as below
	// only loop for the difference in length of two string
	while (*p1Adv) { // only to control loop times
		char *p1Begin = p1;
		p2 = (char*)target;
		while (*p1 && *p2 && *p1 == *p2) {
			p1++;
			p2++;
		}
		if (!*p2)
			return p1Begin;
		
		p1 = p1Begin + 1;
		p1Adv++;
	}
	
	return NULL;
}


main() {
	char *source = "hereroheroero";
	char *pattern = "hero";

	printf(StrStr(source, pattern));
	printf("\n");
	printf(StrStr_Improve(source, pattern));
	printf("\n");
	return(0);
}
