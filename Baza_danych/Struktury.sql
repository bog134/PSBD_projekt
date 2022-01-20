CREATE SCHEMA IF NOT EXISTS FIRMA;
USE FIRMA;

CREATE TABLE IF NOT EXISTS PRACOWNIK
( 
	IdPracownika INT NOT NULL AUTO_INCREMENT, 
	IdStanowiska INT NOT NULL, 
	Data_urodzenia DATE NOT NULL, 
	Imie VARCHAR(45) NOT NULL, 
	Nazwisko VARCHAR(45) NOT NULL, 
	Numer_konta_bankowego VARCHAR(45) NOT NULL, 
	Zarobki DOUBLE NOT NULL, 
	Numer_telefonu VARCHAR(45) NOT NULL,	
	Adres_Kod VARCHAR(45) NOT NULL, 
	Adres_Miasto VARCHAR(45) NOT NULL,		
	Adres_Ulica VARCHAR(45) NOT NULL,	

	PRIMARY KEY (IdPracownika),
	UNIQUE KEY (Numer_konta_bankowego),
	FOREIGN KEY (IdStanowiska) REFERENCES STANOWISKO(IdStanowiska)
);

CREATE TABLE IF NOT EXISTS STANOWISKO
( 
	IdStanowiska INT NOT NULL AUTO_INCREMENT, 
	Nazwa_Stanowiska VARCHAR(45) NOT NULL,

	PRIMARY KEY (IdStanowiska),
	UNIQUE KEY (Nazwa_Stanowiska)
);

CREATE TABLE IF NOT EXISTS CENA 
(
  IdCeny INT NOT NULL AUTO_INCREMENT,
  IdPracownika VARCHAR(45) NOT NULL,
  Koszt_robocizny DOUBLE NOT NULL,
  Koszt_surowcow DOUBLE NOT NULL,
  Marza DOUBLE NOT NULL,
  
  PRIMARY KEY (IdCeny),
  FOREIGN KEY (IdPracownika) REFERENCES PRACOWNIK (IdPracownika)
);

CREATE TABLE IF NOT EXISTS DEFINICJA_ZADANIA
( 
	IdDef_zadania INT NOT NULL AUTO_INCREMENT, 
	IdProj_klient INT NOT NULL, 
	IdProj_katalog INT NOT NULL,
	Opis_zadania VARCHAR(200) NOT NULL, 
	Cena DOUBLE NOT NULL,

	PRIMARY KEY (IdDef_zadania),
	UNIQUE KEY (Opis_zadania),
	FOREIGN KEY (IdProj_klient) REFERENCES PROJEKT_KLIENTA(IdProj_klient),
	FOREIGN KEY (IdProj_katalog) REFERENCES PROJEKT_Z_KATALOGU(IdProj_katalog)
);

