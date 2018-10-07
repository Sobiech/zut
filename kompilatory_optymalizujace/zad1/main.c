#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char* argv[]) {

	if(argc < 3) {
		perror("Too few agruments");
		return 1;
	}

	srand( time(NULL) );

	char *arrayA = argv[1];
	int m = atoi(argv[2]);
	int n = atoi(argv[3]);

	printf("Matrix: m=%d, n=%d\n", m, n );
	for ( int i = 0; i < 10; i++ ) {

		clock_t createStart = clock();

		printf("Generating to file: A=%s_%d \n", arrayA, i );
		char cmd[255];
		sprintf(cmd, "./generateMatrix %s_%d %d %d", arrayA, i, m, n );
		int a = system(cmd);

		clock_t createEnd = clock();
		float generateTime = (float)(createEnd - createStart)/CLOCKS_PER_SEC;
		printf("End iteration of i:%d in %f\n\n", i, generateTime);
	}

	return 0;
}
