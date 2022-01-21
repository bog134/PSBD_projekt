CREATE SCHEMA IF NOT EXISTS FIRMA;
USE FIRMA;

CREATE TABLE IF NOT EXISTS STANOWISKO
( 
	Id_Stanowiska INT NOT NULL AUTO_INCREMENT, 
	Nazwa_Stanowiska VARCHAR(45) NOT NULL,

	PRIMARY KEY (Id_Stanowiska),
	UNIQUE KEY (Nazwa_Stanowiska)
);

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
	UNIQUE KEY (Numer_konta_bankowego)
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
	Opis_zadania VARCHAR(200) NOT NULL, 
	Cena DOUBLE NOT NULL,
	Czas_wykonania TIME NOT NULL,	

	PRIMARY KEY (Id_Def_zadania),
	UNIQUE KEY (Opis_zadania)
);

CREATE TABLE IF NOT EXISTS RODZAJ_POLPRODUKTU
( 
	Id_Rodzaju_polproduktu INT NOT NULL AUTO_INCREMENT, 
	Nazwa VARCHAR(45) NOT NULL, 

	PRIMARY KEY (Id_Rodzaju_polproduktu),
	UNIQUE KEY (Nazwa)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_KOMPONENTY 
( 
	NrZamowienia INT NOT NULL AUTO_INCREMENT, 
	Koszt INT NOT NULL, 
	Stan_realizacji BOOLEAN NOT NULL, 
   	Czas_realizacji_Data_rozpoczecia Date NOT NULL, 
	Czas_realizacji_Data_zakonczenia Date, 

	PRIMARY KEY (NrZamowienia)
);

CREATE TABLE IF NOT EXISTS PROJEKT_POLPRODUKTU
( 
	Id_Proj_polprod INT NOT NULL AUTO_INCREMENT,
	Id_Proj_klient INT NOT NULL,
	Id_Proj_katalog INT NOT NULL,
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

CREATE TABLE IF NOT EXISTS DOSTAWCY
(
  	Id_Dostawcy int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Email varchar(50) NOT NULL,
  	Numer_konta_bankowego varchar(26) NOT NULL,
  	Adres_AdresPocztowy varchar(200) NOT NULL,
  	Adres_Miejscowosc varchar(100) NOT NULL,
  	Adres_Ulica varchar(100) NOT NULL,
  	Adres_Numer varchar(10) NOT NULL,
	
	PRIMARY KEY (Id_Dostawcy)
);

CREATE TABLE IF NOT EXISTS KLIENCI 
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
	
	PRIMARY KEY (Id_Klienta)
);

CREATE TABLE IF NOT EXISTS LACZENIA
(
  	Id_Laczenia int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(30) NOT NULL,
	
	PRIMARY KEY (Id_Laczenia)
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

CREATE TABLE IF NOT EXISTS RODZAJ_MATERIALU
(
  	Id_Rodzaju_materialu INT NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
	
	PRIMARY KEY (Id_Rodzaju_materialu)
);


CREATE TABLE IF NOT EXISTS MATERIAL
(
  	Id_Materialu int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Cena float NOT NULL,
  	Kolor INT NOT NULL,
  	Klasa varchar(10) NOT NULL,
  	Wzor INT DEFAULT NULL,
  	Rodzaj INT NOT NULL,
  	Rozmiar varchar(40) NOT NULL,
	
	PRIMARY KEY (Id_Materialu)
);

CREATE TABLE IF NOT EXISTS MATERIAL_MEBEL
(
  	Id_Mat_mebel int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	Id_Mebla int(11) NOT NULL,
	
	PRIMARY KEY (Id_Mat_mebel)
);

CREATE TABLE IF NOT EXISTS MEBEL
(
  	Id_Mebla int(11) NOT NULL AUTO_INCREMENT,
  	Id_Zamowienia int(11) NOT NULL,
  	Id_Proj_klient int(11) DEFAULT NULL,
  	Id_Proj_katalog int(11) DEFAULT NULL,
	
	PRIMARY KEY (Id_Mebla)
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

CREATE TABLE IF NOT EXISTS PROJEKT_Z_KATALOGU
(
  	Id_Proj_katalog int(11) NOT NULL AUTO_INCREMENT,
  	Id_Typu_mebla int(11) NOT NULL,
  	Id_Laczenia int(11) NOT NULL,
	Id_Opcjonalne_czesci int(11) NULL,
  	Nazwa varchar(20) NOT NULL,
  	Marza float NOT NULL,
  	Wymiary_Szerokosc int(11) NOT NULL,
  	Wymiary_Wysokosc int(11) NOT NULL,
  	Wymiary_Dlugosc int(11) NOT NULL,
	
	PRIMARY KEY (Id_Proj_katalog)
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

CREATE TABLE IF NOT EXISTS ZADANIE
(
  	Id_Zadania int(11) NOT NULL AUTO_INCREMENT,
  	Id_Def_zadania int(11) NOT NULL,
  	Czas_wykonania time NOT NULL,
	
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

ALTER TABLE PROJEKT_POLPRODUKTU
ADD FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog),
ADD	FOREIGN KEY (Id_Rodzaju_polproduktu) REFERENCES RODZAJ_POLPRODUKTU(Id_Rodzaju_polproduktu);

ALTER TABLE POLPRODUKT
ADD FOREIGN KEY (Id_Proj_polprod) REFERENCES PROJEKT_POLPRODUKTU(Id_Proj_polprod),
ADD FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla);

ALTER TABLE MATERIAL
ADD FOREIGN KEY (Kolor) REFERENCES KOLOR(Id_Koloru),
ADD FOREIGN KEY (Wzor) REFERENCES WZOR(Id_Wzoru),
ADD FOREIGN KEY (Rodzaj) REFERENCES RODZAJ_MATERIALU(Id_Rodzaju_materialu);

ALTER TABLE MATERIAL_MEBEL
ADD FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
ADD	FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla);

ALTER TABLE MEBEL
ADD FOREIGN KEY (Id_Zamowienia) REFERENCES ZAMOWIENIE_NA_MEBLE(Id_Zamowienia),
ADD	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
ADD	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog);

ALTER TABLE PROJEKT_KLIENTA
ADD FOREIGN KEY (Id_Ceny) REFERENCES CENA(Id_Ceny);

ALTER TABLE ZADANIE
ADD FOREIGN KEY (Id_Def_zadania) REFERENCES DEFINICJA_ZADANIA(Id_Def_zadania);

ALTER TABLE ZAMOWIENIE_NA_MEBLE
ADD FOREIGN KEY (Id_Klienta) REFERENCES KLIENCI(Id_Klienta)



