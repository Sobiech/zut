clc
clear all
close all

format long

A = [-5 -100 0 0; 0.5 -1.15 -1.1 0.15; 0 0 -0.04 0; 0 0.0055 0.0036 -0.0063];
%wyznacznik
detA = det(A);
%wartosci wlasne
ownData = eig(A);
%iloczyn elementow tablicy
iloczynA = prod(ownData);
%macierz odwrotna
invA = inv(A);
%upper trojkatna
upperA = triu(A);
%down trojkatna
downA = tril(A);

%slad macierzy
slad = trace(A);




