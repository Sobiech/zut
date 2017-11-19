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

#define iXmax 800
#define iYmax 800
static unsigned char color[iXmax][iYmax][3];  

  int main() {

     int iX,iY;

     double Cx,Cy;
     const double CxMin=-2.5;
     const double CxMax=1.5;
     const double CyMin=-2.0;
     const double CyMax=2.0;
     
     /* */
     double PixelWidth=(CxMax-CxMin)/iXmax;
     double PixelHeight=(CyMax-CyMin)/iYmax;
     
     /* color component ( R or G or B) is coded from 0 to 255 */
     /* it is 24 bit color RGB file */
     const int MaxColorComponentValue=255; 
     FILE * fp;
     char *filename="new2.ppm";
     char *comment="# ";/* comment should start with # */
     
     /* Z=Zx+Zy*i  ;   Z0 = 0 */
     double Zx, Zy;
     double Zx2, Zy2; /* Zx2=Zx*Zx;  Zy2=Zy*Zy  */
     
     /*  */
     int Iteration;
     const int IterationMax=200;
     
     /* bail-out value , radius of circle ;  */
     const double EscapeRadius=2;
     double ER2=EscapeRadius*EscapeRadius;
     
     /*create new file,give it a name and open it in binary mode  */
     fp= fopen(filename,"wb"); /* b -  binary mode */
     
     /*write ASCII header to the file*/
     fprintf(fp,"P6\n %s\n %d\n %d\n %d\n",comment,iXmax,iYmax,MaxColorComponentValue);
     
     /* compute and write image data bytes to the file*/
     #pragma omp parallel for private(iX,Iteration,Cy,Cx,Zx,Zy,Zx2,Zy2)
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
                color[iY][iX][0]=255; /* Red*/
                color[iY][iX][1]=255;  /* Green */ 
                color[iY][iX][2]=255;/* Blue */
             };
        }
    }

    /*write color to the file*/
    fwrite(color,1,3*iXmax*iYmax,fp);
    fclose(fp);
    return 0;
}