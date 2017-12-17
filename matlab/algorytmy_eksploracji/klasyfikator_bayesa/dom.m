clc
clear all
close all

format long

% pobranie danych
D = domDataSet('messidor_features.arff');

[maxRn,colls] = size(D);

randomVector = randi(10,1,colls);

[ PY , P ]   = bayes( D, randomVector, 0 ); 
[ PLY , PL ] = bayes( D, randomVector, 1);

startSplit = 1;
randomSplit = randi([1 maxRn - 1]);
map = containers.Map;
i = 1;

while 1
    
    map(num2str(i)) = D ( startSplit : randomSplit, : );
    
    startSplit = randomSplit + 1;
    if  startSplit >= size(D)
        break;
    end
    
    randomSplit = randomSplit .* 2;
    if randomSplit >= length(D)
        randomSplit = length(D);
    end
    
    i = i + 1;
end

mapSize = size(map,1);
ALL_ACC = [];

for i = 1 : mapSize

    DT = map(num2str(i));    
    DL = [];
    
    for j = 1 : mapSize
        if j ~= i 
            data = map(num2str(j));
            DL = [ DL ; data ];
        end
    end
    
    ALL_ACC = [
        ALL_ACC; 
        accuracy( DL, PY , P ) accuracy( DT, PY , P ) accuracy( DL, PLY , PL ) accuracy( DT, PLY , PL )
    ];

end
% dokladnosc



% zadanie domowe 
% http://wikizmsi.zut.edu.pl/wiki/AED/LN/z1
% ostatni punkt zignorowaæ ( zrobiæ tylko 3 )

% k krotna krzy¿owa walidacja ( K - fold crossValidation )
% k > 1 liczba naturalna
% wynikiem k krotnej krzyzowej walidacji jest œrednia 
% paczek testowych accuracy






