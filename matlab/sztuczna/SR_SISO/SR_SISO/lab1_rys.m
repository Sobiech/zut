clear all;
x=[0:0.1:100];

k=1;
for x1=x
    mi_mw_1(k) = trojkat(x1, 18, 18, 25);
    mi_w(k) = trapez(x1, 20,27, 35, 45);
    mi_mw_2(k)= trapez(x1, 35,50, 100, 100); 
    k = k+1;
end

plot(x,mi_mw_1, x, mi_w, x,mi_mw_2);
