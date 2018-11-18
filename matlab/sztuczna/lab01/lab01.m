function y = lab01(x)

b_m = trojkat(x, 0, 0, 19);
m  = trapez(x, 18, 21, 24, 25);
sr = trapez(x, 23, 25, 28, 30);
d = trapez(x, 29, 31, 36, 36);

suma = b_m + m + sr + d;

if suma==0
   y = 0;
else 
   y = (b_m*10 + m*40 + sr*70 + d*100)/suma;
end