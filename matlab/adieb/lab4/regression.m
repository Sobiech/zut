function [ b_0, b_1, Y ] = regression( dataSetX, dataSetY )
    
    b1_licznik = 0;
    b1_mianownik = 0;

    avgX = mean(dataSetX);
    avgY = mean(dataSetY);

    for i = 1 : length(dataSetX)
        x = dataSetX(i);
        y = dataSetY(i);
        b1_licznik = b1_licznik + ( x - avgX ) * ( y - avgY );
        b1_mianownik = b1_mianownik + ( (x - avgX)^ 2 );    
    end

    b_1 = b1_licznik / b1_mianownik;
    b_0 = avgY - ( b_1 * avgX );

    Y = b_0 + b_1 * dataSetX;

end

