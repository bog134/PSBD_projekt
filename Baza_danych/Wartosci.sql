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

INSERT INTO MATERIAL (Id_Materialu, Nazwa, Cena, Kolor, Klasa, Wzor, Rodzaj, Rozmiar) VALUES
(1, 'flok', 120, 1, 'A', 1, 1, '1mb'),
(2, 'welwet', 130, 2, 'A', 2, 1, '1mb'),
(3, 'ekoskóra', 110, 5, 'A', 3, 1, '1mb'),
(4, 'szenil', 100, 6, 'A', 4, 1, '1mb'),
(5, 'płyta wiórowa', 42, 3, 'A1', NULL, 2, '1m^2 x 18 mm'),
(6, 'płyta paździerzowa', 30, 3, 'A1', NULL, 2, '1m^2 x 12 mm'),
(7, 'płyta pilśniowa', 10, 3, 'A1', NULL, 2, '1m^2 x 3 mm'),
(8, 'płyta MDF', 56, 3, 'A1', NULL, 2, '1m^2 x 10 mm'),
(9, 'deska dębowa', 1500, 3, '1', NULL, 3, '1m^3'),
(10, 'drewno bukowe', 720, 3, '1', NULL, 3, '1m^3'),
(11, 'drewno sosnowe', 760, 3, '1', NULL, 3, '1m^3'),
(12, 'drewno brzozowe', 650, 3, '1', NULL, 3, '1m^3'),
(13, 'drewno hebanowe', 1200, 3, '1', NULL, 3, '1m^3'),
(14, 'pianka poliuretanowa', 175, 4, 'T30', NULL, 4, '1m^3'),
(15, 'pianka wysokoeleasty', 300, 4, 'T25', NULL, 4, '1m^3'),
(16, 'pianka polieterowa', 180, 4, 'T35', NULL, 4, '1m^3'),
(17, 'fornir naturalny', 14, 3, 'A', NULL, 5, '1mb'),
(18, 'fornir modyfikowany', 30, 7, 'A', 5, 5, '1mb'),
(19, 'okleina PCV', 25, 8, 'A', 6, 5, '1mb');

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

INSERT INTO KLIENCI (Id_Klienta, Imie, Nazwisko, Numer_telefonu, Email, Adres_Kraj, Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_NumerDomu, Adres_NumerMieszkania) VALUES
(1, 'Marcelina', 'Woźniak', '690793569', 'paulbont@adaov.com', 'Polska', '10-182', 'Bielsko-Biała', 'Akacjowa', 15, 38),
(2, 'Bogumił', 'Łukasik', '212994545', 'stumptown@gmailya.com', 'Polska', '62-875', 'Poznań', 'Niedzielna', 512, 43),
(3, 'Lilianna', 'Bielecka', '814536325', 'olgachernyh@fheiesit.com', 'Polska', '38-650', 'Katowice', 'Gołuńskiego', 3, 123),
(4, 'Tobiasz', 'Bąk', '271211570', 'nixaaron@oreple.com', 'Polska', '17-822', 'Żory', 'Parkowa', 18, NULL),
(5, 'Seweryn', 'Czarnecki', '197232223', 'goukyuu@omdlism.com', 'Polska', '05-405', 'Siedlce', 'Kasztanowa', 54, NULL),
(6, 'Regina', 'Wierzbicka', '875975445', 'cclayz@melowsa.com', 'Polska', '75-554', 'Tychy', 'Szkolna', 64, 12),
(7, 'Tadeusz', 'Jasiński', '310604695', 'dkinakin@plainst.com', 'Polska', '75-554', 'Toruń', 'Sandaczowa', 81, 78),
(8, 'Albina', 'Kozioł', '496653864', 'topher1916@oaouemo.com', 'Polska', '35-521', 'Biała Podlaska', 'Tulipanowa', 2, NULL),
(9, 'Rudolf', 'Romanowski', '137647706', 'hennhaus@famytown.xyz', 'Polska', '76-483', 'Mysłowice', 'Modłowa', 7, NULL),
(10, 'Nicole', 'Urbańska', '893315479', 'alan16@tigo.tk', 'Polska', '21-366', 'Mysłowice', 'Słowackiego', 31, 9),
(11, 'Romualda', 'Kosińska', '234532394', 'djsp1200@filevino.com', 'Polska', '23-004', 'Kielce', 'PCK', 9, 51),
(12, 'Bożena', 'Jóźwiak', '733316402', 'diankaodessa@mobitivaisao.com', 'Polska', '23-004', 'Radom', 'Stachury', 7, 90),
(13, 'Karina', 'Marek', '518052514', 'khan503@hasevo.com', 'Polska', '07-378', 'Krosno', 'Ćwiklińskiej', 13, 13),
(14, 'Włodzimierz', 'Kosiński', '834473715', 'roqstar17@sumikang.com', 'Polska', '57-871', 'Chorzów', 'Rolnicza', 76, 61),
(15, 'Justyna', 'Jastrzębska', '599952162', 'samirainterfarm@taikz.com', 'Polska', '52-233', 'Lublin', 'Okopowa', 100, 101),
(16, 'Oliwia', 'Dąbrowska', '179095786', 'aleksandrcypin@rstoremail.ml', 'Polska', '74-284', 'Wałbrzych', 'Kujawska', 15, 1);

