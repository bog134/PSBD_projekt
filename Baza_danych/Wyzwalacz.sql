-- system z określonym cyklem np. codziennie przygotowuje zestawy materiałów, które są wymagane do zamówienia

SET GLOBAL event_scheduler="ON";

CREATE EVENT `Generuj liste potrzebnych materialow` ON SCHEDULE EVERY 1 DAY ON COMPLETION NOT PRESERVE ENABLE DO 

SELECT tab.Id_Materialu, SUM(tab.Ilosc)
FROM
(
	SELECT material_mebel.Id_Materialu, SUM(material_mebel.Ilosc) AS Ilosc
	FROM material_mebel
	JOIN mebel USING (Id_Mebla)
	JOIN zamowienie_na_meble USING (Id_Zamowienia)
	WHERE Id_Proj_klient is null
	GROUP BY Id_Materialu
	UNION
	SELECT material_mebel.Id_Materialu, SUM(material_proj_klienta.Ilosc)
	FROM material_mebel
	JOIN mebel USING (Id_Mebla)
	JOIN zamowienie_na_meble USING (Id_Zamowienia)
	JOIN material_proj_klienta USING (Id_Proj_klient)
	WHERE Id_Proj_klient is not null
	GROUP BY Id_Materialu
) AS tab
GROUP BY tab.Id_Materialu
ORDER BY tab.Id_Materialu 

