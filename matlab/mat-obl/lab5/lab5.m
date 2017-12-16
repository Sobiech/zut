clc
clear all
close all

%f1 = @(x,y) (x.^2)-(y.^2)-1;
%f2 = @(x,y) (x.^3).*(y.^2)-1;
%x = 1:0.1:3;
%y = -3:0.1:3;
f1 = @(x,y) y-(1/2)*(exp(x/2)+exp(-x/2));
f2 = @(x,y) (9.*x.^2)+(25.*y.^2)-225;
x = -6:0.1:6;
y = -6:0.1:6;

h = 0.01;

[osX, osY] = meshgrid(x, y);
[c,d] = contour(osX, osY, f1(osX, osY));
clabel(c,d);
hold on;

[osX, osY] = meshgrid(x, y);
[c,d] = contour(osX, osY, f2(osX, osY));
clabel(c,d);
hold on;

x0 = 1;
y0 = 4;

poch1_f1 = [];
poch2_f2 = [];

while 1
   %licze pochodne dla kazdje z funkcji
   poch1_f1 = [(f1(x0+h,y0)- f1(x0-h,y0))/(2*h),(f1(x0,y0+h)-f1(x0,y0-h))/(2*h)];
   poch1_f2 = [(f2(x0+h,y0)- f2(x0-h,y0))/(2*h),(f2(x0,y0+h)-f2(x0,y0-h))/(2*h)]; 
   %macierz Jacobiego (chyba)
   J = [poch1_f1(1) poch1_f1(2); poch1_f2(1) poch1_f2(2) ];
   %wyliczenie drugiego punktu algorytmu
   fxy = [f1(x0,y0);f2(x0,y0)];
   %wyznacznie d
   d = J\fxy;
   %wyznaczenie kolejnego oszacowania rozwi¹zania
   x1 = x0 - d(1);
   y1 = y0 - d(2);
   
   %warunek stopu
   if (abs(d(1))<h && abs(d(2))<h)
        break;
   end
   %aktualizuje punkty
   x0 = x1;
   y0 = y1;
   
end

x = x0
y = y0

hold on

plot(x,y,'r*')
