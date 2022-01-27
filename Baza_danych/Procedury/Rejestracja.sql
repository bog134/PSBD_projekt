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
    IF(login NOT IN (SELECT klient.Login FROM klient)) THEN
    INSERT IGNORE INTO klient (Imie, Nazwisko, Numer_telefonu, Email, Adres_Kraj, 
    Adres_AdresPocztowy, Adres_Miejscowosc, Adres_Ulica, Adres_NumerDomu, Adres_NumerMieszkania, Login, Haslo) 
	VALUES
		(imie, nazwisko, numer_telefonu, email, adres_kraj, adres_adres_pocztowy, adres_miejscowosc,
        adres_ulica, adres_numer_domu, adres_numer_mieszkania, login, haslo);
		SELECT TRUE INTO zarejestrowano;
	END IF;
END