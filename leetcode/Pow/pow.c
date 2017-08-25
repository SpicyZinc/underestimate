/**
Divide and Conquer C program and Recursion
Better than pure recursion, saving running time.

*/

#include <stdio.h>
int main(int argc, char*argv[]){
	printf("\n%s == [%d]\n", " pow(5, 4)",  ipow(5, 4));
	printf("\n%s == [%d]\n", " pow(3, 5)",  ipow(3, 5));
}

int ipow(int x, int n){
	if(n==0)
		return 1;
	else if(n%2 == 0){
			return (ipow(x, n/2) * ipow(x, (n/2)));
		}
		else{
			return (x * ipow(x, n/2) * ipow(x, (n/2)));
		}
}
