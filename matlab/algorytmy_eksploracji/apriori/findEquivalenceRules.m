function [ ] = findEquivalenceRules( txRules, txResults, confSet, labelMapper )
    
    for i = 1: length(txRules) - 1
        
        for j = 1: length(txRules{i})

            rule       = txRules{i}(j,:);
            result     = txResults{i}(j,:);
            
            for x = j: length(txRules{i})
                
                if txRules{i}(x,:) == result
                    
                    if txResults{i}(x,:) == rule
                        
                        ruleA        = '';
                        resultA      = '';

                        similarRuleA = '';
                        similarResA  = '';

                        for y = 1 : i
                            ruleA   = [ ruleA   ' ' labelMapper.get(num2str(txRules{i}(j,y)))];
                            resultA = [ resultA ' ' labelMapper.get(num2str(txResults{i}(j,y)))];
                            
                            similarRuleA = [ similarRuleA ' ' labelMapper.get(num2str(txRules{i}(x,y))) ];
                            similarResA  = [ similarResA  ' ' labelMapper.get(num2str(txResults{i}(x,y)))];
                        end
                        
                        if confSet{i}(j) ~= 0
                            fprintf('\n');
                            disp([ruleA ' -> ' resultA ' conf = ' num2str(confSet{i}(j))]);
                            disp([similarRuleA ' -> ' similarResA ' conf = ' num2str(confSet{i}(x))]);
                        end
                    end
                end
            end 
        end
    end
end

