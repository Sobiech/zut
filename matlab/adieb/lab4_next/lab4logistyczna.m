clc
clear all
close all

%% Zadanie 1 Regresja llogistyczna
disp('***************ZADANIE 1***************');
alfa = 0.05;
PB1 = [ 0.9 0.8 0.6 0.8 3.2 1.7 0.4 1.4 1.6 1.8 1.9 2.7 1.1 1.2 1.0 1.3 3.5 0.8 0.7 1.1 0.9 0.6 1.4 2.3 1.6 2.4 1.1 0.8 0.9 1.8 1.0 1.5 0.6 0.9 0.8 3.7 1.3 0.8 0.6 ];
PB2 = [ 75 150 250 375 160 106 200 135 178 150 95 175 183 200 190 163 109 150 350 170 140 275 233 164 80 130 220 333 142 180 136 136 150 190 190 180 250 320 75 ];
X = [ ones(length(PB1),1), PB1', PB2' ];
Y = [ 0 0 0 1 1 0 0 0 1 1 1 1 0 1 0 1 1 1 1 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 0 1 1 1 0 ]';
k = size(X,2) - 1;
Yk = mean(Y);

B0 = log(Yk/(1-Yk));
Bp = [B0; zeros(k,1) ];
eps = 10^-3;

while (1)
    Z = X*Bp;
    p = (1./(1+exp(-Z)));
    w = p.*(1-p);
    u = Z + ((Y-p)./w);
    W = diag(w);
    B = inv(X'*W*X)*X'*W*u;
    if sumsqr(B - Bp) < eps
        break;
    end
    Bp = B;
end

disp(['Model = ', num2str(B(1)), ' + ', num2str(B(2)), '*PB1',  ' + ', num2str(B(3)), '*PB2']);
Z = X*B;
PY = (1./(1+exp(-Z)));
L1 = sum(Y.*log(PY) + (1-Y).*(1-log(PY)));

PY0 = (1./(1+exp(-B(1))));
L0 = sum(Y.*log(PY0) + (1-Y).*(1-log(PY0)));

H = -2*(L0-L1);
P = 1 - chi2cdf(H,length(B) - 1);

disp(['P = ', num2str(P)]);

if (P < alfa)
    disp('H1: Model regresji jest istotny statystycznie.');
else
    disp('H0: Model regresji nie jest istotny statystycznie.');
end

E = Y - PY;

PunktyOdstajace = find(abs(E) >= 0.8)

YL = round(PY);
PZP0 = find(Y==YL & Y==0);
PZP1 = find(Y==YL & Y==1);
NZP0 = find(Y~=YL & Y==0);
NZP1 = find(Y~=YL & Y==1);
ILS = (length(PZP0)*length(PZP1))/(length(NZP0)*length(NZP1));

disp(['Iloraz szans = ', num2str(ILS)]);

YZ = [1 0.95 143]*B;
PZ = (1./(1+exp(-YZ)))

%% Zadanie 2 Regresja llogistyczna
disp('***************ZADANIE 2***************');