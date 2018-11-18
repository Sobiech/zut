clear all;

% wyniki swojej pracy w komentarzu dopisaæ

% AND
% epochs = 5
% w1=2, w2=1, b=-3

% OR
% epochs = 3
% w1=1, w2=1, b=-1

% XOR
% epochs = 50
% w1=-1, w2=0, b=0 ( nieseparowalny )
% performence: 0.5 at epoch = 0

%we = [0 0 1 1; 0 1 0 1];
%wy = [0 1 1 0];


% zad 2

% Przypadek separowalny dwuwejœciowy:
% -> percep
% epochs = 15
% w1=1, w2=5, b=-18

% Przypadek nieseparowalny dwuwejœciowy
% -> dane_1
% epochs = 50
% w1=52.9214, w2=-37.2199, b=-62
% performance: 0.47

% Przypadek separowalny trzywejœciowy
% -> dane3d_a
% epochs = 143
% w1=35.8290, w2=-21.9810, w3=-55.5216, b=105

% Przypadek nieseparowalny trzywejœciowy
% -> dane3d_1
% w1=21.8554, w2=-23.4040, w3=-34.6563, b=-34.653
% performance=0.58

% Przypadek separowalny oœmiowejœciowy,
% -> dane8d_a
% epochs = 1610
% w1=37.7694, w2=45.1847, w3=35.6250, w4=44.0490, w5=43.4297, w6=47.9128,w7=36.2586,w8=31.7003
% b = -1588

% Przypadek nieseparowalny oœmiowejœciowy
% -> dane8d_1
% epochs=50
% w1=26.6190,w2=2.3566,w3=5.6455,w4=11.0617,w5=0.8822,w6=9.8071,w7=11.0988,w8=-9.1965
% b = -277
% perfomence: 0.58

load dane8d_1_i.txt;
load dane8d_1_o.txt;

%wejœcia musz¹ le¿eæ w wierszach macierzy!
we = dane8d_1_i'; 
wy = dane8d_1_o';

% pojedynczy neuron w perceptronie
net = newp(minmax(we), 1);

% symulacja dzia³ania sieci
y = sim(net, we); 

% wykres b³êdu
figure(1); plot(abs(y-wy)); 
title('Error before learning');

% ponowne losowanie wag pocz¹tkowych
net = init(net); 

% maksymalna iloœæ epok uczenia
net.trainParam.epochs = 2000; 

% uczenie sieci
net = train(net, we, wy); 

% symulacja dzia³ania sieci
y = sim(net, we); 

% wykres b³êdu sieci
figure(2); 
plot(abs(y-wy));
title('Error after learning');

% wagi na wejœciach neuronu
net.iw{1,1} 

% waga na wejœciu progowym
net.b{1} 

% wykres danych
figure(3); 
plotpv(we, wy); 

%linia podzia³u danych
plotpc(net.iw{1,1}, net.b{1}); 