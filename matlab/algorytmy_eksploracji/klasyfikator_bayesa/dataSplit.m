% trainFunction  = 0.7
% pociecie macierzy na dwie odrebne macierze
% DL - dane uczace, DT - dane testowe

function [DL, DT ] = dataSplit( D, trainFraction)

    %m - ilosc wierszy , n - ilosc kolumn
    [m, n] = size(D);
    
    n = n -1;
    
    %przepisuje wszystkie kolumny / przy losowej permutacji dla wierszy
    D = D( randperm(m), :);
    
    split = round ( trainFraction * m );
    split
    DL = D ( 1 : split, : );
    DT = D ( split + 1 : m , : );
    
end

