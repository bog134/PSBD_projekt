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
(5, 12, 1, 20, 20000, 1, 'mojWymazonyKredens_xd.dwg'),
(6, 10, 1, 2800, 85, 1121, 'kanapaNarozna.dwg'),
(7, 10, 1, 2800, 85, 1600, 'kanapaNarozna-trw.dwg'),
(8, 7, 1, 2500, 2400, 65, 'szafa.dwg'),
(9, 3, 1, 45, 1500, 65, 'fotel.dwg'),
(10, 3, 1, 45, 1500, 65, 'fotel-atye.dwg'),
(11, 9, 1, 50, 50, 40, 'fajne_biureczko.dwg');

INSERT INTO CENA (Id_Ceny, Id_Proj_klient, Id_Pracownika, Koszt_robocizny, Koszt_materialow, Marza,Koszt_polproduktow) VALUES
(1, 4, 5, 820, 2010, 4020, 500),
(2, 5, 5, 1005, 2342, 2020, 600),
(3, 6, 5, 1005, 4342, 1020, 400),
(4, 7, 5, 1005, 3342, 3020, 300),
(5, 8, 9, 200, 551, 700, 200),
(6, 9, 5, 500, 2300, 900, 100),
(7, 10, 9, 400, 400, 300, 200),
(8, 11, 9, 460, 410, 200, 300);

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

INSERT INTO ZAMOWIENIE_NA_KOMPONENTY (NrZamowienia, Id_Dostawcy, Stan_realizacji, Czas_realizacji_Data_rozpoczecia, Czas_realizacji_Data_zakonczenia) VALUES
(1, 1, 1, '2021-06-15', '2021-06-25'),
(2, 2, 1, '2021-10-10', '2021-10-20'),
(3, 3, 1, '2021-02-10', '2021-02-20'),
(4, 4, 1, '2021-09-06', '2021-09-16'),
(5, 5, 1, '2021-01-03', '2021-01-13'),
(6, 6, 0, '2022-01-15', '2022-01-15'),
(7, 1, 0, '2022-01-04', '2022-01-14'),
(8, 2, 0, '2022-01-20', '2022-01-25'),
(9, 3, 0, '2022-01-04', '2022-01-09'),
(10, 4, 0, '2022-01-09', '2022-01-18');

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

INSERT INTO PROJEKT_POLPRODUKTU (Id_Proj_polprod, Id_Proj_klient, Id_Proj_katalog, Id_Rodzaju_polproduktu, Id_Dostawcy, Nazwa, Rozmiar_Wysokosc, Rozmiar_Szerokosc, Rozmiar_Dlugosc, Cena, Nazwa_pliku_rysunku) VALUES
(1, NULL, 7, 1, 4, 'FUTURA NV', 60, 1000, 2000, 270, 'futura.dwg'),
(2, NULL, 8, 1, 4, 'FUTURA NV', 60, 1400, 2000, 305, 'futura2.dwg'),
(3, 3, NULL, 1, 4, 'NATURA', 55, 700, 2000,NULL, 'natura.jpg'),
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
(16, NULL, 23, 4, 6, 'DEKOR DREWNIANY NAROZNY', 100, 5, 100, 25, 'dekor_dr_nar.png'),
(17, 5, NULL, 3, 5, 'FRONT MAHONIOWY', 600, 20, 400, 25, 'mahon_f.jpg'),
(18, 8, NULL, 3, 5, 'FRONT MDF', 2000, 20, 1000, 25, 'mdf_f.jpg'),
(19, 11, NULL, 3, 5, 'FRONT RZEZBIONY', 400, 20, 400, 25, 'front_rzezb.png');



