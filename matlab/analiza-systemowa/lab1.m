clc
clear all
close all

format long

X1 = [ 0 0 0 0 0 0 0 2 2 2 2 2 2 2 4 4 4 4 4 4 4 6 6 6 6 6 6 6 ];
X2 = [ 0 1 2 3 4 5 6 0 1 2 3 4 5 6 0 1 2 3 4 5 6 0 1 2 3 4 5 6 ];
X3 = [ 0 0.1 0.4 0.9 1.6 2.5 3.6 0.4 0.1 0 0.1 0.4 0.9 1.6 1.6 0.9 0.4 0.1 0 0.1 0.4 3.6 2.5 1.6 0.9 0.4 0.1 0 ];

X = [ones(1, size(X1,2)); X1; X2 ];

A = X3 * X' * ( X * X')^-1;

[v1,v2] = meshgrid(0:0.1:6, 0:0.1:6);

v3 = A(1) + A(2) * v1 + A(3) * v2;

figure
mesh(v1,v2,v3);
hold on;
plot3(X1,X2,X3,'*');

x3p = A(1) + A(2) * X1 + A(3) * X2;

blad = 1/ 28 .* sum ((X3 - x3p).^ 2) ;

%% zad 2

X = [ones(1, size(X1,2)); X1 .* X2 ];

A = X3 * X' * ( X * X')^-1;

[v1,v2] = meshgrid(0:0.1:6, 0:0.1:6);

v3 = A(1) + A(2) * v1 .* v2;


figure
mesh(v1,v2,v3);
hold on;
plot3(X1,X2,X3,'*');

x3p = A(1) + A(2) * X1 .* X2;

blad = 1/ 28 .* sum ((X3 - x3p).^ 2) ;

%% wzor 3
clc

X = [ones(1, size(X1,2)); X1; X2; X1 .* X2];

A = X3 * X' * ( X * X')^-1;

[v1,v2] = meshgrid(0:0.1:6, 0:0.1:6);

v3 = A(1) + A(2) * v1 + A(3) * v2 + A(4) * v1 .* v2;

figure
mesh(v1,v2,v3);
hold on;
plot3(X1,X2,X3,'*');

x3p = A(1) + A(2) * X1 + A(3) * X2 + A(4) * X1 .* X2;

blad = 1/ 28 .* sum ((X3 - x3p).^ 2) ;

%% wzor 4
clc

X = [ones(1, size(X1,2)); X1; X2; X1 .* X2; X1.^2; X2.^2];

A = X3 * X' * ( X * X')^-1;

[v1,v2] = meshgrid(0:0.1:6, 0:0.1:6);

v3 = A(1) + A(2) * v1 + A(3) * v2 + A(4) * v1 .* v2 + A(5) .* v1 .^2 + A(6) .* v2 .^2;

figure
mesh(v1,v2,v3);
hold on;
plot3(X1,X2,X3,'*');

x3p = A(1) + A(2) * X1 + A(3) * X2 + A(4) * X1 .* X2 + A(5) .* X1 .^2 + A(6) .* X2 .^2;

blad = 1/ 28 .* sum ((X3 - x3p).^ 2) ;


%% wzor 5
clc

X = [ones(1, size(X1,2)); X1.^4; X2.^4; X1 .* X2];

A = X3 * X' * ( X * X')^-1;

[v1,v2] = meshgrid(0:0.1:6, 0:0.1:6);

v3 = A(1) + A(2) * v1.^4 + A(3) * v2.^4 + A(4) * v1 .* v2;

figure
mesh(v1,v2,v3);
hold on;
plot3(X1,X2,X3,'*');

x3p = A(1) + A(2) * X1 .^4 + A(3) * X2 .^4 + A(4) * X1 .* X2;

blad = 1/ 28 .* sum ((X3 - x3p).^ 2) ;


%%% MODEL 4 jest najdok³adniejszy