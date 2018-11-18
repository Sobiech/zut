clear all;
x=[18:0.1:36];

k=1;
for x1=x
    ys(k) = lab01(x1);
    k = k+1;
end

plot(x,ys);
