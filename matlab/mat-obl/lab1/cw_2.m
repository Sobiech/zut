
w1(1)=0;
w2(1)=0;

kn(1)=0;
single n1;
single n2;

for k = 4:10
   
    x = 10.^k;
    kn(k-3)=k;
    
    n1 = ( x - sqrt(1 + x.^2) )
    n2 = ( -1 ) / ( x + sqrt(1 + x.^2) )
    w2(k-3) = n2;
    w1(k-3) = n1;
    
end

