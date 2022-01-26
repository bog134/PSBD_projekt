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

INSERT INTO KLIENT (Id_Klienta, Imie, Nazwisko, Numer_telefonu, Email, Adres_Kraj, Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_NumerDomu, Adres_NumerMieszkania, Login, Haslo) VALUES
(1, 'Marcelina', 'Woźniak', '690793569', 'paulbont@adaov.com', 'Polska', '10-182', 'Bielsko-Biała', 'Akacjowa', 15, 38, 'Marcelina123', 'qaz123'),
(2, 'Bogumił', 'Łukasik', '212994545', 'stumptown@gmailya.com', 'Polska', '62-875', 'Poznań', 'Niedzielna', 512, 43, 'Boguslaw457', '12345Boguslaw'),
(3, 'Lilianna', 'Bielecka', '814536325', 'olgachernyh@fheiesit.com', 'Polska', '38-650', 'Katowice', 'Gołuńskiego', 3, 123, 'Lil8124', 'Si7n3H@${0'),
(4, 'Tobiasz', 'Bąk', '271211570', 'nixaaron@oreple.com', 'Polska', '17-822', 'Żory', 'Parkowa', 18, NULL, 'Toby', 'aS2$^V2A'),
(5, 'Seweryn', 'Czarnecki', '197232223', 'goukyuu@omdlism.com', 'Polska', '05-405', 'Siedlce', 'Kasztanowa', 54, NULL, 'Seweryn', 'haD&2Gjia'),
(6, 'Regina', 'Wierzbicka', '875975445', 'cclayz@melowsa.com', 'Polska', '75-554', 'Tychy', 'Szkolna', 64, 12, 'Regina3W', 'qaZ123xsw'),
(7, 'Tadeusz', 'Jasiński', '310604695', 'dkinakin@plainst.com', 'Polska', '75-554', 'Toruń', 'Sandaczowa', 81, 78, 'Tadek7', 'jkdfyhd!$'),
(8, 'Albina', 'Kozioł', '496653864', 'topher1916@oaouemo.com', 'Polska', '35-521', 'Biała Podlaska', 'Tulipanowa', 2, NULL, 'Albi08', "akdjhds264"),
(9, 'Rudolf', 'Romanowski', '137647706', 'hennhaus@famytown.xyz', 'Polska', '76-483', 'Mysłowice', 'Modłowa', 7, NULL, 'Rudolf34', 'hdh&*$ca*z@#'),
(10, 'Nicole', 'Urbańska', '893315479', 'alan16@tigo.tk', 'Polska', '21-366', 'Mysłowice', 'Słowackiego', 31, 9, 'Nicole9675', 'ZU2^aJ5@#cf^12b'),
(11, 'Romualda', 'Kosińska', '234532394', 'djsp1200@filevino.com', 'Polska', '23-004', 'Kielce', 'PCK', 9, 51, "Romualda123", 'gUI3$%SF2as'),
(12, 'Bożena', 'Jóźwiak', '733316402', 'diankaodessa@mobitivaisao.com', 'Polska', '23-004', 'Radom', 'Stachury', 7, 90, 'Bożenaka', 'y%da$)h3NE'),
(13, 'Karina', 'Marek', '518052514', 'khan503@hasevo.com', 'Polska', '07-378', 'Krosno', 'Ćwiklińskiej', 13, 13, 'Karina', 'sd2H%Y#YBW533y'),
(14, 'Włodzimierz', 'Kosiński', '834473715', 'roqstar17@sumikang.com', 'Polska', '57-871', 'Chorzów', 'Rolnicza', 76, 61, 'Wlodzio', 'sadas@#$^ASa'),
(15, 'Justyna', 'Jastrzębska', '599952162', 'samirainterfarm@taikz.com', 'Polska', '52-233', 'Lublin', 'Okopowa', 100, 101, 'Justyna5', 'jtMTRFN$4y23g$'),
(16, 'Oliwia', 'Dąbrowska', '179095786', 'aleksandrcypin@rstoremail.ml', 'Polska', '74-284', 'Wałbrzych', 'Kujawska', 15, 1,'Oliwia858', 'dsdf3^#&*EWTGB');

