/**
idea:
this idea should be remembered 
because it is using char as index of an array of 256

*/

#include <stdio.h>

int check_anagram(char [], char []);
 
int main() {
	char a[100], b[100];
	int flag;

	printf("Enter first string\n");
	gets(a);
	printf("Enter second string\n");
	gets(b);

	flag = check_anagram(a, b);

	if(flag == 1)
		printf("\"%s\" and \"%s\" are anagrams.\n", a, b);
	else
		printf("\"%s\" and \"%s\" are not anagrams.\n", a, b);

	return 0;
}
/**
idea:

first - a, record each letter appearing times
second - b, record each letter appearing times
only appearing the same times for each letter of 26 letter, 
they are anagram

*/ 
int check_anagram(char a[], char b[]) {

	int first[26] = {0};
	int second[26] = {0};
	
	int c = 0;
	while(a[c] != '\0') {
		first[a[c] - 'a']++;
		c++;
	}
	c = 0;
	while(b[c] != '\0') {
		second[b[c] - 'a']++;
		c++;
	}
	for(c = 0; c < 26; c++) {
		if(first[c] != second[c])
			return 0;
	}
	return 1;
}
