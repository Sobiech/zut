function mi=trojkat(x, a, b, c)

if a==b & x==a
   mi = 1;
   return;
end

if b==c & x==b
   mi = 1;
   return;
end

if x<=a
   mi = 0;
end
if x>=c
   mi = 0;
end
if x>a & x<=b
   mi = (x-a)/(b-a);
end
if x>b & x<c
   mi = (x-c)/(b-c);
end