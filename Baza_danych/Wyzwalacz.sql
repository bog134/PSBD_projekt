-- system z określonym cyklem np. codziennie przygotowuje zestawy materiałów, które są wymagane do zamówienia

SET GLOBAL event_scheduler="ON";

CREATE EVENT `Generuj liste potrzebnych materialow` ON SCHEDULE EVERY 1 DAY ON COMPLETION NOT PRESERVE ENABLE DO 

SELECT material_mebel.Id_Materialu, SUM(material_mebel.Ilosc)
FROM material_mebel
JOIN mebel USING (Id_Mebla)
JOIN zamowienie_na_meble USING (Id_Zamowienia)
WHERE zamowienie_na_meble.Czas_realizacji_Data_zlozenia=CURRENT_DATE
GROUP BY Id_Materialu

