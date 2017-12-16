clc
clear all
close all

format long

% pobranie danych
D = domDataSet('messidor_features.arff');
[maxRn,~] = size(D);

[ PY , P ]   = bayes( D, 5 * ones(1, 20), 0 ); 
[ PLY , PL ] = bayes ( D, 5 * ones(1, 20), 1);

startSplit = 1;
randomSplit = randi([1 maxRn - 1]);


map = containers.Map;

i = 1;

while 1
    
    data = D ( startSplit : randomSplit, : );
    
    map(num2str(i)) = data;
    startSplit = randomSplit + 1;
    randomSplit = randomSplit .* 2;
    
    if startSplit >= size(D)
        break;
    end
    
    if randomSplit >= size(D)
        [randomSplit, ~] = size(D);
    end
    
    i = i + 1;
end

[max_rows,max_colls] = size(D);

mapSize = size(map,1);
ALL_ACC = [];

for i = 1 : mapSize

    DT = map(num2str(i));
    
    max_rows = max_rows - length(DT); 
    
    DL = [];
    
    for j = 1 : mapSize
        
        if j ~= i 
            data = map(num2str(j));
            DL = [ DL ; data ];
        end
    end
    
    [ acc ] = [ accuracy( DL, PY , P ) accuracy( DT, PY , P ) accuracy( DL, PLY , PL ) accuracy( DT, PLY , PL ) ];
    
    ALL_ACC = [ALL_ACC; acc];

end
% dokladnosc



% zadanie domowe 
% http://wikizmsi.zut.edu.pl/wiki/AED/LN/z1
% ostatni punkt zignorowaæ ( zrobiæ tylko 3 )

% k krotna krzy¿owa walidacja ( K - fold crossValidation )
% k > 1 liczba naturalna
% wynikiem k krotnej krzyzowej walidacji jest œrednia 
% paczek testowych accuracy






