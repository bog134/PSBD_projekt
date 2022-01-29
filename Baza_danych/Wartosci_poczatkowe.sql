CREATE SCHEMA IF NOT EXISTS FIRMA;
USE FIRMA;

-- Słowniki
CREATE TABLE IF NOT EXISTS LACZENIA
(
  	Id_Laczenia int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(30) NOT NULL,
	
	PRIMARY KEY (Id_Laczenia)
);

CREATE TABLE IF NOT EXISTS STAN_REALIZACJI
(
  	Id_Stanu_realizacji int(11) NOT NULL AUTO_INCREMENT,
 	Nazwa_Stanu varchar(50) NOT NULL,
	
	PRIMARY KEY (Id_Stanu_realizacji)
);

CREATE TABLE IF NOT EXISTS TYP_MEBLA
(
  	Id_Typu_mebla int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(50) NOT NULL,
	
	PRIMARY KEY (Id_Typu_mebla)
);

CREATE TABLE IF NOT EXISTS RODZAJ_MATERIALU
(
  	Id_Rodzaju_materialu INT NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
	
	PRIMARY KEY (Id_Rodzaju_materialu)
);

CREATE TABLE IF NOT EXISTS KOLOR 
(
  	Id_Koloru INT NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
	
	PRIMARY KEY (Id_Koloru)
);

CREATE TABLE IF NOT EXISTS WZOR
(
  	Id_Wzoru INT NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
	
	PRIMARY KEY (Id_Wzoru)
);

CREATE TABLE IF NOT EXISTS RODZAJ_POLPRODUKTU
( 
	Id_Rodzaju_polproduktu INT NOT NULL AUTO_INCREMENT, 
	Nazwa VARCHAR(45) NOT NULL, 

	PRIMARY KEY (Id_Rodzaju_polproduktu),
	UNIQUE KEY (Nazwa)
);

CREATE TABLE IF NOT EXISTS STANOWISKO
( 
	Id_Stanowiska INT NOT NULL AUTO_INCREMENT, 
	Nazwa_Stanowiska VARCHAR(45) NOT NULL,

	PRIMARY KEY (Id_Stanowiska),
	UNIQUE KEY (Nazwa_Stanowiska)
);

-- Koniec słowników

CREATE TABLE IF NOT EXISTS PRACOWNIK
( 
	Id_Pracownika INT NOT NULL AUTO_INCREMENT, 
	Id_Stanowiska INT NOT NULL, 
	Data_urodzenia DATE NOT NULL, 
	Imie VARCHAR(45) NOT NULL, 
	Nazwisko VARCHAR(45) NOT NULL, 
	Numer_konta_bankowego VARCHAR(45) NOT NULL, 
	Zarobki DOUBLE NOT NULL, 
	Numer_telefonu VARCHAR(45) NOT NULL,	
	Adres_Kod VARCHAR(45) NOT NULL, 
	Adres_Miasto VARCHAR(45) NOT NULL,		
	Adres_Ulica VARCHAR(45) NOT NULL,	
	Login VARCHAR(45) NOT NULL,
	Haslo VARCHAR(45) NOT NULL,

	PRIMARY KEY (Id_Pracownika),
	UNIQUE KEY (Numer_konta_bankowego),
	UNIQUE KEY (Login)
);

CREATE TABLE IF NOT EXISTS CENA 
(
  	Id_Ceny INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient int(11) NOT NULL,
  	Id_Pracownika INT NOT NULL,
  	Koszt_robocizny DOUBLE NOT NULL,
  	Koszt_surowcow DOUBLE NOT NULL,
  	Marza DOUBLE NOT NULL,
	Koszt_polproduktow DOUBLE NOT NULL,
	
  	PRIMARY KEY (Id_Ceny)
);

CREATE TABLE IF NOT EXISTS DEFINICJA_ZADANIA
( 
	Id_Def_zadania INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient INT DEFAULT NULL,
	Id_Proj_katalog INT DEFAULT NULL,	
	Opis_zadania VARCHAR(200) NOT NULL, 
	Cena DOUBLE NOT NULL,
	Czas_wykonania INT NOT NULL,	

	PRIMARY KEY (Id_Def_zadania)
);

CREATE TABLE IF NOT EXISTS PROJEKT_POLPRODUKTU
( 
	Id_Proj_polprod INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient INT DEFAULT NULL,
	Id_Proj_katalog INT DEFAULT NULL,
	Id_Rodzaju_polproduktu INT NOT NULL,
	Id_Dostawcy int(11) DEFAULT NULL,
	Nazwa VARCHAR(45) NOT NULL,
	Rozmiar_Wysokosc DOUBLE NOT NULL,
	Rozmiar_Szerokosc DOUBLE NOT NULL,
	Rozmiar_Dlugosc DOUBLE NOT NULL, 
	Cena DOUBLE DEFAULT NULL,
	Nazwa_pliku_rysunku VARCHAR(45) NOT NULL,

	PRIMARY KEY (Id_Proj_polprod)
);

CREATE TABLE IF NOT EXISTS POLPRODUKT
( 
	Id_Polprod INT NOT NULL AUTO_INCREMENT,
	Id_Proj_polprod INT NOT NULL,
	Id_Mebla INT NOT NULL,
	NrZamowienia INT DEFAULT NULL,	

	PRIMARY KEY (Id_Polprod)
);

CREATE TABLE IF NOT EXISTS DOSTAWCA
(
  	Id_Dostawcy int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Email varchar(50) NOT NULL,
  	Numer_konta_bankowego varchar(26) NOT NULL,
  	Adres_AdresPocztowy varchar(200) NOT NULL,
  	Adres_Miejscowosc varchar(100) NOT NULL,
  	Adres_Ulica varchar(100) NOT NULL,
  	Adres_Numer varchar(10) NOT NULL,
	
	PRIMARY KEY (Id_Dostawcy),
	UNIQUE KEY (Numer_konta_bankowego)
);

