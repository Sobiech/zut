clc
clear all
close all

format long

A  = [1 -1 2;2 1 -4;3 0 6];

wielomianA = poly(A);
wspXn = roots(wielomianA);
plot(real(wspXn), imag(wspXn), 'r');
