clc
clear all
close all

format long

MATRIX_SIZES = [ 5, 10, 100, 500, 1000, 3000, 5000 ];
ACCURACY     =  [ 0.1, 0.01, 0.001 ]; 

for  k = 1 : length(ACCURACY)
    
    ACCURACY(k)
    tic 
    for i = 1 : length(MATRIX_SIZES)
        
        matrixSize = MATRIX_SIZES(i);
        A = [];
        b = [];
        
        for  x = 1 : matrixSize 
            for y = 1 : matrixSize
                A(x,y) = rand();
            end
            b(x,:) = rand();
        end
         
        A = A + ( eye(matrixSize) * 10 );
        WZ = [ A b ];
        xn = 0;
        for x = 1 : matrixSize
            
            for y = 1 : matrixSize
                if x == y 
                    WZ(x, : ) = WZ(x,:)./WZ(x,x);
                    WZ(x, 1 : matrixSize) = WZ(x, 1:matrixSize) * ( -1 );
                    WZ( x , x ) = 0;
                end
               
            end
             
        end
        
        tempX = 0;
        
        x = 1;
        
        vectorSize = length(WZ);
        
        while true
            
            d = max ( abs ( WZ (:, x) - tempX ) );
            val  = WZ(:, vectorSize - 1).* tempX .* WZ(:, vectorSize);
            tempX = WZ(:, vectorSize);
            
            if d <= eps
                break
            end
        end
    end
    
    toc
end

