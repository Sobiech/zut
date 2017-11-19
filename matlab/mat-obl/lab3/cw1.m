clc
clear all
close all

format long

A = [ 1, 2; 1, 2.0001 ];
b = [ 4 ; 4.0001 ];


C = [A  b];

W1 = [ 4, 2; 4.0001, 2.0001 ];
W2 = [ 1, 4; 1, 4.0001];

detA = det(A);
[ det(W1)/detA, det(W2)/detA ];


in = inv(A);
inW1 = [ 4, -2; 4.0001 1 ];
inW2 = [ 2, 4; -1 4.0001 ];

detInA = det(in);
detInW1 = det(inW1);
detInW2 = det(inW2);

ix1 = detInW1 / detInA;
ix2 = detInW2 / detInA;

[L , U ] = lu(A);

wer1LU = inv(U) * inv(L) * b;

[L , U ] = lu(A);

xb = A \ b;
yb = U * xb;

[ Q, R ] = qr(A);


qx1 = inv(R) * Q' * b;

qx1 = Q\b;
qy1 = R\qx1;

[U,S,V] = svd(A,0);

solution = V';