CREATE TABLE IF NOT EXISTS KLIENT 
(
  	Id_Klienta int(11) NOT NULL AUTO_INCREMENT,
  	Imie varchar(20) NOT NULL,
  	Nazwisko varchar(20) NOT NULL,
  	Numer_telefonu varchar(10) NOT NULL,
  	Email varchar(50) NOT NULL,
	Adres_Kraj varchar(50) NOT NULL,
  	Adres_AdresPocztowy varchar(200) NOT NULL,
  	Adres_Miejscowosc varchar(100) NOT NULL,
  	Adres_Ulica varchar(100) NOT NULL,
  	Adres_NumerDomu varchar(10) NOT NULL,
	Adres_NumerMieszkania varchar(10) NULL,
	Login varchar(45) NOT NULL,
	Haslo varchar(45) NOT NULL,
	
	PRIMARY KEY (Id_Klienta),
	UNIQUE KEY (Login)
);

CREATE TABLE IF NOT EXISTS MATERIAL_MEBEL
(
  	Id_Mat_mebel int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	Id_Mebla int(11) NOT NULL,
	Ilosc int(11) DEFAULT NULL,
	
	PRIMARY KEY (Id_Mat_mebel)
);

CREATE TABLE IF NOT EXISTS MATERIAL
(
  	Id_Materialu int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Cena float NOT NULL,
  	Id_Koloru INT NOT NULL,
  	Klasa INT DEFAULT NULL,
  	Id_Wzoru INT DEFAULT NULL,
  	Id_Rodzaju_materialu INT NOT NULL,
  	Rozmiar varchar(40) NOT NULL,
	
	PRIMARY KEY (Id_Materialu)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_KOMPONENTY 
( 
	NrZamowienia INT NOT NULL AUTO_INCREMENT,
	Id_Dostawcy int(11) NOT NULL,	
	Stan_realizacji BOOLEAN NOT NULL, 
   	Czas_realizacji_Data_rozpoczecia Date NOT NULL, 
	Czas_realizacji_Data_zakonczenia Date DEFAULT NULL,

	PRIMARY KEY (NrZamowienia)
);

CREATE TABLE IF NOT EXISTS MATERIAL_ZAMOW_KOMP
(
  	Id_Mat_zam int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	NrZamowienia int(11) NOT NULL,
	Ilosc int(11) NOT NULL,
	
	PRIMARY KEY (Id_Mat_zam)
);

CREATE TABLE MATERIAL_DOSTAWCA (
  Id_Mat_dost int(11) NOT NULL AUTO_INCREMENT,
  Id_Materialu int(11) NOT NULL,
  Id_Dostawcy int(11) NOT NULL,
  
  PRIMARY KEY (Id_Mat_dost)
); 

CREATE TABLE IF NOT EXISTS MEBEL
(
  	Id_Mebla int(11) NOT NULL AUTO_INCREMENT,
  	Id_Zamowienia int(11) NOT NULL,
  	Id_Proj_klient int(11) DEFAULT NULL,
  	Id_Proj_katalog int(11) DEFAULT NULL,
	Id_Opcj_czesci int(11) DEFAULT NULL,
	Wykonany BOOLEAN DEFAULT NULL,
	
	PRIMARY KEY (Id_Mebla)
);

CREATE TABLE IF NOT EXISTS MATERIAL_PROJ_KLIENTA
(
  	Id_Mat_Proj_klient int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	Id_Proj_klient int(11) NOT NULL,
	Ilosc Int DEFAULT NULL,
	
	PRIMARY KEY (Id_Mat_Proj_klient)
);

CREATE TABLE IF NOT EXISTS PROJEKT_KLIENTA
(
  	Id_Proj_klient int(11) NOT NULL AUTO_INCREMENT,
  	Id_Typu_mebla int(11) NOT NULL,
  	Id_Laczenia int(11) NOT NULL,
  	Wymiary_Szerokosc int(11) NOT NULL,
  	Wymiary_Wysokosc int(11) NOT NULL,
  	Wymiary_Dlugosc int(11) NOT NULL,
  	Nazwa_pliku_rysunku varchar(40) NOT NULL,
	
	PRIMARY KEY (Id_Proj_klient)
);

CREATE TABLE IF NOT EXISTS OPCJONALNA_CZESC
(
  	Id_Opcj_czesci int(11) NOT NULL AUTO_INCREMENT,
	Id_Proj_katalog int(11) NOT NULL,
  	Nazwa varchar(45) NOT NULL,
	
	PRIMARY KEY (Id_Opcj_czesci)
);

CREATE TABLE IF NOT EXISTS MATERIAL_PROJ_KATALOG
(
  	Id_Mat_Proj_katalog int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	Id_Proj_katalog int(11) NOT NULL,
	Ilosc Int NOT NULL,
	
	PRIMARY KEY (Id_Mat_Proj_katalog)
);

CREATE TABLE IF NOT EXISTS PROJEKT_Z_KATALOGU
(
  	Id_Proj_katalog int(11) NOT NULL AUTO_INCREMENT,
  	Id_Typu_mebla int(11) NOT NULL,
  	Id_Laczenia int(11) NOT NULL,
  	Nazwa varchar(20) NOT NULL,
  	Marza float NOT NULL,
  	Wymiary_Szerokosc int(11) NOT NULL,
  	Wymiary_Wysokosc int(11) NOT NULL,
  	Wymiary_Dlugosc int(11) NOT NULL,
	
	PRIMARY KEY (Id_Proj_katalog)
);


CREATE TABLE IF NOT EXISTS ZADANIE
(
  	Id_Zadania int(11) NOT NULL AUTO_INCREMENT,
	Id_Pracownika int(11) NOT NULL,
	Id_Mebla int(11) NOT NULL,
	
	PRIMARY KEY (Id_Zadania)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_MEBLE (
  	Id_Zamowienia int(11) NOT NULL AUTO_INCREMENT,
  	Id_Klienta int(11) NOT NULL,
  	Id_Stanu_Realizacji int(11) NOT NULL,
  	Czas_realizacji_Data_zlozenia date NOT NULL,
  	Czas_Realizacji_Data_zakonczenia date DEFAULT NULL,
	
	PRIMARY KEY (Id_Zamowienia)
);

CREATE TABLE IF NOT EXISTS REKLAMACJA
(
  	Id_Reklamacji int(11) NOT NULL AUTO_INCREMENT,
	Id_Mebla int(11) NOT NULL,
	Opis_reklamacji varchar(200),
	
	PRIMARY KEY (Id_Reklamacji)
);

ALTER TABLE PRACOWNIK
ADD FOREIGN KEY (Id_Stanowiska) REFERENCES STANOWISKO(Id_Stanowiska);

ALTER TABLE CENA
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD FOREIGN KEY (Id_Pracownika) REFERENCES PRACOWNIK (Id_Pracownika);

ALTER TABLE DEFINICJA_ZADANIA
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE PROJEKT_POLPRODUKTU
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog),
ADD	FOREIGN KEY (Id_Rodzaju_polproduktu) REFERENCES RODZAJ_POLPRODUKTU(Id_Rodzaju_polproduktu),
ADD FOREIGN KEY (Id_Dostawcy) REFERENCES DOSTAWCA(Id_Dostawcy);

ALTER TABLE POLPRODUKT
ADD FOREIGN KEY (Id_Proj_polprod) REFERENCES PROJEKT_POLPRODUKTU(Id_Proj_polprod),
ADD FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla),
ADD FOREIGN KEY (NrZamowienia) REFERENCES ZAMOWIENIE_NA_KOMPONENTY(NrZamowienia);

