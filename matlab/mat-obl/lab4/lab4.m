clc
clear all
close all

blad = [ 0.1, 0.01, 0.001 ];
n = [ 5, 10, 100, 500, 1000, 3000, 5000 ];
czas = zeros(3*size(blad,2),size(n,2));
for i = 1:size(blad,2)
    fprintf('bl?d = %f\n',blad(i));
    for j = 1:size(n,2)
        fprintf('\trozmiar macierzy = %d\n',n(j));
        % przygotowanie do oblicze?
            a = rand(n(j),n(j))+n(j)*eye(n(j),n(j));
            b = a*ones(n(j),1);
            W = a;
            Z = b;
            WZ = [W,Z];
            for k = 1:n(j)
                for l = 1:n(j)
                    if (k==l)
                        WZ(k,:) = WZ(k,:)./W(k,k);
                        WZ(k,1:n(j)) = WZ(k,1:n(j))*(-1);
                        WZ(k,k) = 0;
                    end
                end
            end
        %obliczenia 
        tic
            W = WZ(:,1:n(j));
            Z = WZ(:,max(size(WZ)));
            XW = zeros(n(j),1);
            X = W*XW(:,1)+Z;
            XW = [XW,X];
            licznik = 2;
            while (blad(i)<blad_iter(XW(:,licznik),XW(:,(licznik-1))))
                X = W*XW(:,licznik)+Z;
                XW = [XW,X];
                licznik = licznik + 1;
            end
        czas(i,j) = toc;
        fprintf('\t-\tIteracja prosta wykonana w czasie %f i %d krokach\n',czas(i,j),licznik);
        Wynik = X;
        tic
            W = WZ(:,1:n(j));
            Z = WZ(:,max(size(WZ)));
            Wl = tril(W);
            Wu = triu(W);
            X = zeros(n(j),1);
            XW = X;
            for k = 1:size(W,1)
                X(k) = Wl(k,:)*X+Wu(k,:)*XW(:,1)+Z(k);
            end
            XW = [XW,X];
            licznik = 2;
            while (blad(i)<blad_iter(XW(:,licznik),XW(:,(licznik-1))))
                X = zeros(n(j),1); 
                for k = 1:size(W,1)
                    X(k) = Wl(k,:)*X+Wu(k,:)*XW(:,licznik)+Z(k);
                end
                XW = [XW,X];
                licznik = licznik + 1;
            end
        czas(i+3,j) = toc;
        fprintf('\t-\tIteracja Gaussa-Seidla wykonana w czasie %f i %d krokach\n',czas(i+3,j),licznik);
        Wynik = X;
        tic
        Wynik = a\b;
        czas(i+6,j) = toc;
        fprintf('\t-\tEliminacja Gaussa wykonana w czasie %f\n',czas(i+6,j));
    end
end
fprintf('*************************************************\n');
figure
subplot(3,1,1)
hold on
grid on
plot(n,czas(1,:),'b')
plot(n,czas(4,:),'g')
plot(n,czas(7,:),'r')
legend('Iteracja prosta','Iteracja Gaussa-Seidla','Eliminacja Gaussa');

subplot(3,1,2)
hold on
grid on
plot(n,czas(2,:),'b')
plot(n,czas(5,:),'g')
plot(n,czas(8,:),'r')
legend('Iteracja prosta','Iteracja Gaussa-Seidla','Eliminacja Gaussa');

subplot(3,1,3)
hold on
grid on
plot(n,czas(3,:),'b')
plot(n,czas(6,:),'g')
plot(n,czas(9,:),'r')
legend('Iteracja prosta','Iteracja Gaussa-Seidla','Eliminacja Gaussa');