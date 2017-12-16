% http://wikizmsi.zut.edu.pl/wiki/AED/LN/z2

clc
clear all
close all

format long

load transactions.mat

tic

% whos
F = frequentSets(dataSet, 0.1);

toc