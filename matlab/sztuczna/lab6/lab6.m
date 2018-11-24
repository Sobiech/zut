clear all;

% funkcje aktywacji
% purelin [lin] / logsig [sig] / tansig [hiper]


% Testy
% XOR: logsig/logsig: 2.7267e-08, epochs: 9 
% parity: logsig/purelin: 4.3137e-21, epochs: 651
% build: tansig/logsig: 1.4841e+05, epochs: 7
% glass: logsig/logsig: 0.0.0125, epochs: 58
% diabet: purelin/purelin: 0.1583, epochs: 5


load build_i.txt
load build_o.txt

we = build_i';
wy = build_o';

% 2 neurony w warstwie ukrytej
% 1 w warstwie wyjsciowej
net = newff(minmax(we), [5 3], {'tansig', 'logsig'});

figure(1)

y = sim(net, we);
% na czerwono - b³¹d przed uczeniem
plot(abs(y-wy),'r'); hold on;

net.trainParam.epochs = 10000;
net = train(net, we, wy);

y1 = sim(net, we);
% na zielono - b³¹d po uczeniu
plot(abs(y1-wy),'g'); 
hold off; 

figure(3)
plot(1:length(we),wy,'ob', 1:length(we),y,'r', 1:length(we),y1,'xg');

% na niebiesko - wyjscia zadane
% na czerwono - wyjscia sieci przed uczeniem
% na zielono - wyjscia sieci po uczeniu
perform(net,wy,y1)
