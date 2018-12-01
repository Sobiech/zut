function scores = fitness(pop,ffunc,range,len)
% FITNESS oblicze funkcje przystosowania
% scores = fitness(pop,ffunc,range,len)
%
% scores:	kolumna wartosci funkcji dla chromosomow
% pop:		macierz chromosomow
% range:	zakres dziedziny dla poszczegolnych funkcji
% len:      wektor z dlugosciami ciagow przypadajacymi na poszczegolne zmienne

[popsize,lchrome] = size(pop);
%dekodowanie zmiennych 
vars = decodeb(pop,range,ffunc,len);

%
% Bohachevsky
%
if ffunc == 1 
	  
    for i = 1:popsize
		scores(i) = ((vars(i,1)^2) + (2 * (vars(i,2)^2)) - (0.3 * (cos(3 * pi * vars(i,1)))) - (0.4 * (cos(4 * pi * vars(i,2)))) + 0.7);
	end
 
%
% Rosenbrock
%
elseif ffunc == 2

	for i = 1:popsize
		scores(i) = (100*(vars(i,1)^2 - vars(i,2))^2 + (vars(i,1) - 1)^2);
	end
%
% Michalewicz
%
elseif ffunc == 3   

    m = 10;

    for i = 1:popsize
		scores(i) = - (sin(vars(i,1))*(sin(vars(i,1)^2/pi))^(2*m) + sin(vars(i,2))*(sin(2*vars(i,2)^2/pi))^(2*m));
	end  

%
% Easom
%
elseif ffunc == 4   

     for i = 1:popsize
		scores(i) = - (cos(vars(i,1))*cos(vars(i,2))*exp(-(vars(i,1)-pi)^2-(vars(i,2)-pi)^2));
	 end  
%
% Schwefel function
%
elseif ffunc == 5   
     for i = 1:popsize
		scores(i) =  418.9829*2 - (vars(i,1)*sin(sqrt(abs(vars(i,1)))) + vars(i,2)*sin(sqrt(abs(vars(i,2)))));
	 end  
     
 end