ALTER TABLE MATERIAL
ADD FOREIGN KEY (Id_Koloru) REFERENCES KOLOR(Id_Koloru),
ADD FOREIGN KEY (Id_Wzoru) REFERENCES WZOR(Id_Wzoru),
ADD FOREIGN KEY (Id_Rodzaju_materialu) REFERENCES RODZAJ_MATERIALU(Id_Rodzaju_materialu);

ALTER TABLE MATERIAL_MEBEL
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla);

ALTER TABLE MATERIAL_PROJ_KLIENTA
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient);

ALTER TABLE PROJEKT_KLIENTA
ADD FOREIGN KEY (Id_Typu_mebla) REFERENCES TYP_MEBLA(Id_Typu_mebla),
ADD FOREIGN KEY (Id_Laczenia) REFERENCES LACZENIA(Id_Laczenia);

ALTER TABLE OPCJONALNA_CZESC
ADD FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE MEBEL
ADD FOREIGN KEY (Id_Zamowienia) REFERENCES ZAMOWIENIE_NA_MEBLE(Id_Zamowienia),
ADD	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog),
ADD	FOREIGN KEY (Id_Opcj_czesci) REFERENCES OPCJONALNA_CZESC(Id_Opcj_czesci);

ALTER TABLE MATERIAL_PROJ_KATALOG
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE PROJEKT_Z_KATALOGU
ADD FOREIGN KEY (Id_Typu_mebla) REFERENCES TYP_MEBLA(Id_Typu_mebla),
ADD FOREIGN KEY (Id_Laczenia) REFERENCES LACZENIA(Id_Laczenia);

ALTER TABLE ZADANIE
ADD FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla),
ADD FOREIGN KEY (Id_Pracownika) REFERENCES PRACOWNIK (Id_Pracownika);

ALTER TABLE ZAMOWIENIE_NA_MEBLE
ADD FOREIGN KEY (Id_Klienta) REFERENCES KLIENT(Id_Klienta),
ADD FOREIGN KEY (Id_Stanu_Realizacji) REFERENCES STAN_REALIZACJI(Id_Stanu_realizacji);

ALTER TABLE REKLAMACJA
ADD FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla);

ALTER TABLE MATERIAL_ZAMOW_KOMP
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD FOREIGN KEY (NrZamowienia) REFERENCES ZAMOWIENIE_NA_KOMPONENTY(NrZamowienia);

ALTER TABLE ZAMOWIENIE_NA_KOMPONENTY 
ADD FOREIGN KEY (Id_Dostawcy) REFERENCES DOSTAWCA(Id_Dostawcy);

ALTER TABLE MATERIAL_DOSTAWCA
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD FOREIGN KEY (Id_Dostawcy) REFERENCES DOSTAWCA(Id_Dostawcy);
	

USE FIRMA;

-- Słowniki

INSERT INTO LACZENIA (Id_Laczenia, Nazwa) VALUES
(1, 'wkręty'),
(2, 'klej'),
(3, 'wciskowe'),
(4, 'kołki'),
(5, 'mimośrody i trzpienie'),
(6, 'gwintowe');

INSERT INTO STAN_REALIZACJI (Id_Stanu_realizacji, Nazwa_Stanu) VALUES
(1, 'W realizacji'),
(2, 'Gotowe do odebrania'),
(3, 'Oczekuje na zatwierdzenie'),
(4, 'Wyceniono'),
(5, 'Odrzucono'),
(6, 'Odebrano');


INSERT INTO TYP_MEBLA (Id_Typu_mebla, Nazwa) VALUES
(1, 'Stół'),
(2, 'Krzesło'),
(3, 'Fotel'),
(4, 'Łóżko'),
(5, 'Sofa'),
(6, 'Biurko'),
(7, 'Szafa'),
(8, 'Komoda'),
(9, 'Szafka nocna'),
(10, 'Narożnik'),
(11, 'Regał'),
(12, 'Kredens');

INSERT INTO RODZAJ_MATERIALU (Id_Rodzaju_materialu, Nazwa) VALUES
(1, 'Tkanina obiciowa'),
(2, 'Płyta meblowa'),
(3, 'Tarcica'),
(4, 'Pianka tapicerska'),
(5, 'Okleina');

