% http://wikizmsi.zut.edu.pl/wiki/AED/LN/z2

clc
clear all
close all

format long

load transactions.mat

minData = [
    0.2 0.6;
    0.2 0.8;
    0.2 0.9;
    
    0.15 0.6;
    0.15 0.8;
    0.15 0.9;
    
    0.1 0.6; 
    0.1 0.8; 
    0.1 0.9
];

suppMap = containers.Map;
X = [];
tic
for matrixIndex = 1 : length(minData)
    
    minSupp = minData(matrixIndex, 1); 
    minConf = minData(matrixIndex, 2);
    
    str = ['Obliczam reguly dla minSupp=' num2str(minSupp) ', minConf=' num2str(minConf)];
    disp(str);
    
    F = frequentSets(dataSet, minSupp);
    gen_rules = hmGenerateRules( F, minConf );
    X = [ X gen_rules ];
    for i = 1 : length (gen_rules)
        str = gen_rules(i);
        splited = strsplit(str,';');
        strRepo = strrep(splited,'  ',',');
        disp(strRepo);
    end
    
end
fprintf('\n');
disp('Algorithm done in:');

toc

