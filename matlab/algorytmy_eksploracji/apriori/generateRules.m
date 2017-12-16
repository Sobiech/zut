
% zadanie domowe / zrobic wykresy regulowe
% labelMappery mapowanie liczb do wartosci danych elementow (string)
% formatowanie
function [ ] = generateRules( F, minConf, labelMapper )
    
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
                        str1 = '';
                        str2 = '';
                        
                        diff = setdiff(f,g);
                        for y = 1 : length(g)
                            str1 = [ str1 ' ' labelMapper.get(num2str(g(y))) ]; 
                        end
                        
                        for z = 1 : length(diff)
                            str2 = [ str2 ' ' labelMapper.get(num2str(diff(z))) ];
                        end
                        
                        disp([str1 ' -> ' str2 ' conf = ' num2str(conf)]);
                    end
                end
            end
        end
    end
    
end

