#include <omp.h>
#include <math.h>
#define ceild(n,d)  ceil(((double)(n))/((double)(d)))
#define floord(n,d) floor(((double)(n))/((double)(d)))
#define max(x,y)    ((x) > (y)? (x) : (y))
#define min(x,y)    ((x) < (y)? (x) : (y))

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

/* Copyright (C) 1991-2016 Free Software Foundation, Inc.
   This file is part of the GNU C Library.

   The GNU C Library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.

   The GNU C Library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.

   You should have received a copy of the GNU Lesser General Public
   License along with the GNU C Library; if not, see
   <http://www.gnu.org/licenses/>.  */
/* This header is separate from features.h so that the compiler can
   include it implicitly at the start of every compilation.  It must
   not itself include <features.h> or any other header that includes
   <features.h> because the implicit include comes before any feature
   test macros that may be defined in a source file before it first
   explicitly includes a system header.  GCC knows the name of this
   header in order to preinclude it.  */
/* glibc's intent is to support the IEC 559 math functionality, real
   and complex.  If the GCC (4.9 and later) predefined macros
   specifying compiler intent are available, use them to determine
   whether the overall intent is to support these features; otherwise,
   presume an older compiler has intent to support these features and
   define these macros by default.  */
/* wchar_t uses Unicode 8.0.0.  Version 8.0 of the Unicode Standard is
   synchronized with ISO/IEC 10646:2014, plus Amendment 1 (published
   2015-05-15).  */
/* We do not support C11 <threads.h>.  */
  int t1, t2, t3, t4, t5, t6;
 int lb, ub, lbp, ubp, lb2, ub2;
 register int lbv, ubv;
/* Start of CLooG code */
if ((sizeA1 >= 1) && (sizeC0 >= 1) && (sizeC1 >= 1)) {
  lbp=0;
  ubp=floord(sizeC0-1,32);
#pragma omp parallel for private(lbv,ubv,t2,t3,t4,t5,t6)
  for (t1=lbp;t1<=ubp;t1++) {
    for (t2=0;t2<=floord(sizeC1-1,32);t2++) {
      for (t3=0;t3<=floord(sizeA1-1,32);t3++) {
        for (t4=32*t1;t4<=min(sizeC0-1,32*t1+31);t4++) {
          for (t5=32*t3;t5<=min(sizeA1-1,32*t3+31);t5++) {
            lbv=32*t2;
            ubv=min(sizeC1-1,32*t2+31);
#pragma ivdep
#pragma vector always
            for (t6=lbv;t6<=ubv;t6++) {
              matrixC[t4][t6] += matrixA[t4][t5] * matrixB[t5][t6];;
            }
          }
        }
      }
    }
  }
}
/* End of CLooG code */

	clock_t stop = clock();

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

	float calculateTime = (float)(stop-start)/CLOCKS_PER_SEC;

	int iteration = atoi(argv[4]);
	int tileA = atoi(argv[5]);
	int tileB = atoi(argv[6]);
	int tileC = atoi(argv[7]);

	int v = tileA * tileB * tileC;
	float sum = calculateTime + readWriteTime;
	
	printf("%d; %d; %f; %f; %f\n", iteration, v, calculateTime, readWriteTime, sum);

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
