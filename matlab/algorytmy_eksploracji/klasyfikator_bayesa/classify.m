
% klasyfikacja konkertnego wektora
% wejscie PY, P - naiwny klasyfikator bayesa
function [ y ] = classify( x, PY, P )
    
    results = PY;

    for y = 1 : length ( PY )
        for i = 1 : size(P, 1)
        
            results(y) = results(y) * P ( i, x(i), y ); 

        end
    end
    
    % tylda pochlania wynik
    [~, y] = max(results);
    
end

