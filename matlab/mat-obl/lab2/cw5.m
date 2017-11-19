clc
clear all
close all

format long

A = [ 1 1 1; 2 1 -1; 2 0 2];

[V,S] = eig(A);

detA = det(A);

B = inv(V) * A * V;
