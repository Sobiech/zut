clear all;
x=[0:0.1:16];

k=1;
for x1=x
    ys(k) = system1(x1);
    k = k+1;
end

plot(x,ys);
