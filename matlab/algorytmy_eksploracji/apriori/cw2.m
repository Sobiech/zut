clc
clear all
close all

format long

load transactions.mat
minConfArray = [0.6,0.8,0.9];
minSuppArray = [0.2,0.15,0.1];
k = 1;
for i =1:length(minConfArray)
    for j = 1:length(minSuppArray)
        disp(['minConf: ' num2str(minConfArray(i))]);
        disp(['minSupp: ' num2str(minSuppArray(j))]);
        subplot(3,3,k);
        title(['minConf: ' ,num2str(minConfArray(i)), ' | minSupp: ' ,num2str(minSuppArray(j))])
        hold on;
        l = linspace(0,1);
        plot(linspace(0,1,1000),ones(1,1000)*minConfArray(i),'k:');
        hold on;
        plot(ones(1,1000)*minSuppArray(j),linspace(0,1,1000),'b:');
        hold on;
        dataLength = length(dataSet);
        F = frequentSets(dataSet, minSuppArray(j));
        [from,to,conf] = generateRules(F,minConfArray(i),labelMapper,dataLength);
        cellLength = length(from);
        k = k+1;
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
end


