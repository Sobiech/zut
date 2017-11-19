function [wineDataSet] = wineDataSet(filePath)
% Returns the discretized version of 'wine data set' (from UCI repository).
% Each input variable is discretized into q = 5 intervals.
% The output variable (class number) has 3 values - kind of wine {1, 2, 3}

dataSet = textread(filePath, '', 'delimiter', ',');
[I, n] = size(dataSet);
n = n - 1;

%moving y column from the first to the last
y = dataSet(:, 1);
dataSet(:, 1) = dataSet(:, n + 1);
dataSet(:, n + 1) = y;

wineDataSet = zeros(I, n + 1);

%discretizing all input variables into m intervals of equal length
q = 5;
for i = 1 : n
    minValue = min(dataSet(:, i));
    maxValue = max(dataSet(:, i));
    wineDataSet(:, i) = 1 + round((q - 1) * (dataSet(:, i) - minValue) / (maxValue - minValue));
end

wineDataSet(:, n + 1) = y;