INSERT INTO POLPRODUKT(Id_Polprod, Id_Proj_polprod, Id_Mebla, NrZamowienia) VALUES
(1, 1, 16,1),
(2, 2, 17,2),
(3, 3, 5,3),
(4, 4, 18,4),
(5, 5, 2,5),
(6, 6, 33,6),
(7, 7, 22,7),
(8, 8, 23,8),
(9, 9, 24,9),
(10, 10, 25,10),
(11, 11, 26,1),
(12, 12, 27,2),
(13, 13, 32,3),
(14, 14, 33,4),
(15, 15, 16,5),
(16, 16, 32,6),
(17, 17, 9,7),
(18, 18, 12,8),
(19, 19, 31,9);

INSERT INTO MATERIAL_MEBEL (Id_Mat_mebel, Id_Materialu, Id_Mebla, Ilosc) VALUES
(1, 10, 1, 4),
(2, 11, 2, 2),
(3, 13, 3, 4),
(4, 11, 4, 1),
(5, 3, 5, 3),
(6, 14, 5, 5),
(7, 12, 6, 1),
(8, 8, 7, 5),
(9, 10, 8, 2),
(10, 10, 9, 4),
(11, 4, 10, 4),
(12, 15, 10, 4),
(13, 1, 11, 2),
(14, 6, 12, 1),
(15, 3, 13, 3),
(16, 15, 13, 5),
(17, 4, 14, 2),
(18, 16, 14, 4),
(19, 2, 15, 1),
(20, 14, 15, 5),
(21, 9, 16, 3),
(22, 9, 17, 2),
(23, 3, 18, 3),
(24, 16, 18, 4),
(25, 2, 19, 5),
(26, 15, 19, 4),
(27, 10, 20, 2),
(28, 7, 21, 5),
(29, 17, 21, 3),
(30, 13, 22, 1),
(31, 8, 23, 5),
(32, 18, 23, 1),
(33, 11, 24, 2),
(34, 5, 25, 5),
(35, 19, 25, 2),
(36, 5, 26, 2),
(37, 19, 26, 2),
(38, 6, 27, 1),
(39, 17, 27, 1),
(40, 2, 28, 4),
(41, 15, 28, 3),
(42, 4, 29, 2),
(43, 16, 29, 2),
(44, 3, 30, 1),
(45, 14, 30, 5),
(46, 12, 31, 1),
(47, 10, 32, 2),
(48, 13, 33, 1);

