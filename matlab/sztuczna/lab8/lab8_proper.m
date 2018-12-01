clc
clear all
close all

%L
m = [1 -1 -1  -1;
    1  -1 -1 -1;
    1  -1 -1 -1;
    1   1  1  -1];

%Z
m2 = [1   1  1  1;
     -1  -1  1 -1;
     -1   1 -1 -1;
      1   1  1  1];

%C
m3 = [1 1 1  -1;
    1  -1 -1 -1;
    1  -1 -1 -1;
    1   1  1  -1];

%H
m4 = [1 -1  -1  -1;
      1  1  1  -1;
      1  -1 1  -1;
      1 -1  1  -1];

%F
m5 = [1 1 1  -1;
      1  -1  -1 -1;
      1  1 -1 -1;
      1 -1  -1  -1];
  
%1
m6 = [-1 -1 -1  1;
      -1  -1  1 1;
      -1  -1 -1 1;
      -1 -1  -1  1];
  
%9
m7 = [-1   1  1  1;
      -1   1 -1 1;
      -1   1  1  1;
      -1  -1 -1  1];
  
%K
m8 = [1   -1  1  -1;
      1   1  -1  -1;
      1   1  -1  -1;
      1   -1  1  -1];
  
  
%O
m9 = [1    1   1  1;
      1   -1  -1  1;
      1   -1  -1  1;
      1    1   1  1];
  
%U
m10 = [1  -1  1  -1;
      1   -1  1  -1;
      1   -1  1  -1;
      1    1  1  -1];

imagesc(m3)

%m = [1 -1 1; -1 1 1];              %macierz z wzorcami
%m = m';                            %ka¿da kolumna stanowi jeden wzorzec


M1 = reshape(m, 16, 1);
M2 = reshape(m2, 16, 1);
M3 = reshape(m3, 16, 1);
M4 = reshape(m4, 16, 1);
M5 = reshape(m5, 16, 1);
M6 = reshape(m6, 16, 1);
M7 = reshape(m7, 16, 1);
M8 = reshape(m8, 16, 1);
M9 = reshape(m9, 16, 1);
M10 = reshape(m10, 16, 1);

m = [ M1 M2 M3 M4 M5 M6 M7 M8 M9 M10 ];


net = newhop(m);                    % tworzymy sieæ i wyznaczamy jej wagi
w = net.LW{1,1}                     % wagi sieci (dla ciekawskich)
b = net.b{1,1}                      % wagi wejœæ progowych (dla ciekawskich)
[y,pf,af] = sim(net, 10, {}, m)      % symulacja dzia³ania sieci dla wzorców podanych na wejœcie
                                    %(drugi parametr funkcji sim oznacza iloœæ wzorców, zmienne
                                    %pf i af s¹ nieistotne)
y'                                  %poka¿ wynik
%a = {[0.9; 0.1; 0.8]};              %testujemy wejœcie ró¿ne od zapamiêtanego wzorca
a =  [-1 -1 1  1;
      -1  -1  1 1;
      -1  1 -1 1;
      -1 -1  -1  1];
  

imagesc(a)
a = reshape(a, 16, 1);


[y,pf,af] = sim(net, {1 20}, {}, a); %symulacja dzia³ania sieci dla wzorca a podanego na wejœcie
                                    %(drugi parametr funkcji sim oznacza iloœæ iteracji – tu 20)
y1 = cell2mat(y)                    %poka¿ wyniki
%plot3(m(1,:), m(2,:), m(3,:), '*'); %wykreœl punkty odpowiadaj¹ce wzorcom
%hold on
%plot3(y1(1,:), y1(2,:), y1(3,:));   %wykreœl trajektoriê przejœcia sieci od obrazu podanego na
                                    %wejœcie do najbardziej podobnego obrazu zapamiêtanego

wyn = reshape(y1(: , end), 4, 4);                                    
imagesc(wyn)                                 
%hold off