CREATE DEFINER=`root`@`localhost` PROCEDURE `select_customer_by_id`(in id int)
BEGIN
	SELECT  Id_Klienta, Nazwisko, Imie
	FROM klient
	WHERE Id_Klienta = id;
END