
--################################Ekran Główny################################ 

-- Przypadek Użycia - "Zalogowanie się"
-- Dane: LOGIN, HASŁO -> Wciśnięcie przycisku zaloguj.
-- Zapytanie wyszukujące użytkownika o podanym loginie i sprawdzające poprawność hasła
    -- 1.Wyszukanie z tabeli KLIENT użytkownika o podanym LOGIN-ie
    -- 2.Jeżeli dany użytkownik istnieje, sprawdzić czy wprowadzone HASŁO zgadza się z klient.Haslo
SELECT * 
FROM klient
WHERE login = "parametr_login" AND haslo = "parametr_haslo"; 

--################################Ekran Rejestracji################################

-- Przypadek Użycia - "Założenie konta"
-- 1.Zapytanie sprawdzające czy w bazie istnieje już użytkownik o podanym LOGINIE
    -- Wyszukaj w tabeli KLIENT użytkownika o podanym LOGINIE, jeżeli istnieje zwróć
        --wartość logiczną 1 jeżeli nie 0
-- 2.Jeżeli użytkownik o podanym LOGINIE nie istnieje jeszcze w bazie danych dodanie do
    --tabeli KLIENT, klienta o podanych danych.

CREATE DEFINER=`root`@`localhost` PROCEDURE `zarejestruj_klienta`(
inout zarejestrowano boolean,
in imie varchar(20), 
in nazwisko varchar(20), 
in numer_telefonu varchar(10), 
in email varchar(50),
in adres_kraj varchar(50),
in adres_adres_pocztowy varchar(200), 
in adres_miejscowosc varchar(100), 
in adres_ulica varchar(100), 
in adres_numer_domu varchar(10), 
in adres_numer_mieszkania varchar(10), 
in login varchar(45), 
in haslo varchar(45))
BEGIN    
    IF(login NOT IN (
		SELECT klient.Login AS Login
		FROM klient
		UNION 
		SELECT pracownik.Login AS Login
		FROM pracownik )) THEN
    INSERT IGNORE INTO klient (Imie, Nazwisko, Numer_telefonu, Email, Adres_Kraj, 
		Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_NumerDomu, Adres_NumerMieszkania, Login, Haslo) 
	VALUES
		(imie, nazwisko, numer_telefonu, email, adres_kraj, adres_adres_pocztowy, adres_miejscowosc,
        adres_ulica, adres_numer_domu, adres_numer_mieszkania, login, haslo);
		SELECT TRUE INTO zarejestrowano;
	END IF;
END



--################################Ekran Klienta################################

-- Przypadek Użycia - Wyświetlenie katalogu
    -- 1. Zapytanie wyświetlające projekty z katalogu, id i nazwę
SELECT PROJEKT_Z_KATALOGU.Id_Proj_katalog, PROJEKT_Z_KATALOGU.Nazwa, TYP_MEBLA.Nazwa
FROM PROJEKT_Z_KATALOGU
LEFT JOIN TYP_MEBLA ON TYP_MEBLA.Id_Typu_mebla = PROJEKT_Z_KATALOGU.Id_Typu_mebla
    -- 2. Zapytanie wyświetlające projekty z katalogu, id i nazwę z opcją filtracji. 
        -- Wyświetlenie tylko projektów na meble o danym typie, np "stoły"
SELECT PROJEKT_Z_KATALOGU.Id_Proj_katalog, PROJEKT_Z_KATALOGU.Nazwa, TYP_MEBLA.Nazwa
FROM PROJEKT_Z_KATALOGU
LEFT JOIN TYP_MEBLA ON TYP_MEBLA.Id_Typu_mebla = PROJEKT_Z_KATALOGU.Id_Typu_mebla
WHERE TYP_MEBLA.Nazwa = "parametr"

	-- 3. Zapytanie wyświetlające projekty z katalogu wraz z ich ceną
	