INSERT INTO DOSTAWCY (Id_Dostawcy, Nazwa, Email, Numer_konta_bankowego, Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_Numer) VALUES
(1, 'FTransport', 'ftransport@gmail.com', '56782491346520215687524589', '43-421', 'Poznan', 'Widok', '9'),
(2, 'BigTransit', 'bigtransit@wp.pl', '53154097207946681000682451', '41-558', 'Koszalin', 'Rzemieślnicza', '28'),
(3, 'SzybkieDostawy', 'szybkiedostawy@o2.pl', '86193076266895453453276931', '43-421', 'Leszno', 'Piastowska', '12'),
(4, 'XeDef', 'xedef@gmail.com', '61154062116032497746659251', '92-256', 'Kraków', 'Lubińska', '67'),
(5, 'NTN', 'ntn@onet.pl', '94187099089534543073777450', '10-682', 'Gdynia', 'Okopowa', '56'),
(6, 'pdp', 'pdp@gmail.com', '75194026083794609212034698', '98-395', 'Zabrze', 'Usługowa', '48');

INSERT INTO ZAMOWIENIE_NA_MEBLE (Id_Zamowienia, Id_Klienta, Koszt, Id_Stanu_Realizacji, Czas_realizacji_Data_zlozenia, Czas_Realizacji_Data_zakonczenia) VALUES
(1, 1, 14267, 4, '2022-01-01', NULL),
(2, 4, NULL, 5, '2022-01-07', NULL),
(3, 4, NULL, 3, '2022-01-20', NULL),
(4, 7, 15000, 6, '2001-05-22', '2001-06-11'),
(5, 7, 2000, 6, '2001-08-01', '2001-08-08'),
(6, 7, 7261, 6, '2012-04-25', '2001-05-12'),
(7, 1, 875, 1, '2022-01-18', NULL),
(8, 2, 400, 2, '2021-12-29', '2022-01-04'),
(9, 15, 2345, 6, '2015-07-21', '2015-09-09'),
(10, 3, 2345, 6, '2018-11-27', '2018-12-10'),
(11, 5, NULL, 3, '2021-12-01', NULL),
(12, 6, NULL, 5, '2021-12-01', NULL),
(13, 8, 4564, 4, '2021-11-08', NULL),
(14, 9, 923, 6, '2019-06-14', '2019-06-28'),
(15, 10, NULL, 5, '2015-02-10', NULL),
(16, 11, 1235, 6, '2016-05-08', '2016-05-26'),
(17, 12, NULL, 3, '2022-01-22', NULL),
(18, 14, 24238, 2, '2021-09-02', '2021-11-23'),
(19, 15, 7621, 1, '2021-09-02', NULL),
(20, 8, 9865, 1, '2021-09-02', NULL),
(21, 2, 1235, 6, '2020-01-02', '2020-01-02'),
(22, 9, 1235, 6, '2002-06-03', '2002-08-07'),
(23, 11, NULL, 5, '2015-06-03', NULL),
(24, 12, NULL, 3, '2022-01-31', NULL),
(25, 3, 7814, 4, '2022-01-01', NULL),
(26, 10, 8712, 4, '2022-01-22', NULL);

INSERT INTO CENA (Id_Ceny, Id_Pracownika, Koszt_robocizny, Koszt_surowcow, Marza) VALUES
(1, 5, 2000, 3000, 1500),
(2, 9, 150, 200, 100),
(3, 9, 800, 2000, 4000),
(4, 5, 820, 2010, 4020),
(5, 5, 1005, 2342, 2020),
(6, 5, 1005, 4342, 1020),
(7, 5, 1005, 3342, 3020),
(8, 9, 200, 551, 700),
(9, 5, 500, 2300, 900),
(10, 9, 400, 400, 300),
(11, 9, 460, 410, 200);

INSERT INTO PROJEKT_KLIENTA (Id_Proj_klient, Id_Ceny, Id_Typu_mebla, Id_Laczenia, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc, Nazwa_pliku_rysunku) VALUES
(1, 1, 1, 1, 900, 58, 2400, 'rysuneczek_stolu123.dwg'),
(2, 2, 2, 1, 50, 240, 70, 'krzeselko_drewniane.dwg'),
(3, 3, 5, 1, 156, 81, 80, 'sofa.dwg'),
(4, 4, 11, 1, 70, 1900, 20, 'regal_23.dwg'),
(5, 5, 12, 1, 20, 20000, 1, 'mojWymazonyKredens_xd.dwg'),
(6, 6, 10, 1, 2800, 85, 1121, 'kanapaNarozna.dwg'),
(7, 7, 10, 1, 2800, 85, 1600, 'kanapaNarozna-trw.dwg'),
(8, 8, 7, 1, 2500, 2400, 65, 'szafa.dwg'),
(9, 9, 3, 1, 45, 1500, 65, 'fotel.dwg'),
(10, 10, 3, 1, 45, 1500, 65, 'fotel-atye.dwg'),
(11, 11, 9, 1, 50, 50, 40, 'fajne_biureczko.dwg');


