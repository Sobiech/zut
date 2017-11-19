clc
clear all
close all

format long

Y = [1486.2497 878366.9879 -22.37492 4773714.647 0.000185049];
X = [exp(1) -pi sqrt(2) -psi(1) log10(2)];

dot(X,Y);

A = 0;

[W,K] = size(X);

for i = 1 : K
    
    D = A;
    D = X(i) * Y(i);
    
    A = D + A;
end

B = 0;
for j = 1 : K
    
    E = B;
    indeks = K - j + 1;
    E = X(indeks) * Y(indeks);
    
    B = E + B;
end

C = Y(K) * X(K) + Y(1) * X(1) + Y(2) * X(2) + Y(4) * X(4) + Y(3) * X(3);