SELECT tab1.Id_Proj_katalog, projekt_z_katalogu.Nazwa, typ_mebla.Nazwa, (tab1.Marza + COALESCE(tab1.CenaPolProd,0) + tab2.CenaMaterialow + tab3.CenaZadan) AS KosztCalkowity FROM projekt_z_katalogu
LEFT JOIN
(SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(projekt_z_katalogu.Marza) AS Marza, SUM(projekt_polproduktu.cena) AS CenaPolprod  FROM projekt_z_katalogu
LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog
GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab1 USING (Id_Proj_katalog)
LEFT JOIN (
SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(material.Cena) AS CenaMaterialow FROM projekt_z_katalogu
LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog
LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu
GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab2 USING (Id_Proj_katalog)
LEFT JOIN (
SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(definicja_zadania.Cena) AS CenaZadan FROM firma.projekt_z_katalogu
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog
GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab3 USING (Id_Proj_katalog)
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla
WHERE typ_mebla.Nazwa = "parametr"

-- Przypadek Użycia - Wybranie mebla z katalogu
    -- Po wybraniu mebla z katalogu należy wyświetlnić opcje konfiguracji danego mebla.
-- 1. Zapytanie zwracające Materiały dla wybranego mebla
SELECT material.Nazwa
FROM material_proj_katalog
LEFT JOIN material ON material.Id_Materialu =  material_proj_katalog.Id_Materialu
WHERE material_proj_katalog.Id_Proj_katalog = "id_wybranego_przez_użytkownika_praojektu_z_katalogu"
-- 2. Zapytanie zwracające Opcjonalne części dla wybranego mebla
SELECT opcjonalna_czesc.Nazwa
FROM opcjonalna_czesc
WHERE opcjonalna_czesc.Id_Proj_katalog = "id_wybranego_przez_użytkownika_praojektu_z_katalogu"

-- Przypadek Użycia - Złożenie zamówienia na mebel
    -- Dodanie mebla o ustalonej konfiguracji. 
        -- mebel dodawany jest do buforu w javie (vektor) - dodanie go do bazy danych odbywa się przez
        -- EKRAN SZCZEGÓŁÓW ZAMÓWIEŃ ponieważ najpierw trzeba utworzyć zamówienie do którego będzie przypisany
-- INNE:
-- Wyświetlenie Loginu użytkownika na GUI - 
-- Wyświetlenie liczby sztuk w koszyku - z buforu 

--################################Ekran Szczegółów Zamówienia################################
    -- Lista mebli zamówieniu wyświetla się za pośrednictwem buforu w Javie
--Klient akceptuje zamówienie 
INSERT INTO ZAMOWIENIE_NA_MEBLE (Id_Klienta, Id_Stanu_Realizacji, Czas_realizacji_Data_zlozenia, Czas_Realizacji_Data_zakonczenia) VALUES
("Id_Klienta", 1, "Czas_realizacji_Data_zlozenia", NULL);

INSERT INTO MEBEL (Id_Zamowienia, Id_Proj_klient, Id_Proj_katalog, Id_Opcj_czesci, Wykonany) VALUES
("Id_Zamowienia", "Id_Proj_klient", "Id_Proj_katalog", "Id_Opcj_czesci", FALSE);

--################################Arkusz Reklamacyjny################################
INSERT INTO REKLAMACJA (Id_Mebla, Opis_reklamacji) VALUES
("Id_Mebla", "Opis"),


--################################Ekran Historii Zamówień################################
-- Wyświetlenie historii zamówień:
-- 1. Zapytanie wyświetlające Id zamówienia, Datę złożenia zamówienia , Opis zamówienia(?), Cenę zamówienia
--     konkretnego klienta, filtrowanie po dacie złożenia zamówienia (parametr)

SELECT COALESCE(mebel.Id_Proj_katalog,0) AS a, zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia, stan_realizacji.Nazwa_Stanu, SUM(tab.Cena) AS Laczny_Koszt FROM zamowienie_na_meble
LEFT JOIN stan_realizacji on stan_realizacji.Id_Stanu_realizacji = zamowienie_na_meble.Id_Stanu_Realizacji
LEFT JOIN (
SELECT mebel.Id_Mebla, mebel.Id_Zamowienia, typ_mebla.Nazwa, (projekt_z_katalogu.Marza + cenaRobociznyTab.Robocizna + COALESCE(cenaMaterialuTab.CenaMaterialu,0) + COALESCE(cenaPolprTab.cenaPolprod,0)) AS Cena, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel
LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = mebel.Id_Proj_katalog
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaRobociznyTab ON cenaRobociznyTab.Id_Mebla = mebel.Id_Mebla 
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(material.Cena) AS CenaMaterialu FROM mebel
LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaMaterialuTab ON cenaMaterialuTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(projekt_polproduktu.Cena) AS CenaPolprod FROM polprodukt
LEFT JOIN mebel ON mebel.Id_Mebla = polprodukt.Id_Mebla
LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_polprod = polprodukt.Id_Proj_polprod
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaPolprTab ON cenaPolprTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia) tab ON tab.Id_Zamowienia = zamowienie_na_meble.Id_Zamowienia
LEFT JOIN mebel ON mebel.Id_Zamowienia = tab.Id_Zamowienia
WHERE zamowienie_na_meble.Id_Klienta = 7
GROUP BY tab.Id_Zamowienia
HAVING  a > 0

-- Drugie zapytanie wywoływane po pierwszym

SELECT DISTINCT COALESCE(mebel.Id_Proj_klient,0) AS a, zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia, stan_realizacji.Nazwa_Stanu, SUM(tab.CenaPKlient) AS Cena FROM zamowienie_na_meble
LEFT JOIN stan_realizacji on stan_realizacji.Id_Stanu_realizacji = zamowienie_na_meble.Id_Stanu_Realizacji
LEFT JOIN(SELECT mebel.Id_Mebla, mebel.Id_Zamowienia, typ_mebla.Nazwa AS NazwaPKlient, (cena.Koszt_robocizny + cena.Koszt_surowcow + cena.Marza) AS CenaPKlient, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel
LEFT JOIN projekt_klienta ON projekt_klienta.Id_Proj_klient = mebel.Id_Proj_klient
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_klienta.Id_Typu_mebla
LEFT JOIN cena ON cena.Id_Ceny = projekt_klienta.Id_Ceny
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia
WHERE typ_mebla.Nazwa IS NOT NULL) tab ON tab.Id_Zamowienia = zamowienie_na_meble.Id_Zamowienia
LEFT JOIN mebel ON mebel.Id_Zamowienia = tab.Id_Zamowienia
WHERE zamowienie_na_meble.Id_Klienta = 7
GROUP BY zamowienie_na_meble.Id_Zamowienia
HAVING a > 0

-- 
-- Zaakceptowanie ceny zamówienia według projektu klienta:
-- 1. Wybranie jednego z zamówień -> zczytanie id zamówienia i rodzaju projektu przez jave i zapisanie tego w zmiennych ->
--    Zapytanie sql wyświetlające szczegóły tego zamówienia (Id mebla, typ mebla, cenę mebla), filtracja po id zamówienia ze zmiennej
-- Zapytanie do projektu z katalogu:
SELECT mebel.Id_Mebla, typ_mebla.Nazwa, (projekt_z_katalogu.Marza + cenaRobociznyTab.Robocizna + COALESCE(cenaMaterialuTab.CenaMaterialu,0) + COALESCE(cenaPolprTab.cenaPolprod,0)) AS Cena, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel
LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = mebel.Id_Proj_katalog
-- WHERE mebel.Id_Zamowienia = "parametr"
GROUP BY mebel.Id_Mebla) AS cenaRobociznyTab ON cenaRobociznyTab.Id_Mebla = mebel.Id_Mebla 
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(material.Cena) AS CenaMaterialu FROM mebel
LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu
-- WHERE mebel.Id_Zamowienia = "parametr"
GROUP BY mebel.Id_Mebla) AS cenaMaterialuTab ON cenaMaterialuTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(projekt_polproduktu.Cena) AS CenaPolprod FROM polprodukt
LEFT JOIN mebel ON mebel.Id_Mebla = polprodukt.Id_Mebla
LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_polprod = polprodukt.Id_Proj_polprod
-- WHERE mebel.Id_Zamowienia = "parametr"
GROUP BY mebel.Id_Mebla) AS cenaPolprTab ON cenaPolprTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia
WHERE mebel.Id_Zamowienia = "parametr"