INSERT INTO DOSTAWCA (Id_Dostawcy, Nazwa, Email, Numer_konta_bankowego, Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_Numer) VALUES
(1, 'FTransport', 'ftransport@gmail.com', '56782491346520215687524589', '43-421', 'Poznan', 'Widok', '9'),
(2, 'BigTransit', 'bigtransit@wp.pl', '53154097207946681000682451', '41-558', 'Koszalin', 'Rzemieślnicza', '28'),
(3, 'SzybkieDostawy', 'szybkiedostawy@o2.pl', '86193076266895453453276931', '43-421', 'Leszno', 'Piastowska', '12'),
(4, 'XeDef', 'xedef@gmail.com', '61154062116032497746659251', '92-256', 'Kraków', 'Lubińska', '67'),
(5, 'NTN', 'ntn@onet.pl', '94187099089534543073777450', '10-682', 'Gdynia', 'Okopowa', '56'),
(6, 'pdp', 'pdp@gmail.com', '75194026083794609212034698', '98-395', 'Zabrze', 'Usługowa', '48');

INSERT INTO ZAMOWIENIE_NA_MEBLE (Id_Zamowienia, Id_Klienta, Id_Stanu_Realizacji, Czas_realizacji_Data_zlozenia, Czas_Realizacji_Data_zakonczenia) VALUES
(1, 1, 4, '2022-01-01', NULL),
(2, 4, 5, '2022-01-07', NULL),
(3, 4, 3, '2022-01-20', NULL),
(4, 7, 6, '2001-05-22', '2001-06-11'),
(5, 7, 6, '2001-08-01', '2001-08-08'),
(6, 7, 6, '2012-04-25', '2001-05-12'),
(7, 1, 1, '2022-01-18', NULL),
(8, 2, 2, '2021-12-29', '2022-01-04'),
(9, 15, 6, '2015-07-21', '2015-09-09'),
(10, 3, 6, '2018-11-27', '2018-12-10'),
(11, 5, 3, '2021-12-01', NULL),
(12, 6, 5, '2021-12-01', NULL),
(13, 8, 4, '2021-11-08', NULL),
(14, 9, 6, '2019-06-14', '2019-06-28'),
(15, 10, 5, '2015-02-10', NULL),
(16, 11, 6, '2016-05-08', '2016-05-26'),
(17, 12, 3, '2022-01-22', NULL),
(18, 14, 2, '2021-09-02', '2021-11-23'),
(19, 15, 1, '2021-09-02', NULL),
(20, 8, 1, '2021-09-02', NULL),
(21, 2, 6, '2020-01-02', '2020-01-02'),
(22, 9, 6, '2002-06-03', '2002-08-07'),
(23, 11, 5, '2015-06-03', NULL),
(24, 12, 3, '2022-01-31', NULL),
(25, 3, 4, '2022-01-01', NULL),
(26, 10, 4, '2022-01-22', NULL);

INSERT INTO PROJEKT_KLIENTA (Id_Proj_klient, Id_Typu_mebla, Id_Laczenia, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc, Nazwa_pliku_rysunku) VALUES
(1, 1, 1, 900, 58, 2400, 'rysuneczek_stolu123.dwg'),
(2, 2, 1, 50, 240, 70, 'krzeselko_drewniane.dwg'),
(3, 5, 1, 156, 81, 80, 'sofa.dwg'),
(4, 11, 1, 70, 1900, 20, 'regal_23.dwg'),
(5, 12, 1, 20, 20000, 1, 'mojWymazonyKredens.dwg'),
(6, 10, 1, 2800, 85, 1121, 'kanapaNarozna.dwg'),
(7, 10, 1, 2800, 85, 1600, 'kanapaNarozna-trw.dwg'),
(8, 7, 1, 2500, 2400, 65, 'szafa.dwg'),
(9, 3, 1, 45, 1500, 65, 'fotel.dwg'),
(10, 3, 1, 45, 1500, 65, 'fotel-atye.dwg'),
(11, 9, 1, 50, 50, 40, 'fajne_biureczko.dwg');

INSERT INTO CENA (Id_Ceny, Id_Pracownika, Id_Proj_klient, Koszt_robocizny, Koszt_surowcow, Marza) VALUES
(1, 5, 1, 820, 2010, 4020),
(2, 5, 2, 1005, 2342, 2020),
(3, 5, 4, 1005, 4342, 1020),
(4, 5, 5, 1005, 3342, 3020),
(5, 9, 6, 200, 551, 700),
(6, 5, 7, 500, 2300, 900),
(7, 9, 8, 400, 400, 300),
(8, 9, 9, 460, 410, 200);


