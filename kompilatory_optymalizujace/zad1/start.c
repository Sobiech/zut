#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

int main(int argc, char* argv[]) {

	srand( time(NULL) );

	int i, a, b, c;
	int v;
	char command[255];

	int minV = 5000000;

	for(i=0; i < 1000; i++) {
		
		do {
			a = rand() % (1000+1-2)+2;
			b = rand() % (1000+1-2)+2;
			c = rand() % (1000+1-2)+2;

			v = a * b * c;

		} while( v > minV );
			sprintf(command, "gcc multiplyMatrix.poly.c -w -lm -D TILE1=%d -D TILE2=%d -D TILE3=%d -lgomp -fopenmp -O3 -o multiplyMatrixPoly", a, b, c);
			// sprintf(command, "gcc multiplyMatrix.poly.c -w -lm -D TILE1=%d -D TILE2=%d -D TILE3=%d -o multiplyMatrixPoly",a,b,c);

		    system(command);
		    sprintf(command, "./multiplyMatrixPoly A.txt B.txt C.txt %d %d %d %d", i + 1, a, b, c);
		    system(command);
	}

	return 0;
}
