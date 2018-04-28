CREATE TABLE javaz.pracownik
(
	id character varying(11) NOT NULL,
	imie character varying(100),
	nazwisko character varying(100),
	wynagrodzenie numeric,
	stanowisko character varying(100),
	telefon character varying(100),
  	CONSTRAINT pracownik_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE javaz.pracownik
  OWNER TO kso_test;
GRANT ALL ON TABLE javaz.pracownik TO kso_test;


CREATE TABLE javaz.handlowiec
(
	id character varying(11) NOT NULL,
	imie character varying(100),
	nazwisko character varying(100),
	wynagrodzenie numeric,
	stanowisko character varying(100),
	telefon character varying(100),
	stawka_prowizji numeric,
	limit_prowizji integer,
  	CONSTRAINT handlowiec_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE javaz.handlowiec
  OWNER TO kso_test;
GRANT ALL ON TABLE javaz.handlowiec TO kso_test;


CREATE TABLE javaz.dyrektor
(
	id character varying(11) NOT NULL,
	imie character varying(100),
	nazwisko character varying(100),
	wynagrodzenie numeric,
	stanowisko character varying(100),
	telefon character varying(100),
	dodatek_sluzbowy numeric,
	karta_sluzbowa integer,
	limit_kosztow integer,
  	CONSTRAINT dyrektor_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE javaz.dyrektor
  OWNER TO kso_test;
GRANT ALL ON TABLE javaz.dyrektor TO kso_test;

INSERT INTO javaz.pracownik VALUES ('11122233344', 'Janusz', 'Kurant', 10000, 'Dyspozytor', '111222333' );
INSERT INTO javaz.pracownik VALUES ('11122233345', 'Krystyna', 'Pilecki', 15000, 'Sprz¹taczka', '111222334' );
INSERT INTO javaz.pracownik VALUES ('11122233346', 'Mateusz', 'Likian', 9000, 'Magazynier', '111222335' );
INSERT INTO javaz.pracownik VALUES ('11122233347', 'Marcin', 'Szerka', 3490, 'Magazynier', '111222336' );
INSERT INTO javaz.pracownik VALUES ('11122233348', 'Micha³', 'Wertyk', 9013, 'Kierowca', '111222337' );

INSERT INTO javaz.handlowiec VALUES ('11122233354', 'Rafa³', 'Bobek', 40993, 'Kierownik sprzeda¿y', '911222343', 25, 88 );
INSERT INTO javaz.handlowiec VALUES ('11122233355', 'Robert', 'Dido', 11330, 'Kierownik CC', '911222344', 23, 30 );
INSERT INTO javaz.handlowiec VALUES ('11122233356', 'Mateusz', 'Forbek', 9993, 'Handlowiec', '911222345', 10, 20 );
INSERT INTO javaz.handlowiec VALUES ('11122233357', 'Zbigniew', 'Grawik', 9943, 'Handlowiec', '911222346', 10, 15 );
INSERT INTO javaz.handlowiec VALUES ('11122233358', 'Andrzej', 'M¹kol', 8881, 'Handlowiec', '911222347', 10, 15 );

INSERT INTO javaz.dyrektor VALUES('15122233365','Krzystof','Jarzyna',999999,'Dyrektor wszystkiego','111522344',999999,99,99999 );
INSERT INTO javaz.dyrektor VALUES('11122233365','Karolina','Zamek',115330,'Dyrektor transportu','911522344',15000,99,50000 );
INSERT INTO javaz.dyrektor VALUES('11122233364','Robert','Markus',100993,'Dyrektor dzia³u sprzeda¿y','911252343',15000,98,50000 );