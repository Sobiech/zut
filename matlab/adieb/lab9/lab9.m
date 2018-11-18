clc
clear all
close all

format short
%euklides_distance = sqrt(suma(  (x_i - y_i)^2 ));


X = [ 
    39.8 38 19.2 23.2;
    47.6 39.8 22.4 22.1;
    41.7 37.6 21 22.3;
    40.7 38.5 24.8 23.4;
    47.9 39.8 22 23.3;
    39.7 38 20 22.3;
    48 39.9 23.3 22.1;
    39.5 37.9 20.2 23.3;
    47.7 39.7 22.7 23;
    47.8 39.8 22 23.3;
    47.9 39.9 22.4 22.7;
    39.4 37.6 19.8 22.5;
    39.6 38.1 18.8 23.2;
    48.1 39.7 23 23.3;
];
    
[x_row, x_col] = size(X);

X_sr  = mean(X);
X_std = std(X);

X_u = [];
for i = 1 : x_col
    X_u = [ X_u ((X(:,i) - X_sr(i)) / X_std(i)) ]; 
end

minD = [ max(max(X_u)) 0 0];
D = zeros(x_row, x_row);

% odleglosc euklidesowa
for i = 1 : x_row
    for j = 1 : x_row
        pow_of = ( X_u(i,:) -  X_u(j,:) );
        D(i,j) = sqrt( sum(pow_of) .^ 2 );
        if ( D(i,j) > 0 && D(i,j) < minD(1) ) 
            minD =  [ D(i,j) i j ];
        end
     end
end

S = zeros(x_row - 1, x_row - 1);
minS = [ max(max(D)) 0 0];

for i = 1 : x_row
    for j = 1 : x_row
        
        if ( i == D(3) ) 
            val = D(
        val = D(i,j);
        if ( i == minD(2) && val > 0 )
            look_min = min(val,D(minD(3),j));
            val = look_min;
        end

        S(i,j) = val;
        if ( S(i,j) > 0 && S(i,j) < minS(1) ) 
            minS =  [ S(i,j) i j ];
        end
    end
end    