INSERT INTO KOLOR (Id_Koloru, Nazwa) VALUES
(1, 'niebieski'),
(2, 'zielony'),
(3, 'brązowy'),
(4, 'szary'),
(5, 'czerwony'),
(6, 'żółty'),
(7, 'czarny'),
(8, 'pomarańczowy');

INSERT INTO WZOR(Id_Wzoru, Nazwa) VALUES
(1, 'pasy z rombami'),
(2, 'ornamenty'),
(3, 'kwiaty'),
(4, 'pasy i kratka'),
(5, 'romby'),
(6, 'pasy');

INSERT INTO RODZAJ_POLPRODUKTU (Id_Rodzaju_polproduktu, Nazwa) VALUES
(1, 'Stelaz'),
(2, 'Rama'),
(3, 'Front'),
(4, 'Dekory'),
(5, 'Klienta');

INSERT INTO STANOWISKO (Id_Stanowiska, Nazwa_Stanowiska) VALUES
(1, 'Pracownik fizyczny'),
(2, 'Technolog'),
(3, 'Logistyk'),
(4, 'Kierownik');

-- Koniec słowników

INSERT INTO MATERIAL (Id_Materialu, Nazwa, Cena, Id_Koloru, Klasa, Id_Wzoru, Id_Rodzaju_materialu, Rozmiar) VALUES
(1, 'flok', 120, 1, 1, 1, 1, '1mb'),
(2, 'welwet', 130, 2, 1, 2, 1, '1mb'),
(3, 'ekoskóra', 110, 5, 1, 3, 1, '1mb'),
(4, 'szenil', 100, 6, 1, 4, 1, '1mb'),
(5, 'płyta wiórowa', 42, 3, 2, NULL, 2, '1m^2 x 18 mm'),
(6, 'płyta paździerzowa', 30, 3, 2, NULL, 2, '1m^2 x 12 mm'),
(7, 'płyta pilśniowa', 10, 3, 2, NULL, 2, '1m^2 x 3 mm'),
(8, 'płyta MDF', 56, 3, 2, NULL, 2, '1m^2 x 10 mm'),
(9, 'deska dębowa', 1500, 3, 1, NULL, 3, '1m^3'),
(10, 'drewno bukowe', 720, 3, 1, NULL, 3, '1m^3'),
(11, 'drewno sosnowe', 760, 3, 1, NULL, 3, '1m^3'),
(12, 'drewno brzozowe', 650, 3, 1, NULL, 3, '1m^3'),
(13, 'drewno hebanowe', 1200, 3, 1, NULL, 3, '1m^3'),
(14, 'pianka poliuretanowa', 175, 4, 30, NULL, 4, '1m^3'),
(15, 'pianka wysokoeleasty', 300, 4, 25, NULL, 4, '1m^3'),
(16, 'pianka polieterowa', 180, 4, 35, NULL, 4, '1m^3'),
(17, 'fornir naturalny', 14, 3, 1, NULL, 5, '1mb'),
(18, 'fornir modyfikowany', 30, 7, 1, 5, 5, '1mb'),
(19, 'okleina PCV', 25, 8, 1, 6, 5, '1mb');

INSERT INTO PRACOWNIK (Id_Pracownika, Id_Stanowiska, Data_urodzenia, Imie, Nazwisko, Numer_konta_bankowego, Zarobki, Numer_telefonu, Adres_Kod, Adres_Miasto, Adres_Ulica, Login, Haslo) VALUES
(1, 1, '1956-06-15', 'Borys', 'Zakrzewski', '95249010289970091125976427', 3000, '997-015-469', '70-513', 'Szczecin', 'Małopolska 148/2', 'Borys134', 'mhfojv5p2q'),
(2, 3, '1996-06-04', 'Milan', 'Rutkowski', '25249010154483197770208474', 4000, '188-482-975', '90-954', 'Łódź', 'Al. Kościuszki 97', 'Milan24', '7o28ua4jiz'),
(3, 1, '1995-05-15', 'Fryderyk', 'Lewandowski', '70249010443972240527274151', 3000, '739-722-015', '32-305', 'Olkusz', 'Krasińskiego 72/3', 'Fryderyk67', 'nbczpbad9s'),
(4, 1, '1975-05-01', 'Alex', 'Walczak', '45249010318881704000536399', 3000, '744-446-093', '15-161', 'Białystok', 'Czarnej Hańczy 7', 'Alex12', 'qva6ku1pk3'),
(5, 2, '1996-12-26', 'Artur', 'Kołodziej', '09249010447474422165839092', 3500, '748-686-884', '05-075', 'Warszawa', 'I Pułku Praskiego 108/5', 'Artur56', 'grsi0mt55b'),
(6, 3, '1998-10-22', 'Lucyna', 'Cieślak', '93249010441741221091577543', 4000, '727-799-091', '50-114', 'Wrocław', 'Odrzańska 127', 'Lucyna17', '4tmhsbs2p2'),
(7, 1, '1979-11-12', 'Balbina', 'Dąbrowska', '72249010284538998156445325', 3000, '580-035-635', '01-158', 'Warszawa', 'Złocienia 3/7', 'Balbina23', 'vfy6mj0n8k'),
(8, 1, '1965-01-20', 'Anita', 'Adamska', '04249010152485639713389353', 3000, '223-192-870', '85-430', 'Bydgoszcz', 'Jarząbkowa 12', 'Anita67', 'rocj7c60ib'),
(9, 2, '1998-10-10', 'Faustyna', 'Sadowska', '57249010579142041118217285', 3500, '175-855-865', '59-202', 'Legnica', 'Nowy Świat 58', 'Faustyna89', 'cm4y3wawz0'),
(10, 4, '1957-06-25', 'Julita', 'Mazur', '48249010571218574983266911', 5000, '109-202-875', '70-352', 'Szczecin', 'Księdza Ściegiennego Piotra 34', 'Julita76', 'zvgx12g2pp');

