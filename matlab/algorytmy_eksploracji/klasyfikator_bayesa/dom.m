clc
clear all
close all

format long

% pobranie danych
D = wineDataSet('bank-additional.csv');

% wykonywanie trenowania
[ DL, DT ] = dataSplit(D, 0.7); 

[ PY , P ] = bayes( D, 5 * ones(1, 20), 0 ); 

% 20 - podajemy ilosc cech ( ilosc kolumn )
[ PLY , PL ] = bayes ( D, 5 * ones(1, 20), 1);

% x = DT(5, :)
% 
% % zwraca klasyfikacje ( tj. 1 )
% y = classify(x, PY, P)

% dokladnosc
[ acc ] = [
    
    % bez laplace'a
    accuracy( DL, PY , P ) accuracy( DT, PY , P ); 
    
    % z laplace'm
    accuracy( DL, PLY , PL ) accuracy( DT, PLY , PL )
    
];

% zadanie domowe 
% http://wikizmsi.zut.edu.pl/wiki/AED/LN/z1
% ostatni punkt zignorowaæ ( zrobiæ tylko 3 )

% k krotna krzy¿owa walidacja ( K - fold crossValidation )
% k > 1 liczba naturalna
% wynikiem k krotnej krzyzowej walidacji jest œrednia 
% paczek testowych accuracy






