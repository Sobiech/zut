function y = system1(x)

mi_oz = trojkat(x, 0, 0, 1);
mi_m  = trojkat(x, 0, 1, 4);
mi_sr = trojkat(x, 1, 4, 8);
mi_d  = trapez(x, 4, 8, 10, 14);
mi_bd = trapez(x, 10, 14, 16, 16);

suma = mi_oz + mi_m + mi_sr + mi_d + mi_bd;

if suma==0
   y = 0;
else 
   y = (mi_oz*3 + mi_m*4 + mi_sr*3 + mi_d*2 + mi_bd*1)/suma;
end
