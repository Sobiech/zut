function mateset = tselect(pop,scores,elite)
% TSELECT selecja metoda turnieju
%

[popsize,ndim] = size(pop);

%
% Jezeli wlaczony jest elitaryzm najlepszego wylacza sie z turnieju
%
if elite == 1
	popsize = popsize - 1;
end
%
% utworz losowy wektor numerow chromosomow do turnieju
%
randlist = [round(rand((popsize*2),1)*popsize+0.5)];
%
%  Rozpoczecie turnieju
%
count = 1;
for i = 1:popsize
%
%	2 losowo wybrane chromosomy beda walczyly o 
%	przejscie do nastepnej populacji
%
    if scores(randlist(count + 1)) < scores(randlist(count))
		mateset(i,:) = pop(randlist(count+1),:);
	else
		mateset(i,:) = pop(randlist(count),:);
	end
	count = count + 2;    
  
end
