package SQL;

import java.sql.*;  


public class MySqlconnection {
    
    public static void main(String args[]){  
        String data1="2022-01-19";
        String data2="2022-01-21";
        try{  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "SELECT projekt_klienta.Id_Proj_klient, zamowienie_na_meble.Czas_realizacji_Data_zlozenia,\n" +
                    "typ_mebla.Nazwa AS Typ_mebla\n" +
                    "\n" +
                    "FROM projekt_klienta\n" +
                    "JOIN mebel USING (Id_Proj_klient)\n" +
                    "JOIN zamowienie_na_meble USING (Id_Zamowienia)\n" +
                    "JOIN typ_mebla USING (Id_Typu_mebla)\n" +
                    "\n" +  
                    "WHERE zamowienie_na_meble.Id_Zamowienia=3 AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia BETWEEN DATE('" + data1 + "') AND DATE('" + data2 + "');";
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
            con.close(); 
        }catch(Exception e){ System.out.println(e);}  
    }  
}  
