% zadanie domowe / zrobic wykresy regulowe
% labelMappery mapowanie liczb do wartosci danych elementow (string)
% formatowanie
function [txRules, ruleResults, confSet] = generateRules( F, minConf, dataSetLength )
    
    colorSet = {' ','b.','g.','r.','p.','o.'};
    for k = 2 : length(F)
        
        txIndex  = 1;
        txRule   = 0;
        txResult = 0;
        
        confidence = 0;
        
        keys = F{k}.keySet().toArray();

        for i = 1 : length(keys)
            
            f = str2num(keys(i));
            for j = 1 : k -1
                subsets = nchoosek(f, j);
                for x = 1 : size(subsets, 1)
                   
                    g = subsets(x, :);
                    conf = F{k}.get(keys(i))/F{j}.get(num2str(g));
                    
                    if conf >= minConf
                        
                        diff = setdiff(f,g);
                        for y = 1 : length(g)
                            txRule(txIndex,y) = g(y);
                        end
                        
                        for z = 1 : length(diff)
                            txResult(txIndex,z) = diff(z);
                        end
                        
                        confidence(txIndex,:) = conf;
                        
                        txIndex = txIndex+1;
                        
                        supp = F{length(g)}.get(num2str(g))/dataSetLength;
                        scatter(supp, conf, colorSet{k});
                        hold on;
                    end
                end
            end
        end
        
        txRules{k-1}     = txRule;
        ruleResults{k-1} = txResult;
        confSet{k-1}     = confidence;
        
        txRule = [];
        txResult = [];
        confidence = [];
    end
end