INSERT INTO DOSTAWCA (Id_Dostawcy, Nazwa, Email, Numer_konta_bankowego, Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_Numer) VALUES
(1, 'FTransport', 'ftransport@gmail.com', '56782491346520215687524589', '43-421', 'Poznan', 'Widok', '9'),
(2, 'BigTransit', 'bigtransit@wp.pl', '53154097207946681000682451', '41-558', 'Koszalin', 'Rzemieślnicza', '28'),
(3, 'SzybkieDostawy', 'szybkiedostawy@o2.pl', '86193076266895453453276931', '43-421', 'Leszno', 'Piastowska', '12'),
(4, 'XeDef', 'xedef@gmail.com', '61154062116032497746659251', '92-256', 'Kraków', 'Lubińska', '67'),
(5, 'NTN', 'ntn@onet.pl', '94187099089534543073777450', '10-682', 'Gdynia', 'Okopowa', '56'),
(6, 'pdp', 'pdp@gmail.com', '75194026083794609212034698', '98-395', 'Zabrze', 'Usługowa', '48');

INSERT INTO PROJEKT_Z_KATALOGU(Id_Proj_katalog, Id_Typu_mebla, Id_Laczenia, Nazwa, Marza, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc) VALUES
(1, 1, 2, 'VEDBO', 500, 950, 750, 1600),
(2, 1, 4, 'EKEDALEN', 240, 900, 730, 2400),
(3, 2, 4, 'ODGER', 39, 410, 810, 380),
(4, 2, 1, 'INGOLF', 50, 420, 910, 380),
(5, 3, 1, 'EKENASET', 270, 640, 760, 780),
(6, 3, 1, 'STRANDMON', 500, 820, 1010, 960),
(7, 4, 1, 'MALM', 130, 1350, 1000, 2090),
(8, 4, 1, 'SONGESAND', 400, 1530, 950, 2070),
(9, 5, 1, 'ANGSTA', 340, 2000, 420, 820),
(10, 5, 1, 'LINANAS', 400, 1360, 800, 800),
(11, 6, 1, 'GRUPPSPEL', 1200, 1800, 1500, 800),
(12, 6, 1, 'TROTTEN', 400, 1600, 1200, 800),
(13, 7, 1, 'PAX', 300, 1750, 2012, 580),
(14, 7, 1, 'VISTHUS', 415, 1219, 2157, 569),
(15, 8, 1, 'MALM', 120, 1600, 780, 480),
(16, 8, 1, 'ALEX', 40, 360, 700, 580),
(17, 9, 1, 'KULLEN', 30, 350, 490, 400),
(18, 9, 1, 'KVISTBRO', 40, 400, 420, 400),
(19, 10, 1, 'KIVIK', 545, 2800, 830, 1630),
(20, 10, 1, 'BENNEBOL', 399, 2200, 830, 2020),
(21, 11, 1, 'KALLAX', 200, 770, 1470, 390),
(22, 11, 1, 'BILLY', 380, 800, 2020, 280),
(23, 12, 1, 'VIHALS', 900, 1400, 750, 370),
(24, 12, 1, 'KLACKENAS', 360, 1200, 970, 410);

INSERT INTO OPCJONALNA_CZESC (Id_Opcj_czesci, Id_Proj_katalog, Nazwa) VALUES
(1, 1, 'nogi z regulowaną wysokością'),
(2, 1, 'nogi z kółkami'),
(3, 1, 'rozsuwany blat'),
(4, 2, 'nogi z regulowaną wysokością'),
(5, 2, 'nogi z kółkami'),
(6, 2, 'rozsuwany blat'),
(7, 3, 'podłokietniki'),
(8, 3, 'oparcie wygięte w łuk'),
(9, 3, 'gąbka na siedzisku'),
(10, 4, 'podłokietniki'),
(11, 4, 'oparcie wygięte w łuk'),
(12, 4, 'gąbka na siedzisku'),
(13, 5, 'rozkładany podnóżek '),
(14, 5, 'podłokietniki wygięte w łuk'),
(15, 5, 'tapicerowane podłokietniki'),
(16, 6, 'rozkładany podnóżek '),
(17, 6, 'podłokietniki wygięte w łuk'),
(18, 6, 'tapicerowane podłokietniki'),
(19, 7, 'skrzynia po łóżkiem'),
(20, 7, 'składany materac'),
(21, 7, 'oparcie'),
(22, 8, 'skrzynia po łóżkiem'),
(23, 8, 'składany materac'),
(24, 8, 'oparcie'),
(25, 9, 'funkcja spania'),
(26, 9, 'tapicerowane podłokietniki'),
(27, 10, 'funkcja spania '),
(28, 10, 'tapicerowane podłokietniki'),
(29, 11, 'ręczna regulacja wysokości '),
(30, 11, 'elektryczna regulacja wysokości '),
(31, 11, 'prowadnica na kable '),
(32, 12, 'ręczna regulacja wysokości '),
(33, 12, 'elektryczna regulacja wysokości '),
(34, 12, 'prowadnica na kable '),
(35, 13, 'przesuwane drzwi'),
(36, 13, 'wysuwana półka'),
(37, 14, 'przesuwane drzwi'),
(38, 14, 'wysuwana półka'),
(39, 15, 'nadstawka'),
(40, 15, 'zdobione drzwiczki'),
(41, 16, 'nadstawka'),
(42, 16, 'zdobione drzwiczki'),
(43, 17, 'drzwiczki '),
(44, 17, 'nadstawka'),
(45, 18, 'drzwiczki '),
(46, 18, 'nadstawka'),
(47, 19, 'funkcja spania '),
(48, 19, 'dostawka'),
(49, 19, 'funkcja relaksu '),
(50, 20, 'funkcja spania '),
(51, 20, 'dostawka'),
(52, 20, 'funkcja relaksu '),
(53, 21, 'przesuwane przednie drzwiczki '),
(54, 21, 'nadstawka'),
(55, 22, 'przesuwane przednie drzwiczki '),
(56, 22, 'nadstawka'),
(57, 23, 'przesuwane przednie drzwiczki '),
(58, 24, 'przesuwane przednie drzwiczki ');

