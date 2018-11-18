clear all;
x=[0:1000:10000];

k=1;
for x1=x
    ys(k) = lab0102(x1);
    k = k+1;
end

plot(x,ys);