INSERT INTO MATERIAL_PROJ_KLIENTA (Id_Mat_Proj_klient, Id_Materialu, Id_Proj_klient, Ilosc) VALUES
(1, 7, 1, NULL),
(2, 13, 1, NULL),
(3, 2, 2, NULL),
(4, 5, 2, NULL),
(5, 11, 2, NULL),
(6, 2, 3, NULL),
(7, 6, 3, NULL),
(8, 12, 3, NULL),
(9, 16, 3, NULL),
(10, 6, 4, 2),
(11, 13, 4, 3),
(12, 18, 4, 2),
(13, 9, 5, 3),
(14, 17, 5, 1),
(15, 6, 6, 1),
(16, 16, 6, 1),
(17, 17, 6, 4),
(18, 8, 7, 1),
(19, 16, 7, 4),
(20, 19, 7, 2),
(21, 6, 8, 3),
(22, 14, 8, 2),
(23, 17, 8, 4),
(24, 4, 9, 4),
(25, 7, 9, 2),
(26, 12, 9, 3),
(27, 15, 9, 1);

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

INSERT INTO ZAMOWIENIE_NA_KOMPONENTY (NrZamowienia, Stan_realizacji, Czas_realizacji_Data_rozpoczecia, Czas_realizacji_Data_zakonczenia) VALUES
(1, 1, '2021-06-15', '2021-06-25'),
(2, 1, '2021-10-10', '2021-10-20'),
(3, 1, '2021-02-10', '2021-02-20'),
(4, 1, '2021-09-06', '2021-09-16'),
(5, 1, '2021-01-03', '2021-01-13'),
(6, 0, '2022-01-15', NULL),
(7, 0, '2022-01-04', NULL),
(8, 0, '2022-01-20', NULL),
(9, 0, '2022-01-04', NULL),
(10, 0, '2022-01-09', NULL);

INSERT INTO MATERIAL_ZAMOW_KOMP (Id_Mat_zam, Id_Materialu, NrZamowienia, Ilosc) VALUES
(1, 1, 5, 32),
(2, 14, 1, 44),
(3, 12, 5, 38),
(4, 19, 6, 49),
(5, 9, 1, 32),
(6, 11, 3, 49),
(7, 2, 7, 25),
(8, 12, 4, 45),
(9, 19, 10, 30),
(10, 11, 2, 31),
(11, 6, 1, 20),
(12, 5, 1, 42),
(13, 15, 4, 24),
(14, 15, 1, 27),
(15, 13, 10, 26),
(16, 6, 9, 36),
(17, 5, 3, 46),
(18, 4, 2, 41),
(19, 1, 4, 39);

INSERT INTO MEBEL (Id_Mebla, Id_Zamowienia, Id_Proj_klient, Id_Proj_katalog, Id_Opcj_czesci, Wykonany) VALUES
(1, 1, NULL, 1, 2, 0),
(2, 1, NULL, 2, 5, 0),
(3, 2, 1, NULL, NULL, 0),
(4, 2, 2, NULL, NULL, 1),
(5, 3, 3, NULL, NULL, 1),
(6, 3, NULL, 3, 8, 1),
(7, 4, 4, NULL, NULL, 0),
(8, 5, NULL, 4, 11, 1),
(9, 6, 5, NULL, NULL, 1),
(10, 7, 6, NULL, NULL, 1),
(11, 8, 7, NULL, NULL, 0),
(12, 9, 8, NULL, NULL, 0),
(13, 10, 9, NULL, NULL, 0),
(14, 11, NULL, 5, 15, 1),
(15, 12, NULL, 6, 17, 0),
(16, 13, NULL, 7, 19, 0),
(17, 14, NULL, 8, 24, 1),
(18, 15, NULL, 9, 26, 0),
(19, 16, NULL, 10, 27, 0),
(20, 17, NULL, 11, 31, 0),
(21, 18, NULL, 12, 33, 1),
(22, 19, NULL, 13, 35, 0),
(23, 20, NULL, 14, 37, 0),
(24, 21, NULL, 15, 39, 1),
(25, 22, NULL, 16, 42, 1),
(26, 23, NULL, 17, 44, 0),
(27, 24, NULL, 18, 45,0),
(28, 24, NULL, 19, 48, 0),
(29, 25, NULL, 20, 50, 0),
(30, 25, 10, NULL, NULL,0),
(31, 26, 11, NULL, NULL,0),
(32, 26, NULL, 23, 57, 0),
(33, 26, NULL, 24, 58, 0);

