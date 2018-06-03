#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//gcc generateMatrix.c -lgomp -fopenmp -O2 -o generateMatrix
//./generateMatrix NAME X Y

int main(int argc, char* argv[]) {

	if(argc < 4) {
		perror("Too few agruments");
		return 1;
	}

	char *filename = argv[1];
	int m = atoi(argv[2]);
	int n = atoi(argv[3]);

	int i, j;

	FILE *f = fopen(filename, "w");

	if(f == NULL) {
		perror("Error opening file\n");
	}

	srand( time(NULL) );

	for(i=1; i<=m; i++) {
		for(j=1; j<=n; j++) {
			fprintf(f, "%d ", rand() % 100);
			if(j == n)	fprintf(f, "\n\r");
		}
	}

	fclose(f);

	return 0;
}
