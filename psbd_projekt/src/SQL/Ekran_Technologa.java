package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Ekran_Technologa {
    
    public static void main(String args[]){  
        
        /*ArrayList<String[]> allRows= ;
        
        wyswietl_na_konsoli(allRows);*/
        /*        utworzenie_ceny ("3","3","1000","1000","1000");*/

    } 
    
    static private ArrayList<String[]> ResultSet_to_ArrayList(ResultSet rs) throws SQLException
    {
            ArrayList<String[]> allRows = new ArrayList();
            ResultSetMetaData metaData = rs.getMetaData();
            while(rs.next()){
                String[] currentRow = new String[metaData.getColumnCount()];
                for(int i = 1;i<=metaData.getColumnCount();i++){
                    currentRow [i-1]=rs.getString(i);
                }
                allRows.add(currentRow);
            }
            return allRows;
    }
    static private ArrayList<String[]> pobierz_z_bazy_danych(String zapytanie)
    {
        ArrayList<String[]> allRows = new ArrayList();
        
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(zapytanie);
            allRows=ResultSet_to_ArrayList(rs);
            con.close();
        }catch(SQLException e){ System.out.println(e);} 
        return allRows;
    }
    static public void wyswietl_na_konsoli(ArrayList<String[]> allRows)
    {
        for(int i=0; i<allRows.size(); i++)
        {
            String[] currentRow = allRows.get(i);
            String row = " ";
            for(int j=0; j<currentRow.length; j++)
            {
                row+=currentRow[j];
                row+=" ";
            }
            System.out.println(row);
        }
    }
    /*---------*/
    static public ArrayList<String[]> wyswietlenie_listy_zamowien()
    {         
        String zapytanie = 
                "SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia\n" +
                "FROM zamowienie_na_meble\n" +
                "WHERE zamowienie_na_meble.Id_Stanu_Realizacji=3;"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> modyfikacja_listy_zamowien_w_zale??no??ci_od_filtra(String data1, String data2)
    {         
        String zapytanie = 
                "SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia\n" +
                "FROM zamowienie_na_meble\n" +
                "WHERE zamowienie_na_meble.Id_Stanu_Realizacji=3 AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia \n" +
                "BETWEEN DATE('" + data1  + "') AND DATE('" + data2 + "');"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> wyswietlenie_listy_projektow_klienta_w_zamowieniu (String id_zam)
    {         
        String zapytanie = 
                "SELECT DISTINCT projekt_klienta.Id_Proj_klient, typ_mebla.Nazwa\n" +
                "\n" +
                "FROM projekt_klienta\n" +
                "JOIN typ_mebla USING (Id_Typu_mebla)\n" +
                "JOIN mebel USING (Id_Proj_klient)\n" +
                "\n" +
                "WHERE mebel.Id_Zamowienia=" + id_zam + " AND projekt_klienta.zaakceptowany is null;"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> modyfikacja_listy_projektow_w_zale??no??ci_od_filtra (String id_zam, String parametr)
    {         
        String zapytanie = 
                "SELECT DISTINCT projekt_klienta.Id_Proj_klient, typ_mebla.Nazwa\n" +
                "\n" +
                "FROM projekt_klienta\n" +
                "JOIN typ_mebla USING (Id_Typu_mebla)\n" +
                "JOIN mebel USING (Id_Proj_klient)\n" +
                "\n" +
                "WHERE mebel.Id_Zamowienia= " + id_zam + " AND projekt_klienta.zaakceptowany is null AND typ_mebla.Nazwa LIKE ('" + parametr + "%')"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    /*---------*/
    static public ArrayList<String[]> wyswietlenie_szczegolow_projektu(String id)
    {         
        String zapytanie = 
                "SELECT typ_mebla.Nazwa, CONCAT(projekt_klienta.Wymiary_Dlugosc,\"x\",projekt_klienta.Wymiary_Wysokosc,\"x\",projekt_klienta.Wymiary_Szerokosc),\n" +
                "laczenia.Nazwa,\n" +
                "COUNT(mebel.Id_Mebla),\n" +
                "projekt_klienta.Nazwa_pliku_rysunku\n" +
                "\n" +
                "FROM typ_mebla\n" +
                "JOIN projekt_klienta USING (Id_Typu_mebla)\n" +
                "JOIN laczenia USING (Id_Laczenia)\n" +
                "JOIN mebel USING (Id_Proj_klient)\n" +
                "\n" +
                "WHERE projekt_klienta.Id_Proj_klient=" + id + "\n" +
                "GROUP BY Id_Proj_klient;";    
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> wyswietlenie_projektow_polproduktow(String id)
    {       
        String zapytanie = 
                "SELECT projekt_polproduktu.Nazwa\n" +
                "\n" +
                "FROM projekt_polproduktu\n" +
                "JOIN projekt_klienta USING (Id_Proj_klient)\n" +
                "\n" +
                "WHERE projekt_klienta.Id_Proj_klient = " + id + "\n" +
                "GROUP BY (projekt_polproduktu.Nazwa);"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> wyswietlenie_materialow(String id)
    {       
        String zapytanie = 
                "SELECT DISTINCT material_proj_klienta.Id_Mat_Proj_klient, material.Nazwa, material.rozmiar, material.Id_Materialu\n" +
                "\n" +
                "FROM material\n" +
                "LEFT JOIN rodzaj_materialu USING (Id_Rodzaju_materialu)\n" +
                "LEFT JOIN wzor USING (Id_Wzoru)\n" +
                "LEFT JOIN material_proj_klienta USING (Id_Materialu)\n" +
                "\n" +
                "WHERE material_proj_klienta.Id_Proj_klient=" + id + ";"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> wy??wietlenie_szczeg??????w_wybranego_p????produktu(String id, String nazwa)
    {       
        String zapytanie = 
                "SELECT projekt_polproduktu.Nazwa, rodzaj_polproduktu.Nazwa, CONCAT(projekt_polproduktu.Rozmiar_Dlugosc,\"x\",projekt_polproduktu.Rozmiar_Wysokosc,\"x\",projekt_polproduktu.Rozmiar_Szerokosc),\n" +
                "COUNT(*),\n" +
                "projekt_polproduktu.Nazwa_pliku_rysunku\n" +
                "\n" +
                "FROM projekt_polproduktu\n" +
                "JOIN rodzaj_polproduktu USING (Id_Rodzaju_polproduktu)\n" +
                "\n" +
                "WHERE projekt_polproduktu.Id_Proj_klient=" + id + "\n" +
                "GROUP BY (projekt_polproduktu.Nazwa)\n" +
                "HAVING projekt_polproduktu.Nazwa = '" + nazwa + "';"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    static public ArrayList<String[]> pobranie_ceny_danego_materialu(String id_mat)
    {       
        String zapytanie = 
                "SELECT Cena\n" +
                "FROM material\n" +
                "WHERE Id_Materialu=" + id_mat + ";"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }    
    static public ArrayList<String[]> pobranie_ceny_osobogodziny()
    {       
        String zapytanie = 
                "SELECT Cena\n" +
                "FROM definicja_zadania\n" +
                "WHERE Id_Def_zadania=1;"; 
        
        return pobierz_z_bazy_danych(zapytanie);
    }
    
    static private void dodaj_do_bazy_danych(String zapytanie)
    {        
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            stmt.executeUpdate(zapytanie);
            con.close();
        }catch(SQLException e){ System.out.println(e);} 
    }
    static public void zaaktualizowanie_ilo??ci_materialow(String ilosc, String id_mat)
    {       
        String zapytanie = 
                "UPDATE material_proj_klienta\n" +
                "SET ilosc=" + ilosc + "\n" +
                "WHERE Id_Mat_Proj_klient = " + id_mat + ";"; 

        dodaj_do_bazy_danych(zapytanie);
    } 
    static public void utworzenie_definicji_zada??(String id, String opis, String czas, String id_techn)
    {       
        ArrayList<String[]> cena = pobranie_ceny_osobogodziny();
        String zapytanie = 
                "INSERT INTO DEFINICJA_ZADANIA (Id_Proj_klient, Opis_zadania, Czas_wykonania, Cena, Id_Pracownika) VALUES (" + id + "," + "'" + opis + "'," + czas + "," + cena.get(0)[0] + "," + id_techn + ");"; 

        dodaj_do_bazy_danych(zapytanie);
    }
    static public void utworzenie_ceny (String id_techn, String id, String robocizna, String materialy, String marza, String polprodukty)
    {       
        String zapytanie = 
                "INSERT INTO CENA (Id_Pracownika, Id_Proj_klient, Koszt_robocizny, Koszt_surowcow, Marza, Koszt_polproduktow) VALUES \n" +
                "(" + id_techn + "," + id + "," + robocizna + "," + materialy + "," + marza + "," + polprodukty + ");"; 

        dodaj_do_bazy_danych(zapytanie);
    }
    /*---------*/
    static public void zaakceptowanie_projektu_klienta(String id)
    {       
        String zapytanie = 
                "UPDATE projekt_klienta\n" +
                "SET zaakceptowany=1\n" +
                "WHERE projekt_klienta.Id_Proj_klient=" + id; 

        dodaj_do_bazy_danych(zapytanie);
    }
    static public void zaakceptowanie_zamowienia(String id_zam)
    {       
        String zapytanie = 
                "UPDATE zamowienie_na_meble\n" +
                "SET Id_Stanu_Realizacji = 4\n" +
                "WHERE Id_Zamowienia =" + id_zam; 

        dodaj_do_bazy_danych(zapytanie);
    }
    static public void odrzucenie_zamowienia(String id_zam)
    {       
        String zapytanie = 
                "UPDATE zamowienie_na_meble\n" +
                "SET Id_Stanu_Realizacji = 5\n" +
                "WHERE Id_Zamowienia = " + id_zam;

        dodaj_do_bazy_danych(zapytanie);
    }
}    /*---------*/
    

