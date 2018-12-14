function graph(ffunc,swich,realvars,bestscore)
% GRAPH -- rysuje wykres podanej funkcji
% ffunc = numer funkcji z listy
%
%
fig = figure('Units','Normal','Position',[.25 .25 .5 .5], ...
              'Name','GA Demo',...
              'NumberTitle','off','Tag','GA Demo');

%Ustalenie zakresu zmiennych
range = [-100, -10, 0, -10, -500;...
          100, 10, pi, 10, 500]; %zakresy dziedzin dla funkcji


%Wygenerowanie macierzy X i Y do wykresu 3D
gap = range(2,ffunc) - range(1,ffunc);
[X,Y] = meshgrid(range(1,ffunc):(gap/50):range(2,ffunc));

%
% Bohachevsky
%
if ffunc == 1 
	  
	Z = ((X.^2) + (2 .* (Y.^2)) - (0.3 .* (cos(3 .* pi .* X))) - (0.4 .* (cos(4 .* pi .* Y))) + 0.7);
%
% Rosenbrock
%
elseif ffunc == 2
    
	Z = (100.*(X.^2 - Y).^2 + (X - 1).^2);

%
% Michalewicz
%
elseif ffunc == 3   

        m=10;
        
		Z = - (sin(X).*(sin(X.^2./pi)).^(2*m) + sin(Y).*(sin(2.*Y.^2./pi)).^(2*m));

%
% Easom
%
elseif ffunc == 4   

		Z = - cos(X).*cos(Y).*exp(-(X-pi).^2-(Y-pi).^2);
%
% Schwefel function
%
elseif ffunc == 5   
  
		Z =  418.9829*2 - (X.*sin(sqrt(abs(X))) + Y.*sin(sqrt(abs(Y))));
	 
     
end

if swich == 0
    surf(X,Y,Z);  
    alpha(0.4);
else
    contour(X,Y,Z);
    hold on;
    plot3(realvars(1), realvars(2), bestscore,'mo');
    hold off;
end    
