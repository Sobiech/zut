clear all;
x=[0:0.1:16];

k=1;
for x1=x
    mi_oz(k) = trojkat(x1, 0, 0, 1);
    mi_m(k)  = trojkat(x1, 0, 1, 4);
    mi_sr(k) = trojkat(x1, 1, 4, 8);
    mi_d(k)  = trapez(x1, 4, 8, 10, 14);
    mi_bd(k) = trapez(x1, 10, 14, 16, 16);
    k = k+1;
end

plot(x,mi_oz, x,mi_m, x,mi_sr, x,mi_d, x,mi_bd);
