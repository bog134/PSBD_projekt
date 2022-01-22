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
  	Id_Pracownika INT NOT NULL,
  	Koszt_robocizny DOUBLE NOT NULL,
  	Koszt_surowcow DOUBLE NOT NULL,
  	Marza DOUBLE NOT NULL,
  
  	PRIMARY KEY (Id_Ceny)
);

CREATE TABLE IF NOT EXISTS DEFINICJA_ZADANIA
( 
	Id_Def_zadania INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient INT,
	Id_Proj_katalog INT,	
	Opis_zadania VARCHAR(200) NOT NULL, 
	Cena DOUBLE NOT NULL,
	Czas_wykonania TIME NOT NULL,	

	PRIMARY KEY (Id_Def_zadania)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_KOMPONENTY 
( 
	NrZamowienia INT NOT NULL AUTO_INCREMENT,  
	Stan_realizacji BOOLEAN NOT NULL, 
   	Czas_realizacji_Data_rozpoczecia Date NOT NULL, 
	Czas_realizacji_Data_zakonczenia Date, 

	PRIMARY KEY (NrZamowienia)
);

CREATE TABLE IF NOT EXISTS PROJEKT_POLPRODUKTU
( 
	Id_Proj_polprod INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient INT NULL,
	Id_Proj_katalog INT NULL,
	Id_Rodzaju_polproduktu INT NOT NULL,
	Nazwa VARCHAR(45) NOT NULL,
	Rozmiar_Wysokosc DOUBLE NOT NULL,
	Rozmiar_Szerokosc DOUBLE NOT NULL,
	Rozmiar_Dlugosc DOUBLE NOT NULL, 
	Cena DOUBLE NOT NULL,
	Nazwa_pliku_rysunku VARCHAR(45) NULL,

	PRIMARY KEY (Id_Proj_polprod)
);

CREATE TABLE IF NOT EXISTS POLPRODUKT
( 
	Id_Polprod INT NOT NULL AUTO_INCREMENT,
	Id_Proj_polprod INT NOT NULL,
	Id_Mebla INT NOT NULL,

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

CREATE TABLE IF NOT EXISTS MATERIAL
(
  	Id_Materialu int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Cena float NOT NULL,
  	Id_Koloru INT NOT NULL,
  	Klasa varchar(40) NOT NULL,
  	Id_Wzoru INT DEFAULT NULL,
  	Id_Rodzaju INT NOT NULL,
  	Rozmiar varchar(40) NOT NULL,
	
	PRIMARY KEY (Id_Materialu)
);

CREATE TABLE IF NOT EXISTS MEBEL
(
  	Id_Mebla int(11) NOT NULL AUTO_INCREMENT,
  	Id_Zamowienia int(11) NOT NULL,
  	Id_Proj_klient int(11) DEFAULT NULL,
  	Id_Proj_katalog int(11) DEFAULT NULL,
	
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
  	Id_Ceny int(11) NOT NULL,
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
  	Id_Czesci int(11) NOT NULL AUTO_INCREMENT,
	Id_Proj_katalog int(11) NOT NULL,
  	Nazwa varchar(45) NOT NULL,
	
	PRIMARY KEY (Id_Czesci)
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
  	Id_Def_zadania int(11) NOT NULL,
	Id_Pracownika int(11) NOT NULL,
	
	PRIMARY KEY (Id_Zadania)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_MEBLE (
  	Id_Zamowienia int(11) NOT NULL AUTO_INCREMENT,
  	Id_Klienta int(11) NOT NULL,
  	Koszt float,
  	Id_Stanu_Realizacji int(11) NOT NULL,
  	Czas_realizacji_Data_zlozenia date NOT NULL,
  	Czas_Realizacji_Data_zakonczenia date,
	
	PRIMARY KEY (Id_Zamowienia)
);

ALTER TABLE PRACOWNIK
ADD FOREIGN KEY (Id_Stanowiska) REFERENCES STANOWISKO(Id_Stanowiska);

ALTER TABLE CENA
ADD FOREIGN KEY (Id_Pracownika) REFERENCES PRACOWNIK (Id_Pracownika);

ALTER TABLE DEFINICJA_ZADANIA
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE PROJEKT_POLPRODUKTU
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog),
ADD	FOREIGN KEY (Id_Rodzaju_polproduktu) REFERENCES RODZAJ_POLPRODUKTU(Id_Rodzaju_polproduktu);

ALTER TABLE POLPRODUKT
ADD FOREIGN KEY (Id_Proj_polprod) REFERENCES PROJEKT_POLPRODUKTU(Id_Proj_polprod),
ADD FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla);

ALTER TABLE MATERIAL
ADD FOREIGN KEY (Id_Koloru) REFERENCES KOLOR(Id_Koloru),
ADD FOREIGN KEY (Id_Wzoru) REFERENCES WZOR(Id_Wzoru),
ADD FOREIGN KEY (Id_Rodzaju) REFERENCES RODZAJ_MATERIALU(Id_Rodzaju_materialu);

ALTER TABLE MEBEL
ADD FOREIGN KEY (Id_Zamowienia) REFERENCES ZAMOWIENIE_NA_MEBLE(Id_Zamowienia),
ADD	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE MATERIAL_PROJ_KLIENTA
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient);

ALTER TABLE PROJEKT_KLIENTA
ADD FOREIGN KEY (Id_Ceny) REFERENCES CENA(Id_Ceny),
ADD FOREIGN KEY (Id_Typu_mebla) REFERENCES TYP_MEBLA(Id_Typu_mebla),
ADD FOREIGN KEY (Id_Laczenia) REFERENCES LACZENIA(Id_Laczenia);

ALTER TABLE OPCJONALNA_CZESC
ADD FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE MATERIAL_PROJ_KATALOG
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE PROJEKT_Z_KATALOGU
ADD FOREIGN KEY (Id_Typu_mebla) REFERENCES TYP_MEBLA(Id_Typu_mebla),
ADD FOREIGN KEY (Id_Laczenia) REFERENCES LACZENIA(Id_Laczenia);

ALTER TABLE ZADANIE
ADD FOREIGN KEY (Id_Def_zadania) REFERENCES DEFINICJA_ZADANIA(Id_Def_zadania),
ADD FOREIGN KEY (Id_Pracownika) REFERENCES PRACOWNIK(Id_Pracownika);

ALTER TABLE ZAMOWIENIE_NA_MEBLE
ADD FOREIGN KEY (Id_Klienta) REFERENCES KLIENT(Id_Klienta);



