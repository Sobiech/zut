clc
clear all
close all

format long

% row 1 liczba odwiedzajacych
% row 2 wielkosc obrotow w tys
dataSetX = [ 20 25 32 21 35 36 42 39 38 41 51 ];
dataSetY = [ 2.5 3.1 4.5 5.3 6.4 7.8 8.9 9.5 10.3 11.5 12.1 ];

fprintf('Zadanie 1: równanie regresji \n' );
%inDependVar = input('WprowadŸ zmienn¹ niezale¿n¹: ');
%relvanceWsp = input('WprowadŸ wsp. istotnoœci: ');

b1_licznik = 0;
b1_mianownik = 0;

avgX = mean(dataSetX);
avgY = mean(dataSetY);

for i = 1 : length(dataSetX)
    x = dataSetX(i);
    y = dataSetY(i);
    b1_licznik = b1_licznik + ( x - avgX ) * ( y - avgY );
    b1_mianownik = b1_mianownik + ( (x - avgX)^ 2 );    
end

b_1 = b1_licznik / b1_mianownik;
b_0 = avgY - ( b_1 * avgX );

fprintf('- wsp. b_0 = %f \n', b_0 );
fprintf('- wsp. b_1 = %f \n', b_1 );

fprintf('\nZadanie 1: blad / istotnoœæ \n' );
% nie podano alfa przyjmujemy wartosc 0.05
alfa = 0.05;

sumZmiennaZalezna = 0;
% blad standardowy estymacji 1.533
for i = 1 : length(dataSetY)
    y = dataSetY(i);
    x = dataSetX(i);
    sumZmiennaZalezna = sumZmiennaZalezna + (( y - ( b_0 + b_1 * x ))^2 );
end

nSe = length(dataSetX);
sE = sqrt(( 1 / ( nSe - 2 ) ) * sumZmiennaZalezna);
fprintf('- blad estymacji: %f \n', sE );

st_SumX  = 0;
st_0PowX = 0;

for i = 1 : length(dataSetX)
    x = dataSetX(i);
    st_SumX  = st_SumX  + (( x - avgX ) ^ 2);
    st_0PowX = st_0PowX +  ( ( x ) ^ 2 );
end

sT_0 = ( b_0 / sE ) * sqrt(  nSe * ( st_SumX / st_0PowX ) );
sT_1 = ( b_1 / sE ) * sqrt(st_SumX);

fprintf('- statystyka testujaca: \n');
fprintf('\t t dla b_0:%f \n', sT_0 );
fprintf('\t t dla b_1:%f \n', sT_1 );

dystrT_b0 = tcdf(sT_0, nSe - 2 );
dystrT_b1 = tcdf(sT_1, nSe - 2 );

p_b0 = 2 * min( dystrT_b0, 1 - dystrT_b0 );
p_b1 = 2 * min( dystrT_b1, 1 - dystrT_b1 );

fprintf('- poziom odrzucenia hipotezy zerowej: \n');

isB0Istotny = 'TAK';
if  p_b0 < alfa
    isB0Istotny = 'NIE';
end

isB1Istotny = 'TAK';
if p_b1 < alfa
    isB1Istotny = 'NIE';
end

fprintf('\t p dla sT_0:%f / istotny statystycznie: %s \n', p_b0 , isB0Istotny );
fprintf('\t p dla sT_1:%f / istotny statystycznie: %s \n', p_b1 , isB1Istotny );


Y = b_0 + b_1 * dataSetX;

plot(dataSetX, dataSetY,'ro');
hold on;
grid on;
plot(dataSetX, Y, 'b', dataSetX, Y + sE, 'g', x, Y - sE, 'g');

