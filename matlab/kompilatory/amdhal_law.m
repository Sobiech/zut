clc
clear all
close all

X = [];

for i = 1 : 10
   X(i)=2^i; 
end

Y = []
for i = 1 : 10
    Y(i) = 1 / ( 0.1 + ( 1 - 0.1) / X(i));
end

Y1 = []
for i = 1 : 10
    Y1(i) = 1 / ( 0.05 + ( 1 - 0.05) / X(i));
end

Y2 = []
for i = 1 : 10
    Y2(i) = 1 / ( 0.2 + ( 1 - 0.2) / X(i));
end

Y3 = []
for i = 1 : 10
    Y3(i) = 1 / ( 0.3 + ( 1 - 0.3) / X(i));
end


Xi = 0:0.1:1024
Yi = pchip(X,Y,Xi);
Yi1= pchip(X,Y1,Xi);
Yi2 = pchip(X,Y2,Xi);
Yi3 = pchip(X,Y3,Xi);

plot(Xi,Yi1,'black')
hold on
plot(Xi,Yi,'red');
hold on
plot(Xi,Yi2,'blue');
hold on
plot(Xi,Yi3,'green');

legend('Czêœæ równoleg³a 95%', 'Czêœæ równoleg³a: 90%','Czêœæ równoleg³a 80%', 'Czêœæ równoleg³a 70%');
xlabel('Iloœæ procesorów');
ylabel('Przyspieszenie');
hold off