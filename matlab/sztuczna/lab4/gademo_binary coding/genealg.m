function [stats,pop,elitechrome] = genealg(len, npop, pm, px, xtype, ffunc, elite, ngen, range);
% GENEALG -- wywoalanie poszczegolnych krokow algorytmu genetycznego
% len   : dlugosc chromsomu (wektor - dla kazdej zmiennej)
% npop  : licznosc populacji
% pm    : prawdopodobienstwo mutacji
% px    : prawdopodobienstwo krzyzowania
% xtype : typ krzyzowania
% elite : czy wykorzystany elitaryzm
% ffunc : jaka funcja przystosowania
% ngen  : liczba generacji
% rage  : dziedzina zmiennych
% stats : statystyki do tworzenia zestawien
% pop   : populacji wynikowa
% elitchrome: najlepszy chromosom
%
%
fprintf('** Rozpoczecie optymalizacji ** \n');
fprintf('#:  Srednia \t     O.Stand. \t    Najlepszy \t #krz \t #mut \n');

ndim = len(1)+len(2); %dl chromosomu
popsize = npop;
numgen = ngen;
numxover = 0; %liczba krzyzowan
nummut = 0;   %liczba mutacji

%
% W przypadku elityzmu wielkosc populacji zwieksza sie o 1.
%
if elite == 1
	popsize = popsize + 1;
end
%
% losowa inicjalizacji populacji
%
pop = [(rand(popsize,ndim)<0.5)];
%
% Optymalizuj dla zadanej liczby generacji
%
for cgn = 1:numgen
%
% Obliczenie wartosci funkcji przystosowania	
%
    scores = fitness(pop,ffunc,range,len);
%    
% Wypisuwanie populcji i przystosowania kazdego
% chromosomu w trakcie ewolucji
% 
%		for j = 1:popsize
%			fprintf('%d',pop(j,1:ndim));
%			fprintf('\t %8.3f',scores(j));
%			fprintf('\n');
%		end

    [bestfit,besti] = min(scores);
	ms = mean(scores);
	sd = std(scores);
	fprintf('%i: %8.2f \t \t %8.2f \t \t %8.2f \t ', cgn, ms, sd, bestfit);
	stats(cgn,:) = [cgn ms sd bestfit nummut numxover];
%
%	przepisz wartosci najlepszego chromosomu w populcji
%
	elitechrome = pop(besti,:);

%
%	wybieranie nowej populacji z zastosowaniem turnieju bez dyskryminatora
%	stosowanie elitaryzmu jezeli wlaczone
%
	mate = tselect(pop,scores,elite);
%
%	przeprowadzenie krzyzowania
%
	[child,txnum] = xover(mate, px, xtype);
	numxover = numxover + txnum;
%
%	przeprowadzenie mutacji
%
	[child,tnmut] = mutate(child,pm);
	nummut = nummut + tnmut;
   	fprintf('%d \t \t %d \n', txnum, tnmut);
%
% 	Pokolenie dzieci + chromosom elitarny tworza nowe pokolenie
%
	pop = child;
	if elite == 1
		pop(popsize,:) = elitechrome;
	end
%
end