-- Zapytanie do projektu klienta:
SELECT mebel.Id_Mebla, typ_mebla.Nazwa AS NazwaPKlient, (cena.Koszt_robocizny + cena.Koszt_surowcow + cena.Marza) AS CenaPKlient, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel
LEFT JOIN projekt_klienta ON projekt_klienta.Id_Proj_klient = mebel.Id_Proj_klient
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_klienta.Id_Typu_mebla
LEFT JOIN cena ON cena.Id_Ceny = projekt_klienta.Id_Ceny
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia
WHERE mebel.Id_Zamowienia = "parametr"

--Złączone zapytanie:

SELECT * FROM

(SELECT mebel.Id_Mebla, typ_mebla.Nazwa AS NazwaPKatalog, (projekt_z_katalogu.Marza + cenaRobociznyTab.Robocizna + COALESCE(cenaMaterialuTab.CenaMaterialu,0) + COALESCE(cenaPolprTab.cenaPolprod,0)) AS CenaPKatalog FROM mebel
LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = mebel.Id_Proj_katalog
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaRobociznyTab ON cenaRobociznyTab.Id_Mebla = mebel.Id_Mebla 
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(material.Cena) AS CenaMaterialu FROM mebel
LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaMaterialuTab ON cenaMaterialuTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(projekt_polproduktu.Cena) AS CenaPolprod FROM polprodukt
LEFT JOIN mebel ON mebel.Id_Mebla = polprodukt.Id_Mebla
LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_polprod = polprodukt.Id_Proj_polprod
-- WHERE mebel.Id_Zamowienia = 5
GROUP BY mebel.Id_Mebla) AS cenaPolprTab ON cenaPolprTab.Id_Mebla = mebel.Id_Mebla
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia
WHERE mebel.Id_Zamowienia = "parametr") tab1

