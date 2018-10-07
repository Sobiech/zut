function mi=trapez(x, a, b, c, d)

if x<a
   mi = 0;
end
if x>d
   mi = 0;
end
if x>=a & x<=b
   if a~=b
      mi = (x-a)/(b-a);
   else
      mi = 1;
   end
end
if x>b & x<=c
   mi = 1;
end
if x>c & x<=d
   if c~=d
      mi = (x-d)/(c-d);
   else
      mi = 1;
   end
end