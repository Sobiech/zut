clc
clear all
close all

format long

n = 2;
A = [1 2; 0 3];
B = [1; 0];

Ws = [B A*B];

dataToDisplay = sprintf('Zad1 -> 1. Uk³ad nie jest sterowalny rank:  %d!=%d / n.', rank(Ws), n);
disp(dataToDisplay)

n = 3;
A1 = [-1 2 -3; 2 2 -6; -1 -2 1];
B1 = [1; -1; 0];

Ws = [B1 A1*B1 (A1^2)*B1];
dataToDisplay = sprintf('Zad1 -> 2. Uk³ad nie jest sterowalny rank:  %d!=%d / n.', rank(Ws), n);
disp(dataToDisplay)

b = [1 -2 -20 -24];
a = [-2 -4];
tf1 = tf2ss(a,b);


n = 3;
b = [1 -2 -20 -24];
a = [-2 -4];
[A, B] = tf2ss(a,b);
Ws = [B A*B A^2*B];
dataToDisplay = sprintf('Zad1 -> 3. Uk³ad jest sterowalny rank:  %d==%d / n.', rank(Ws), n);
disp(dataToDisplay);

%Zadanie 2
%%
A = [1 -1; 0 2];
B = [0; 1];
C = [1 -1];

n = 2;
Ws = [B A*B];
W0 = [C' A' * C'];
dataToDisplay = sprintf('\nZad2 -> 1. Uk³ad jest sterowalny rank:  %d==%d / n.', rank(Ws), n);
disp(dataToDisplay);
dataToDisplay = sprintf('Zad2 -> 1. Uk³ad jest obserwowalny rank:  %d==%d / n. ', rank(W0), n);
disp(dataToDisplay);


A = [3 -8; 2 -5];
B = [1; 1];
C = [-2 3];

n=2;
Ws = [B A*B];
W0 = [C' A' * C' ];
dataToDisplay = sprintf('\nZad2 -> 2. Uk³ad jest sterowalny rank:  %d==%d / n.', rank(Ws), n);
disp(dataToDisplay);
dataToDisplay = sprintf('Zad2 -> 2. Uk³ad jest obserwowalny rank:  %d==%d / n. ', rank(W0), n);
disp(dataToDisplay);

n = 3;
b = [1 -2 -20 -24];
a = [-2 -4];
[A, B, C] = tf2ss(a,b);
Ws = [B A*B A^2*B];
W0 = [C' A' * C' A'^2 * C' ];
dataToDisplay = sprintf('\nZad2 -> 3. Uk³ad jest sterowalny rank:  %d==%d / n.', rank(Ws), n);
disp(dataToDisplay);
dataToDisplay = sprintf('Zad2 -> 3. Uk³ad nie jest obserwowalny rank:  %d!=%d / n. ', rank(W0), n);
disp(dataToDisplay);
