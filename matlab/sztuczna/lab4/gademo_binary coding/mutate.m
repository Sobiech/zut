function [newpop,nmut] = mutate(pop,pm)
% MUTATE mutacja chromosomow
% newpop = mutate(pop,pm)
% 
[popsize,ndim] = size(pop);
nmut = 0;
%
% petla przez populacje sprawdzajaca czy przeprowadzic mutacje
%
for i = 1:popsize
	for j = 1:ndim
		if rand < pm
			pop(i,j) = abs(pop(i,j)-1);
			nmut = nmut + 1;
		end
	end
end
%
% zwraca nowa populacje
%
newpop = pop;

		
