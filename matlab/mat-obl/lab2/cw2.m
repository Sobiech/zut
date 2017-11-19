clc
clear all
close all

format long

A = [ 1 1 2; 2 1 4; 3 0 6];

%rzad macierzy
rankedA = rank(A);

%wlasne
eigA = eig(A);

%sum elementow wlasnych
sumEIG = sum(eigA);

%wsp. wielomianu charakterystyczynego
wspWiel = poly(A);

%pierwiastki wielomianu charakterystycznego
xWwsp = roots(wspWiel);

%slad macierzy
sladA = trace(A);
