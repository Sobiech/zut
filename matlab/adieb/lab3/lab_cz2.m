clc
clear all
close all

format long

% m liczba pomiarow
% k liczba zmiennych niezaleznych

% musimy wyznaczyc wspolczynniki B
% k+1 wspolczynnik

dataSetX = [
    %ONES
    1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1;  
    
    % BUDZET
    3500 10073 11825 33550 37200 55400 55565 66501 71000 82107 83100 90496 100000 102100 132222 136297 139114 165575

    % CENA
    88 110 85 28 101 71 7 82 62 24 91 40 45 21 40 8 63 5;   
];

dataSetX = dataSetX';

%SPRZEDAZ
dataSetY = [ 16523 6305 1769 30570 7698 9554 54154 54450 47800 74598 25257 80608 40800 63200 69675 98715 75886 83360];

[x_a, x_b] = size(dataSetX);

% liczba obserwacji
n = x_a;

% liczba zmiennych niezaleznych
k = x_b - 1;

alfa = 0.5;

% model regresji
D = inv( dataSetX' * dataSetX );
B = D * dataSetX' * dataSetY';

E = dataSetY' - dataSetX * B;
S2_e = (1 / (n - k - 1)) * E' * E;

% blad szacunku parametru
S_b = sqrt( S2_e * diag(D) );

% wspolczynnik determinacji
R2 = 1 - (( E' * E ) / ( ( dataSetY * dataSetY' ) - ( n * mean(dataSetY)^2 ) ) );
fprintf('- wspolczynnik determinacji: %f\n', R2);

% statystyka testujaca
F = ( ( n - k - 1 ) * R2 )/( k * (1 - R2) );
fprintf('- statystyka testujaca: FISHER %f\n', F);

% poziom odrzucenia hipotezy zerowej
pF = 1 - fcdf( F, k, (n - k - 1) );
fprintf('- poziom odrzucenia hipotezy zerowej: %f \n', pF);

if (pF < alfa)
    fprintf('-> H1: Model regresji jest istotny statystycznie. %f < %f\n', pF, alfa);
    
    % istotnosc wspolczynnikow regresji
    fprintf('\n- statystyka testujaca: STUDENT \n');
    t = B./S_b;
    pt = 2 * min( tcdf(t,(n - k - 1)), 1 - tcdf(t,(n - k - 1)));
    for i = 1:size(B,1)
        
        fprintf('- wspó³czynik regresji b%d = %d\n', i-1, B(i));
        if (pt(i) < alfa)
            fprintf(' -> H1: Wspó³czynik regresji b%d != 0 jest istotny statystycznie\n', i-1 );
        else
            fprintf(' -> H0: Wspó³czynik regresji b%d = 0 nie jest istotny statystycznie\n',i-1);
        end
    end
else
    fprintf(' -> H0: Model regresji nie jest istotny statystycznie.\n');
end


% test jarque-bera
Se = sqrt(sum(E.^2)/n);

B1 = (sum(E.^3))/(n*(Se^3));
B2 = (sum(E.^4))/(n*(Se^4));

%statystyka testowa
JB = n*(((B1^2)/6)+(((B2-3)^2)/24));
fprintf('\n- test Jarque-Bera: wartosc statystyki testowej %f\n', JB );

pch = 1 - chi2cdf(JB,2);
if (pch < alfa)
    fprintf(' -> H1: Model nie ma rozk³adu normalnego.\n');
else
    fprintf(' -> H0: Model ma rozk³ad normalny.\n');
end


%Test Goldfelda-Quandta
n1 = 11; n2 = n - n1;

S_n1 = std( E( 1:n1 ) )^ 2;
S_n2 = std( E( n1+1: end ))^2;

% statystyka testowa
F_n = ( ( n1 - 1 )*( n2 - k - 1 )* S_n1 )/( ( n2-1 )*( n1-k-1 ) * S_n2);
fprintf('\n- test Goldfelda-Quandta: wartosc statystyki testowej %f\n', F_n );

%poziom odrzucenia hipotezy zerowej
pFF = 1 - fcdf( F_n, ( n1 - 1 ),( n2 - k - 1 ) );
if (pFF < alfa)
    fprintf(' -> H1: Wariancje nie s¹ równe.\n');
else
    fprintf(' -> H0: Obie wariancje s¹ równe.\n');
end


%Test Lagrange
X_l = [ dataSetX( 2:18, : ), E(1:17,:) ];
Y_l = E( 2:18,: )';

D_l = inv( X_l' * X_l );
B_l = D_l * X_l' * Y_l';

E_l = Y_l' - X_l * B_l;

[n_l, k_l] = size(X_l);
k_l = k_l - 1;


S2_e_l = (1 / (n_l - k_l - 1)) * E_l' * E_l;

% blad szacunku parametru
S_b_l = sqrt( S2_e_l * diag(D) );

% wspolczynnik determinacji
R2_l = 1 - (( E_l' * E_l ) / ( ( Y_l * Y_l' ) - ( n * mean(Y_l)^2 ) ) );
fprintf('- wspolczynnik determinacji: %f\n', R2_l);

% statystyka testowa
F_l = (n_l - 1) * R2_l;
fprintf('- chi statystyka testowa: %f\n', F_l);

pFL = 1 - chi2cdf(F_l, 1);
if (pFL < alfa)
    
    fprintf(' -> H1: Model regresji jest istotny statystycznie.');
    t_l = B_l./S_b_l;
    ptL = 2 * min( tcdf(t_l,(n_l - k_l - 1)), 1 - tcdf(t_l,(n_l - k_l - 1)));
else
    
    fprintf(' -> H0: Model regresji nie jest istotny statystycznie.');
end