INSERT INTO MATERIAL_PROJ_KATALOG (Id_Mat_Proj_katalog, Id_Materialu, Id_Proj_katalog, Ilosc) VALUES
(1, 5, 1, 2),
(2, 9, 1, 2),
(3, 7, 2, 2),
(4, 10, 2, 2),
(5, 3, 3, 4),
(6, 8, 3, 1),
(7, 12, 3, 1),
(8, 4, 4, 1),
(9, 6, 4, 1),
(10, 10, 4, 1),
(11, 1, 5, 1),
(12, 5, 5, 2),
(13, 11, 5, 3),
(14, 16, 5, 2),
(15, 1, 6, 1),
(16, 7, 6, 4),
(17, 9, 6, 4),
(18, 16, 6, 3),
(19, 1, 7, 1),
(20, 5, 7, 3),
(21, 11, 7, 1),
(22, 16, 7, 4),
(23, 17, 7, 3),
(24, 2, 8, 1),
(25, 5, 8, 1),
(26, 11, 8, 1),
(27, 14, 8, 2),
(28, 17, 8, 2),
(29, 3, 9, 2),
(30, 5, 9, 3),
(31, 9, 9, 3),
(32, 15, 9, 1),
(33, 18, 9, 1),
(34, 1, 10, 3),
(35, 6, 10, 4),
(36, 12, 10, 4),
(37, 16, 10, 4),
(38, 18, 10, 2),
(39, 6, 11, 3),
(40, 12, 11, 1),
(41, 18, 11, 2),
(42, 6, 12, 1),
(43, 11, 12, 3),
(44, 19, 12, 1),
(45, 6, 13, 1),
(46, 11, 13, 2),
(47, 17, 13, 1),
(48, 7, 14, 2),
(49, 12, 14, 1),
(50, 17, 14, 4),
(51, 6, 15, 1),
(52, 11, 15, 3),
(53, 17, 15, 2),
(54, 5, 16, 4),
(55, 12, 16, 2),
(56, 17, 16, 2),
(57, 8, 17, 1),
(58, 11, 17, 1),
(59, 19, 17, 4),
(60, 7, 18, 2),
(61, 10, 18, 1),
(62, 17, 18, 4),
(63, 3, 19, 1),
(64, 8, 19, 3),
(65, 13, 19, 3),
(66, 14, 19, 4),
(67, 4, 20, 4),
(68, 5, 20, 3),
(69, 10, 20, 4),
(70, 16, 20, 3),
(71, 6, 21, 2),
(72, 12, 21, 1),
(73, 18, 21, 1),
(74, 7, 22, 2),
(75, 10, 22, 4),
(76, 19, 22, 4),
(77, 6, 23, 1),
(78, 10, 23, 4),
(79, 18, 23, 3),
(80, 8, 24, 1),
(81, 9, 24, 2),
(82, 18, 24, 2);

INSERT INTO PROJEKT_POLPRODUKTU (Id_Proj_polprod, Id_Proj_klient, Id_Proj_katalog, Id_Rodzaju_polproduktu, Id_Dostawcy, Nazwa, Rozmiar_Wysokosc, Rozmiar_Szerokosc, Rozmiar_Dlugosc, Cena, Nazwa_pliku_rysunku) VALUES
(1, NULL, 7, 1, 4, 'FUTURA NV', 60, 1000, 2000, 270, 'futura.dwg'),
(2, NULL, 8, 1, 4, 'FUTURA NV', 60, 1400, 2000, 305, 'futura2.dwg'),
(4, NULL, 9, 1, 4, 'NATURA NV', 55, 1200, 2000, 215, 'natura2.jpg'),
(5, NULL, 2, 2, 6, 'RAMA STOLU STALOWA', 450, 600, 900, 215, 'rama_stol.png'),
(6, NULL, 24, 2, 6, 'RAMA KREDENSU STALOWA', 600, 300, 600, 250, 'rama_kredens.dwg'),
(7, NULL, 13, 3, 6, 'DRZWICZKI AZUROWE', 2000, 20, 600, 440, 'front_azur.jpg'),
(8, NULL, 14, 3, 5, 'FRONT MDF', 2000, 20, 600, 240, 'mdf1.jpg'),
(9, NULL, 15, 3, 5, 'FRONT MDF', 600, 20, 250, 200, 'mdf2.jpg'),
(10, NULL, 16, 3, 6, 'LUSTRZANY FRONT', 600, 20, 250, 950, 'front_lustro.jpg'),
(11, NULL, 17, 3, 5, 'FRONT BUKOWY', 400, 20, 400, 400, 'Front_buk.png'),
(12, NULL, 18, 3, 5, 'FRONT MDF', 400, 20, 450, 220, 'mdf3.png'),
(13, NULL, 23, 3, 5, 'FRONT RZEZBIONY', 400, 20, 600, 500, 'front_rzezbiony.dwg'),
(14, NULL, 24, 3, 5, 'FRONT FREZOWANY', 400, 20, 600, 420, 'front_frez.jpg'),
(15, NULL, 7, 4, 6, 'DEKOR DREWNIANY', 100, 5, 200, 30, 'dekor.jpg'),
(16, NULL, 23, 4, 6, 'DEKOR DREWNIANY NAROZNY', 100, 5, 100, 25, 'dekor_dr_nar.png');

