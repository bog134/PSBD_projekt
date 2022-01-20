USE FIRMA;

INSERT INTO pracownik (IdPracownika, IdStanowiska, Data_urodzenia, Imie, Nazwisko, Numer_konta_bankowego, Zarobki, Numer_telefonu, Adres_Kod, Adres_Miasto, Adres_Ulica) VALUES
(1, 1, '1956-06-15', 'Borys', 'Zakrzewski', '95249010289970091125976427', 3000, '997-015-469', '70-513', 'Szczecin', 'Małopolska 148/2'),
(2, 3, '1996-06-04', 'Milan', 'Rutkowski', '25249010154483197770208474', 4000, '188-482-975', '90-954', 'Łódź', 'Al. Kościuszki 97'),
(3, 1, '1995-05-15', 'Fryderyk', 'Lewandowski', '70249010443972240527274151', 3000, '739-722-015', '32-305', 'Olkusz', 'Krasińskiego 72/3'),
(4, 1, '1975-05-01', 'Alex', 'Walczak', '45249010318881704000536399', 3000, '744-446-093', '15-161', 'Białystok', 'Czarnej Hańczy 7'),
(5, 2, '1996-12-26', 'Artur', 'Kołodziej', '09249010447474422165839092', 3500, '748-686-884', '05-075', 'Warszawa', 'I Pułku Praskiego 108/5'),
(6, 3, '1998-10-22', 'Lucyna', 'Cieślak', '93249010441741221091577543', 4000, '727-799-091', '50-114', 'Wrocław', 'Odrzańska 127'),
(7, 1, '1979-11-12', 'Balbina', 'Dąbrowska', '72249010284538998156445325', 3000, '580-035-635', '01-158', 'Warszawa', 'Złocienia 3/7'),
(8, 1, '1965-01-20', 'Anita', 'Adamska', '04249010152485639713389353', 3000, '223-192-870', '85-430', 'Bydgoszcz', 'Jarząbkowa 12'),
(9, 2, '1998-10-10', 'Faustyna', 'Sadowska', '57249010579142041118217285', 3500, '175-855-865', '59-202', 'Legnica', 'Nowy Świat 58'),
(10, 4, '1957-06-25', 'Julita', 'Mazur', '48249010571218574983266911', 5000, '109-202-875', '70-352', 'Szczecin', 'Księdza Ściegiennego Piotra 34');

INSERT INTO klienci ('Id_Klienta', 'Imie', 'Nazwisko', 'Numer_telefonu', 'E-mail','Adres_Kraj', 'Adres_AdresPocztowy', 'Adres_Miejscowosc', 'Adres_Ulica', 'Adres_NumerDomu', "Adres_NumerMieszkania") VALUES
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
(12, 'Karina', 'Marek', '518052514', 'khan503@hasevo.com', 'Polska', '07-378', 'Krosno', 'Ćwiklińskiej', 13, 13),
(13, 'Włodzimierz', 'Kosiński', '834473715', 'roqstar17@sumikang.com', 'Polska', '57-871', 'Chorzów', 'Rolnicza', 76, 61),
(14, 'Justyna', 'Jastrzębska', '599952162', 'samirainterfarm@taikz.com', 'Polska', '52-233', 'Lublin', 'Okopowa', 100, 101),
(15, 'Oliwia', 'Dąbrowska', '179095786', 'aleksandrcypin@rstoremail.ml', 'Polska', '74-284', 'Wałbrzych', 'Kujawska', 15, 1),