INSERT INTO DEFINICJA_ZADANIA (Id_Def_zadania, Id_Proj_klient, Id_Proj_katalog, Opis_zadania, Cena, Czas_wykonania) VALUES
(1, 4, NULL, 'wytnij elementy z płyty paździerzowej', 25, 2),
(2, 4, NULL, 'oklej fronty czarną okleiną', 25, 2),
(3, 4, NULL, 'nawierć otwory pod śruby', 25, 3),
(4, 4, NULL, 'umieść element na frezarce', 25, 3),
(5, 5, NULL, 'wytnij elementy z płyty wiórwej', 25, 1),
(6, 5, NULL, 'oklej fornty niebieską okleiną', 25, 2),
(7, 5, NULL, 'nawierć otwory pod wkręty', 25, 5),
(8, 5, NULL, 'umieść element na wietarce', 25, 2),
(9, 6, NULL, 'wytnij elementy stelażu szlifierką', 25, 2),
(10, 6, NULL, 'pomaluj elementy ozdobne ', 25, 4),
(11, 6, NULL, 'wytnij listwy z drewna bukowego', 25, 1),
(12, 6, NULL, 'zatapiceruj tkaniną welwet', 25, 4),
(13, 7, NULL, 'wytnij elementy stelażu piłką do metalu', 25, 1),
(14, 7, NULL, 'pomaluj plastikowe elementy ozdobne ', 25, 2),
(15, 7, NULL, 'wytnij listwy z drewna sosnowego', 25, 3),
(16, 7, NULL, 'zatapiceruj ekoskórą', 25, 2),
(17, 8, NULL, 'wytnij fronty z płyty MDF', 25, 2),
(18, 8, NULL, 'zamontuj prowadnice liniowe', 25, 1),
(19, 8, NULL, 'zamontuj zawiasy po bokach frontów', 25, 4),
(20, 8, NULL, 'zamontuj pułki z drewna dębowego', 25, 4),
(21, 9, NULL, 'wytnij elementy konstrukcyjne ze sklejki sosnowej 15 mm', 25, 1),
(22, 9, NULL, 'dodaj pikowania na oparciu ', 25, 3),
(23, 9, NULL, 'załóż guziki na oparcie', 25, 5),
(24, 9, NULL, 'zatapiceruj ', 25, 1),
(25, 9, NULL, 'załóż drewniane elementy ozdobne ', 25, 5),
(26, 10, NULL, 'wytnij elementy konstrukcyjne ze sklejki sosnowej 16 mm', 25, 4),
(27, 10, NULL, 'opisz elementy konstrukcyjne ', 25, 5),
(28, 10, NULL, 'zbij stelaż ', 25, 3),
(29, 10, NULL, 'załóż sprężyny siedziska ', 25, 5),
(30, 10, NULL, 'oklej siedzisko dwoma warstwami pianki średnikej miękkości ', 25, 1),
(31, 11, NULL, 'dotnij deski grubości 34mm jesionowe na blat, na długość 520 mm', 25, 3),
(32, 11, NULL, 'nawierć otwory ', 25, 2),
(33, 11, NULL, 'sklej ze sobą deski blatu ', 25, 1),
(34, 11, NULL, 'hebluj blat na grubość 30 mm', 25, 4),
(35, NULL, 1, 'przygotuj deski dębowe', 25, 1),
(36, NULL, 1, 'dotnij deski na 2m', 25, 5),
(37, NULL, 1, 'hebluj deski dębowe', 25, 5),
(38, NULL, 1, 'sklej deski na blat klejem aminowym', 25, 1),
(39, NULL, 1, 'przygotuj deski sosnowe na nogi ', 25, 5),
(40, NULL, 1, 'wytnij nogi według szablonu pierwszego', 25, 1),
(41, NULL, 1, 'obrób szablon nóg przy pomocy frezaki', 25, 1),
(42, NULL, 1, 'umieść sklejony blat na centrum obróbczym ', 25, 1),
(43, NULL, 1, 'sklej blat klejem ftalowym', 25, 3),
(44, NULL, 1, 'zmontuj stół na śruby', 25, 1),
(45, NULL, 2, 'przygotuj deski sosnowe', 25, 5),
(46, NULL, 2, 'dotnij deski na 1m', 25, 1),
(47, NULL, 2, 'hebluj deski sosnowe', 25, 3),
(48, NULL, 2, 'sklej deski na blat klejem ftalowym', 25, 4),
(49, NULL, 2, 'przygotuj deski bukowe na nogi ', 25, 3),
(50, NULL, 2, 'wytnij nogi według szablonu drugiego', 25, 2),
(51, NULL, 2, 'obrób szablon nóg ręcznie', 25, 1),
(52, NULL, 2, 'umieść sklejony blat na nogach', 25, 4),
(53, NULL, 2, 'sklej blat klejem aminowym', 25, 3),
(54, NULL, 2, 'zmontuj stół na kołki', 25, 1),
(55, NULL, 3, 'wytnij drewaniane listwy na 2,5m', 25, 5),
(56, NULL, 3, 'wygnij elementy oparcia w łuk', 25, 5),
(57, NULL, 3, 'oklej siedzisko fornirem naturalnym', 25, 2),
(58, NULL, 3, 'otapiceruj siedzisko flokiem', 25, 3),
(59, NULL, 4, 'wytnij drewaniane listwy na 1m', 25, 5),
(60, NULL, 4, 'wygnij elementy oparcia w koło', 25, 3),
(61, NULL, 4, 'oklej siedzisko fornirem modyfikowanym', 25, 5),
(62, NULL, 4, 'otapiceruj siedzisko welwetem', 25, 4),
(63, NULL, 5, 'wytnij elementy stelażu z stali C20', 25, 2),
(64, NULL, 5, 'zbij stelaż w kwadrat', 25, 5),
(65, NULL, 5, 'oklej stelaż pianką poliureatnową', 25, 2),
(66, NULL, 5, 'zrób roskrój tkanin  ekoskóry', 25, 5),
(67, NULL, 5, 'zatapiceruj ekoskórą', 25, 5),
(68, NULL, 6, 'wytnij elementy stelażu z stali C25', 25, 4),
(69, NULL, 6, 'zbij stelaż w trójkąt', 25, 4),
(70, NULL, 6, 'oklej stelaż pianką wysokoelastyczną', 25, 3),
(71, NULL, 6, 'zrób roskrój tkanin szenilu', 25, 5),
(72, NULL, 6, 'zatapiceruj welwetem', 25, 3),
(73, NULL, 7, 'dotnij płytę paździerzową na skrzynię ', 25, 1),
(74, NULL, 7, 'dotnij płytę pilśniową na zagłówek ', 25, 1),
(75, NULL, 7, 'dodaj otwory montażowe ', 25, 3),
(76, NULL, 7, 'zbij skrzynię ', 25, 4),
(77, NULL, 7, 'oklej zagłówek okleiną PCV', 25, 1),
(78, NULL, 8, 'dotnij płytę wiórową na skrzynię ', 25, 3),
(79, NULL, 8, 'dotnij płytę MDF na zagłówek ', 25, 4),
(80, NULL, 8, 'dodaj kołki montażowe ', 25, 2),
(81, NULL, 8, 'skręć skrzynię ', 25, 5),
(82, NULL, 8, 'oklej zagłówek formirem naturalnym', 25, 2),
(83, NULL, 9, 'wytnij elementy stelażu w prostopadłościan', 25, 2),
(84, NULL, 9, 'wytnnij elementy boczka z płyty wiórowej', 25, 1),
(85, NULL, 9, 'zbij stelaż ', 25, 3),
(86, NULL, 9, 'oklej stelaż pianką wysokoelastyczną', 25, 5),
(87, NULL, 9, 'zrób roskrój szenilu', 25, 4),
(88, NULL, 9, 'zatapiceruj ekoskórą', 25, 2),
(89, NULL, 10, 'wytnij elementy stelażu w ostrosłup', 25, 3),
(90, NULL, 10, 'wytnnij elementy boczka z płyty paździerzowej', 25, 2),
(91, NULL, 10, 'skręć stelaż ', 25, 4),
(92, NULL, 10, 'oklej stelaż pianką poliueratnową', 25, 2),
(93, NULL, 10, 'zrób roskrój wlwetu', 25, 4),
(94, NULL, 10, 'zatapiceruj flokiem', 25, 4),
(95, NULL, 11, 'wytnij element blatu w okrąg', 25, 4),
(96, NULL, 11, 'wytnij płytę na nogi z drewna sosnowego', 25, 3),
(97, NULL, 11, 'polakieruj elementy lakierem bezbarwnym', 25, 2),
(98, NULL, 11, 'nawierć otwory na kołki ', 25, 2),
(99, NULL, 11, 'sklej elementy klejem do drewna', 25, 4),
(100, NULL, 12, 'wytnij element blatu w prostokąt', 25, 3),
(101, NULL, 12, 'wytnij płytę na nogi z drewna dębowego', 25, 3),
(102, NULL, 12, 'polakieruj elementy lakierem brązowym', 25, 1),
(103, NULL, 12, 'nawierć otwory na wkęty', 25, 2),
(104, NULL, 12, 'sklej elementy klejem akrylowym', 25, 1),
(105, NULL, 13, 'wytnij fronty z płyty lamniowanej', 25, 5),
(106, NULL, 13, 'zamontuj prowadnice stalowe', 25, 4),
(107, NULL, 13, 'zamontuj zawiasy alumniowe', 25, 3),
(108, NULL, 13, 'zamontuj pułki z drewna sosnowego', 25, 3),
(109, NULL, 14, 'wytnij fronty z płyty pilśniowej', 25, 3),
(110, NULL, 14, 'zamontuj prowadnice ', 25, 4),
(111, NULL, 14, 'zamontuj zawiasy ', 25, 2),
(112, NULL, 14, 'zamontuj pułki na kołki', 25, 3),
(113, NULL, 15, 'przygotuj deski', 25, 2),
(114, NULL, 15, 'dotnij deski ', 25, 4),
(115, NULL, 15, 'hebluj deki ', 25, 5),
(116, NULL, 15, 'wywierć otwory na centum obróbczym ', 25, 2),
(117, NULL, 16, 'przygotuj deski', 25, 5),
(118, NULL, 16, 'dotnij deski na długość 580mm', 25, 4),
(119, NULL, 16, 'hebluj deski', 25, 4),
(120, NULL, 16, 'wytnij fronty na frezarce numerycznej ', 25, 1),
(121, NULL, 17, 'dotnij drewniane listwy ', 25, 5),
(122, NULL, 17, 'dotnij elementy z płyty na szuflady ', 25, 3),
(123, NULL, 17, 'polakieruj ', 25, 3),
(124, NULL, 17, 'zmontuj ', 25, 2),
(125, NULL, 18, 'ofrezuj elementy nóżek frezem kształtowym ', 25, 5),
(126, NULL, 18, 'dotnij elementy z płyty laminowanej na szuflady ', 25, 3),
(127, NULL, 18, 'nawierć otwory pod prowadnice szuflady ', 25, 4),
(128, NULL, 18, 'pomaluj ', 25, 1),
(129, NULL, 19, 'wytnij elementy stelażu na frezarce numerycznej ', 25, 3),
(130, NULL, 19, 'wytnnij elementy boczka z płyty ', 25, 2),
(131, NULL, 19, 'zbij stelaż ', 25, 3),
(132, NULL, 19, 'oklej stelaż pianką ', 25, 3),
(133, NULL, 19, 'zrób roskrój tkanin ', 25, 1),
(134, NULL, 19, 'zatapiceruj ', 25, 1),
(135, NULL, 19, 'zamontuj zaczepy ', 25, 2),
(136, NULL, 20, 'wytnij elementy stelażu na frezarce numerycznej ', 25, 3),
(137, NULL, 20, 'pomaluj drewniane elementy ozdobne ', 25, 4),
(138, NULL, 20, 'wytnij drewniane listwy konstrukcyjne', 25, 4),
(139, NULL, 20, 'zatapiceruj ', 25, 4),
(140, NULL, 21, 'wytnij elementy z płyty ', 25, 3),
(141, NULL, 21, 'oklej fornty białą okleiną', 25, 2),
(142, NULL, 21, 'nawierć otwory pod mimośrody ', 25, 4),
(143, NULL, 21, 'umieść element na frezarce numerycznej ', 25, 3),
(144, NULL, 22, 'wytnij elementy ze sklejki ', 25, 5),
(145, NULL, 22, 'oklej fornty czarną okleiną', 25, 1),
(146, NULL, 22, 'nawierć otwory pod kołki ', 25, 4),
(147, NULL, 22, 'umieść element na frezarce numerycznej ', 25, 2),
(148, NULL, 23, 'wytnij fronty z płyty lamniowanej, orzech ', 25, 3),
(149, NULL, 23, 'zamontuj prowdnice długości 345mm', 25, 4),
(150, NULL, 23, 'zamontuj zawiasy, STAR', 25, 3),
(151, NULL, 23, 'zmontuj wszystkie elementy ', 25, 4),
(152, NULL, 24, 'wytnij fronty z płyty lamniowanej, wiśnia  ', 25, 4),
(153, NULL, 24, 'zamontuj prowdnice długości 390', 25, 5),
(154, NULL, 24, 'zamontuj zawiasy GTV', 25, 3),
(155, NULL, 24, 'zmontuj wszystkie elementy ', 25, 1);

