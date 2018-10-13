using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab1
{
    class MatrixUtils
    {

        public static int[,] multiplyMatrix(int[,] matrixA, int [,] matrixB )
        {
            
            int rA = matrixA.GetLength(0);
            int cA = matrixA.GetLength(1);
            int rB = matrixB.GetLength(0);
            int cB = matrixB.GetLength(1);
            int temp = 0;
            int[,] kHasil = new int[rA, cB];

            if (cA != rB)
            {
                throw new Exception("matrix can't be multiplied !!");
            }
            
            
            for (int i = 0; i < rA; i++)
            {
                for (int j = 0; j < cB; j++)
                {
                    temp = 0;
                    for (int k = 0; k < cA; k++)
                    {
                        temp += matrixA[i, k] * matrixB[k, j];
                    }
                    kHasil[i, j] = temp;
                }
            }
            return kHasil;
        }


        public static int[,] readMatrixFromFile(String fileName)
        {
            int rows = 0, cols = 0;

            ICollection<int> matrixCollection = new List<int>();
            using (StreamReader sr = new StreamReader(fileName))
            {
                // Read the stream to a string, and write the string to the console.
                String line = sr.ReadLine();
                Console.WriteLine(line);
                String[] splitedLine = line.Replace("\r", "").Split(';');
                if (cols == 0)
                {
                    cols = splitedLine.Length;
                }

                foreach (String num in splitedLine)
                {
                    Console.WriteLine(num);
                    matrixCollection.Add(Int32.Parse(num));
                }
                
                rows += 1;
            }

            int[,] matrix = new int[rows, cols];
            int iteration = 1;
            int row = 0;
            int col = 0;
            foreach ( int number in matrix )
            {
                if ( iteration % cols == 0 )
                {
                    row += 1;
                    col = 0;
                } else
                {
                    col += 1;
                }

                matrix[row, col] = number;
                iteration += 1;
            }

            return matrix;
        }
    }
}
