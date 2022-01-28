--który pracownik w danym okresie czasu ma najwięcej zgłoszeń reklamacyjnych 
SELECT MAX(tab.Ilosc_reklamacji1) AS Ilosc_reklamacji, tab.Id_Pracownika, tab.Imie, tab.Nazwisko
FROM 
(
    SELECT COUNT(reklamacja.Id_Reklamacji) AS Ilosc_reklamacji1, PRACOWNIK.Id_Pracownika, PRACOWNIK.Imie, PRACOWNIK.Nazwisko
    FROM PRACOWNIK
    JOIN ZADANIE USING(Id_Pracownika)
    JOIN MEBEL USING (Id_Mebla)
    JOIN REKLAMACJA USING (Id_Mebla)
    JOIN ZAMOWIENIE_NA_MEBLE USING(Id_Zamowienia)
	
    WHERE ZAMOWIENIE_NA_MEBLE.Czas_Realizacji_Data_zakonczenia BETWEEN DATE(data1) AND DATE(data2)
    GROUP BY PRACOWNIK.Id_Pracownika
) AS tab;

-- który z dostawców dostarcza dany element najszybciej 
-- polprodukt
SELECT MIN(Sredni_czas_dostawy),tab.Id_Dostawcy
FROM
(
    SELECT AVG(ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_zakonczenia-ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_rozpoczecia) AS Sredni_czas_dostawy, DOSTAWCA.Id_dostawcy
    FROM DOSTAWCA
    JOIN ZAMOWIENIE_NA_KOMPONENTY USING (Id_dostawcy)
    JOIN POLPRODUKT USING (NrZamowienia)

    WHERE ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_zakonczenia IS NOT NULL AND POLPRODUKT.Id_Proj_polprod=id_proj_polproduktu
    GROUP BY polprodukt.Id_Proj_polprod,dostawca.Id_Dostawcy
) AS tab;

-- material
SELECT MIN(Sredni_czas_dostawy),tab.Id_Dostawcy
FROM
(
    SELECT AVG(ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_zakonczenia-ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_rozpoczecia) AS Sredni_czas_dostawy, DOSTAWCA.Id_dostawcy
    FROM DOSTAWCA
    JOIN ZAMOWIENIE_NA_KOMPONENTY USING (Id_dostawcy)
    JOIN MATERIAL_ZAMOW_KOMP USING (NrZamowienia)

    WHERE ZAMOWIENIE_NA_KOMPONENTY.Czas_realizacji_Data_zakonczenia IS NOT NULL AND material_zamow_komp.Id_Materialu=id_materialu
    GROUP BY material_zamow_komp.Id_Materialu,dostawca.Id_Dostawcy
) AS tab;