LEFT JOIN(

SELECT mebel.Id_Mebla, typ_mebla.Nazwa AS NazwaPKlient, (cena.Koszt_robocizny + cena.Koszt_surowcow + cena.Marza) AS CenaPKlient, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel
LEFT JOIN projekt_klienta ON projekt_klienta.Id_Proj_klient = mebel.Id_Proj_klient
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_klienta.Id_Typu_mebla
LEFT JOIN cena ON cena.Id_Ceny = projekt_klienta.Id_Ceny
LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia
WHERE mebel.Id_Zamowienia = "parametr") tab2 ON tab2.Id_Mebla = tab1.Id_Mebla

-- 2a. Kliknięcie przycisku "Zaakceptuj zamówienie" -> 
--      wyświetlenie komunikatu o zatwierdzeniu zamówienia, aktualizacja stanu zamówienia "Wyceniono" (zapytanie sql) (technolog wpisuje proponowaną cenę w kolumnę "Cena")
UPDATE zamowienie_na_meble
SET Id_stanu_realizacji = 1
WHERE zamowienie_na_meble.Id_Zamowienia = "parametr"

-- 2b. Kliknięcie przycisku "Anuluj zamówienie" ->wyświetlenie komunikatu o odrzuceniu zamówienia, aktualizacja stanu zamówienia "Odrzucono" (zapytanie sql)
UPDATE zamowienie_na_meble
SET Id_stanu_realizacji = 5
WHERE zamowienie_na_meble.Id_Zamowienia = "parametr"

-- 
-- Złożenie reklamacji:
-- 1. Kliknięcie przycisku "Złóż reklamację" -> wyświetlenie arkusza reklamacyjnego, zapisanie przez jave id mebla w zmiennej
-- 2a. Wypełnienie arkusza i kliknięcie "Potwierdź" -> zapisanie opisu reklamacji w zmiennej -> dodanie reklamacji do bazy danych (zapytanie sql)

INSERT INTO REKLAMACJA (Id_Mebla, Opis_reklamacji) VALUES
("zmienna", "Opis reklamacji")

-- 2b. Kliknięcie "Odrzuć" -> zamknięcie arkusza

--################################Ekran Technologa################################
--wyswietlenie listy zamowien 
SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia
FROM zamowienie_na_meble
WHERE zamowienie_na_meble.Id_Stanu_Realizacji=3;

-- modyfikacja listy zamowien w zależności od filtra
SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia
FROM zamowienie_na_meble
WHERE zamowienie_na_meble.Id_Stanu_Realizacji=3 AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia 
BETWEEN DATE(data1) AND DATE(data2);

-- wyświetlenie listy projektów klienta
SELECT DISTINCT projekt_klienta.Id_Proj_klient, typ_mebla.Nazwa

FROM projekt_klienta
JOIN typ_mebla USING (Id_Typu_mebla)
JOIN mebel USING (Id_Proj_klient)

WHERE mebel.Id_Zamowienia=id_zam AND projekt_klienta.zaakceptowany is null;