CREATE TABLE IF NOT EXISTS RODZAJ
( 
	IdRodzaju INT NOT NULL AUTO_INCREMENT, 
	Nazwa VARCHAR(45) NOT NULL, 

	PRIMARY KEY (IdRodzaju),
	UNIQUE KEY (Nazwa)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_KOMPONENTY 
( 
	NrZamowienia INT NOT NULL AUTO_INCREMENT, 
	Koszt INT NOT NULL, 
	Stan_realizacji BOOLEAN NOT NULL, 
   	Czas_realizacji_Data_rozpoczecia Date NOT NULL, 
	Czas_realizacji_Data_zakonczenia Date NOT NULL, 
	Adres_Miejscowosc Char(100) NOT NULL, 
	Adres_Ulica Char(100) NOT NULL, 
	Adres_Numer Char(10) NOT NULL, 
	Adres_AdresPocztowy Char(200) NOT NULL, 

	PRIMARY KEY (NrZamowienia)
);

CREATE TABLE IF NOT EXISTS PROJEKT_KLIENTA_DEFINICJA_ZADANIA
( 
	IdProj_klienta_def_zadania INT NOT NULL AUTO_INCREMENT,
	IdProj_klient INT NOT NULL, 
	IdDef_zadania INT NOT NULL,

	PRIMARY KEY (IdProj_klienta_def_zadania),
	FOREIGN KEY (IdProj_klient) REFERENCES PROJEKT_KLIENTA(IdProj_klient),
	FOREIGN KEY (IdDef_zadania) REFERENCES DEFINICJA_ZADANIA(IdDef_zadania)
);

CREATE TABLE IF NOT EXISTS PROJEKT_Z_KATALOGU_DEFINICJA_ZADANIA
( 
	IdProj_katalog_def_zadania INT NOT NULL AUTO_INCREMENT,
	IdProj_katalog INT NOT NULL, 
	IdDef_zadania INT NOT NULL,

	PRIMARY KEY (IdProj_katalog_def_zadania),
	FOREIGN KEY (IdProj_klient) REFERENCES PROJEKT_KLIENTA(IdProj_klient),
	FOREIGN KEY (IdDef_zadania) REFERENCES DEFINICJA_ZADANIA(IdDef_zadania)
);

CREATE TABLE IF NOT EXISTS PROJEKT_POLPRODUKTU
( 
	IdProj_polprod INT NOT NULL AUTO_INCREMENT,
	IdProj_klient INT NOT NULL,
	IdProj_katalog INT NOT NULL,
	IdRodzaju INT NOT NULL,
	Nazwa VARCHAR(45) NOT NULL,
	Rozmiar_Wysokosc DOUBLE NOT NULL,
	Rozmiar_Szerokosc DOUBLE NOT NULL,
	Rozmiar_Dlugosc DOUBLE NOT NULL, 
	Cena DOUBLE NOT NULL,
	Nazwa_pliku_rysunku VARCHAR(45) NOT NULL,

	PRIMARY KEY (IdProj_polprod),
	FOREIGN KEY (IdProj_klient) REFERENCES PROJEKT_KLIENTA(IdProj_klient),
	FOREIGN KEY (IdProj_katalog) REFERENCES PROJEKT_Z_KATALOGU(IdProj_katalog),
	FOREIGN KEY (IdRodzaju) REFERENCES RODZAJ(IdRodzaju)

);

CREATE TABLE IF NOT EXISTS POLPRODUKT
( 
	IdPolprod INT NOT NULL AUTO_INCREMENT,
	IdProj_polprod INT NOT NULL,

	PRIMARY KEY (IdPolprod),
	FOREIGN KEY (IdProj_polprod) REFERENCES PROJEKT_POLPRODUKTU(IdProj_polprod)
);

CREATE TABLE IF NOT EXISTS DOSTAWCY
(
  	Id_Dostawcy int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	E-mail varchar(50) NOT NULL,
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
  	E-mail varchar(50) NOT NULL,
  	Adres_AdresPocztowy varchar(200) NOT NULL,
  	Adres_Miejscowosc varchar(100) NOT NULL,
  	Adres_Ulica varchar(100) NOT NULL,
  	Adres_NumerDomu varchar(10) NOT NULL,
	
	PRIMARY KEY (Id_Klienta)
);

CREATE TABLE IF NOT EXISTS LACZENIE 
(
  	Id_Laczenia int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
	
	PRIMARY KEY (Id_Laczenia)
);


CREATE TABLE IF NOT EXISTS MATERIAL
(
  	Id_Materialu int(11) NOT NULL AUTO_INCREMENT,
  	Nazwa varchar(20) NOT NULL,
  	Cena float NOT NULL,
  	Kolor varchar(20) NOT NULL,
  	Klasa varchar(1) NOT NULL,
  	Wzor varchar(40) DEFAULT NULL,
  	Rodzaj varchar(40) NOT NULL,
  	Rozmiar int(11) NOT NULL,
	
	PRIMARY KEY (Id_Materialu)
);

CREATE TABLE IF NOT EXISTS MATERIAL_MEBEL
(
  	Id_Mat_mebel int(11) NOT NULL AUTO_INCREMENT,
  	Id_Materialu int(11) NOT NULL,
  	Id_Mebla int(11) NOT NULL
	
	PRIMARY KEY (Id_Mat_mebel),
	FOREIGN KEY (Id_Materialu) REFERENCES MATERIAL(Id_Materialu),
	FOREIGN KEY (Id_Mebla) REFERENCES MEBEL(Id_Mebla)
);

CREATE TABLE IF NOT EXISTS MEBEL
(
  	Id_Mebla int(11) NOT NULL AUTO_INCREMENT,
  	Numer_zamowienia int(11) NOT NULL,
  	Id_Proj_klient int(11) DEFAULT NULL,
  	Id_Proj_katalog int(11) DEFAULT NULL
	
	PRIMARY KEY (Id_Mebla),
	FOREIGN KEY (Numer_zamowienia) REFERENCES ZAMOWIENIE_NA_MEBLE(Numer_zamowienia),
	FOREIGN KEY (Id_Proj_klient) REFERENCES PROJEKT_KLIENTA(Id_Proj_klient),
	FOREIGN KEY (Id_Proj_katalog) REFERENCES PROJEKT_Z_KATALOGU(Id_Proj_katalog)
);

CREATE TABLE IF NOT EXISTS PROJEKT_KLIENTA
(
  	Id_Proj_klient int(11) NOT NULL AUTO_INCREMENT,
  	Id_Ceny int(11) NOT NULL,
  	Id_Typu_mebla int(11) NOT NULL,
  	Id_Laczenia int(11) NOT NULL,
  	Wymiary_Szerokosc int(11) NOT NULL,
  	Wymiary_Wysokosc int(11) NOT NULL,
  	Wymiary_Glebokosc int(11) NOT NULL,
  	Nazwa_pliku_rysunku varchar(40) NOT NULL,
	
	PRIMARY KEY (Id_Proj_klient),
	FOREIGN KEY (Id_Ceny) REFERENCES CENA(Id_Ceny)
);

CREATE TABLE IF NOT EXISTS PROJEKT_Z_KATALOGU
(
  	Id_Proj_katalog int(11) NOT NULL AUTO_INCREMENT,
  	Id_Typu_mebla int(11) NOT NULL,
  	Id_Laczenia int(11) NOT NULL,
  	Nazwa varchar(20) NOT NULL,
  	Opcjonalne_czesci enum('x','','','') NOT NULL,
  	Wykonywalny tinyint(1) NOT NULL,
  	Marza float NOT NULL,
  	Wymiary_Szerokosc int(11) NOT NULL,
  	Wymiary_Wysokosc int(11) NOT NULL,
  	Wymiary_Glebokosc int(11) NOT NULL,
	
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
	
	PRIMARY KEY (Id_Zadania),
	FOREIGN KEY (Id_Def_zadania) REFERENCES DEFINICJA_ZADANIA(IdDef_zadania)
);

CREATE TABLE IF NOT EXISTS ZAMOWIENIE_NA_MEBLE
(
  	Numer_zamowienia int(11) NOT NULL AUTO_INCREMENT,
  	Id_Klienta int(11) NOT NULL,
  	Koszt float NOT NULL,
  	Id_Stanu_Realizacji int(11) NOT NULL,
  	Czas_realizacji_Data_rozpoczecia date NOT NULL,
  	Czas_Realizacji_Data_zakonczenia date NOT NULL,
	
	PRIMARY KEY (Numer_zamowienia),
	FOREIGN KEY (Id_Klienta) REFERENCES KLIENCI(Id_Klienta)
);


