using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

public class MatrixUtils {

    private const char VALUE_SEPARATOR = ',';

    public static List<String> MultiplyMatrix(int[,] matrixA, int[,] matrixB) {

        int rA = matrixA.GetLength(0);
        int cA = matrixA.GetLength(1);
        int rB = matrixB.GetLength(0);
        int cB = matrixB.GetLength(1);
        int temp = 0;
        //int[,] kHasil = new int[rA, cB];

        List<String> result = new List<string>();

        if (cA != rB) {
            throw new Exception("Macierze nie mogą zostać pomnożone !!");
        }

        Parallel.For(0, rA, i => {
            string line = string.Empty;
            for (int j = 0; j < cB; ++j) {
                temp = 0;
                for (int k = 0; k < cA; ++k) {
                    temp += matrixA[i, k] * matrixB[k, j];
                }
                line += ( temp + "," );
            }
            result.Add(line);
        });

        return result;
    }


    public static int[,] ReadMatrixFromContent(List<String> fileLines) {

        int rows = 0, cols = 0;

        ICollection<int> matrixCollection = new List<int>();
        foreach (String line in fileLines) {

            String[] splitedLine = line.Replace("\r", "").Split(VALUE_SEPARATOR);
            if (cols == 0) {
                cols = splitedLine.Length;
            }
            System.Diagnostics.Debug.WriteLine("");
            foreach (String num in splitedLine) {
                int number = Int32.Parse(num);
                matrixCollection.Add(number);
            }
            
            rows += 1;
        }

        int[,] matrix = new int[rows, cols];
        int iteration = 1;
        int row = 0;
        int col = 0;
        foreach (int number in matrixCollection) {
            matrix[row, col] = number;
            if (iteration % cols == 0) {
                row += 1;
                col = 0;
            } else {
                col += 1;
            }

            iteration += 1;
        }

        return matrix;
    }
}

