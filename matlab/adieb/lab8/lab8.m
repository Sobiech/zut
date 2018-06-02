clc
clear all
close all

% srednie wartosci
% macierze kowariancji normalizowane dla kazdej klasy 
% prawdopodobienstwa ze nasze x nalezy do jednej z klas
% jezeli mam 14 obiektow 3 klasy macierz P 14-3 
% liczymy funkcje wiarygodnosci na bazie okreslonego bledu 


delta = 10^-4;


X = [ 
    27 11 25 36 35 10 11 36 26 26  9 33 27 10
    19 46 15 27 25 43 44 24 14 14 45 23 16 47
]';

[n,b] = size(X);
q = n*b;
k = 3;

M = [
    10 25 35;
    45 20 25 
]';

W = ones ( k, 1 ) / k;
xCov = cov(X,1);

import java.util.*;
C = HashMap;
for key = 1 : k 
    C.put(key,xCov);
end



% mieszanka rozkladow normalnych
p = [,];
for i = 1:n
    
    for j = 1:k
        
        x_i = X(i,:);
        m_j = M(j,:);
        c_j = C.get(j);
        
        %odleglosc mahalanobisa
        d = ( x_i - m_j ) * inv(c_j) * ( x_i - m_j )';
        
        Cj_detSqrt = sqrt(det( c_j ));
        exp_d = exp( (-1/2) * d );
        
        p(i,j) =  exp_d / ( 2 * pi * Cj_detSqrt );
    end
end

% krok E
g = [,];
for i = 1 : n
   
    for j = 1 : k        
        licznik = W(j) * p(i,j);
        mianownik = sum(p(i,:) * W(j));
        g(i,j) = licznik / mianownik;
    end
    
end    

func_ll_sum = sum(log(p*W))
% funkcja wiarygodnosci
%func_ll_sum = 0;

% to samo  co ln. 72
%{ 
for i = 1 : n
    class_sum = 0;
    for j = 1 : k   
        class_sum = sum(p(i,:) * W(j));
    end
    
    func_ll_sum = func_ll_sum + log(class_sum);
end  
%}



