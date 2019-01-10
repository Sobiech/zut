function [ wyn ] = findLetter( net, a, y, pf, af )

    % symulacja dzia�ania sieci dla wzorca a podanego na wej�cie
    % (drugi parametr funkcji sim oznacza ilo�� iteracji � tu 20)
    [y,pf,af] = sim(net, {1 20}, {}, a); 

    %poka� wyniki
    y1 = cell2mat(y)                    

    wyn = reshape(y1(: , end), 7, 6);

end

