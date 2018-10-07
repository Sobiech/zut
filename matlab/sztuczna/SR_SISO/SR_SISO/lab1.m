% http://wikizmsi.zut.edu.pl/wiki/Strona_g%C5%82%C3%B3wna strona

% http://wikizmsi.zut.edu.pl/wiki/MSI2/LJK

function y = lab1(x)

mi_mw_1 = trojkat(x, 18, 18, 25);
mi_w = trapez(x, 20,27, 35, 45);
mi_mw_2 = trapez(x, 35,50, 100, 100); 

suma = mi_mw_1 + mi_w + mi_mw_2;

if suma==0
   y = 0;
else 
    
   y = /suma;
end
