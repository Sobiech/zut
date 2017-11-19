#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define  NUM_LOOPS 50

int main(int argc, char *argv[]) {

    double **A;
    double *u, *v;

    long rows = 5000, columns = 5000;

    time_t begin_t, end_t;
    long i, j, k;

    // Inicjujemy wektory i macierz.
    u = (double*)malloc(columns * sizeof(double));
    v = (double*)malloc(rows * sizeof(double));

    A = (double**)malloc(rows * sizeof(double*));

    for (i = 0; i < rows; i++)
        A[i] = (double*)malloc(columns * sizeof(double));


    // Wypełniamy wektor u oraz macierz A dowolnymi wartościami.
    for (i = 0; i < columns; i++) {
        u[i] = (double)(i / 1000.0f);

        for ( j = 0; j < rows; j++ )
            A[j][i] = (double)(i * j / 1000.0f);
    }


    begin_t = time(NULL);


    // Pętla zewnętrzna.
    for (k = 0; k < NUM_LOOPS; k++) {

        // Obliczamy v = A*u z wykorzystaniem dyrektyw OpenMP.
        #pragma omp parallel for shared(A, v, u, rows, columns) private(i, j)

        for (i = 0; i < rows; i++) {
            v[i] = 0.0f;
            for (j = 0; j < columns; j++)
                v[i]  = v[i] + A[i][j] * u[j];
        }
    }

    end_t = time(NULL);

    printf("Czas obliczen: %f.\n", difftime(end_t, begin_t));

    // Zwalniamy pamięć.
    free(u);
    free(v);

    for (i = 0; i < rows; i++)
        free(A[i]);

    free(A);

    return 0;
}