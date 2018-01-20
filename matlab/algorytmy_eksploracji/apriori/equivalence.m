function equivalence(from, to, conf,labelMapper)
disp('-----------------------------------------------');
        for z=1:length(from)-1
            for i1 = 1:length(from{z})
                a = from{z}(i1,:);
                b = to{z}(i1,:);
                for j1=i1:length(from{z})
                    if(from{z}(j1,:) == b)
                        if (to{z}(j1,:) == a)
                            str1First ='';
                            str1Sec = '';
                            str2First ='';
                            str2Sec = '';
                            for y1 = 1 : z
                                str1First = [ str1First ' ' labelMapper.get(num2str(from{z}(i1,y1)))];
                                str2First = [ str2First ' ' labelMapper.get(num2str(to{z}(i1,y1)))];
                                str1Sec = [ str1Sec ' ' labelMapper.get(num2str(from{z}(j1,y1)))];
                                str2Sec = [ str2Sec ' ' labelMapper.get(num2str(to{z}(j1,y1)))];
                            end
                            
                            if(conf{z}(i1)~=0)
                                disp([str1First ' |-> ' str2First ' conf = ' num2str(conf{z}(i1))]);
                                disp([str1Sec ' |-> ' str2Sec ' conf = ' num2str(conf{z}(j1))]);
                                disp('------');
                            end
                        end
                    end
                end
                
            end
        end
end

