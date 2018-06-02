clc
clear all
close all
format long


X_1 = [ 
    9.4 9.9 9.1 10.0 9.4 9.0;
    1.9 1.7 2.3 2.6 2.0 1.9
]';

X_2 = [
    7.4 6.7 6.6 7.0 7.5;
    1.09 1.23 1.33 1.25 1.15
]';

X_3 = [
    5.5 5.1 5.4 5.8;
    0.9 0.88 1.20 1.25 
]';

class = 2;
groups = 3;

X_sr = [ mean(X_1);mean(X_2); mean(X_3) ];

W_1 =  [ ( X_1(:,1) - X_sr(1,1) )   (  X_1(:,2) - X_sr(1,2) ) ];
W_2 =  [ ( X_2(:,1) - X_sr(2,1) )   (  X_2(:,2) - X_sr(2,2) ) ];
W_3 =  [ ( X_3(:,1) - X_sr(3,1) )   (  X_3(:,2) - X_sr(3,2) ) ];

% macierz rozrzutu wewnatrzgrupowego
W = [ W_1' * W_1 + W_2' * W_2 + W_3' * W_3];

X_vec = [ X_1(:, 1)' X_2(:, 1)' X_3(:, 1)'; X_1(:, 2)' X_2(:, 2)' X_3(:, 2)' ];
X_m_sr = [ mean(X_vec(1,:)) mean(X_vec(2,:)) ];

% macierz rozrzutu miedzygrupowego
M_1 = [ X_sr(1,:) - X_m_sr ] ;
M_2 = [ X_sr(2,:) - X_m_sr ] ;
M_3 = [ X_sr(3,:) - X_m_sr ] ;

M = [ length(X_1) * M_1' * M_1 + length(X_2) * M_2' * M_2 + length(X_3) * M_3' * M_3 ];

C_1 = ( length(X_1) - groups ) * X_sr(1,:) * inv(W);
C_2 = ( length(X_2) - groups ) * X_sr(2,:) * inv(W);
C_3 = ( length(X_3) - groups ) * X_sr(3,:) * inv(W);

fprintf ( ' funkcja klasyfikacyjna nr. %d'


