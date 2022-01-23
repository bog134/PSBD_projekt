
--###Ekran Główny### 
-- Przypadek Użycia - "Zalogowanie się"
-- Dane: LOGIN, HASŁO -> Wciśnięcie przycisku zaloguj.
-- Zapytanie wyszukujące użytkownika o podanym loginie i sprawdzające poprawność hasła
    -- 1.Wyszukanie z tabeli KLIENT użytkownika o podanym LOGIN-ie
    -- 2.Jeżeli dany użytkownik istnieje, sprawdzić czy wprowadzone HASŁO zgadza się z klient.Haslo

--###Ekran Rejestracji###
-- Przypadek Użycia - "Założenie konta"
-- 1.Zapytanie sprawdzające czy w bazie istnieje już użytkownik o podanym LOGINIE
    -- Wyszukaj w tabeli KLIENT użytkownika o podanym LOGINIE, jeżeli istnieje zwróć
        --wartość logiczną 1 jeżeli nie 0
-- 2.Jeżeli użytkownik o podanym LOGINIE nie istnieje jeszcze w bazie danych dodanie do
    --tabeli KLIENT, klienta o podanych danych.

--###Ekran Rejestracji###
-- Przypadek Użycia - Wyświetlenie katalogu
-- Przypadek Użycia - Wybranie mebla z katalogu  
-- Przypadek Użycia - Złożenie zamówienia na mebel
    --

--###Ekran Historii Zamówień###
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
SELECT mebel.Id_Mebla, typ_mebla.Nazwa, (projekt_z_katalogu.Marza + cenaTab.Robocizna) AS Cena FROM mebel
LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = mebel.Id_Proj_katalog
WHERE mebel.Id_Zamowienia = "parametr"
GROUP BY mebel.Id_Mebla) AS cenaTab ON cenaTab.Id_Mebla = mebel.Id_Mebla 
WHERE mebel.Id_Zamowienia = "parametr"

-- Zapytanie do projektu klienta:
SELECT mebel.Id_Mebla, typ_mebla.Nazwa, projekt_klienta.Id_Proj_klient, (cena.Koszt_robocizny + cena.Koszt_surowcow + cena.Marza) AS Cena, cenaTab.Robocizna FROM mebel
LEFT JOIN projekt_klienta ON projekt_klienta.Id_Proj_klient = mebel.Id_Proj_klient
LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_klienta.Id_Typu_mebla
LEFT JOIN cena ON cena.Id_Ceny = projekt_klienta.Id_Ceny
LEFT JOIN (
SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel
LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_klient = mebel.Id_Proj_klient
WHERE mebel.Id_Zamowienia = "parametr"
GROUP BY mebel.Id_Mebla) AS cenaTab ON cenaTab.Id_Mebla = mebel.Id_Mebla 
WHERE mebel.Id_Zamowienia = "parametr" -- work in progress (wyjasnic nieścisłości dotyczace ceny)

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
-- 1. Kliknięcie przycisku "Złóż reklamację" -> wyświetlenie arkusza reklamacyjnego
-- 2a. Wypełnienie arkusza i kliknięcie "Potwierdź" -> dodanie reklamacji do bazy danych (zapytanie sql) (!!! nie ma tabelki przechowującej reklamacje !!!) 
-- 2b. Kliknięcie "Odrzuć" -> zamknięcie arkusza