INSERT INTO PROJEKT_POLPRODUKTU(Id_Proj_polprod, Id_Proj_klient, Id_Proj_katalog, Id_Rodzaju_polproduktu, Nazwa, Rozmiar_Wysokosc, Rozmiar_Szerokosc, Rozmiar_Dlugosc, Cena, Nazwa_pliku_rysunku) VALUES
(1, NULL, 7, 1, 'FUTURA NV', 60, 1000, 2000, 270, 'futura.dwg'),
(2, NULL, 8, 1, 'FUTURA NV', 60, 1400, 2000, 305, 'futura2.dwg'),
(3, 3, NULL, 1, 'NATURA', 55, 700, 2000, 152, 'natura.jpg'),
(4, NULL, 9, 1, 'NATURA NV', 55, 1200, 2000, 215, 'natura2.jpg'),
(5, NULL, 2, 2, 'RAMA STOLU STALOWA', 450, 600, 900, 215, 'rama_stol.png'),
(6, NULL, 24, 2, 'RAMA KREDENSU STALOWA', 600, 300, 600, 250, 'rama_kredens.dwg'),
(7, NULL, 13, 3, 'DRZWICZKI AZUROWE', 2000, 20, 600, 440, 'front_azur.jpg'),
(8, NULL, 14, 3, 'FRONT MDF', 2000, 20, 600, 240, 'mdf1.jpg'),
(9, NULL, 15, 3, 'FRONT MDF', 600, 20, 250, 200, 'mdf2.jpg'),
(10, NULL, 16, 3, 'LUSTRZANY FRONT', 600, 20, 250, 950, 'front_lustro.jpg'),
(11, NULL, 17, 3, 'FRONT BUKOWY', 400, 20, 400, 400, 'Front_buk.png'),
(12, NULL, 18, 3, 'FRONT MDF', 400, 20, 450, 220, 'mdf3.png'),
(13, NULL, 23, 3, 'FRONT RZEZBIONY', 400, 20, 600, 500, 'front_rzezbiony.dwg'),
(14, NULL, 24, 3, 'FRONT FREZOWANY', 400, 20, 600, 420, 'front_frez.jpg'),
(15, NULL, 7, 4, 'DEKOR DREWNIANY', 100, 5, 200, 30, 'dekor.jpg'),
(16, NULL, 23, 4, 'DEKOR DREWNIANY NAROZNY', 100, 5, 100, 25, 'dekor_dr_nar.png'),
(17, 5, NULL, 3, 'FRONT MAHONIOWY', 600, 20, 400, 25, 'mahon_f.jpg'),
(18, 8, NULL, 3, 'FRONT MDF', 2000, 20, 1000, 25, 'mdf_f.jpg'),
(19, 11, NULL, 3, 'FRONT RZEZBIONY', 400, 20, 400, 25, 'front_rzezb.png');


INSERT INTO POLPRODUKT(Id_Polprod, Id_Proj_polprod, Id_Mebla) VALUES
(1, 1, 16),
(2, 2, 17),
(3, 3, 5),
(4, 4, 18),
(5, 5, 2),
(6, 6, 33),
(7, 7, 22),
(8, 8, 23),
(9, 9, 24),
(10, 10, 25),
(11, 11, 26),
(12, 12, 27),
(13, 13, 32),
(14, 14, 33),
(15, 15, 16),
(16, 16, 32),
(17, 17, 9),
(18, 18, 12),
(19, 19, 31);

INSERT INTO MATERIAL_MEBEL(Id_Mat_mebel, Id_Materialu, Id_Mebla) VALUES
(1, 10, 1),
(2, 11, 2),
(3, 13, 3),
(4, 11, 4),
(5, 3, 5),
(6, 14, 5),
(7, 12, 6),
(8, 8, 7),
(9, 10, 8),
(10, 10, 9),
(11, 4, 10),
(12, 15, 10),
(13, 1, 11),
(14, 6, 12),
(15, 3, 13),
(16, 15, 13),
(17, 4, 14),
(18, 16, 14),
(19, 2, 15),
(20, 14, 15),
(21, 9, 16),
(22, 9, 17),
(23, 3, 18),
(24, 16, 18),
(25, 2, 19),
(26, 15, 19),
(27, 10, 20),
(28, 7, 21),
(29, 17, 21),
(30, 13, 22),
(31, 8, 23),
(32, 18, 23),
(33, 11, 24),
(34, 5, 25),
(35, 19, 25),
(36, 5, 26),
(37, 19, 26),
(38, 6, 27),
(39, 17, 27),
(40, 2, 28),
(41, 15, 28),
(42, 4, 29),
(43, 16, 29),
(44, 3, 30),
(45, 14, 30),
(46, 12, 31),
(47, 10, 32),
(48, 13, 33);

