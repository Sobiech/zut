clc
clear all
close all

% literki

% wyslac skrypt i 4 obrazki
% 1 - wartosci wejsciowe
% 2 - wartosci nauczone / jak sie siec ich nauczyla
% 3 - wartosc wejsciowa / zaszumiona
% 4 - wartosc wynikowa

literaL = [ -1 -1 -1 -1 -1 -1;-1  1 -1 -1 -1 -1;-1  1 -1 -1 -1 -1;  -1  1 -1 -1 -1 -1;-1  1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaI = [ -1 -1 -1 -1 -1 -1;-1 -1  1 -1 -1 -1;-1 -1  1 -1 -1 -1;  -1 -1  1 -1 -1 -1;-1 -1  1 -1 -1 -1;-1 -1  1 -1 -1 -1;-1 -1 -1 -1 -1 -1];
literaE = [ -1 -1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1  1 -1 -1 -1 -1;  -1  1  1  1 -1 -1;-1  1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaF = [ -1 -1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1  1 -1 -1 -1 -1;  -1  1  1  1 -1 -1;-1  1 -1 -1 -1 -1;-1  1 -1 -1 -1 -1;-1 -1 -1 -1 -1 -1];
literaB = [ -1 -1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1  1 -1 -1  1 -1;  -1  1  1  1  1 -1;-1  1 -1 -1  1 -1;-1  1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaC = [ -1 -1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1  1 -1 -1 -1 -1;  -1  1 -1 -1 -1 -1;-1  1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaG = [ -1 -1 -1 -1 -1 -1;-1  1  1  1  1 -1;-1  1 -1 -1 -1 -1;  -1  1 -1  1  1 -1;-1  1 -1 -1  1 -1;-1  1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaH = [ -1 -1 -1 -1 -1 -1;-1  1 -1 -1  1 -1;-1  1 -1 -1  1 -1;  -1  1  1  1  1 -1;-1  1 -1 -1  1 -1;-1  1 -1 -1  1 -1;-1 -1 -1 -1 -1 -1];
literaJ = [ -1 -1 -1 -1 -1 -1;-1 -1 -1 -1  1 -1;-1 -1 -1 -1  1 -1;  -1 -1 -1 -1  1 -1;-1 -1  1 -1  1 -1;-1 -1  1  1  1 -1;-1 -1 -1 -1 -1 -1];
literaK = [ -1 -1 -1 -1 -1 -1;-1  1 -1 -1  1 -1;-1  1 -1  1 -1 -1;  -1  1  1 -1 -1 -1;-1  1 -1  1 -1 -1;-1  1 -1 -1  1 -1;-1 -1 -1 -1 -1 -1];

colormap('gray');
figure('NumberTitle', 'off', 'Name', 'Figure[1] - Letters base');

drawMatrix(literaL,1);
drawMatrix(literaI,2);
drawMatrix(literaE,3);
drawMatrix(literaF,4);
drawMatrix(literaB,5);
drawMatrix(literaC,6);
drawMatrix(literaG,7);
drawMatrix(literaH,8);
drawMatrix(literaJ,9);
drawMatrix(literaK,10);

M1 = reshape(literaL, 42, 1);
M2 = reshape(literaI, 42, 1);
M3 = reshape(literaE, 42, 1);
M4 = reshape(literaF, 42, 1);
M5 = reshape(literaB, 42, 1);
M6 = reshape(literaC, 42, 1);
M7 = reshape(literaG, 42, 1);
M8 = reshape(literaH, 42, 1);
M9 = reshape(literaJ, 42, 1);
M10 = reshape(literaK, 42, 1);

m = [ M1 M2 M3 M4 M5 M6 M7 M8 M9 M10 ];

% imagesc(literaK) / wyswietl macierz w formie obrazka

% tworzymy sieæ i wyznaczamy jej wagi
net = newhop(m);                    

% wagi sieci (dla ciekawskich)
w = net.LW{1,1}

% wagi wejœæ progowych (dla ciekawskich)
b = net.b{1,1}                      

% symulacja dzia³ania sieci dla wzorców podanych na wejœcie
[y,pf,af] = sim(net, 10, {}, m)      

% (drugi parametr funkcji sim oznacza iloœæ wzorców, zmienne
% pf i af s¹ nieistotne)

% poka¿ wynik
y'                                  

% I I uciete do testow
literaDoTestow = [ 
-1 -1 -1 -1 -1 -1;
-1  1 -1 -1  1 -1;
-1  1 -1 -1  1 -1;  
-1  1  1 -1  1 -1;
-1  1 -1 -1  1 -1;
-1  1 -1 -1  1 -1;
-1 -1 -1 -1 -1 -1
];

% testujemy wejœcie ró¿ne od zapamiêtanego wzorca
figure('NumberTitle', 'off', 'Name', 'Figure[2] -  Test letter with noise');
imagesc(literaDoTestow)
a = reshape(literaDoTestow, 42, 1);

figure('NumberTitle', 'off', 'Name', 'Figure[3] - TEST RESULT / Letter with noise');
imagesc(findLetter( net, a, y, pf, af ));

figure('NumberTitle', 'off', 'Name', 'Figure[4] - TEST RESULT / Letters base');
drawMatrix(findLetter( net, M1, y, pf, af ), 1);
drawMatrix(findLetter( net, M2, y, pf, af ), 2);
drawMatrix(findLetter( net, M3, y, pf, af ), 3);
drawMatrix(findLetter( net, M4, y, pf, af ), 4);
drawMatrix(findLetter( net, M5, y, pf, af ), 5);
drawMatrix(findLetter( net, M6, y, pf, af ), 6);
drawMatrix(findLetter( net, M7, y, pf, af ), 7);
drawMatrix(findLetter( net, M8, y, pf, af ), 8);
drawMatrix(findLetter( net, M9, y, pf, af ), 9);
drawMatrix(findLetter( net, M10, y, pf, af ), 10);

