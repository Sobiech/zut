function gademo(len, npop, ngen, pm, px, xtype, elite, ffunc)
% GADEMO -- funcja uruchamiajaca AG dla podanych parametrow
% len   : dlugosc chromsomu (wektor - dla kazdej zmiennej)
% npop  : licznosc populacji
% ngen  : liczba generacji
% pm    : prawdopodobienstwo mutacji
% px    : prawdopodobienstwo krzyzowania
% xtype : typ krzyzowania
% elite : czy wykorzystany elitaryzm
% ffunc : jaka funcja przystosowania
%
%
fig = figure('Units','Normal','Position',[.25 .25 .5 .5], ...
              'Name','GA Demo',...
              'NumberTitle','off','Tag','GA Demo');
%
%Zakresy dziedzin dla poszczegolnych funkcji ffun
%
range = [-100, -5, 0, -100, -500;...
          100, 10, pi, 100, 500]; 

%
% Start GA 
%
[stats,pop,bestc] = genealg(len, npop, pm, px, xtype, ffunc, elite, ngen, range);

if (ishold) == 1
	hold off;
end

%
% Narysuj i wypisz statystyki dotyczace procesu optymalizacji
%
plot(stats(:,1),stats(:,4),'r',stats(:,1),stats(:,2),'b')
title ('GA Statystyki');

ylabel ('Przystosowanie');
xlabel ('Generacja #');
legend ('Najlepszy','Srednia');

%
% Koncowe podsumowanie
%
fprintf('***** Statystyka koncowa ***** \n');

fprintf('Najlepszyy chromosom: ');
fprintf('%d',bestc(:));
fprintf('\n');

realvars = decodeb(bestc,range,ffunc,len);
for i = 1:2
       	fprintf('Zmienna # %d = %6.4f \n',i,realvars(1,i));
end

bestscore = fitness(bestc,ffunc,range,len);
fprintf('Wartosc przystosowania:  %8.4f \n', bestscore); 
graphics(ffunc, 1, realvars, bestscore);
fprintf ('Koniec \n');













