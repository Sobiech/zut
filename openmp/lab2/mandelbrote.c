 /* 
 c program:
 --------------------------------
  1. draws Mandelbrot set for Fc(z)=z*z +c
  using Mandelbrot algorithm ( boolean escape time )
 -------------------------------         
 2. technique of creating ppm file is  based on the code of Claudio Rocchini
 http://en.wikipedia.org/wiki/Image:Color_complex_plot.jpg
 create 24 bit color graphic file ,  portable pixmap file = PPM 
 see http://en.wikipedia.org/wiki/Portable_pixmap
 to see the file use external application ( graphic viewer)
  */
//   https://rosettacode.org/wiki/Mandelbrot_set#PPM_non_interactive
#include <stdio.h>
#include <math.h>
#include <omp.h>
#include <time.h>

#define iXmax 1600
#define iYmax 1600
static unsigned char color[iXmax][iYmax][3];  

void scheduleGuided();
void scheduleStatic();
void scheduleDynamic();

int main() {

    // scheduleGuided();
    omp_set_num_threads(8);
    scheduleStatic();
    // scheduleDynamic();

    return 0;
}



  void scheduleGuided() {

    int iX,iY;

     double Cx,Cy;
     const double CxMin=-2.5;
     const double CxMax=1.5;
     const double CyMin=-2.0;
     const double CyMax=2.0;
     
    double begin_t, end_t;

    //ompt_get_w_time();
     /* */
     double PixelWidth=(CxMax-CxMin)/iXmax;
     double PixelHeight=(CyMax-CyMin)/iYmax;
     
     /* color component ( R or G or B) is coded from 0 to 255 */
     /* it is 24 bit color RGB file */
     const int MaxColorComponentValue=255; 
     FILE * fp;
     char *filename="guided_schedule.ppm";
     char *comment="# ";/* comment should start with # */
     
     /* Z=Zx+Zy*i  ;   Z0 = 0 */
     double Zx, Zy;
     double Zx2, Zy2; /* Zx2=Zx*Zx;  Zy2=Zy*Zy  */
     
     /*  */
     int Iteration;
     const int IterationMax=200;
     
     int id;

     /* bail-out value , radius of circle ;  */
     const double EscapeRadius=2;
     double ER2=EscapeRadius*EscapeRadius;
     
     /*create new file,give it a name and open it in binary mode  */
     fp= fopen(filename,"wb"); /* b -  binary mode */
     
     /*write ASCII header to the file*/
     fprintf(fp,"P6\n %s\n %d\n %d\n %d\n",comment,iXmax,iYmax,MaxColorComponentValue);
     
     /* compute and write image data bytes to the file*/
     begin_t = omp_get_wtime();

     int chunk_size = 1;

     #pragma omp parallel private(id, chunk_size) 
     {

        id = omp_get_thread_num();

         #pragma omp for private(iX,Iteration,Cy,Cx,Zx,Zy,Zx2,Zy2) schedule(guided, chunk_size)
         for ( iY = 0; iY < iYmax; iY++ ) {

            Cy = CyMin + iY * PixelHeight;
            
            if ( fabs(Cy) < PixelHeight/2 ) {
                /* Main antenna */
                Cy=0.0;            
            }
            
            for ( iX=0; iX < iXmax; iX++ ) {

                 Cx=CxMin + iX*PixelWidth;
     
                 /* initial value of orbit = critical point Z= 0 */
                 Zx=0.0;
                 Zy=0.0;
                 Zx2=Zx*Zx;
                 Zy2=Zy*Zy;

                 for ( Iteration=0; Iteration < IterationMax && ( ( Zx2 + Zy2 ) < ER2 ); Iteration++ ) {

                     Zy=2*Zx*Zy + Cy;
                     Zx=Zx2-Zy2 +Cx;
                     Zx2=Zx*Zx;
                     Zy2=Zy*Zy;
                 };

                 /* compute  pixel color (24 bit = 3 bytes) */
                if (Iteration==IterationMax) {
                
                /*  interior of Mandelbrot set = black */
                    color[iY][iX][0]=0;
                    color[iY][iX][1]=0;
                    color[iY][iX][2]=0;                           
                } else {

                 /* exterior of Mandelbrot set = white */
                    color[iY][iX][0]=id*60; /* Red*/
                    color[iY][iX][1]=id*60;  /* Green */ 
                    color[iY][iX][2]=id*60;/* Blue */
                };
            }
        }

    }  

    end_t = omp_get_wtime();

    printf("Czas obliczen z guided: %f.\n", end_t - begin_t );

    /*write color to the file*/
    fwrite(color,1,3*iXmax*iYmax,fp);
    fclose(fp);

  }

  void scheduleStatic() {

    int iX,iY;

     double Cx,Cy;
     const double CxMin=-2.5;
     const double CxMax=1.5;
     const double CyMin=-2.0;
     const double CyMax=2.0;
     
    double begin_t, end_t;

    //ompt_get_w_time();
     /* */
     double PixelWidth=(CxMax-CxMin)/iXmax;
     double PixelHeight=(CyMax-CyMin)/iYmax;
     
     /* color component ( R or G or B) is coded from 0 to 255 */
     /* it is 24 bit color RGB file */
     const int MaxColorComponentValue=255; 
     FILE * fp;
     char *filename="static_schedule.ppm";
     char *comment="# ";/* comment should start with # */
     
     /* Z=Zx+Zy*i  ;   Z0 = 0 */
     double Zx, Zy;
     double Zx2, Zy2; /* Zx2=Zx*Zx;  Zy2=Zy*Zy  */
     
     /*  */
     int Iteration;
     const int IterationMax=200;
     
     int id;

     /* bail-out value , radius of circle ;  */
     const double EscapeRadius=2;
     double ER2=EscapeRadius*EscapeRadius;
     
     /*create new file,give it a name and open it in binary mode  */
     fp= fopen(filename,"wb"); /* b -  binary mode */
     
     /*write ASCII header to the file*/
     fprintf(fp,"P6\n %s\n %d\n %d\n %d\n",comment,iXmax,iYmax,MaxColorComponentValue);
     
     /* compute and write image data bytes to the file*/
     begin_t = omp_get_wtime();

     int chunk_size = 1;

     #pragma omp parallel private(id, chunk_size) 
     {

        id = omp_get_thread_num();
        printf("Aktualny watek %d \n", id);
         #pragma omp for private(iX,iY,Iteration,Cy,Cx,Zx,Zy,Zx2,Zy2) schedule(static, 1)
         for ( iY = 0; iY < iYmax; iY++ ) {

            Cy = CyMin + iY * PixelHeight;
            
            if ( fabs(Cy) < PixelHeight/2 ) {
                /* Main antenna */
                Cy=0.0;            
            }
            
            for ( iX=0; iX < iXmax; iX++ ) {

                 Cx=CxMin + iX*PixelWidth;
     
                 /* initial value of orbit = critical point Z= 0 */
                 Zx=0.0;
                 Zy=0.0;
                 Zx2=Zx*Zx;
                 Zy2=Zy*Zy;

                 for ( Iteration=0; Iteration < IterationMax && ( ( Zx2 + Zy2 ) < ER2 ); Iteration++ ) {

                     Zy=2*Zx*Zy + Cy;
                     Zx=Zx2-Zy2 +Cx;
                     Zx2=Zx*Zx;
                     Zy2=Zy*Zy;
                 };

                 if ( id == 0)
                    printf ("%d\n", Iteration);

                 /* compute  pixel color (24 bit = 3 bytes) */
                if (Iteration==IterationMax) {
                /*  interior of Mandelbrot set = black */
                    color[iY][iX][0]=0;
                    color[iY][iX][1]=0;
                    color[iY][iX][2]=0;                           
                } else {

                 /* exterior of Mandelbrot set = white */
                    color[iY][iX][0]=id*60; /* Red*/
                    color[iY][iX][1]=id*60;  /* Green */ 
                    color[iY][iX][2]=id*60;/* Blue */
                };
            }
        }

    }  

    end_t = omp_get_wtime();

    printf("Czas obliczen z static: %f.\n", end_t - begin_t );

    /*write color to the file*/
    fwrite(color,1,3*iXmax*iYmax,fp);
    fclose(fp);

  }

  void scheduleDynamic() {

    int iX,iY;

     double Cx,Cy;
     const double CxMin=-2.5;
     const double CxMax=1.5;
     const double CyMin=-2.0;
     const double CyMax=2.0;
     
    double begin_t, end_t;

    //ompt_get_w_time();
     /* */
     double PixelWidth=(CxMax-CxMin)/iXmax;
     double PixelHeight=(CyMax-CyMin)/iYmax;
     
     /* color component ( R or G or B) is coded from 0 to 255 */
     /* it is 24 bit color RGB file */
     const int MaxColorComponentValue=255; 
     FILE * fp;
     char *filename="dynamic_schedule.ppm";
     char *comment="# ";/* comment should start with # */
     
     /* Z=Zx+Zy*i  ;   Z0 = 0 */
     double Zx, Zy;
     double Zx2, Zy2; /* Zx2=Zx*Zx;  Zy2=Zy*Zy  */
     
     /*  */
     int Iteration;
     const int IterationMax=200;
     
     int id;

     /* bail-out value , radius of circle ;  */
     const double EscapeRadius=2;
     double ER2=EscapeRadius*EscapeRadius;
     
     /*create new file,give it a name and open it in binary mode  */
     fp= fopen(filename,"wb"); /* b -  binary mode */
     
     /*write ASCII header to the file*/
     fprintf(fp,"P6\n %s\n %d\n %d\n %d\n",comment,iXmax,iYmax,MaxColorComponentValue);
     
     /* compute and write image data bytes to the file*/
     begin_t = omp_get_wtime();

     int chunk_size = 1;

     #pragma omp parallel private(id, chunk_size) 
     {

        id = omp_get_thread_num();

         #pragma omp for private(iX,Iteration,Cy,Cx,Zx,Zy,Zx2,Zy2) schedule(dynamic, 1)
         for ( iY = 0; iY < iYmax; iY++ ) {

            Cy = CyMin + iY * PixelHeight;
            
            if ( fabs(Cy) < PixelHeight/2 ) {
                /* Main antenna */
                Cy=0.0;            
            }
            
            for ( iX=0; iX < iXmax; iX++ ) {

                 Cx=CxMin + iX*PixelWidth;
     
                 /* initial value of orbit = critical point Z= 0 */
                 Zx=0.0;
                 Zy=0.0;
                 Zx2=Zx*Zx;
                 Zy2=Zy*Zy;

                 for ( Iteration=0; Iteration < IterationMax && ( ( Zx2 + Zy2 ) < ER2 ); Iteration++ ) {

                     Zy=2*Zx*Zy + Cy;
                     Zx=Zx2-Zy2 +Cx;
                     Zx2=Zx*Zx;
                     Zy2=Zy*Zy;
                 };

                 /* compute  pixel color (24 bit = 3 bytes) */
                if (Iteration==IterationMax) {
                
                /*  interior of Mandelbrot set = black */
                    color[iY][iX][0]=0;
                    color[iY][iX][1]=0;
                    color[iY][iX][2]=0;                           
                } else {

                 /* exterior of Mandelbrot set = white */
                    color[iY][iX][0]=id*60; /* Red*/
                    color[iY][iX][1]=id*60;  /* Green */ 
                    color[iY][iX][2]=id*60;/* Blue */
                };
            }
        }

    }  

    end_t = omp_get_wtime();

    printf("Czas obliczen z dynamic: %f.\n", end_t - begin_t );

    /*write color to the file*/
    fwrite(color,1,3*iXmax*iYmax,fp);
    fclose(fp);
  }