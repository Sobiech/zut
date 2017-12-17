function [domDataSet] = domDataSet(filePath)

dataSet = textread(filePath, '', 'delimiter', ',');
[I, n] = size(dataSet);
n = n - 1;

%moving y column from the first to the last
y = dataSet(:, end) + 1;
domDataSet = zeros(I, n + 1);

%discretizing all input variables into m intervals of equal length

for i = 1 : n
    minValue = min(dataSet(:, i));
    maxValue = max(dataSet(:, i));
    domDataSet(:, i) = 1 + round( (maxValue - minValue) * 10 * (dataSet(:, i) - minValue) / (maxValue - minValue));
end

domDataSet(:, n + 1) = y;