INSERT INTO DEFINICJA_ZADANIA (Id_Def_zadania, Id_Proj_klient, Id_Proj_katalog, Opis_zadania, Cena, Czas_wykonania) VALUES
(1, 4, NULL, 'wytnij elementy z płyty paździerzowej', NULL, NULL),
(2, 4, NULL, 'oklej fronty czarną okleiną', NULL, NULL),
(3, 4, NULL, 'nawierć otwory pod śruby', NULL, NULL),
(4, 4, NULL, 'umieść element na frezarce', NULL, NULL),
(5, 5, NULL, 'wytnij elementy z płyty wiórwej', NULL, NULL),
(6, 5, NULL, 'oklej fornty niebieską okleiną', NULL, NULL),
(7, 5, NULL, 'nawierć otwory pod wkręty', NULL, NULL),
(8, 5, NULL, 'umieść element na wietarce', NULL, NULL),
(9, 6, NULL, 'wytnij elementy stelażu szlifierką', NULL, NULL),
(10, 6, NULL, 'pomaluj elementy ozdobne ', NULL, NULL),
(11, 6, NULL, 'wytnij listwy z drewna bukowego', NULL, NULL),
(12, 6, NULL, 'zatapiceruj tkaniną welwet', NULL, NULL),
(13, 7, NULL, 'wytnij elementy stelażu piłką do metalu', NULL, NULL),
(14, 7, NULL, 'pomaluj plastikowe elementy ozdobne ', NULL, NULL),
(15, 7, NULL, 'wytnij listwy z drewna sosnowego', NULL, NULL),
(16, 7, NULL, 'zatapiceruj ekoskórą', NULL, NULL),
(17, 8, NULL, 'wytnij fronty z płyty MDF', NULL, NULL),
(18, 8, NULL, 'zamontuj prowadnice liniowe', NULL, NULL),
(19, 8, NULL, 'zamontuj zawiasy po bokach frontów', NULL, NULL),
(20, 8, NULL, 'zamontuj pułki z drewna dębowego', NULL, NULL),
(21, 9, NULL, 'wytnij elementy konstrukcyjne ze sklejki sosnowej 15 mm', NULL, NULL),
(22, 9, NULL, 'dodaj pikowania na oparciu ', NULL, NULL),
(23, 9, NULL, 'załóż guziki na oparcie', NULL, NULL),
(24, 9, NULL, 'zatapiceruj ', NULL, NULL),
(25, 9, NULL, 'załóż drewniane elementy ozdobne ', NULL, NULL),
(26, 10, NULL, 'wytnij elementy konstrukcyjne ze sklejki sosnowej 16 mm', NULL, NULL),
(27, 10, NULL, 'opisz elementy konstrukcyjne ', NULL, NULL),
(28, 10, NULL, 'zbij stelaż ', NULL, NULL),
(29, 10, NULL, 'załóż sprężyny siedziska ', NULL, NULL),
(30, 10, NULL, 'oklej siedzisko dwoma warstwami pianki średnikej miękkości ', NULL, NULL),
(31, 11, NULL, 'dotnij deski grubości 34mm jesionowe na blat, na długość 520 mm', NULL, NULL),
(32, 11, NULL, 'nawierć otwory ', NULL, NULL),
(33, 11, NULL, 'sklej ze sobą deski blatu ', NULL, NULL),
(34, 11, NULL, 'hebluj blat na grubość 30 mm', NULL, NULL),
(35, NULL, 1, 'przygotuj deski dębowe', 12, '00:48:00'),
(36, NULL, 1, 'dotnij deski na 2m', 15, '00:40:00'),
(37, NULL, 1, 'hebluj deski dębowe', 13, '00:51:00'),
(38, NULL, 1, 'sklej deski na blat klejem aminowym', 20, '00:30:00'),
(39, NULL, 1, 'przygotuj deski sosnowe na nogi ', 17, '00:53:00'),
(40, NULL, 1, 'wytnij nogi według szablonu pierwszego', 18, '00:56:00'),
(41, NULL, 1, 'obrób szablon nóg przy pomocy frezaki', 14, '00:41:00'),
(42, NULL, 1, 'umieść sklejony blat na centrum obróbczym ', 19, '00:18:00'),
(43, NULL, 1, 'sklej blat klejem ftalowym', 11, '00:33:00'),
(44, NULL, 1, 'zmontuj stół na śruby', 10, '00:44:00'),
(45, NULL, 2, 'przygotuj deski sosnowe', 12, '00:35:00'),
(46, NULL, 2, 'dotnij deski na 1m', 15, '00:23:00'),
(47, NULL, 2, 'hebluj deski sosnowe', 13, '00:11:00'),
(48, NULL, 2, 'sklej deski na blat klejem ftalowym', 20, '00:41:00'),
(49, NULL, 2, 'przygotuj deski bukowe na nogi ', 17, '00:26:00'),
(50, NULL, 2, 'wytnij nogi według szablonu drugiego', 18, '00:30:00'),
(51, NULL, 2, 'obrób szablon nóg ręcznie', 14, '00:42:00'),
(52, NULL, 2, 'umieść sklejony blat na nogach', 19, '00:45:00'),
(53, NULL, 2, 'sklej blat klejem aminowym', 11, '00:54:00'),
(54, NULL, 2, 'zmontuj stół na kołki', 10, '00:57:00'),
(55, NULL, 3, 'wytnij drewaniane listwy na 2,5m', 13, '00:16:00'),
(56, NULL, 3, 'wygnij elementy oparcia w łuk', 14, '00:14:00'),
(57, NULL, 3, 'oklej siedzisko fornirem naturalnym', 19, '00:34:00'),
(58, NULL, 3, 'otapiceruj siedzisko flokiem', 16, '00:14:00'),
(59, NULL, 4, 'wytnij drewaniane listwy na 1m', 13, '00:35:00'),
(60, NULL, 4, 'wygnij elementy oparcia w koło', 14, '00:11:00'),
(61, NULL, 4, 'oklej siedzisko fornirem modyfikowanym', 19, '00:19:00'),
(62, NULL, 4, 'otapiceruj siedzisko welwetem', 16, '00:22:00'),
(63, NULL, 5, 'wytnij elementy stelażu z stali C20', 13, '00:48:00'),
(64, NULL, 5, 'zbij stelaż w kwadrat', 20, '00:31:00'),
(65, NULL, 5, 'oklej stelaż pianką poliureatnową', 13, '00:10:00'),
(66, NULL, 5, 'zrób roskrój tkanin  ekoskóry', 20, '00:37:00'),
(67, NULL, 5, 'zatapiceruj ekoskórą', 15, '00:12:00'),
(68, NULL, 6, 'wytnij elementy stelażu z stali C25', 13, '00:52:00'),
(69, NULL, 6, 'zbij stelaż w trójkąt', 20, '00:52:00'),
(70, NULL, 6, 'oklej stelaż pianką wysokoelastyczną', 13, '00:19:00'),
(71, NULL, 6, 'zrób roskrój tkanin szenilu', 20, '00:35:00'),
(72, NULL, 6, 'zatapiceruj welwetem', 15, '00:49:00'),
(73, NULL, 7, 'dotnij płytę paździerzową na skrzynię ', 13, '00:14:00'),
(74, NULL, 7, 'dotnij płytę pilśniową na zagłówek ', 19, '00:13:00'),
(75, NULL, 7, 'dodaj otwory montażowe ', 18, '00:33:00'),
(76, NULL, 7, 'zbij skrzynię ', 19, '00:42:00'),
(77, NULL, 7, 'oklej zagłówek okleiną PCV', 15, '00:29:00'),
(78, NULL, 8, 'dotnij płytę wiórową na skrzynię ', 10, '00:43:00'),
(79, NULL, 8, 'dotnij płytę MDF na zagłówek ', 18, '00:21:00'),
(80, NULL, 8, 'dodaj kołki montażowe ', 12, '00:10:00'),
(81, NULL, 8, 'skręć skrzynię ', 12, '00:49:00'),
(82, NULL, 8, 'oklej zagłówek formirem naturalnym', 15, '00:22:00'),
(83, NULL, 9, 'wytnij elementy stelażu w prostopadłościan', 20, '00:22:00'),
(84, NULL, 9, 'wytnnij elementy boczka z płyty wiórowej', 10, '00:50:00'),
(85, NULL, 9, 'zbij stelaż ', 13, '00:10:00'),
(86, NULL, 9, 'oklej stelaż pianką wysokoelastyczną', 12, '00:34:00'),
(87, NULL, 9, 'zrób roskrój szenilu', 20, '00:47:00'),
(88, NULL, 9, 'zatapiceruj ekoskórą', 15, '00:52:00'),
(89, NULL, 10, 'wytnij elementy stelażu w ostrosłup', 20, '00:52:00'),
(90, NULL, 10, 'wytnnij elementy boczka z płyty paździerzowej', 13, '00:10:00'),
(91, NULL, 10, 'skręć stelaż ', 12, '00:58:00'),
(92, NULL, 10, 'oklej stelaż pianką poliueratnową', 18, '00:50:00'),
(93, NULL, 10, 'zrób roskrój wlwetu', 17, '00:12:00'),
(94, NULL, 10, 'zatapiceruj flokiem', 13, '00:24:00'),
(95, NULL, 11, 'wytnij element blatu w okrąg', 11, '00:14:00'),
(96, NULL, 11, 'wytnij płytę na nogi z drewna sosnowego', 14, '00:38:00'),
(97, NULL, 11, 'polakieruj elementy lakierem bezbarwnym', 11, '00:58:00'),
(98, NULL, 11, 'nawierć otwory na kołki ', 20, '00:13:00'),
(99, NULL, 11, 'sklej elementy klejem do drewna', 10, '00:24:00'),
(100, NULL, 12, 'wytnij element blatu w prostokąt', 19, '00:30:00'),
(101, NULL, 12, 'wytnij płytę na nogi z drewna dębowego', 17, '00:17:00'),
(102, NULL, 12, 'polakieruj elementy lakierem brązowym', 14, '00:43:00'),
(103, NULL, 12, 'nawierć otwory na wkęty', 14, '00:42:00'),
(104, NULL, 12, 'sklej elementy klejem akrylowym', 19, '00:56:00'),
(105, NULL, 13, 'wytnij fronty z płyty lamniowanej', 16, '00:24:00'),
(106, NULL, 13, 'zamontuj prowadnice stalowe', 13, '00:54:00'),
(107, NULL, 13, 'zamontuj zawiasy alumniowe', 10, '00:43:00'),
(108, NULL, 13, 'zamontuj pułki z drewna sosnowego', 12, '00:50:00'),
(109, NULL, 14, 'wytnij fronty z płyty pilśniowej', 13, '00:49:00'),
(110, NULL, 14, 'zamontuj prowadnice ', 12, '00:32:00'),
(111, NULL, 14, 'zamontuj zawiasy ', 13, '00:41:00'),
(112, NULL, 14, 'zamontuj pułki na kołki', 15, '00:58:00'),
(113, NULL, 15, 'przygotuj deski', 11, '00:46:00'),
(114, NULL, 15, 'dotnij deski ', 18, '00:30:00'),
(115, NULL, 15, 'hebluj deki ', 20, '00:16:00'),
(116, NULL, 15, 'wywierć otwory na centum obróbczym ', 15, '00:55:00'),
(117, NULL, 16, 'przygotuj deski', 11, '00:42:00'),
(118, NULL, 16, 'dotnij deski na długość 580mm', 18, '00:39:00'),
(119, NULL, 16, 'hebluj deski', 15, '00:47:00'),
(120, NULL, 16, 'wytnij fronty na frezarce numerycznej ', 11, '00:39:00'),
(121, NULL, 17, 'dotnij drewniane listwy ', 13, '00:37:00'),
(122, NULL, 17, 'dotnij elementy z płyty na szuflady ', 18, '00:32:00'),
(123, NULL, 17, 'polakieruj ', 10, '00:27:00'),
(124, NULL, 17, 'zmontuj ', 16, '00:49:00'),
(125, NULL, 18, 'ofrezuj elementy nóżek frezem kształtowym ', 19, '00:42:00'),
(126, NULL, 18, 'dotnij elementy z płyty laminowanej na szuflady ', 11, '00:39:00'),
(127, NULL, 18, 'nawierć otwory pod prowadnice szuflady ', 17, '00:19:00'),
(128, NULL, 18, 'pomaluj ', 11, '00:54:00'),
(129, NULL, 19, 'wytnij elementy stelażu na frezarce numerycznej ', 10, '00:55:00'),
(130, NULL, 19, 'wytnnij elementy boczka z płyty ', 17, '00:22:00'),
(131, NULL, 19, 'zbij stelaż ', 12, '00:30:00'),
(132, NULL, 19, 'oklej stelaż pianką ', 16, '00:25:00'),
(133, NULL, 19, 'zrób roskrój tkanin ', 15, '00:18:00'),
(134, NULL, 19, 'zatapiceruj ', 16, '00:24:00'),
(135, NULL, 19, 'zamontuj zaczepy ', 11, '00:16:00'),
(136, NULL, 20, 'wytnij elementy stelażu na frezarce numerycznej ', 14, '00:45:00'),
(137, NULL, 20, 'pomaluj drewniane elementy ozdobne ', 19, '00:26:00'),
(138, NULL, 20, 'wytnij drewniane listwy konstrukcyjne', 16, '00:49:00'),
(139, NULL, 20, 'zatapiceruj ', 20, '00:00:00'),
(140, NULL, 21, 'wytnij elementy z płyty ', 19, '00:17:00'),
(141, NULL, 21, 'oklej fornty białą okleiną', 13, '00:36:00'),
(142, NULL, 21, 'nawierć otwory pod mimośrody ', 18, '00:49:00'),
(143, NULL, 21, 'umieść element na frezarce numerycznej ', 10, '00:34:00'),
(144, NULL, 22, 'wytnij elementy ze sklejki ', 10, '00:38:00'),
(145, NULL, 22, 'oklej fornty czarną okleiną', 11, '00:49:00'),
(146, NULL, 22, 'nawierć otwory pod kołki ', 14, '00:48:00'),
(147, NULL, 22, 'umieść element na frezarce numerycznej ', 13, '00:38:00'),
(148, NULL, 23, 'wytnij fronty z płyty lamniowanej, orzech ', 16, '00:11:00'),
(149, NULL, 23, 'zamontuj prowdnice długości 345mm', 19, '00:10:00'),
(150, NULL, 23, 'zamontuj zawiasy, STAR', 11, '00:22:00'),
(151, NULL, 23, 'zmontuj wszystkie elementy ', 14, '00:39:00'),
(152, NULL, 24, 'wytnij fronty z płyty lamniowanej, wiśnia  ', 10, '00:58:00'),
(153, NULL, 24, 'zamontuj prowdnice długości 390', 20, '00:27:00'),
(154, NULL, 24, 'zamontuj zawiasy GTV', 16, '00:42:00'),
(155, NULL, 24, 'zmontuj wszystkie elementy ', 15, '00:47:00');