INSERT INTO DEFINICJA_ZADANIA (Id_Def_zadania, Id_Proj_klient, Id_Proj_katalog, Opis_zadania, Cena, Czas_wykonania) VALUES
(1, NULL, 1, 'przygotuj deski dębowe', 25, 1),
(2, NULL, 1, 'dotnij deski na 2m', 25, 5),
(3, NULL, 1, 'hebluj deski dębowe', 25, 5),
(4, NULL, 1, 'sklej deski na blat klejem aminowym', 25, 1),
(5, NULL, 1, 'przygotuj deski sosnowe na nogi ', 25, 5),
(6, NULL, 1, 'wytnij nogi według szablonu pierwszego', 25, 1),
(7, NULL, 1, 'obrób szablon nóg przy pomocy frezaki', 25, 1),
(8, NULL, 1, 'umieść sklejony blat na centrum obróbczym ', 25, 1),
(9, NULL, 1, 'sklej blat klejem ftalowym', 25, 3),
(10, NULL, 1, 'zmontuj stół na śruby', 25, 1),
(11, NULL, 2, 'przygotuj deski sosnowe', 25, 5),
(12, NULL, 2, 'dotnij deski na 1m', 25, 1),
(13, NULL, 2, 'hebluj deski sosnowe', 25, 3),
(14, NULL, 2, 'sklej deski na blat klejem ftalowym', 25, 4),
(15, NULL, 2, 'przygotuj deski bukowe na nogi ', 25, 3),
(16, NULL, 2, 'wytnij nogi według szablonu drugiego', 25, 2),
(17, NULL, 2, 'obrób szablon nóg ręcznie', 25, 1),
(18, NULL, 2, 'umieść sklejony blat na nogach', 25, 4),
(19, NULL, 2, 'sklej blat klejem aminowym', 25, 3),
(20, NULL, 2, 'zmontuj stół na kołki', 25, 1),
(21, NULL, 3, 'wytnij drewaniane listwy na 2,5m', 25, 5),
(22, NULL, 3, 'wygnij elementy oparcia w łuk', 25, 5),
(23, NULL, 3, 'oklej siedzisko fornirem naturalnym', 25, 2),
(24, NULL, 3, 'otapiceruj siedzisko flokiem', 25, 3),
(25, NULL, 4, 'wytnij drewaniane listwy na 1m', 25, 5),
(26, NULL, 4, 'wygnij elementy oparcia w koło', 25, 3),
(27, NULL, 4, 'oklej siedzisko fornirem modyfikowanym', 25, 5),
(28, NULL, 4, 'otapiceruj siedzisko welwetem', 25, 4),
(29, NULL, 5, 'wytnij elementy stelażu z stali C20', 25, 2),
(30, NULL, 5, 'zbij stelaż w kwadrat', 25, 5),
(31, NULL, 5, 'oklej stelaż pianką poliureatnową', 25, 2),
(32, NULL, 5, 'zrób roskrój tkanin  ekoskóry', 25, 5),
(33, NULL, 5, 'zatapiceruj ekoskórą', 25, 5),
(34, NULL, 6, 'wytnij elementy stelażu z stali C25', 25, 4),
(35, NULL, 6, 'zbij stelaż w trójkąt', 25, 4),
(36, NULL, 6, 'oklej stelaż pianką wysokoelastyczną', 25, 3),
(37, NULL, 6, 'zrób roskrój tkanin szenilu', 25, 5),
(38, NULL, 6, 'zatapiceruj welwetem', 25, 3),
(39, NULL, 7, 'dotnij płytę paździerzową na skrzynię ', 25, 1),
(40, NULL, 7, 'dotnij płytę pilśniową na zagłówek ', 25, 1),
(41, NULL, 7, 'dodaj otwory montażowe ', 25, 3),
(42, NULL, 7, 'zbij skrzynię ', 25, 4),
(43, NULL, 7, 'oklej zagłówek okleiną PCV', 25, 1),
(44, NULL, 8, 'dotnij płytę wiórową na skrzynię ', 25, 3),
(45, NULL, 8, 'dotnij płytę MDF na zagłówek ', 25, 4),
(46, NULL, 8, 'dodaj kołki montażowe ', 25, 2),
(47, NULL, 8, 'skręć skrzynię ', 25, 5),
(48, NULL, 8, 'oklej zagłówek formirem naturalnym', 25, 2),
(49, NULL, 9, 'wytnij elementy stelażu w prostopadłościan', 25, 2),
(50, NULL, 9, 'wytnnij elementy boczka z płyty wiórowej', 25, 1),
(51, NULL, 9, 'zbij stelaż ', 25, 3),
(52, NULL, 9, 'oklej stelaż pianką wysokoelastyczną', 25, 5),
(53, NULL, 9, 'zrób roskrój szenilu', 25, 4),
(54, NULL, 9, 'zatapiceruj ekoskórą', 25, 2),
(55, NULL, 10, 'wytnij elementy stelażu w ostrosłup', 25, 3),
(56, NULL, 10, 'wytnnij elementy boczka z płyty paździerzowej', 25, 2),
(57, NULL, 10, 'skręć stelaż ', 25, 4),
(58, NULL, 10, 'oklej stelaż pianką poliueratnową', 25, 2),
(59, NULL, 10, 'zrób roskrój wlwetu', 25, 4),
(60, NULL, 10, 'zatapiceruj flokiem', 25, 4),
(61, NULL, 11, 'wytnij element blatu w okrąg', 25, 4),
(62, NULL, 11, 'wytnij płytę na nogi z drewna sosnowego', 25, 3),
(63, NULL, 11, 'polakieruj elementy lakierem bezbarwnym', 25, 2),
(64, NULL, 11, 'nawierć otwory na kołki ', 25, 2),
(65, NULL, 11, 'sklej elementy klejem do drewna', 25, 4),
(66, NULL, 12, 'wytnij element blatu w prostokąt', 25, 3),
(67, NULL, 12, 'wytnij płytę na nogi z drewna dębowego', 25, 3),
(68, NULL, 12, 'polakieruj elementy lakierem brązowym', 25, 1),
(69, NULL, 12, 'nawierć otwory na wkęty', 25, 2),
(70, NULL, 12, 'sklej elementy klejem akrylowym', 25, 1),
(71, NULL, 13, 'wytnij fronty z płyty lamniowanej', 25, 5),
(72, NULL, 13, 'zamontuj prowadnice stalowe', 25, 4),
(73, NULL, 13, 'zamontuj zawiasy alumniowe', 25, 3),
(74, NULL, 13, 'zamontuj pułki z drewna sosnowego', 25, 3),
(75, NULL, 14, 'wytnij fronty z płyty pilśniowej', 25, 3),
(76, NULL, 14, 'zamontuj prowadnice ', 25, 4),
(77, NULL, 14, 'zamontuj zawiasy ', 25, 2),
(78, NULL, 14, 'zamontuj pułki na kołki', 25, 3),
(79, NULL, 15, 'przygotuj deski', 25, 2),
(80, NULL, 15, 'dotnij deski ', 25, 4),
(81, NULL, 15, 'hebluj deki ', 25, 5),
(82, NULL, 15, 'wywierć otwory na centum obróbczym ', 25, 2),
(83, NULL, 16, 'przygotuj deski', 25, 5),
(84, NULL, 16, 'dotnij deski na długość 580mm', 25, 4),
(85, NULL, 16, 'hebluj deski', 25, 4),
(86, NULL, 16, 'wytnij fronty na frezarce numerycznej ', 25, 1),
(87, NULL, 17, 'dotnij drewniane listwy ', 25, 5),
(88, NULL, 17, 'dotnij elementy z płyty na szuflady ', 25, 3),
(89, NULL, 17, 'polakieruj ', 25, 3),
(90, NULL, 17, 'zmontuj ', 25, 2),
(91, NULL, 18, 'ofrezuj elementy nóżek frezem kształtowym ', 25, 5),
(92, NULL, 18, 'dotnij elementy z płyty laminowanej na szuflady ', 25, 3),
(93, NULL, 18, 'nawierć otwory pod prowadnice szuflady ', 25, 4),
(94, NULL, 18, 'pomaluj ', 25, 1),
(95, NULL, 19, 'wytnij elementy stelażu na frezarce numerycznej ', 25, 3),
(96, NULL, 19, 'wytnnij elementy boczka z płyty ', 25, 2),
(97, NULL, 19, 'zbij stelaż ', 25, 3),
(98, NULL, 19, 'oklej stelaż pianką ', 25, 3),
(99, NULL, 19, 'zrób roskrój tkanin ', 25, 1),
(100, NULL, 19, 'zatapiceruj ', 25, 1),
(101, NULL, 19, 'zamontuj zaczepy ', 25, 2),
(102, NULL, 20, 'wytnij elementy stelażu na frezarce numerycznej ', 25, 3),
(103, NULL, 20, 'pomaluj drewniane elementy ozdobne ', 25, 4),
(104, NULL, 20, 'wytnij drewniane listwy konstrukcyjne', 25, 4),
(105, NULL, 20, 'zatapiceruj ', 25, 4),
(106, NULL, 21, 'wytnij elementy z płyty ', 25, 3),
(107, NULL, 21, 'oklej fornty białą okleiną', 25, 2),
(108, NULL, 21, 'nawierć otwory pod mimośrody ', 25, 4),
(109, NULL, 21, 'umieść element na frezarce numerycznej ', 25, 3),
(110, NULL, 22, 'wytnij elementy ze sklejki ', 25, 5),
(111, NULL, 22, 'oklej fornty czarną okleiną', 25, 1),
(112, NULL, 22, 'nawierć otwory pod kołki ', 25, 4),
(113, NULL, 22, 'umieść element na frezarce numerycznej ', 25, 2),
(114, NULL, 23, 'wytnij fronty z płyty lamniowanej, orzech ', 25, 3),
(115, NULL, 23, 'zamontuj prowdnice długości 345mm', 25, 4),
(116, NULL, 23, 'zamontuj zawiasy, STAR', 25, 3),
(117, NULL, 23, 'zmontuj wszystkie elementy ', 25, 4),
(118, NULL, 24, 'wytnij fronty z płyty lamniowanej, wiśnia  ', 25, 4),
(119, NULL, 24, 'zamontuj prowdnice długości 390', 25, 5),
(120, NULL, 24, 'zamontuj zawiasy GTV', 25, 3),
(121, NULL, 24, 'zmontuj wszystkie elementy ', 25, 1);

