% wejscie - D - zbior danych ( DL ) , domais - rozmiary poszczegolnych
% dziedzin

% wyjscie 
%  PY - prawdodopodobienstwo klasyfikacji PY ( 1 wymiarowe )
%  P - ( 3 wymiarowe )
%  useLaplace - 0 - nie , 1 - tak
function [ PY, P ] = bayes( D,  domains, useLaplace )

    [m,n] = size(D);
    
    % n liczba x'ow
    n = n - 1;
    
    % unikalna liczba klas ( ilosc nie liczba )
    K = length ( unique ( D( :, end) ) );
    
    PY  = zeros ( 1, K );
    
    
    for y = 1 : K 
    
        PY ( y ) = length ( find ( D ( :, end ) == y ) ) / m;
        
    end
    
    %max domains wielkosc maksymalna dziedziny
    P = zeros ( n, max(domains), K );
    
    for i = 1 : m 
        
        x = D ( i, 1 : n );
        y = D ( i, n + 1 );
        
        for j = 1 : n
           P ( j, x(j), y ) = P (j, x(j), y) + 1; 
        end
    end     
  

    if ( useLaplace == 0 )
        
        for y = 1 : K 
            
            P(:,:,y ) = P ( :, : , y ) / ( m * PY(y) );
            
        end
        
    else
        for y = 1 : K

            for i = 1 : n
            
                P(i,:,y) = ( P ( i, :, y ) + 1 ) / ( m * PY(y) + domains(i));
                
            end
            
        end
        
    end
        
    
end