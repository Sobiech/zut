clc
clear all
close all

X = [ 28.3 12.8  5;
      11    9.2 10;
      17.9 14    5;
       2.1 12.7  3;
      46.5 27.7  4;
      37.1 16.4  1;
      16   20.1  5;
      35.8 23.1  2;
       7.9 14.1  5;
      10   25.5  9;
       0.7 12.5 10;
      17.5  4.5  5;
      10.8 20.8  1;
       4.9 16.8  2;
      39.5 20    7;
       0.2 17.6  2;
      40   22.8  1;
      48.1 25.2 10;
      22   16.8 10;
      23.8 17.7  2;];
  
Y = [ 5 5.6 9.4 26.7;
        2 2 1.9 9.3;
        5 4.4 12.1 35.7;
        3 0.9 1.8 7.8;
        9 10.5 24.3 69.3;
        5 9.5 17.2 53.7;
        7 4 9.8 27.3;
        10 8.2 13.7 37.2;
        4 2.8 8.9 23.4;
        7 3.1 6.5 22.2;
        5 0.5 2.5 10.2;
        1 5.5 11.3 33.9;
        6 2.4 5.1 13.2;
        4 1.6 3.5 11.7;
        8 11 21.1 63;
        5 0.8 4.3 11.7;
        10 9.4 18.8 54;
        7 11.5 22.4 67.8;
        7 5.2 8 26.7;
        7 6.4 10.1 3];
    
xsr = mean(X);
ysr = mean(Y);
xstd = std(X);
ystd = std(Y);

Xu = [ ((X(:,1)-xsr(1))/xstd(1)), ((X(:,2)-xsr(2))/xstd(2)), ((X(:,3)-xsr(3))/xstd(3)) ];
Yu = [ ((Y(:,1)-ysr(1))/ystd(1)), ((Y(:,2)-ysr(2))/ystd(2)), ((Y(:,3)-ysr(3))/ystd(3)), ((Y(:,4)-ysr(4))/ystd(4)) ];
xs = size(Xu,2);
ys = size(Yu,2);
Z = [Xu,Yu];

Cz = cov(Z)
[w k] = size(Cz);
Cxx = Cz(1:xs,1:xs);
Cxy = Cz(1:xs,ys:k);
Cyx = Cz(ys:w,1:xs);
Cyy = Cz(ys:w,ys:k);

Cm = inv(Cxx)*Cxy*inv(Cyy)*Cyx

[V D] = eig(Cm) % D to wartoœci w³asne (lamda^2), V to wektory w³asne 

SORT = [sum(real(D)); V];
SORT = fliplr(sortrows(SORT',1)');

D = SORT(1,:)
V = SORT(2:size(SORT,1),:)

Su = sum(V'*Cxx*V)
sqrtSu = sqrt(Su)
A = V(1,:)./sqrtSu;
for i = 2:size(V,1)
    A = [A; V(i,:)./sqrtSu];
end
disp(['Wagi kanoniczne zmiennej X']);
A % wagi kanoniczne dla X

test = A'*Cxx*A;

disp(['Wartoœæ korelacji kanonicznej']);
R = sqrt(D) %Wartoœæ korelacji kanonicznej
B = (1./R(1))*inv(Cyy)*Cyx*A(:,1);
for i = 2:size(A,2)
    B = [B,(1./R(1))*inv(Cyy)*Cyx*A(:,i)];
end
disp(['Wagi kanoniczne zmiennej Y']);
B % wagi kanoniczne zmiennej

for i = 1:size(B,2)
    [a pozA] = max(abs(A(:,i)));
    [b pozB] = max(abs(B(:,i)));
    disp(['Dla ', num2str(i), ' zmiennej kanonicznej najwiêkszy wp³yw ma X', num2str(pozA), ' oraz Y',  num2str(pozB)]);
end

p = size(Xu,2);
q = size(Yu,2);
n = size(Yu,1);
alfa = 0.05;
for i = 1:size(R,2)
    disp(['Dla R', num2str(i), ':']);
    D1 = D(i:size(D,2));
    z = q + 1 - i;
    t = p + 1 - i;
    H = -1*(n-(1/2)*(z+t+3))*log(prod(1-D1));
    P = 1 - chi2cdf(H,z*t);
    if (P < alfa)
        disp('H1: Przynajmniej jeden ze wspó³czynników korelacji kanonicznej jest istotny.');
    else
        disp('H0: Wszystkie korelacje s¹ nie istone');
        break;
    end
end

disp('ladunki czynnikowe')
X = A'*Xu';
Y = B'*Yu';
Kor_X = corr(Xu,X')
Kor_Y = corr(Yu,Y')

disp('wariancje wyodrebnione')
warX = sum(Kor_X.^2)/3
warY = sum(Kor_Y.^2)/4