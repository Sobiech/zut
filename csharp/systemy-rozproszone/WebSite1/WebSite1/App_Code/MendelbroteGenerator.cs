using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading;
using System.Threading.Tasks;
using System.Web;

public class MendelbroteGenerator {

    private const double CxMax = 1.5;
    private const double CxMin = -2.5;
    
    private const double CyMax = 2.2;
    private const double CyMin = -2.0;

    private const int iXmax = 500;
    private const int iYmax = 500;

    private const int colorsCount = 100;

    private Color[,] bitmapColors;

    Bitmap FractalBitmap;

    public MendelbroteGenerator() {
        this.FractalBitmap = new Bitmap(iXmax, iYmax);
        bitmapColors = new Color[iXmax,iYmax];
    }

    public Bitmap GenerateBitmapMendelbrot(double criticalPointZx, double criticalPointZy, double circleRadius, int IterationMax) {

        Color[] colors = RandomColors.Generate(colorsCount);

        double ER2 = circleRadius * circleRadius;

        double PixelWidth = ( CxMax - CxMin ) / iXmax;
        double PixelHeight = ( CyMax - CyMin ) / iYmax;

        int Iteration;

        double Cx, Cy;

        /* Z=Zx+Zy*i  ;   Z0 = 0 */
        double Zx, Zy;
        double Zx2, Zy2; /* Zx2=Zx*Zx;  Zy2=Zy*Zy  */


        Parallel.For(0, iYmax, iY => {
            
            Cy = CyMin + iY * PixelHeight;
            if (Math.Abs(Cy) < PixelHeight / 2) {
                Cy = 0.0;
            }

            for (int iX = 0; iX < iXmax; iX++) {

                Cx = CxMin + iX * PixelWidth;

                Zx = criticalPointZx;
                Zy = criticalPointZy;
                Zx2 = Zx * Zx;
                Zy2 = Zy * Zy;

                for (Iteration = 0; Iteration < IterationMax && ( ( Zx2 + Zy2 ) < ER2 ); Iteration++) {
                    Zy = 2 * Zx * Zy + Cy;
                    Zx = Zx2 - Zy2 + Cx;
                    Zx2 = Zx * Zx;
                    Zy2 = Zy * Zy;    
                }

                SetPixel(iX, iY, colors[ Iteration % colorsCount]);
            }
        });

        for (int x = 0; x < iXmax; x++) {
            for (int y = 0; y < iYmax; y++) {
                FractalBitmap.SetPixel(x, y, bitmapColors[x, y]);
            }
        }

        return FractalBitmap;
    }


    [MethodImpl(MethodImplOptions.AggressiveInlining)]
    public void SetPixel(int x, int y, Color c) {
        bitmapColors[x, y] = c;
    }

}