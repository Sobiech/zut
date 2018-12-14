function vars = decodeb(pop,range,ffunc,len)
% DECODEB -- konwertuje binarny chromosom do wartosci rzeczywistych
% dla kazdej zmiennej
% pop:		populacja binarnych chromosomow
% maxval:	maksymalny rzeczywisty zakres dziedziny
% minval:	minimalny rzeczywisty zakres dziedziny
% vars:		macierz zmiennych rzeczywistych (popsize,lzmiennych)

[popsize,lchrome] = size(pop);

maxintval(1) = (2^len(1))-1;
maxintval(2) = (2^len(2))-1;

minval = range(1, ffunc);
maxval = range(2, ffunc);

rangeg = maxval-minval;

for i = 1:popsize
	
    %obliczenie wartosci dziesietnej z binarnego ciagu
    sum = [0 , 0];
    for j = 1:len(1)
        sum(1) = sum(1) + 2^(len(1) - j) * pop(i,j);
    end    
    for j = len(1)+1:(len(1)+len(2))
        sum(2) = sum(2) + 2^(len(1) + len(2) - j) * pop(i,j);
    end    

    
    %skalowanie do zakresu dziedziny
	vars(i,1) = minval + (sum(1) * (rangeg/maxintval(1)));	
    vars(i,2) = minval + (sum(2) * (rangeg/maxintval(2)));

end

