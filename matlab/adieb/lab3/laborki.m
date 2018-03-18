clc
clear all
close all

format long

% Wielkoœæ popytu Y w tys zl 
dataSetX = [ 
    1 1 1 1 1 1 1;                  % ones
    100 100 300 200 400 400 600;    % x1
    5 6 6 8 6 9 9;                  % x2
    8 7 6 5 6 5 5                   % x3
];

dataSetX = dataSetX';

dataSetY = [ 
    12; 14; 17; 20; 25; 30; 36            % Y
];

fprintf('Zadanie 1: model regresji wielorakiej \n' );

% - istotnosc modelu
% - wyspolczynnik determinacji
% - istotnosc wsploczynnikow regresji
% - funkcja regresji wielorakiej
% - wniosek o istotnosci modelu
% - wyswietlic wspolczynnik oraz wnioski o ich istotnosci

[x_a, x_b] = size(dataSetX);

% liczba obserwacji
n = x_a;

% liczba zmiennych niezaleznych
k = x_b - 1;

a = 0.05;

% model regresji
B = ( inv( dataSetX' * dataSetX ) * dataSetX') * dataSetY;
E = dataSetY - dataSetX * B;

% wariancja
wariancja = ((( 1 / ( n - k - 1) ) * E' ) * E );

% blad szacunku
D = diag(inv(dataSetX' * dataSetX));
SB = [];
for i = 1 : length(D)
    SB(i) = sqrt(wariancja * D(i));
end

% wspolczynnik determinacji
R2 = 1 - ( ( E' * E ) / ( ( dataSetY' * dataSetY ) - n *  mean(dataSetY)^2 ) );
fprintf('- wspolczynnik determinacji: %f\n', R2);

% statystyka testujaca
F = (( n - k - 1 ) * R2 ) / ( k *( 1 - R2 ));
fprintf('- statystyka testujaca: FISHER %f\n', F);

% poziom odrzucenia hipotezy zerowej
p = 1 - fcdf(F, k, n - k - 1);
fprintf('- poziom odrzucenia hipotezy zerowej: %f ', p);

if p > a
    fprintf( ' -> model jest nieistotny \n' );
else
    fprintf(' -> model jest istotny \n' );
end

% istotnosc wspolczynnikow regresji
fprintf('- statystyka testujaca: STUDENT \n');
t = [];
pt = [];
for i=1 : length(SB)
    
    t(i) = B(i) / SB(i);
    
    dst_t_i =  tcdf(t(i), n - k - 1);
    pt(i) = 2 * min( dst_t_i , 1 - dst_t_i );
    
    isIstotny = 'TAK';
    if  pt(i) > a
        isIstotny = 'NIE';
    end
    fprintf('- poziom odrzucenia hipotezy zerowej: t_%d = %f  p = %f : CZY JEST ISTOTNY: %s\n', i, t(i), pt(i), isIstotny );
    
end

