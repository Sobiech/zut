clc
clear all
close all

X = [ 28.3 12.8  5;
      11    9.2 10;
      17.9 14    5;
       2.1 12.7  3;
      46.5 27.7  4;
      37.1 16.4  1;
      16   20.1  5;
      35.8 23.1  2;
       7.9 14.1  5;
      10   25.5  9;
       0.7 12.5 10;
      17.5  4.5  5;
      10.8 20.8  1;
       4.9 16.8  2;
      39.5 20    7;
       0.2 17.6  2;
      40   22.8  1;
      48.1 25.2 10;
      22   16.8 10;
      23.8 17.7  2;];
  
Y = [ 5 5.6 9.4 26.7;
        2 2 1.9 9.3;
        5 4.4 12.1 35.7;
        3 0.9 1.8 7.8;
        9 10.5 24.3 69.3;
        5 9.5 17.2 53.7;
        7 4 9.8 27.3;
        10 8.2 13.7 37.2;
        4 2.8 8.9 23.4;
        7 3.1 6.5 22.2;
        5 0.5 2.5 10.2;
        1 5.5 11.3 33.9;
        6 2.4 5.1 13.2;
        4 1.6 3.5 11.7;
        8 11 21.1 63;
        5 0.8 4.3 11.7;
        10 9.4 18.8 54;
        7 11.5 22.4 67.8;
        7 5.2 8 26.7;
        7 6.4 10.1 3];

% standaryzacja  / 1
X_sr = mean(X);
Y_sr = mean(Y);

X_std = std(X);
Y_std = std(Y);

[ x_row x_col ] = size(X);
[ y_row y_col ] = size(Y);

X_u = [];
Y_u = [];

for i = 1 : x_col
    X_u = [ X_u ((X(:,i) - X_sr(i)) / X_std(i)) ]; 
end

for i = 1 : y_col 
    Y_u = [ Y ((Y(:,i) - Y_sr(i)) / Y_std(i)) ]; 
end

% budowa macierzy / 2
Z = [X_u, Y_u];

% macierz kowariancji / 3
C_z = cov(Z);
[cz_row cz_col] = size(C_z);

C_xx = C_z( 1:x_col, 1:x_col );
C_xy = C_z( 1:x_col, y_col:cz_col );

C_yx = C_z( y_col:cz_row, 1:x_col );
C_yy = C_z( y_col:cz_row, y_col:cz_col);


C_m = inv(C_xx) * C_xy * inv(C_yy)* C_yx;

[A U] = eig(C_m);

SORT = [sum(U); A];
SORT = fliplr(sortrows(SORT',1)');

U = SORT(1,:);
A = SORT(2:size(SORT,1),:);

S_u = sum( A' * C_xx * A );
S_u_sqrt = sqrt(S_u);
A = U(1,:)./S_u_sqrt;

for i = 2:size(U,1)
    A = [A; U(i,:)./S_u_sqrt];
end

fprintf('wagi kanoniczne zmiennej x: \n');
fprintf('\t- %s \n', A);


R = sqrt(U);
fprintf('\nwartoœæ korelacji kanonicznej: \n');
fprintf('\t- %s \n', R);

B = ( 1./ R(1) ) * inv(C_yy) * C_yx * A(:,1);
for i = 2:size(A,2)
    B = [B,(1./R(1)) * inv(C_yy) * C_yx * A(:,i)];
end

B = SORT(1,:);
fprintf('\n wagi kanoniczne zmiennej y: \n');
fprintf('\t- %s \n', B);

