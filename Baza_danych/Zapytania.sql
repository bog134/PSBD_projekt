
--################################Ekran Główny################################ 

-- Przypadek Użycia - "Zalogowanie się"
-- Dane: LOGIN, HASŁO -> Wciśnięcie przycisku zaloguj.
-- Zapytanie wyszukujące użytkownika o podanym loginie i sprawdzające poprawność hasła
    -- 1.Wyszukanie z tabeli KLIENT użytkownika o podanym LOGIN-ie
    -- 2.Jeżeli dany użytkownik istnieje, sprawdzić czy wprowadzone HASŁO zgadza się z klient.Haslo
SELECT * 
FROM klient
where login = "parametr_login" AND haslo = "parametr_haslo"; 

--################################Ekran Rejestracji################################

-- Przypadek Użycia - "Założenie konta"
-- 1.Zapytanie sprawdzające czy w bazie istnieje już użytkownik o podanym LOGINIE
    -- Wyszukaj w tabeli KLIENT użytkownika o podanym LOGINIE, jeżeli istnieje zwróć
        --wartość logiczną 1 jeżeli nie 0
SELECT 
IF (EXISTS(
	    SELECT * 
        FROM klient
        WHERE klient.login = "Toby"), 1, 0) AS Czy_istnieje;
-- 2.Jeżeli użytkownik o podanym LOGINIE nie istnieje jeszcze w bazie danych dodanie do
    --tabeli KLIENT, klienta o podanych danych.


--################################Ekran Klienta################################

-- Przypadek Użycia - Wyświetlenie katalogu
    -- 1. Zapytanie wyświetlające projekty z katalogu, id i nazwę
    -- 2. Zapytanie wyświetlające projekty z katalogu, id i nazwę z opcją filtracji. 
        -- Wyświetlenie tylko projektów na meble o danym typie, np "stoły"
-- Przypadek Użycia - Wybranie mebla z katalogu
    -- Po wybraniu mebla z katalogu należy wyświetlnić opcje konfiguracji danego mebla.
        -- 1. Zapytanie zwracające Materiały, Łączenia i Opcjonalne części dla wybranego mebla
-- Przypadek Użycia - Złożenie zamówienia na mebel
    -- Dodanie mebla o ustalonej konfiguracji.
-- INNE:
-- Wyświetlenie Loginu użytkownika na GUI
-- Wyświetlenie liczby sztuk w koszyku 
    --

--################################Ekran Historii Zamówień################################
-- Wyświetlenie historii zamówień:
-- 1. Zapytanie wyświetlające Id zamówienia, Datę złożenia zamówienia , Opis zamówienia(?), Cenę zamówienia
--     konkretnego klienta, filtrowanie po dacie złożenia zamówienia (parametr)

SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia, stan_realizacji.Nazwa_Stanu, zamowienie_na_meble.Koszt FROM zamowienie_na_meble
LEFT JOIN stan_realizacji on stan_realizacji.Id_Stanu_realizacji = zamowienie_na_meble.Id_Stanu_Realizacji
WHERE zamowienie_na_meble.Id_Klienta = "parametr"

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
SET Id_stanu_realizacji = 4
WHERE zamowienie_na_meble.Id_Zamowienia = "parametr"

-- 2b. Kliknięcie przycisku "Anuluj zamówienie" ->wyświetlenie komunikatu o odrzuceniu zamówienia, aktualizacja stanu zamówienia "Odrzucono" (zapytanie sql)
UPDATE zamowienie_na_meble
SET Id_stanu_realizacji = 5
WHERE zamowienie_na_meble.Id_Zamowienia = "parametr"

-- 
-- Złożenie reklamacji:
-- 1. Kliknięcie przycisku "Złóż reklamację" -> wyświetlenie arkusza reklamacyjnego, zapisanie przez jave id mebla w zmiennej
-- 2a. Wypełnienie arkusza i kliknięcie "Potwierdź" -> zapisanie opisu reklamacji w zmiennej -> dodanie reklamacji do bazy danych (zapytanie sql)

INSERT INTO REKLAMACJE (Id_Mebla, Opis_reklamacji) VALUES
("zmienna", "Opis reklamacji")

-- 2b. Kliknięcie "Odrzuć" -> zamknięcie arkusza

--################################Ekran Technologa################################
-- pobranie id_technologa
SELECT pracownik.Id_Pracownika
FROM pracownik
WHERE pracownik.Login = login;

-- wyświetlenie listy projektów klienta
SELECT projekt_klienta.Id_Proj_klient, zamowienie_na_meble.Czas_realizacji_Data_zlozenia,
typ_mebla.Nazwa

FROM projekt_klienta
JOIN mebel USING (Id_Proj_klient)
JOIN zamowienie_na_meble USING (Id_Zamowienia)
JOIN typ_mebla USING (Id_Typu_mebla)

WHERE zamowienie_na_meble.Id_Zamowienia=3;

-- modyfikacja listy projektów w zależności od filtra

SELECT projekt_klienta.Id_Proj_klient, zamowienie_na_meble.Czas_realizacji_Data_zlozenia,
typ_mebla.Nazwa AS Typ_mebla

FROM projekt_klienta
JOIN mebel USING (Id_Proj_klient)
JOIN zamowienie_na_meble USING (Id_Zamowienia)
JOIN typ_mebla USING (Id_Typu_mebla)

WHERE zamowienie_na_meble.Id_Zamowienia=3 AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia 
BETWEEN DATE(data1) AND DATE(data2) OR typ_mebla.Nazwa LIKE parametr; --'Fo%';

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

WHERE mebel.Wykonany=0 AND projekt_klienta.Id_Proj_klient=id
GROUP BY Id_Proj_klient;

-- 2. wyświetlenie id projektów półproduktów
SELECT projekt_polproduktu.Id_Proj_polprod

FROM projekt_polproduktu
JOIN projekt_klienta USING (Id_Proj_klient)

WHERE projekt_klienta.Id_Proj_klient = id;

-- 3. wyświetlenie materiałów
SELECT material.Nazwa, rodzaj_materialu.Nazwa, wzor.Nazwa, material.Klasa

FROM material
JOIN rodzaj_materialu USING (Id_Rodzaju_materialu)
JOIN wzor USING (Id_Wzoru)
JOIN material_proj_klienta USING (Id_Materialu)

WHERE material_proj_klienta.Id_Proj_klient=3;

-- 4. wyświetlenie szczegółów wybranego półproduktu
SELECT projekt_polproduktu.Nazwa, rodzaj_polproduktu.Nazwa, CONCAT(projekt_polproduktu.Rozmiar_Dlugosc,"x",projekt_polproduktu.Rozmiar_Wysokosc,"x",projekt_polproduktu.Rozmiar_Szerokosc),
COUNT(*),
projekt_polproduktu.Nazwa_pliku_rysunku

FROM projekt_polproduktu
JOIN rodzaj_polproduktu USING (Id_Rodzaju_polproduktu)

WHERE projekt_polproduktu.Id_Proj_klient=id
GROUP BY Id_Proj_klient;

-- zaaktulizowanie danych w projekcie klienta
-- 1. zaaktulizowanie ilości materialow
UPDATE material_proj_klienta
SET ilosc=x
WHERE Id_Mat_Proj_klient = id_mat;

-- 2. utworzenie definicji zadań 
INSERT INTO DEFINICJA_ZADANIA (Id_Proj_klient, Opis_zadania) VALUES (id,opis);

-- 3. utworzenie ceny 
INSERT INTO CENA (Id_Pracownika, Koszt_robocizny, Koszt_surowcow, Marza) VALUES 
(id_techn,robocizna,materialy,marza);




