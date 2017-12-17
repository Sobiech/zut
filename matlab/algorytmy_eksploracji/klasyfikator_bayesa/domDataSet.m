function [domDataSet] = domDataSet(filePath, q )

dataSet = textread(filePath, '', 'delimiter', ',');
[I, n] = size(dataSet);
n = n - 1;

%moving y column from the first to the last
y = dataSet(:, end) + 1;
domDataSet = zeros(I, n + 1);

for i = 1 : n
    
    minValue = min(dataSet(:, i));
    maxValue = max(dataSet(:, i));
    
    domDataSet(:, i) = 1 + round((q - 1) * (dataSet(:, i) - minValue) / (maxValue - minValue));
end

domDataSet(:, n + 1) = y;