INSERT INTO ZADANIE (Id_Zadania, Id_Pracownika, Id_Mebla) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 4, 1),
(4, 7, 1),
(5, 8, 1),
(6, 1, 1),
(7, 3, 1),
(8, 4, 1),
(9, 7, 1),
(10, 8, 1),
(11, 1, 2),
(12, 3, 2),
(13, 4, 2),
(14, 7, 2),
(15, 8, 2),
(16, 1, 2),
(17, 3, 2),
(18, 4, 2),
(19, 7, 2),
(20, 8, 2),
(21, 1, 6),
(22, 3, 6),
(23, 4, 6),
(24, 7, 6),
(25, 8, 8),
(26, 1, 8),
(27, 3, 8),
(28, 4, 8),
(29, 7, 14),
(30, 8, 14),
(31, 1, 14),
(32, 3, 14),
(33, 4, 14),
(34, 7, 15),
(35, 8, 15),
(36, 1, 15),
(37, 3, 15),
(38, 4, 15),
(39, 7, 16),
(40, 8, 16),
(41, 1, 16),
(42, 3, 16),
(43, 4, 16),
(44, 7, 17),
(45, 8, 17),
(46, 1, 17),
(47, 3, 17),
(48, 4, 17),
(49, 7, 18),
(50, 8, 18),
(51, 1, 18),
(52, 3, 18),
(53, 4, 18),
(54, 7, 18),
(55, 8, 19),
(56, 1, 19),
(57, 3, 19),
(58, 4, 19),
(59, 7, 19),
(60, 8, 19),
(61, 1, 20),
(62, 3, 20),
(63, 4, 20),
(64, 7, 20),
(65, 8, 20),
(66, 1, 21),
(67, 3, 21),
(68, 4, 21),
(69, 7, 21),
(70, 8, 21),
(71, 1, 22),
(72, 3, 22),
(73, 4, 22),
(74, 7, 22),
(75, 8, 23),
(76, 1, 23),
(77, 3, 23),
(78, 4, 23),
(79, 7, 24),
(80, 8, 24),
(81, 1, 24),
(82, 3, 24),
(83, 4, 25),
(84, 7, 25),
(85, 8, 25),
(86, 1, 25),
(87, 3, 26),
(88, 4, 26),
(89, 7, 26),
(90, 8, 26),
(91, 1, 27),
(92, 3, 27),
(93, 4, 27),
(94, 7, 27),
(95, 8, 28),
(96, 1, 28),
(97, 3, 28),
(98, 4, 28),
(99, 7, 28),
(100, 8, 28),
(101, 1, 28),
(102, 3, 29),
(103, 4, 29),
(104, 7, 29),
(105, 8, 29),
(106, 1, 32),
(107, 3, 32),
(108, 4, 32),
(109, 7, 32),
(110, 8, 33),
(111, 1, 33),
(112, 3, 33),
(113, 4, 33),
(114, 7, 7),
(115, 8, 7),
(116, 1, 7),
(117, 3, 7),
(118, 4, 9),
(119, 7, 9),
(120, 8, 9),
(121, 1, 9),
(122, 3, 10),
(123, 4, 10),
(124, 7, 10),
(125, 8, 10),
(126, 1, 11),
(127, 3, 11),
(128, 4, 11),
(129, 7, 11),
(130, 8, 12),
(131, 1, 12),
(132, 3, 12),
(133, 4, 12),
(134, 7, 13),
(135, 8, 13),
(136, 1, 13),
(137, 3, 13),
(138, 4, 13),
(139, 7, 30),
(140, 8, 30),
(141, 1, 30),
(142, 3, 30),
(143, 4, 30),
(144, 7, 31),
(145, 8, 31),
(146, 1, 31),
(147, 3, 31);

INSERT INTO REKLAMACJA (Id_Mebla, Opis_reklamacji) VALUES
(12, "Drzwi są źle spasowane i ocierają o boki szafy"),
(13, "Siedzisko zapadło się po dwóch dniach użytkowania.");

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


