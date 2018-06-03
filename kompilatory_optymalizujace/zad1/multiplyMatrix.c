#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>

void readSize(FILE*, int*);
void loadMatrix(char*, int**, int*);
void readMatrix(FILE*, int**);


//gcc multiplyMatrix.c -lgomp -fopenmp -O2 -o multiplyMatrix
//./multiplyMatrix TAB_1.txt TAB_2.txt OUTPUT

int main(int argc, char* argv[]) {

	if(argc < 3) {
		perror("Too few agruments");
		return 1;
	}

	clock_t readStart = clock();

	int **matrixA, **matrixB, **matrixC;
	int *sizeA, *sizeB, *sizeC;

	int i,j;

	sizeA = (int*)malloc(2 * sizeof(int));
	sizeB = (int*)malloc(2 * sizeof(int));
	sizeC = (int*)malloc(2 * sizeof(int));


	// -----------------------Matrix A--------------------
	FILE *f = fopen(argv[1], "r");

	readSize(f, sizeA);
	rewind(f);


	// memory allocation
	matrixA = (int**)malloc(sizeA[0] * sizeof(int*));
	for(i=0; i < sizeA[0]; i++) {
		matrixA[i] = (int)malloc(sizeA[1] * sizeof(int*));
	}

	loadMatrix( f, matrixA, sizeA );
	fclose(f);
	// ---------------------------------------------------


	// -----------------------Matrix B--------------------
	FILE *f2 = fopen(argv[2], "r");

	readSize(f2, sizeB);
	rewind(f);

	// memory allocation
	matrixB = (int**)malloc(sizeB[0] * sizeof(int*));
	for(i=0; i < sizeB[0]; i++) {
		matrixB[i] = (int)malloc(sizeB[1] * sizeof(int*));
	}

	loadMatrix( f2, matrixB, sizeB );
	fclose(f2);


	clock_t readStop = clock();
	float readTime = (float)(readStop - readStart)/CLOCKS_PER_SEC;

	// -----------------------Matrix C--------------------

	
	clock_t start = clock();

	int n;
	int k;
	
	sizeC[0] = sizeA[0];
	sizeC[1] = sizeB[1];

	// memory allocation
	matrixC = (int**)malloc(sizeC[0] * sizeof(int*));
	
	for(n=0; n < sizeC[0]; n++) {
		matrixC[n] = (int)malloc(sizeC[1] * sizeof(int*));
	}

	// zeros
	for(i=0; i< sizeC[0]; i++){
		for(j=0; j< sizeC[1]; j++){
			matrixC[i][j] = 0;
		}
	}

	#define sizeC0 sizeC[0] 
	#define sizeC1 sizeC[1]
	#define sizeA1 sizeA[1]

	#pragma scop
	for(i=0; i< sizeC0; i++){
		for(j=0; j< sizeC1; j++){
			for(k=0; k< sizeA1; k++){
				matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
			}
		}
	}
	#pragma endscop

	clock_t stop = clock();

	printf("- parallel: %f[s] \n", (float)(stop-start)/CLOCKS_PER_SEC);

	// -----------------------save to file--------------------

	clock_t saveStart = clock();

	FILE *f3 = fopen(argv[3], "w");

	for(i=0; i< sizeC[0]; i++){
		for(j=0; j< sizeC[1]; j++){
			fprintf(f3, " %d ", matrixC[i][j]);
		}
		fprintf(f3, "\n\n");
	}

	fclose(f3);

	clock_t saveStop = clock();

	float saveTime = (float)(saveStop - saveStart)/CLOCKS_PER_SEC;
	float readWriteTime = readTime + saveTime;

	printf("- sequence: %f[s]\n", readWriteTime);

	return 0;
}

void loadMatrix(char *f, int **matrix, int *size) {

	char line[10000];
	char *begin,*end;

	int i, j;
	i=0;j=0;

	while (fgets(line, sizeof line, f) && !feof(f))  {

		begin = line;
		j=0;
		for(begin = line; ;begin = end,j++) {
			matrix[i][j] = strtol(begin, &end, 10);
			if(begin == end)	break;
		}
		i++;
	}

}

void readSize(FILE *f, int *size) {

	size[0] = 0;
	size[1] = 0;

	char line[10000];
	char *begin,*end;

	long digit;

	while (fgets(line, sizeof line, f) && !feof(f))  {

		size[0]++;

		if(size[1] == 0) {
			begin = line;
			for(begin = line; ;begin = end) {
				digit = strtol(begin, &end, 10);
				if(begin == end)	break;
				size[1]++;
			}
		}
	}

	// printf("Size of matrix: (%d, %d) \n", size[0], size[1]);
}