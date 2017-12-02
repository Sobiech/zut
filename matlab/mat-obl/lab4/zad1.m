clc
clear all
close all

format long

MATRIX_SIZES = [ 5, 10, 100, 500, 1000, 3000, 5000 ];
ACCURACY =  [ 0.1, 0.01, 0.001 ]; 

for  k = 1 : length(ACCURACY)
    
    ACCURACY(k)
    tic 
    for i = 1 : length(MATRIX_SIZES)
        
        matrixSize = MATRIX_SIZES(i);
        A = [];
        for  x = 1 : matrixSize 
            for y = 1 : matrixSize
                A(x,y) = rand();
            end   
        end
         
        A = A + ( eye(matrixSize) * 10 );
        X = ones(matrixSize);
        
        
        %B = A * X;

    end
    toc
end