INSERT INTO MATERIAL_DOSTAWCA (Id_Mat_dost, Id_Materialu, Id_Dostawcy) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 2, 5),
(6, 2, 1),
(7, 2, 2),
(8, 2, 3),
(9, 3, 4),
(10, 3, 5),
(11, 3, 1),
(12, 3, 2),
(13, 4, 3),
(14, 4, 4),
(15, 4, 5),
(16, 4, 1),
(17, 5, 2),
(18, 5, 3),
(19, 5, 4),
(20, 5, 5),
(21, 6, 1),
(22, 6, 2),
(23, 6, 3),
(24, 6, 4),
(25, 7, 5),
(26, 7, 1),
(27, 7, 2),
(28, 7, 3),
(29, 8, 4),
(30, 8, 5),
(31, 8, 1),
(32, 8, 2),
(33, 9, 3),
(34, 9, 4),
(35, 9, 5),
(36, 9, 1),
(37, 10, 2),
(38, 10, 3),
(39, 10, 4),
(40, 10, 5),
(41, 11, 1),
(42, 11, 2),
(43, 11, 3),
(44, 11, 4),
(45, 12, 5),
(46, 12, 1),
(47, 12, 2),
(48, 12, 3),
(49, 13, 4),
(50, 13, 5),
(51, 13, 1),
(52, 13, 2),
(53, 14, 3),
(54, 14, 4),
(55, 14, 5),
(56, 14, 1),
(57, 15, 2),
(58, 15, 3),
(59, 15, 4),
(60, 15, 5),
(61, 16, 1),
(62, 16, 2),
(63, 16, 3),
(64, 16, 4),
(65, 17, 5),
(66, 17, 1),
(67, 17, 2),
(68, 17, 3),
(69, 18, 4),
(70, 18, 5),
(71, 18, 1),
(72, 18, 2),
(73, 19, 3),
(74, 19, 4),
(75, 19, 5),
(76, 19, 1);


