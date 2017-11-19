clc
clear all
close all

format long

% pobranie danych
D = wineDataSet('wine.data');

% wykonywanie trenowania
[ DL, DT ] = dataSplit(D, 0.7); 

%[ PY , P ] = bayes( D, 1 );

% x = DT(5, :)
% zwraca klasyfikacje ( tj. 1 )
% y = classify(x, PY, P)