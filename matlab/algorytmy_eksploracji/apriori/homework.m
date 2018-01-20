clc
clear all
close all

format long

load transactions.mat

minConfV = [0.6,0.8,0.9];
minSuppV = [0.2,0.15,0.1];
graphIndex = 1;

for i =1:length(minConfV)
    
    for j = 1:length(minSuppV)
        
        minConf = minConfV(i);
        minSupp = minSuppV(j);
        
        graphTitle = [ ' minConf: ' num2str(minConf) ', minSupp:' num2str(minSupp)];
        tic
        fprintf('\n');
        disp(['Konfiguracja -> ' graphTitle]);
        fprintf('--------------------------------------------\n');
        subplot( 3, 3, graphIndex);
        title(graphTitle)
        hold on;
        
        A = ones(1, 1000);
        B = linspace(0, 1, 1000);
        
        plot( B, A * minConf, 'black:' );
        hold on;
        
        plot( A * minSupp, B, 'red:');
        hold on;
        
        fprintf('- szukam zbiorow czestych\n');
        F = frequentSets(dataSet, minSupp);
        
        fprintf('- generuje reguly\n');
        [ txRules, txResults, confSet ] = generateRules(F, minConf, length(dataSet));
        
        cellLength = length(txRules);
        graphIndex = graphIndex + 1;
        fprintf('- wyszukuje rownowaznosci\n');
        findEquivalenceRules(txRules, txResults, confSet, labelMapper);
        fprintf('--------------------------------------------\n');
        
        toc
        fprintf('\n');
    end
end


