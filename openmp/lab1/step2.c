#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>

int main(int argc, char *argv[]) {

    int i, n, id, chunk_size;

    time_t begin_t, end_t;

    n = 100000;

    chunk_size = 1;

    begin_t = time(NULL);

    long sum = 0;

    #pragma omp parallel default(none) private(i, id) shared(n, sum, chunk_size) 
    {
        #pragma omp single 
        {
            printf("Program jest wykonywany na %d watkach.\n", 
                omp_get_num_threads());
        }

        #pragma omp for schedule(guided, chunk_size)
        for (i = 0; i < n; i++) {
            id = omp_get_thread_num();
            //printf("Iteracja %d wykonana przez watek nr. %d.\n", i, id);
            sum += (long)i;
        }

    }

    end_t = time(NULL);

    printf("Czas obliczen z omp: %f.\n", difftime(end_t, begin_t));
    printf("Got sum: %ld ", sum);

    begin_t = time(NULL);


    sum = 0;
    
    for ( i = 0; i < n ; i++ ) {

        sum += (long)i;
        //printf("Iteracja %d wykonana przez jeden watek \n", i);
    }

    end_t = time(NULL);

    printf("Czas obliczen bez omp: %f.\n", difftime(end_t, begin_t));
        printf("Got sum: %ld ", sum);


    return 0;
}