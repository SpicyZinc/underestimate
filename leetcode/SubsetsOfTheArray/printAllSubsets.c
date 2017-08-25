#include<stdio.h>

int main(){
	int i, j, k, a[100], total;

	printf("Enter the total Number of an array (length):");
	scanf("%d", &total);

	for(i=0; i<total; i++){
		printf("Enter the %d Number : ", i+1);
		scanf("%d", &a[i]);
		printf("\n");
	}

	i=1<<total;
	while(i>0){
		printf("{");
		for(j=total-1, k=0; j>=0; j--){
			if(1<<j & i)
				printf("%d", a[k]);
			k++;
		}
		i--;
		printf("}\n");

	}

	return 0;
}
