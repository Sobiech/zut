clc
clear all
close all

siatka=1000;
d=10.^-3;

x = linspace((2-d),(2+d), siatka);
a = (x-2);

alg1 = a.^4;
alg2 = x.^4 - 8*x.^3 + 24 * x.^2 - 32*x + 16;

%plot(x, alg1, 'green', x, alg2, 'black');

h=d./500;
k=1;

x_k = 2 - d + ( k * h );
a_k  = (x_k - 2);

% error measurment for two algorithms
alg1_k = a_k.^4;
alg2_k = x_k.^4 - 8 * x_k.^3 + 24 * x_k.^2 - 32*x_k + 16;

max_k = single(max( abs ( alg1_k - alg2_k ) ))

plot( x, abs( alg1 - alg2 ), 'black');



