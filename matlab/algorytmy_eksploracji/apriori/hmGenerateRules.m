
% zadanie domowe / zrobic wykresy regulowe
% labelMappery mapowanie liczb do wartosci danych elementow (string)
% formatowanie
function [ X ] = hmGenerateRules( F, minConf )
    
    X = [];

    for k = 2 : length(F)
    
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
                        
                        temp(1) = num2str(g); 
                        temp(2) = num2str(diff); 
                        temp(3) = num2str(conf);
                        
                        X = [ X temp ];
                    end
                end
            end
        end
    end
    
end

