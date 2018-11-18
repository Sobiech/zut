function y = lab0102(x)

b_m = trojkat(x, 0, 0, 1000);
m  = trojkat(x, 0, 1000, 3000);
sr = trojkat(x, 1000, 3000, 5000);
d = trapez(x, 3000, 5000, 7000, 9000);
bd = trapez(x, 5000, 9000, 10000, 10000);

suma = b_m + m + sr + d + bd;

if suma==0
   y = 0;
else 
   y = (b_m + m*100 + sr*200 + d*300 + bd *500)/suma;
end