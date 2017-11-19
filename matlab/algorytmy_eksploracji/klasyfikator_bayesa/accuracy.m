function [ acc ] = accuracy( D, PY , P )

    acc = 0;
    
    for i = 1 : size ( D, 1 ) 
        
        x = D ( i, 1 : end - 1 );
        y = D ( i , end );
        
        if classify ( x, PY, P ) == y
            acc = acc + 1;
        end
        
    end
    
    acc = acc / size ( D, 1 );
end

