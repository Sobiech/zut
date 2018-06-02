clc
clear all
close all
format long

% grupa A
X_1A = [ 0.15 0.34 0.09 0.21 ];
X_2A = [ 1.91 1.68 1.89 2.30 ];

% grupa B
X_1B = [ 0.48 0.41 0.62 0.50 1.2 ];
X_2B = [ 0.88 0.62 1.09 1.32 0.68 ];

X_a_sr = [ mean(X_1A) mean(X_2A) ];
X_b_sr = [  mean(X_1B) mean(X_2B) ];

Z = [ 0.2 0.75 ];

C_ma = [];
C_mb = [];

n_1 = length(X_1A);
n_2 = length(X_1B);

for i = 1 : n_1
    C_ma = [ C_ma; X_1A(i) - X_a_sr(1) X_2A(i) - X_a_sr(2) ]; 
end

for i = 1 : n_2
    C_mb = [ C_mb; X_1B(i) - X_b_sr(1) X_2B(i) - X_b_sr(2) ]; 
end

%macierz cov dla gr A
S_1 = (1 / n_1) * C_ma' * C_ma;

%macierz cov dla gr B
S_2 = (1 / n_2) * C_mb' * C_mb;

%wsp. macierz cov dla gr A i gr B
S = ( 1 / ( n_1 + n_2 - 2 ) ) * ( n_1 * S_1 + n_2 * S_2 );
S_inv = inv(S);

%wektor wsp. f dyskrminacji
A = S_inv * ( [ X_a_sr - X_b_sr ]' );
  
fprintf('- wektor wsp.f dyskryminacji\n');
fprintf('%s \n', A);

fprintf(' - funkcja dyskryminacji: D = %f * X1 + %f * X2\n', A(1), A(2));

% wekt wartosci fx
D_1 = [ X_1A' X_2A' ] * A;
D_2 = [ X_1B' X_2B' ] * A;

%stala dyskryminacyjna
C = ( mean(D_1) + mean(D_2) ) / 2;

fprintf(' - stala dyskryminacyjna: %f\n', C);

fpritnf( ' czy D nalezy do C');