INSERT INTO ZADANIE (Id_Zadania, Id_Mebla) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 6),
(22, 6),
(23, 6),
(24, 6),
(25, 8),
(26, 8),
(27, 8),
(28, 8),
(29, 14),
(30, 14),
(31, 14),
(32, 14),
(33, 14),
(34, 15),
(35, 15),
(36, 15),
(37, 15),
(38, 15),
(39, 16),
(40, 16),
(41, 16),
(42, 16),
(43, 16),
(44, 17),
(45, 17),
(46, 17),
(47, 17),
(48, 17),
(49, 18),
(50, 18),
(51, 18),
(52, 18),
(53, 18),
(54, 18),
(55, 19),
(56, 19),
(57, 19),
(58, 19),
(59, 19),
(60, 19),
(61, 20),
(62, 20),
(63, 20),
(64, 20),
(65, 20),
(66, 21),
(67, 21),
(68, 21),
(69, 21),
(70, 21),
(71, 22),
(72, 22),
(73, 22),
(74, 22),
(75, 23),
(76, 23),
(77, 23),
(78, 23),
(79, 24),
(80, 24),
(81, 24),
(82, 24),
(83, 25),
(84, 25),
(85, 25),
(86, 25),
(87, 26),
(88, 26),
(89, 26),
(90, 26),
(91, 27),
(92, 27),
(93, 27),
(94, 27),
(95, 28),
(96, 28),
(97, 28),
(98, 28),
(99, 28),
(100, 28),
(101, 28),
(102, 29),
(103, 29),
(104, 29),
(105, 29),
(106, 32),
(107, 32),
(108, 32),
(109, 32),
(110, 33),
(111, 33),
(112, 33),
(113, 33),
(114, 7),
(115, 7),
(116, 7),
(117, 7),
(118, 9),
(119, 9),
(120, 9),
(121, 9),
(122, 10),
(123, 10),
(124, 10),
(125, 10),
(126, 11),
(127, 11),
(128, 11),
(129, 11),
(130, 12),
(131, 12),
(132, 12),
(133, 12),
(134, 13),
(135, 13),
(136, 13),
(137, 13),
(138, 13),
(139, 30),
(140, 30),
(141, 30),
(142, 30),
(143, 30),
(144, 31),
(145, 31),
(146, 31),
(147, 31);

INSERT INTO REKLAMACJA (Id_Mebla, Opis_reklamacji) VALUES
(12, "Drzwi są źle spasowane i ocierają o boki szafy"),
(13, "Siedzisko zapadło się po dwóch dniach użytkowania.")




