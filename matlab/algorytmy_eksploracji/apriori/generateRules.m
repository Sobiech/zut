% zadanie domowe / zrobic wykresy regulowe
% labelMappery mapowanie liczb do wartosci danych elementow (string)
% formatowanie
function [Fr,To,Con] = generateRules( F, minConf, labelMapper, dataLength )
    
    C = {' ','m.','b.','r.','g.','y.'};
    for k = 2 : length(F)
        w = 1;
        from = 0;
        to = 0;
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
                        str1 = '';
                        str2 = '';
                        % zrobiæ tutaj k+1 kolumn z zaufaniem
                        diff = setdiff(f,g);
                        for y = 1 : length(g)
                            from(w,y) = g(y);
                            str1 = [ str1 ' ' labelMapper.get(num2str(g(y))) ]; 
                        end
                        
                        for z = 1 : length(diff)
                            to(w,z) = diff(z);
                            str2 = [ str2 ' ' labelMapper.get(num2str(diff(z))) ];
                        end
                        confidence(w,:) = conf;
                        w = w+1;
                        disp([str1 ' -> ' str2 ' conf = ' num2str(conf)]);
                        supp = F{length(g)}.get(num2str(g))/dataLength;
                        scatter(supp,conf,C{k});
                        hold on;
                    end
                end
            end
        end
        Fr{k-1} = from;
        To{k-1} = to;
        Con{k-1} = confidence;
        from = [];
        to = [];
        confidence = [];
    end
end


