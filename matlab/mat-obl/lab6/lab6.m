clc
clear all
close all

Xmax = 302 + 36 + 44 + 192 + 302
Ymax = 439 + 28 + 130
Zmax = 159 + 29 + 24 + 62

abstractVolume = ( Xmax * Ymax * Zmax ) / 1000000

numberOfIterations = 100000;
cn = 0;
figure
%pod³oga
line( [0 0], [158 597], [0 0] );
line( [0 876], [597 597], [0 0] );
line( [876 876], [158 597], [0 0] );
line( [0 382], [158 158], [0 0]);
line( [382 382], [0 158], [0 0]);
line( [382 574], [0 0] , [0 0]);
line( [574 574], [0 158], [0 0]);
line( [574 876], [158 158], [0 0]);

%sufit
line( [0 0], [597 597], [0 274] );
line( [0 0], [217 217], [0 274] );
line( [876 876], [597 597], [0 274] );
line( [876 876], [217 217], [0 274] );

line( [0 0], [217 597], [274 274] );
line( [0 876], [217 217], [274 274] );
line( [0 876], [597 597], [274 274] );
line( [876 876], [217 597], [274 274] );

%skos sufit
line( [0 0], [158 158], [0 212] );
line( [0 876], [158 158], [212 212] );
line( [876 876], [158 158], [0 212] );
line( [382 382], [158 158], [0 212] );
line( [574 574], [158 158], [0 212] );
line( [0 0], [158 217], [212 274] );
line( [876 876], [158 217], [212 274] );

%wneka
line( [382 382], [130 158], [188 212] );
line( [574 574], [130 158], [188 212] );

line( [382 382], [0 0], [0 188] );
line( [574 574], [0 0], [0 188] );

line( [382 382], [0 130], [188 188] );
line( [382 382], [130 130], [0 188] );
line( [574 574], [130 130], [0 188] );

line( [574 574], [0 130], [188 188] );
line( [382 574], [0 0], [188 188] );
line( [382 574], [130 130], [188 188] );


%dziwny skos
line( [302 338], [158 158], [159 159] );
line( [302 302], [158 237], [159 238] );
line( [338 338], [158 237], [159 238] );

line( [302 302], [237 293], [238 274] );
line( [338 338], [237 293], [238 274] );

line( [302 302], [217 293], [274 274] );
line( [338 338], [217 293], [274 274] );
line( [302 338], [293 293], [274 274] );

hold on;

for i=  1: numberOfIterations
    
    x = rand * Xmax;
    y = rand * Ymax;
    z = rand * Zmax;
    
    color = '.r';
    
    if ( x >= 302 && x <= 338 ) && ( y >= 217 && y <= 293 ) && z > heightT(y, 293, 158, 274, 159) %dziwny skos
        color = '.r';
    elseif  y < 130 && x >= 382 && x <= 575 && z < 188 %wneka
        cn = cn + 1;
        color = '.b';
    elseif ( y < 217 && y >= 158 ) && z < heightT(y, 217, 158, 274, 212) %skos
        cn = cn + 1;
        color = '.b';
    elseif ( y <= 158 && y >= 130 ) && z < heightT(y, 158, 130, 212, 188) %skos wneka
        cn = cn + 1;
        color = '.b';     
    elseif y >= 217 %pozostaly obszar
        cn = cn + 1;
        color = '.b';
    end
    
    plot3( x, y, z, color);

end

calculatedVolume = ( cn / numberOfIterations ) * abstractVolume
%%
vectX = 1:1:Xmax;
vectY = 1:1:Ymax;
[X,Y] = meshgrid(vectX,vectY);
% obliczanie wysokoci sufitu.
for i=1:Ymax
    for j=1:Xmax
        if i>217
            Z(i,j)=274;% ca³y sufit
        elseif i<=217 && i>158
            a = i-158;
            Z(i,j)=a*1.0508+212;%skos du¿y
        elseif i<=158 && i>130 && j>384 && j<574
            b = i-130;
            Z(i,j)=b*0.8571+188;%skos przy wnêce
        elseif i<=130 && j>384 && j<574
            Z(i,j) = 188;% wnêka
        else
            Z(i,j) = 0;% reszta
        end
        if  i<237 && i>=158 && j>302 && j<338
            c = i-158;
            Z(i,j) = c+159;% dziwny skos
        end
        if  i<293 && i>=237 && j>302 && j<338
            d = i-237;
            Z(i,j) = d*0.6428+238;% dziwny skos 2
        end
    end
end
mesh(X,Y,Z)
gridSize = 2;
squareVolume=0;
% Kwadraty
for i = gridSize:gridSize:Ymax
    for j=gridSize:gridSize:Xmax
        midx = ceil(j-(gridSize/2));% branie punktu ze œrodka kwadratu.
        midy = ceil(i-(gridSize/2));
        actual = gridSize*gridSize*Z(midy,midx)/1000000;
        squareVolume = squareVolume + actual;
    end     
end

% results:
fprintf('Monte Carlo: %f\n', calculatedVolume);
fprintf('Square Method: %f\n', squareVolume);
%cdn.



