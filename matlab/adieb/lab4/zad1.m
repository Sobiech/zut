clc
clear all
close all

format long

Y = [ 910 778 784 805 790 1020 746 835 850 750 850 826 725 680 735 865 985 700 ];
X = [ 40 112 116 90 95 25 157 59 48 132 57 70 175 210 168 48 35 180 ];

alfa = 0.05;

% funkcja liniowa
[b0, b1, yModel] = regression(X,Y);
fprintf('Regresja liniowa\n');
fprintf('-> wsp. b0 = %d\n', b0 );
fprintf('-> wsp. b1 = %d\n', b1);

e = Y - yModel;
bladStandardowy = sqrt((sum(e.^2))/(size(e,2)-2));
fprintf('-> b³¹d standardowy estymacji = %f', bladStandardowy );

R = sum((X - mean(X)).*(Y - mean(Y)))/sqrt(sum((X - mean(X)).^2)*sum((Y - mean(Y)).^2));
fprintf('-> wsp. korelacji R = %f\n', R);
R2 = R^2;
fprintf('-> wsp. determinacji R^2 = %f\n', R2);

fprintf('-> postaæ funkcji f(x) = %fx + %f + %f\n\n', b1, b0, bladStandardowy);
XL = min(X):max(X);
YL = b1*XL + b0;


% funkcja potegowa
Yp = log10(Y);
Xp  = log10(X);

[bp0,bp1,ypModel] = regression(Xp,Yp);
bp0 = ( 10 ^ bp0);
fprintf('Funkcja potegowa\n');
fprintf('-> wsp. b0 = %f\n', bp0);
fprintf('-> wsp. b1 = %f\n', bp1 );
ypMoldel = bp0*(Xp.^bp1);
eP = Yp - ypMoldel;
bladStandardowyP = sqrt((sum(eP.^2))/(size(eP,2)-2));
fprintf('-> b³¹d standardowy estymacji = %f\n', bladStandardowyP);

RP = sum((Xp - mean(Xp)).*(Yp - mean(Yp)))/sqrt(sum((Xp - mean(Xp)).^2)*sum((Yp - mean(Yp)).^2));
fprintf('-> wsp. korelacji R = %f\n', RP);
R2P = RP^2;
fprintf('-> wsp. determinacji R^2 = %f\n', R2P);
fprintf('-> postaæ funkcji f(x) = %fx + %f + %f\n\n', bp1, bp0, bladStandardowyP);
YP = bp0*(XL.^bp1);


% funkcja wykladnicza
Xw = X;
Yw = Yp;

[bw0, bw1, ywModel] = regression(Xw,Yw);
bw0 = (10^bw0);
bw1 = (10^bw1);
fprintf('Funkcja wykladnicza\n');
fprintf('-> wsp. b0 = %f\n', bw0);
fprintf('-> wsp. b1 = %f\n', bw1);

eW = Yw - ywModel;
bladStandardowyW = sqrt((sum(eW.^2))/(size(eW,2)-2));
fprintf('-> b³¹d standardowy estymacji = %f\n', bladStandardowyW);

RW = sum((Xw - mean(Xw)).*(Yw - mean(Yw)))/sqrt(sum((Xw - mean(Xw)).^2)*sum((Yw - mean(Yw)).^2));
fprintf('-> wsp. korelacji R = %f\n', RW);
R2W = RW^2;
fprintf('-> wsp. determinacji R^2 = %f\n', R2W);
fprintf('-> postaæ funkcji f(x) = %fx + %f + %f\n\n', bw1, bw0, bladStandardowyW);    


YW = ( bw0 )*(( bw1 ).^XL );
R2ALL = [ R2, R2P, R2W ];
R2MAX = max(R2ALL);

model_array = {'liniowy', 'potêgowy', 'wyk³adniczy'};
fprintf('Wnioski:\n');

for i = 1:max(size(R2ALL))
    if (R2ALL(i) == R2MAX)
        disp(['-> najlepszym modelem jest model:', model_array(i)]);
        break;
    end
end

%wykresy
figure

hold on
grid on

plot(X,Y,'r*')
plot(XL,YL)
plot(XL,YP,'g')
plot(XL,YW,'k')
legend('Punkty empiryczne', 'Równanie regresji', 'Równanie regresji potêgowej', 'Równanie regresji wyk³adniczej')


