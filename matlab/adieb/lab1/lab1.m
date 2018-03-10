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
for i = 1 : size(B)
    suma = (( B(i) - 20) ^ 2 )/ 20 ;
    hiSq = hiSq + suma;
end

p = 1 - chi2cdf(hiSq, 6 - 1);
fprintf('p = %f\n', p );
if p <= alfa
    disp('Rozk³ad jest nie oczekiwany: p <= alfa');
else
    disp('Rozk³ad jest oczekiwany: p > alfa');
end

%% start
fprintf('\nZadanie 2 \n' );
alfa = input('Wprowadz alfa: ');
% 1 - pomarszone i zielone
% 2 - okragle i zielone
% 3 - pomarszone i zolte
% 4 - okragle i zolte
A = [ 1, 3, 3, 9];
B = [ 32, 108, 101, 315 ];

sumB = sum(B);
sumA = sum(A);

hiSq2 = 0;
for i = 1 : size(B)
    np_i = ( sumB / sumA ) * B(i);
    suma = (( B(i) - np_i) ^ 2) / np_i;
    hiSq2 = hiSq2 + suma;
end

p = 1 - chi2cdf(hiSq2, 4 - 1);
fprintf('p = %f\n', p);
if p <= alfa
    disp('Rozk³ad jest nie oczekiwany: p <= alfa');
else
    disp('Rozk³ad jest oczekiwany: p > alfa');
end
