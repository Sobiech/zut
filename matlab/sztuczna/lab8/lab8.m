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
figure

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
figure
imagesc(literaDoTestow)
a = reshape(literaDoTestow, 42, 1);

% symulacja dzia³ania sieci dla wzorca a podanego na wejœcie
% (drugi parametr funkcji sim oznacza iloœæ iteracji – tu 20)
[y,pf,af] = sim(net, {1 20}, {}, a); 

%poka¿ wyniki
y1 = cell2mat(y)                    

figure
wyn = reshape(y1(: , end), 7, 6);                                    
imagesc(wyn)