package SQL;

import java.sql.*;  


public class MySqlconnection {
    
    public static void main(String args[]){ 
        String login = "x";
        String haslo = "x";

        try{  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt = con.createStatement();
            String zapytanie = 
                    "SELECT klient.Id_Klienta\n" +
                    "FROM klient\n" +
                    "WHERE klient.login = '" + login + "' AND klient.haslo = '" + haslo + "';";
            ResultSet rs = stmt.executeQuery(zapytanie);  
            while(rs.next())  
            System.out.println(rs.getString(1));
            if(rs.next() == false) {
                System.out.println("null");
            } else {
                System.out.println("-----");
            }
            con.close(); 
        } catch(Exception e) { 
            System.out.println(e);
        }    
    }  
}  
