#include <stdio.h>

void printSubsets(){
	int source[3] = {1, 2, 3};
	int currentSubset = 7;
	int tmp;
	int i;
		while(currentSubset){
			printf("{");
			tmp = currentSubset;
			for(i = 0; i<3; i++){ // three positions to choose from
				if (tmp & 1)
					printf("%d ", source[i]);
				tmp >>= 1;
			}
			printf("}\n");
			currentSubset--;
		}
}

int main(int argc, char*argv[]){
	printSubsets();
}
