function [newpop,xcount] = xover(pop,px,xtype)
% XOVER krzyzowanie osobnikow z zadanym prawdopodobienstwem
% newpop	-- nowa populacja
% xcount -- liczba krzyzowan
% pop	-- populacja chromosomow
% px	-- prawdopodobienstwo krzyzowania
% xtype	-- typ krzyzowania
%	-- 1) 1-punktowe
%	-- 2) 2-punktowe
%	-- 3) jednorodne


[popsize,ndim] = size(pop);
halfpop = popsize/2;
xcount = 0;
%
% petla po chromosomach usali czy beda sie one krzyzowaly
% i jaki typ krzyzowania zastosowac
%

if xtype == 1
	for i = 1:halfpop
		x = (i*2) - 1;
		xpo = x + 1;
		newpop(x,:) = pop(x,:);
		newpop(xpo,:) = pop(xpo,:);
        if (rand < px) 
			xcount = xcount + 1;
			xpoint = round((rand * ndim)+0.5);
			newpop(xpo,xpoint:ndim)=pop(x,xpoint:ndim);
			newpop(x,xpoint:ndim) = pop(xpo,xpoint:ndim);	
        end
	end
end
%
% krzyzowanie dwupunktowe
%

if xtype == 2
	for i = 1:halfpop
		x = (i*2)-1;
		xpo = x+1;
		newpop(x,:) = pop(x,:);
		newpop(xpo,:) = pop(xpo,:);
        if (rand < px)
			xcount = xcount + 1;
 			[xpoint] = sort(round((rand(1,2) * ndim)+0.5));
			newpop(xpo,xpoint(1):xpoint(2)) = pop(x,xpoint(1):xpoint(2));
			newpop(x,xpoint(1):xpoint(2)) = pop(xpo,xpoint(1):xpoint(2));
		end
	end
end
%
% krzyzowanie jednorodne
%

if xtype == 3
	for i = 1:halfpop
		x = (i*2)-1;
		xpo = x+1;
		newpop(x,:) = pop(x,:);
		newpop(xpo,:) = pop(xpo,:);
		if rand < px
			xcount = xcount + 1;
            for j = 1:ndim
                if rand < 0.5
    			    newpop(xpo,j) = pop(x,j);
    			    newpop(x,j) = pop(xpo,j);
                end    
            end
		end
		
	end
end

