clc
clear all
close all

format long
fprintf('Zadanie 1 \n' );

alfa = input('Wprowadz alfa: ');

% 2.1
% rzuty kostka 
% wiersz 1 = oczka
% wiersz 2 = ilosc trafien

A = [ 1 2 3 4 5 6 ];
B = [ 11 30 14 10 33 22];

hiSq = 0;
for i = 1 : length(B)
    suma = (( B(i) - 20) ^ 2 )/ 20 ;
    hiSq = hiSq + suma;
end

p = 1 - chi2cdf(hiSq, 5);
fprintf('p = %f\n', p );
if p <= alfa
    disp('Rozk�ad jest nie oczekiwany: p <= alfa');
else
    disp('Rozk�ad jest oczekiwany: p > alfa');
end

%% start
fprintf('\nZadanie 2 \n' );
alfa = input('Wprowadz alfa: ');
% 1 - pomarszone i zielone
% 2 - okragle i zielone
% 3 - pomarszone i zolte
% 4 - okragle i zolte
A = [ 1 3 3 9];
B = [ 32 108 101 315 ];

sumA = sum(A);
sumB = sum(B);

hiSq2 = 0;
for i = 1 : length(B)
    np_i = ( sumB / sumA ) * A(i);
    suma2 = (( B(i) - np_i) ^ 2) / np_i;
    hiSq2 = hiSq2 + suma2;
end

p = 1 - chi2cdf(hiSq2, 3);
fprintf('p = %f\n', p);
if p <= alfa
    disp('Rozk�ad jest nie oczekiwany: p <= alfa');
else
    disp('Rozk�ad jest oczekiwany: p > alfa');
end

%% start
fprintf('\nZadanie 3 \n' );
alfa = input('Wprowadz alfa: ');
A = [ 97 103 109 115 121 127 133 139 ];
B = [ 3 7 11 20 28 19 10 2 ];

sumA = sum(A);
sumB = sum(B);

hiSq2 = 0;
for i = 1 : length(B)
    np_i = ( sumB / sumA ) * A(i);
    suma2 = (( B(i) - np_i) ^ 2) / np_i;
    hiSq2 = hiSq2 + suma2;
end

p = 1 - chi2cdf(hiSq2, 3);
fprintf('p = %f\n', p);
if p <= alfa
    disp('Rozk�ad jest nie oczekiwany: p <= alfa');
else
    disp('Rozk�ad jest oczekiwany: p > alfa');
end
