%Regresja logistyczna
clc
clear all
close all

format long

alfa = 0.05;

pb1 = [ 0.9 0.8 0.6 0.8 3.2 1.7 0.4 1.4 1.6 1.8 1.9 2.7 1.1 1.2 1 1.3 3.5 0.8 0.7 1.1 0.9 0.6 1.4 2.3 1.6 2.4 1.1 0.8 0.9 1.8 1 1.5 0.6 0.9 0.8 3.7 1.3 0.8 0.6 ]';
pb2 = [ 75 150 250 375 160 106 200 135 178 150 95 175 183 200 190 163 109 150 350 170 140 275 233 164 80 130 220 333 142 180 136 136 150 190 190 180 250 320 75 ]';
y   = [ 0 0 0 1 1 0 0 0 1 1 1 1 0 1 0 1 1 1 1 0 0 0 1 1 0 0 1 1 1 1 0 0 0 0 0 1 1 1 0 ]';

x = [ ones(length(pb1),1), pb1, pb2 ];
k = size(x,2) - 1;
y_k = mean(y);

b_0 = log( y_k /( 1 - y_k ));
b_p = [ b_0; zeros(k,1) ];
b_delta = 10 ^ -3;

while (1)
    
    Z = x * b_p;
    
    p = ( 1./( 1 + exp(-Z)));
    w = p .* ( 1 - p );
    u = Z + ( ( y - p ) ./ w );
    W = diag(w);
    B = inv(x' * W * x) * x' * W * u;
    
    if sumsqr(B - b_p) < b_delta
        break;
    end
    b_p = B;
end

fprintf('- wspolczynniki: \n\t b_0: %f \n\t b_1: %f \n\t b_2: %f\n', B(1), B(2), B(3));
fprintf('- model regresji: \n\t %f + %f*PB1 + %f*PB2\n', B(1), B(2), B(3));

Z = x * B;
p_y = ( 1./( 1 + exp( -Z )));

log_py = log(p_y);
l_mod = sum( y .* log_py + ( 1 - y ) .* ( 1 - log_py ));

p_y0 = ( 1 ./ ( 1 + exp ( -B(1) )));
l_0 = sum( y .* log( p_y0 ) + (1- y).*( 1-log(p_y0)));

fprintf('- max. ocena glownego modelu l_1: %f\n', l_mod);
fprintf('- ocena zerowego modelu l_0: %f\n', l_0);

H = -2 * ( l_0 - l_mod );
P = 1 - chi2cdf(H, length(B) - 1 );

if (P < alfa)
    fprintf('- model logstyczny jest istotny statystycznie\n');
else
    fprintf('- model logistyczny nie jest istotny statystycznie\n');
end

E = y - p_y;
wsp = 0.8;
points = find(abs(E) > wsp);
fprintf('- punkty odstajace:\n');
for i = 1 : length(points)
   fprintf('\t punkt_%d = %d\n', i ,points(i));
end

%predict PB_1 = 0.95 / PB_2 = 143
y_l = round(p_y);

p_zp0 = find( y == y_l & y == 0);
p_zp1 = find( y == y_l & y == 1);
n_zp0 = find( y ~= y_l & y == 0);
n_zp1 = find( y ~= y_l & y == 1);

iloraz = ( length(p_zp0) * length(p_zp1) )/ ( length( n_zp0 ) * length(n_zp1) );
% iloraz szans 
fprintf('iloraz szans: %d\n', iloraz);

param_bio_pb1 = 0.95;
param_bio_pb2 = 143;