INSERT INTO PROJEKT_Z_KATALOGU(Id_Proj_katalog, Id_Typu_mebla, Id_Laczenia, Nazwa, Id_Opcjonalne_czesci,
Marza, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc) VALUES
(1, 1, 2, 'VEDBO', 1, 500, 950, 750, 1600),
(2, 1, 4, 'EKEDALEN', 1, 240, 900, 730, 2400),
(3, 2, 4, 'ODGER', 1, 39, 410, 810, 380),
(4, 2, 1, 'INGOLF', 1, 50, 420, 910, 380),
(5, 3, 1, 'EKENASET', 1, 270, 640, 760, 780),
(6, 3, 1, 'STRANDMON', 1, 500, 820, 1010, 960),
(7, 4, 1, 'MALM', 1, 130, 1350, 1000, 2090),
(8, 4, 1, 'SONGESAND', 1, 400, 1530, 950, 2070),
(9, 5, 1, 'ANGSTA', 1, 340, 2000, 420, 820),
(10, 5, 1, 'LINANAS', 1, 400, 1360, 800, 800),
(11, 6, 1, 'GRUPPSPEL', 1, 1200, 1800, 1500, 800),
(12, 6, 1, 'TROTTEN', 1, 400, 1600, 1200, 800),
(13, 7, 1, 'PAX', 1, 300, 1750, 2012, 580),
(14, 7, 1, 'VISTHUS', 1, 415, 1219, 2157, 569),
(15, 8, 1, 'MALM', 1, 120, 1600, 780, 480),
(16, 8, 1, 'ALEX', 1, 40, 360, 700, 580),
(17, 9, 1, 'KULLEN', 1, 30, 350, 490, 400),
(18, 9, 1, 'KVISTBRO', 1, 40, 400, 420, 400),
(19, 10, 1, 'KIVIK', 1, 545, 2800, 830, 1630),
(20, 10, 1, 'BENNEBOL', 1, 399, 2200, 830, 2020),
(21, 11, 1, 'KALLAX', 1, 200, 770, 1470, 390),
(22, 11, 1, 'BILLY', 1, 380, 800, 2020, 280),
(23, 12, 1, 'VIHALS', 1, 900, 1400, 750, 370),
(24, 12, 1, 'KLACKENAS', 1, 360, 1200, 970, 410);

INSERT INTO ZAMOWIENIE_NA_KOMPONENTY (NrZamowienia, Koszt, Stan_realizacji, Czas_realizacji_Data_rozpoczecia, Czas_realizacji_Data_zakonczenia) VALUES
(1, 14638, 1, '2021-06-15', '2021-06-25'),
(2, 6872, 1, '2021-10-10', '2021-10-20'),
(3, 8715, 1, '2021-02-10', '2021-02-20'),
(4, 9777, 1, '2021-09-06', '2021-09-16'),
(5, 9245, 1, '2021-01-03', '2021-01-13'),
(6, 6394, 0, '2022-01-15', NULL),
(7, 6159, 0, '2022-01-04', NULL),
(8, 11348, 0, '2022-01-20', NULL),
(9, 12519, 0, '2022-01-04', NULL),
(10, 13648, 0, '2022-01-09', NULL);

INSERT INTO MEBEL (Id_Mebla, Id_Zamowienia, Id_Proj_klient, Id_Proj_katalog) VALUES
(1, 1, NULL, 1),
(2, 1, NULL, 2),
(3, 2, 1, NULL),
(4, 2, 2, NULL),
(5, 3, 3, NULL),
(6, 3, NULL, 3),
(7, 4, 4, NULL),
(8, 5, NULL, 4),
(9, 6, 5, NULL),
(10, 7, 6, NULL),
(11, 8, 7, NULL),
(12, 9, 8, NULL),
(13, 10, 9, NULL),
(14, 11, NULL, 5),
(15, 12, NULL, 6),
(16, 13, NULL, 7),
(17, 14, NULL, 8),
(18, 15, NULL, 9),
(19, 16, NULL, 10),
(20, 17, NULL, 11),
(21, 18, NULL, 12),
(22, 19, NULL, 13),
(23, 20, NULL, 14),
(24, 21, NULL, 15),
(25, 22, NULL, 16),
(26, 23, NULL, 17),
(27, 24, NULL, 18),
(28, 24, NULL, 19),
(29, 25, NULL, 20),
(30, 25, 10, NULL),
(31, 26, 11, NULL),
(32, 26, NULL, 23),
(33, 26, NULL, 24);

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
(13, 16, 11),
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










