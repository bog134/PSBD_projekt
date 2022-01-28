-- system z określonym cyklem np. codziennie przygotowuje zestawy materiałów, które są wymagane do zamówienia

SET GLOBAL event_scheduler="ON"

CREATE EVENT `Generuj liste potrzebnych materialow` ON SCHEDULE EVERY 1 DAY ON COMPLETION NOT PRESERVE ENABLE DO 

SELECT tab.Id_Materialu, tab.Potrzebna_ilosc-tab.Zamowiona_ilosc AS Ilosc
FROM
(
	SELECT material_mebel.Id_Materialu, SUM(material_mebel.Ilosc) AS Potrzebna_ilosc, SUM(material_zamow_komp.Ilosc) AS Zamowiona_ilosc
	FROM material_mebel
	LEFT JOIN material_zamow_komp USING (Id_Materialu)
	JOIN mebel USING (Id_Mebla)
	JOIN zamowienie_na_meble USING (Id_Zamowienia)
	WHERE zamowienie_na_meble.Czas_realizacji_Data_zlozenia=CURRENT_DATE
	GROUP BY Id_Materialu
) AS tab
WHERE tab.Potrzebna_ilosc-tab.Zamowiona_ilosc > 0
