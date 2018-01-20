
% zbiory czeste 
% F - macierz komórkowa której elementy F{k} 
% bêd¹ mapami hashujacymi 

function [ F ] = frequentSets( D, minSupp )
    
    %licznosc
    minM = ceil( minSupp * length(D) );
    
    import java.util.*;

    F{1} = HashMap;
    
    % for po tranzakcjach
    for i = 1 : length(D)
        
        % for po tranzakacji
        for j = 1 : length(D{i})
            
            key = num2str(D{i}(j));
            if F{1}.containsKey(key)
                
                supVal = F{1}.get(key);
                F{1}.put(key, supVal + 1);
            else
                
                F{1}.put(key,1);
            end
        end
    end
    
    %po kluczach
    keys = F{1}.keySet().toArray();
    for i = 1 : length(keys)
        supp = F{1}.get(keys(i));
        if  supp < minM
           F{1}.remove(keys(i)); 
        end
    end
    
    k = 1;
    while F{k}.size() >= k + 1
%         disp('---------------------------------');
%         disp(['k: ' num2str(k)]);
        C = HashMap;
        %sklejanie par
        keys = F{k}.keySet().toArray();
        
        %petle po kluczach
        nums = zeros(length(keys), k);
        %klucze do tablicy
        for i = 1 : length(keys)            
            nums(i, :) = str2num(keys(i));
        end
        
        for i = 1 : length(keys)
            for j = i + 1 : length(keys)
                
                numij = union ( nums(i, :), nums(j, :) );
                
                if length(numij) > k + 1
                    continue;
                end
                    
                key = num2str( numij );
                
                % ~ negacja
                if ~C.containsKey(key)
                    C.put(key, 1);
                end
                
            end
            
        end
        
%         disp('candidates done');
        
        if k >= 2
            
            keys = C.keySet().toArray();
            
            for i = 1 : length(keys)
                
                num = str2num( keys(i) );
                subsets = nchoosek ( num, k );
                
                for j = 1 : size(subsets,1)
                    
                    key = num2str( subsets(j,:) );
                    if ~F{k}.containsKey(key)
                        
                        C.remove( keys(i) );
                    end
                end 
            end
            
%             disp('first removal done');
        end
        
        % kandydaci
        keys = C.keySet().toArray();
        
        for i = 1 : length(D)
            
            for j = 1 : length(keys)
                
                num = str2num (keys(j));
                %condition = length(intersect(num, D{i})) == k + 1
                condition = ( sum( ismember( num, D{i} ) ) == k + 1 );
                if condition
                    
                    supp = C.get(keys(j));
                    C.put(keys(j), supp + 1);
                end
            end
        end
%         disp('counting supports done');
        
        keys = C.keySet().toArray();
        
        for i = 1 : length(keys)
            
            if C.get(keys(i)) < minM
                
                C.remove( keys(i) );
            end
            
        end
%         disp('second removal done');
        
        F{k+1} = C;
        k = k + 1;
    end
end

