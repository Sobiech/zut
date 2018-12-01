clc
clear all
close all

we = load('sincos_i.txt');
wy = load('sincos_o.txt');

we=we';
wy=wy';

% mieszanie probek %
len = length(we);

%permute
r = randperm(len); 

for i=1:len
    we_tmp(i) = we(r(i));
    wy_tmp(i) = wy(r(i));
end

% 70% uczacych
len70 = 1:len*0.7;

dane_uczace_wej = we_tmp(len70);
dane_uczace_wyj = wy_tmp(len70);

% 30%
we_tmp(1:len*0.7) = []; 
wy_tmp(1:len*0.7) = [];

dane_testowe_wej = we_tmp;
dane_testowe_wyj = wy_tmp;

blad_ucz=[];
blad_test=[];

for i = 1:1:10
    net = newff(minmax(we), [i 1], {'logsig', 'tansig'});
    net.trainParam.epochs = 100;
    
    net = train(net, dane_uczace_wej, dane_uczace_wyj);
    
    y = sim(net,dane_uczace_wej);
    blad_ucz(i) = mse(dane_uczace_wyj - y)
    
    y1 = sim(net,dane_testowe_wej);
    blad_test(i) = mse(dane_testowe_wyj - y1)
end

plot(1:1:10, blad_ucz);
hold on
plot(1:1:10, blad_test, 'r');