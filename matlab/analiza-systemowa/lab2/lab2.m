clc
clear all
close all

format short

% metoda hellwiga

% macierz x 10x4
X = [
    6 0.4 15 70; 
    14 0.6 13 40; 
    17 0.4 15 80; 
    14.5 0.7 11 50; 
    20 1 10 40; 
    21.6 1.2 10 50; 
    23 1 7 80; 
    24.5 1.5 6 100; 
    28 1.5 8 110; 
    26.4 1.7 5 80
];

% wektor y 10x1
Y = [14; 17; 14.5; 20; 21.6; 23; 24.5; 28; 26.4; 29];

% zad 1

% korelacja
Rx = corrcoef([X Y]);

% macierz R i wektor R0
R  = Rx(1:4, 1:4);
R0 = Rx(5, 1:4);

% zad 2
% korelacje 

% x1 x2 
subplot(3,4,1)
plot(X(:,1), X(:,2), '*r')
title(['x1 z x2 wynosi: ' num2str(R(1,2))])

% x1 x3
subplot(3,4,2)
plot(X(:,1), X(:,3), '*r')
title(['x1 z x3 wynosi: ' num2str(R(1,3))])

% x1 x4
subplot(3,4,3)
plot(X(:,1), X(:,4), '*r')
title(['x1 z x4 wynosi: ' num2str(R(1,4))])

% x2 x3
subplot(3,4,4)
plot(X(:,2), X(:,3), '*r')
title(['x2 z x3 wynosi: ' num2str(R(2,3))])

% x2 x4
subplot(3,4,5)
plot(X(:,2), X(:,4), '*r')
title(['x2 z x4 wynosi: ' num2str(R(2,4))])

% x3 x4
subplot(3,4,6)
plot(X(:,3), X(:,4), '*r')
title(['x3 z x4 wynosi: ' num2str(R(3,4))])

% x1y
subplot(3,4,7)
plot(X(:,1), Y, '*r')
title(['x1 z y wynosi: ' num2str(R0(1), Y)])

% x2y
subplot(3,4,8)
plot(X(:,2), Y, '*r')
title(['x2 z y wynosi: ' num2str(R0(2), Y)])

% x3y
subplot(3,4,9)
plot(X(:,3), Y, '*r')
title(['x3 z y wynosi: ' num2str(R0(3), Y)])

% x4y
subplot(3,4,10)
plot(X(:,4), Y, '*r')
title(['x4 z y wynosi: ' num2str(R0(4), Y)])


% zad 3
H_1 = ( R0(1)^2 ) /  abs( R(1,1) ) ;
H_2 = ( R0(2)^2 ) /  abs( R(2,2) ) ;
H_3 = ( R0(3)^2 ) /  abs( R(3,3) ) ;
H_4 = ( R0(4)^2 ) /  abs( R(4,4) ) ;

% x1x2
    h_51 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,2) ) );
    h_52 = ( R0(2)^2 ) / ( abs( R(2,1) ) + abs( R(2,2) ) );
H_5 = h_51 + h_52;

% x1x3
    h_61 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,3) ) );
    h_62 = ( R0(3)^2 ) / ( abs( R(3,1) ) + abs( R(3,3) ) );
H_6 = h_61 + h_62;

% x1x4
    h_71 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,4) ) );
    h_72 = ( R0(4)^2 ) / ( abs( R(4,1) ) + abs( R(4,4) ) );
H_7 = h_71 + h_72;

% x2x3
    h_81 = ( R0(2)^2 ) / ( abs( R(2,2) ) + abs( R(2,3) ) );
    h_82 = ( R0(3)^2 ) / ( abs( R(3,2) ) + abs( R(3,3) ) );
H_8 = h_81 + h_82;

% x2x4
    h_91 = ( R0(2)^2 ) / ( abs( R(2,2) ) + abs( R(2,4) ) );
    h_92 = ( R0(4)^2 ) / ( abs( R(4,2) ) + abs( R(4,4) ) );
H_9 = h_91 + h_92;

% x3x4
    h_101 = ( R0(3)^2 ) / ( abs( R(3,3) ) + abs( R(3,4) ) );
    h_102 = ( R0(4)^2 ) / ( abs( R(4,3) ) + abs( R(4,4) ) );
H_10 = h_101 + h_102;


% 3 elementy

% x1x2x3
    h_111 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,2) ) + abs( R(1,3) ) );
    h_112 = ( R0(2)^2 ) / ( abs( R(2,1) ) + abs( R(2,2) ) + abs( R(2,3) ) );
    h_113 = ( R0(3)^2 ) / ( abs( R(3,1) ) + abs( R(3,2) ) + abs( R(3,3) ) );
H_11 = h_111 + h_112 + h_113;

% x1x2x4
    h_121 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,2) ) + abs( R(1,4) ) );
    h_122 = ( R0(2)^2 ) / ( abs( R(2,1) ) + abs( R(2,2) ) + abs( R(2,4) ) );
    h_123 = ( R0(4)^2 ) / ( abs( R(4,1) ) + abs( R(4,2) ) + abs( R(4,4) ) );
H_12 = h_121 + h_122 + h_123;

% x1x3x4
    h_131 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,3) ) + abs( R(1,4) ) );
    h_132 = ( R0(3)^2 ) / ( abs( R(3,1) ) + abs( R(3,3) ) + abs( R(3,4) ) );
    h_133 = ( R0(4)^2 ) / ( abs( R(4,1) ) + abs( R(4,3) ) + abs( R(4,4) ) );
H_13 = h_131 + h_132 + h_133;

% x2x3x4
    h_141 = ( R0(2)^2 ) / ( abs( R(2,2) ) + abs( R(2,3) ) + abs( R(2,4) ) );
    h_142 = ( R0(3)^2 ) / ( abs( R(3,2) ) + abs( R(3,3) ) + abs( R(3,4) ) );
    h_143 = ( R0(4)^2 ) / ( abs( R(4,2) ) + abs( R(4,3) ) + abs( R(4,4) ) );
H_14 = h_141 + h_142 + h_143;

% x1x2x3x4
    h_151 = ( R0(1)^2 ) / ( abs( R(1,1) ) + abs( R(1,2) ) + abs( R(1,3) ) + abs( R(1,4) ) );
    h_152 = ( R0(2)^2 ) / ( abs( R(2,1) ) + abs( R(2,2) ) + abs( R(2,3) ) + abs( R(2,4) ) );
    h_153 = ( R0(3)^2 ) / ( abs( R(3,1) ) + abs( R(3,2) ) + abs( R(3,3) ) + abs( R(3,4) ) );
    h_154 = ( R0(4)^2 ) / ( abs( R(4,1) ) + abs( R(4,2) ) + abs( R(4,3) ) + abs( R(4,4) ) );
H_15 = h_151 + h_152 + h_153 + h_154;

H = [ H_1 ; H_2;  H_3;  H_4 ;  H_5 ;  H_6 ;  H_7;   H_8;   H_9 ;  H_10 ;  H_11 ; H_12 ; H_13 ; H_14 ; H_15];

[hMax,matrixIndex] = max(H);

% ktora z kolei kombinacja
matrixIndex

% maksymalna pojemnosc integarlana wynosi
hMax

% zmienne określające maksymalna wartość dla pojemności integralnej to x2 i x3 (H8)