-- modyfikacja listy projektow w zależności od filtra

SELECT DISTINCT projekt_klienta.Id_Proj_klient, typ_mebla.Nazwa

FROM projekt_klienta
JOIN typ_mebla USING (Id_Typu_mebla)
JOIN mebel USING (Id_Proj_klient)

WHERE mebel.Id_Zamowienia=id_zam AND projekt_klienta.zaakceptowany is null AND typ_mebla.Nazwa LIKE parametr@; 

-- wyświetlenie szczegółow wybranego projektu klienta

-- 1. wyświetlenie szczegółów projektu 
SELECT typ_mebla.Nazwa, CONCAT(projekt_klienta.Wymiary_Dlugosc,"x",projekt_klienta.Wymiary_Wysokosc,"x",projekt_klienta.Wymiary_Szerokosc),
laczenia.Nazwa,
COUNT(mebel.Id_Mebla),
projekt_klienta.Nazwa_pliku_rysunku

FROM typ_mebla
JOIN projekt_klienta USING (Id_Typu_mebla)
JOIN laczenia USING (Id_Laczenia)
JOIN mebel USING (Id_Proj_klient)

WHERE projekt_klienta.Id_Proj_klient=id
GROUP BY Id_Proj_klient;

-- 2. wyświetlenie projektów półproduktów
SELECT projekt_polproduktu.Nazwa

FROM projekt_polproduktu
JOIN projekt_klienta USING (Id_Proj_klient)

WHERE projekt_klienta.Id_Proj_klient = id;

-- 3. wyświetlenie materiałów
SELECT DISTINCT material_proj_klienta.Id_Mat_Proj_klient, material.Nazwa, rodzaj_materialu.Nazwa, wzor.Nazwa, material.Klasa, material.Id_Materialu

FROM material
LEFT JOIN rodzaj_materialu USING (Id_Rodzaju_materialu)
LEFT JOIN wzor USING (Id_Wzoru)
LEFT JOIN material_proj_klienta USING (Id_Materialu)

WHERE material_proj_klienta.Id_Proj_klient=id;

-- 4. wyświetlenie szczegółów wybranego półproduktu
SELECT projekt_polproduktu.Nazwa, rodzaj_polproduktu.Nazwa, CONCAT(projekt_polproduktu.Rozmiar_Dlugosc,"x",projekt_polproduktu.Rozmiar_Wysokosc,"x",projekt_polproduktu.Rozmiar_Szerokosc),
COUNT(*),
projekt_polproduktu.Nazwa_pliku_rysunku

FROM projekt_polproduktu
JOIN rodzaj_polproduktu USING (Id_Rodzaju_polproduktu)

WHERE projekt_polproduktu.Id_Proj_klient=id
GROUP BY (projekt_polproduktu.Nazwa)
HAVING projekt_polproduktu.Nazwa = nazwa;

--5. pobranie ceny danego materiału
SELECT Cena
FROM material
WHERE Id_Materialu=id_mat;

--6. pobranie ceny osobogodziny
SELECT Cena
FROM definicja_zadania
WHERE Id_Def_zadania=1;

-- zaaktulizowanie danych
-- 1. zaaktulizowanie ilości materialow
UPDATE material_proj_klienta
SET ilosc=x
WHERE Id_Mat_Proj_klient = id_mat;

-- 2. utworzenie definicji zadań 
INSERT INTO DEFINICJA_ZADANIA (Id_Proj_klient, Opis_zadania, Czas_wykonania, Cena, Id_Pracownika) VALUES (id,opis,czas,cena,id_techn);

-- 3. utworzenie ceny 
INSERT INTO CENA (Id_Pracownika, Id_Proj_klient, Koszt_robocizny, Koszt_surowcow, Marza) VALUES 
(id_techn,robocizna,materialy,marza);

-- 4. zaakceptowanie projektu klienta
UPDATE projekt_klienta
SET zaakceptowany=1
WHERE Id_Proj_klient = id;

-- 5. zaakceptowanie zamowienia
UPDATE zamowienie_na_meble
SET Id_Stanu_Realizacji = 4
WHERE Id_Zamowienia = id_zam;

-- 6. odrzucenie zamowienia
UPDATE zamowienie_na_meble
SET Id_Stanu_Realizacji = 5
WHERE Id_Zamowienia = id_zam;





