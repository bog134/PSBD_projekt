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
                    "CALL firma.select_customer_by_id(4);";
            ResultSet rs = stmt.executeQuery(zapytanie);  
            while(rs.next())  
            System.out.println(rs.getString(1) + " " +rs.getString(2) + " " + rs.getString(3));
            con.close(); 
        } catch(Exception e) { 
            System.out.println(e);
        }    
    }  
}  
