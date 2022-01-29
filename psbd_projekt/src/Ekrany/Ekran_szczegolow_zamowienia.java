/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;

import Dodatkowe.dodanyProjekt;
import Dodatkowe.ProjektKlienta;
import Dodatkowe.Polprodukt;
import java.awt.Frame;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maciek
 */
public class Ekran_szczegolow_zamowienia extends javax.swing.JFrame {
    
    ArrayList<ProjektKlienta> koszyk_proj_klient;
    ArrayList<dodanyProjekt> koszyk;
    EkranKlienta klient;
    
    public String DbznajdzIdOpcjCzesci(String nazwa, String id_k){
        String id = null;
        
        nazwa = '"' + nazwa+ '"';
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "SELECT Id_Opcj_czesci FROM firma.opcjonalna_czesc\n" +
                    "WHERE Nazwa = "+nazwa+" && Id_Proj_katalog ="+id_k;

            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
               id = rs.getString(1);
            }
          
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        return id;
    }
    
    public void DbskladanieZamowienia(){
               
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "INSERT INTO ZAMOWIENIE_NA_MEBLE (Id_Klienta, Id_Stanu_Realizacji, Czas_realizacji_Data_zlozenia, Czas_Realizacji_Data_zakonczenia) VALUES\n" +
                    "("+klient.id_klienta+", 1,CURTIME() , NULL);";
            
           
            stmt.executeUpdate(zapytanie);
            
            int id_buff = 0;
            int id_buff_meb = 0;
            
            zapytanie = "SELECT last_insert_id()";
            
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
                id_buff = rs.getInt(1);
            }
            
            for(int i=0; i<koszyk.size();i++){
                zapytanie ="INSERT INTO MEBEL (Id_Zamowienia, Id_Proj_klient, Id_Proj_katalog, Id_Opcj_czesci, Wykonany) VALUES\n" +
                        "("+id_buff+", NULL, "+koszyk.get(i).getId()+", "+DbznajdzIdOpcjCzesci(koszyk.get(i).getOpcjonalneCzesci(), koszyk.get(i).getId())+", FALSE);";
            
                stmt.executeUpdate(zapytanie);
                
                zapytanie = "SELECT last_insert_id()";
                rs=stmt.executeQuery(zapytanie);  
                while(rs.next()){
                id_buff_meb = rs.getInt(1);
                }
                
                ArrayList<Integer> temp = new ArrayList<>();
                
                zapytanie = "SELECT Id_Materialu FROM firma.material_proj_katalog\n" +
                            "WHERE Id_Proj_katalog =" +koszyk.get(i).getId();
                rs=stmt.executeQuery(zapytanie);
                while(rs.next()){
                    temp.add(rs.getInt(1));
                    
                }
                
                for(int j=0; j<temp.size(); j++){
                    zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                    "("+temp.get(j)+","+id_buff_meb+")";
            
                    stmt.executeUpdate(zapytanie);
                }
                
                 ArrayList<Integer> temp1 = new ArrayList<>();
                
                zapytanie = "SELECT Id_Proj_polprod FROM firma.projekt_polproduktu\n" +
                            "WHERE Id_Proj_katalog =" +koszyk.get(i).getId();
                rs=stmt.executeQuery(zapytanie);
                while(rs.next()){
                    temp1.add(rs.getInt(1));
                    
                }
                
                for(int j=0; j<temp1.size(); j++){
                    zapytanie ="INSERT INTO POLPRODUKT (Id_Proj_polprod, Id_Mebla) VALUES\n" +
                    "("+temp1.get(j)+","+id_buff_meb+")";
            
                    stmt.executeUpdate(zapytanie);
                }
            }

            int id_buff_proj = 0;

            //dodawanie zamowieni na projekt klienta
            for(int i=0; i<koszyk_proj_klient.size();i++) {
                zapytanie = "INSERT INTO PROJEKT_KLIENTA (Id_Typu_mebla, Id_Laczenia, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc, Nazwa_pliku_rysunku) VALUES" +
                            "("+ koszyk_proj_klient.get(i).getIdTypu()+", "+ koszyk_proj_klient.get(i).getIdLaczenia() + ", '" + koszyk_proj_klient.get(i).getSzerokosc() +
                            "', '"+ koszyk_proj_klient.get(i).getWysokosc() +"', '" + koszyk_proj_klient.get(i).getDlugosc() + "', '" + koszyk_proj_klient.get(i).getRysunekNazwa() + " ');";
                
                stmt.executeUpdate(zapytanie);

                zapytanie = "SELECT last_insert_id()";
                rs=stmt.executeQuery(zapytanie);  
                while(rs.next()){
                id_buff_proj = rs.getInt(1);
                }


                for (int k = 0; k < koszyk_proj_klient.get(i).getIlosc(); k++) {
                    zapytanie ="INSERT INTO MEBEL (Id_Zamowienia, Id_Proj_klient, Id_Proj_katalog, Id_Opcj_czesci, Wykonany) VALUES\n" +
                    "("+id_buff+", " + id_buff_proj + ", NULL, NULL, FALSE);";
        
                     stmt.executeUpdate(zapytanie);

                     zapytanie = "SELECT last_insert_id()";
                     rs=stmt.executeQuery(zapytanie);  
                     while(rs.next()){
                     id_buff_meb = rs.getInt(1);
                     }

                    if (koszyk_proj_klient.get(i).getIdDrewna() != 0) {
                        zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdDrewna() + "," + id_buff_meb + ");";
                        stmt.executeUpdate(zapytanie);
    
                        zapytanie = "INSERT INTO MATERIAL_PROJ_KLIENTA ( Id_Materialu, Id_Proj_klient, Ilosc) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdDrewna() + ", " + id_buff_proj + ", NULL);";
                        stmt.executeUpdate(zapytanie);
                    }

                    if (koszyk_proj_klient.get(i).getIdPlyty() != 0) {
                        zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdPlyty() + "," + id_buff_meb + ");";
                        stmt.executeUpdate(zapytanie);
    
                        zapytanie = "INSERT INTO MATERIAL_PROJ_KLIENTA ( Id_Materialu, Id_Proj_klient, Ilosc) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdPlyty() + ", " + id_buff_proj + ", NULL)";
                        stmt.executeUpdate(zapytanie);
                    }

                    if (koszyk_proj_klient.get(i).getIdTkaninyy() != 0) {
                        zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdTkaninyy() + "," + id_buff_meb + ");";
                        stmt.executeUpdate(zapytanie);
    
                        zapytanie = "INSERT INTO MATERIAL_PROJ_KLIENTA ( Id_Materialu, Id_Proj_klient, Ilosc) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdTkaninyy() + ", " + id_buff_proj + ", NULL);";
                        stmt.executeUpdate(zapytanie);
                    }

                    if (koszyk_proj_klient.get(i).getIdPianki() != 0) {
                        zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdPianki() + "," + id_buff_meb + ");";
                        stmt.executeUpdate(zapytanie);
    
                        zapytanie = "INSERT INTO MATERIAL_PROJ_KLIENTA ( Id_Materialu, Id_Proj_klient, Ilosc) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdPianki() + ", " + id_buff_proj + ", NULL);";
                        stmt.executeUpdate(zapytanie);
                    }

                    if (koszyk_proj_klient.get(i).getIdOkleiny() != 0) {
                        zapytanie ="INSERT INTO MATERIAL_MEBEL (Id_Materialu, Id_Mebla) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdOkleiny() + "," + id_buff_meb + ");";
                        stmt.executeUpdate(zapytanie);
    
                        zapytanie = "INSERT INTO MATERIAL_PROJ_KLIENTA ( Id_Materialu, Id_Proj_klient, Ilosc) VALUES\n" +
                        "(" + koszyk_proj_klient.get(i).getIdOkleiny() + ", " + id_buff_proj + ", NULL);";
                        stmt.executeUpdate(zapytanie);
                    }

                    int id_buff_proj_polp = 0;

                    for (int j = 0; j < koszyk_proj_klient.get(i).getPolprodukty().size(); j++) {
                        zapytanie = "INSERT INTO PROJEKT_POLPRODUKTU(Id_Proj_klient, Id_Proj_katalog, Id_Rodzaju_polproduktu, Nazwa, Rozmiar_Wysokosc, Rozmiar_Szerokosc, Rozmiar_Dlugosc, Cena, Nazwa_pliku_rysunku) VALUES\n" +
                        "("+ id_buff_proj +", NULL, '" + koszyk_proj_klient.get(i).getPolprodukty().get(j).getIdRodzaju() + "', '" + koszyk_proj_klient.get(i).getPolprodukty().get(j).getNazwa() +  "', '" + koszyk_proj_klient.get(i).getPolprodukty().get(j).getWysokosc()
                        + "', '"+ koszyk_proj_klient.get(i).getPolprodukty().get(j).getSzerokosc() +"', '" + koszyk_proj_klient.get(i).getPolprodukty().get(j).getDlugosc() + "', NULL, '" + koszyk_proj_klient.get(i).getPolprodukty().get(j).getRysunekNazwa() + "' )";

                        zapytanie = "SELECT last_insert_id()";
                        rs=stmt.executeQuery(zapytanie);  
                        while(rs.next()){
                            id_buff_proj_polp = rs.getInt(1);
                        }                        

                        for (int x =0; x < koszyk_proj_klient.get(i).getPolprodukty().get(j).getIlosc(); x++) {
                            zapytanie = "INSERT INTO POLPRODUKT(Id_Proj_polprod, Id_Mebla, NrZamowienia) VALUES\n" +
                            "(" + id_buff_proj_polp + ", " + id_buff_meb + ", " + id_buff + ");";
                        }
                    }
                }
            }

            con.close(); 
        } catch(Exception e){ System.out.println(e);}
    }
    

    public void setKoszykProjektuKlienta(ArrayList<ProjektKlienta> k){
        this.koszyk_proj_klient = k;
    }

    public void setKoszyk(ArrayList<dodanyProjekt> k){
        this.koszyk = k;
    }
    
    public void setEkranKlienta(EkranKlienta kl){
        this.klient = kl;
    }
    
    /**
     * Creates new form Ekran_szczegolow_zamowienia
     */
    public Ekran_szczegolow_zamowienia() {
        initComponents();
        
    }
    
    public void koszykProjektowKlientaDoTabeli() {
        DefaultTableModel model = (DefaultTableModel) tabela_proj_klienta.getModel();

        for (int i = 0; i < koszyk_proj_klient.size(); i++) {
            int nr_projektu = koszyk_proj_klient.get(i).getId();
            int ilosc = koszyk_proj_klient.get(i).getIlosc();
            int id_typ = koszyk_proj_klient.get(i).getIdTypu();
            int id_laczenia = koszyk_proj_klient.get(i).getIdLaczenia();
            String dlugosc = koszyk_proj_klient.get(i).getDlugosc();
            String szerokosc = koszyk_proj_klient.get(i).getSzerokosc();
            String wysokosc = koszyk_proj_klient.get(i).getWysokosc();

            String laczenia;
            String typ;

            switch (id_typ) {
                case 1:{ typ = "Stół"; break;} 
                case 2: {typ = "Krzesło"; break;}
                case 3: { typ = "Fotel"; break;}
                case 4: {typ = "Łóżko"; break;} 
                case 5: {typ = "Sofa";break;}
                case 6: {typ = "Buirko"; break;}
                case 7: {typ = "Szafa"; break;}
                case 8: {typ = "Komoda"; break;}
                case 9: {typ = "Szafka nocna"; break;}
                case 10: {typ = "Narożnik"; break;}
                case 11: {typ = "Regał"; break;}
                case 12: {typ = "Kredens"; break;}
                default: { typ = "brak";}
            }

            switch (id_laczenia) {
                case 1: {laczenia = "wkręty"; break;}
                case 2: {laczenia = "klej"; break;}
                case 3: {laczenia = "wciskowe"; break;}
                case 4: {laczenia = "kołki"; break;}
                case 5: {laczenia = "mimośrody"; break;}
                case 6: {laczenia = "gwintowe"; break;}
                default: {laczenia = "brak"; } 
            }

            Object[] row = {nr_projektu,ilosc , typ, laczenia, dlugosc, szerokosc, wysokosc};
            model.addRow(row);
        }
    }

    public void koszykDoTabeli(){
        DefaultTableModel model = (DefaultTableModel) tabela_projekty_z_katalogu.getModel();
        
        for (int i=0; i < koszyk.size(); i++){
            String nr = koszyk.get(i).getId();
            String nazwa = koszyk.get(i).getNazwa();
            String typ = koszyk.get(i).getTyp();
            String cena = koszyk.get(i).getCena();
            //String mat = koszyk.get(i).getMaterial();
            //String opcj = koszyk.get(i).getOpcjonalneCzesci();
            
            Object[] tab = {nr, nazwa, typ, cena};
            
            model.addRow(tab);
        }
    }
    
    public void wyswietlPolP() {
        DefaultTableModel model = (DefaultTableModel) tabela_polp.getModel();
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        try{
            ArrayList<Polprodukt> polprodukty = koszyk_proj_klient.get(tabela_proj_klienta.getSelectedRow()).getPolprodukty();
            model = (DefaultTableModel) tabela_polp.getModel();


            for (int i=0; i < polprodukty.size(); i++){
                int ilosc = polprodukty.get(i).getIlosc();
                String nazwa = polprodukty.get(i).getNazwa();
                int id_rodzaj = polprodukty.get(i).getIdRodzaju();
                String wysokosc = polprodukty.get(i).getWysokosc();
                String szerokosc = polprodukty.get(i).getSzerokosc();
                String dlugosc = polprodukty.get(i).getDlugosc();
        
                String rodzaj;
                switch (id_rodzaj){
                    case 1: {rodzaj = "Stelaż"; break;}
                    case 2: {rodzaj = "Rama"; break;}
                    case 3: {rodzaj = "Front"; break;}
                    case 4: {rodzaj = "Dekory"; break;}
                    default: {rodzaj = "Klienta"; break;}
                }

                Object[] tab = {ilosc, nazwa, rodzaj, wysokosc, szerokosc, dlugosc};
                model.addRow(tab);
            }

        }catch(Exception e){
            e.getStackTrace();
        } 
    }


    public void usunProjektKlienta() {
        try{
            koszyk_proj_klient.remove(tabela_proj_klienta.getSelectedRow());

            DefaultTableModel model = (DefaultTableModel) tabela_proj_klienta.getModel();
            int rowCount = model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
            }
        }catch(Exception e){
            e.getStackTrace();
        }

        koszykProjektowKlientaDoTabeli();
        wyswietlPolP();
    }

    public void usunPolprodukt() {
        try{
            koszyk_proj_klient.get(tabela_proj_klienta.getSelectedRow()).getPolprodukty().remove(tabela_polp.getSelectedRow());
            DefaultTableModel model = (DefaultTableModel) tabela_polp.getModel();
            int rowCount = model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        wyswietlPolP();
    }

    public void usunProjekt() {
        try{
            koszyk.remove(tabela_projekty_z_katalogu.getSelectedRow());
            DefaultTableModel model = (DefaultTableModel) tabela_projekty_z_katalogu.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        koszykDoTabeli();
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        powrotButton = new javax.swing.JButton();
        zlozZamowienieButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_projekty_z_katalogu = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela_polp = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabela_proj_klienta = new javax.swing.JTable();
        usunButton1 = new javax.swing.JButton();
        usun_polpButton = new javax.swing.JButton();
        wyswietl_polp = new javax.swing.JButton();
        usun_projekt_klientaButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Szczegóły zamówień na własny projekt");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 420, 40));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 490, 30));

        powrotButton.setText("Powrót");
        powrotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powrotButtonActionPerformed(evt);
            }
        });
        jPanel2.add(powrotButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 120, 40));

        zlozZamowienieButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zlozZamowienieButton.setText("Złóż zamówienie");
        zlozZamowienieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zlozZamowienieButtonActionPerformed(evt);
            }
        });
        jPanel2.add(zlozZamowienieButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 500, 200, 50));

        tabela_projekty_z_katalogu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr", "Mebel", "Ilość", "Cena"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabela_projekty_z_katalogu);
        if (tabela_projekty_z_katalogu.getColumnModel().getColumnCount() > 0) {
            tabela_projekty_z_katalogu.getColumnModel().getColumn(0).setResizable(false);
            tabela_projekty_z_katalogu.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabela_projekty_z_katalogu.getColumnModel().getColumn(1).setResizable(false);
            tabela_projekty_z_katalogu.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 350, 320));

        tabela_polp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ilość", "Nazwa", "Rodzaj", "Wysokość", "Szerokość", "Długość"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabela_polp);
        if (tabela_polp.getColumnModel().getColumnCount() > 0) {
            tabela_polp.getColumnModel().getColumn(1).setResizable(false);
            tabela_polp.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela_polp.getColumnModel().getColumn(2).setResizable(false);
            tabela_polp.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 510, 160));

        tabela_proj_klienta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr", "Ilość", "Typ", "Łączenia", "Długość", "Szerokość", "Wysokość"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabela_proj_klienta);
        if (tabela_proj_klienta.getColumnModel().getColumnCount() > 0) {
            tabela_proj_klienta.getColumnModel().getColumn(0).setResizable(false);
            tabela_proj_klienta.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabela_proj_klienta.getColumnModel().getColumn(2).setResizable(false);
            tabela_proj_klienta.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 510, 150));

        usunButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usunButton1.setText("Usuń produkt");
        usunButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(usunButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 150, 50));

        usun_polpButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usun_polpButton.setText("Usuń półprodukt");
        usun_polpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usun_polpButtonActionPerformed(evt);
            }
        });
        jPanel2.add(usun_polpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, 150, 50));

        wyswietl_polp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        wyswietl_polp.setText("Wyświetl Półprodukty");
        wyswietl_polp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyswietl_polpActionPerformed(evt);
            }
        });
        jPanel2.add(wyswietl_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 180, 50));

        usun_projekt_klientaButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usun_projekt_klientaButton3.setText("Usuń projekt");
        usun_projekt_klientaButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usun_projekt_klientaButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(usun_projekt_klientaButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 410, 150, 50));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 370, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Szczegóły zamówień z katalogu");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 390, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1020, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void powrotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powrotButtonActionPerformed
        klient.setKoszyk(koszyk);
        klient.updateLiczbewKoszyku();
        this.setVisible(false);
    }//GEN-LAST:event_powrotButtonActionPerformed

    private void zlozZamowienieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zlozZamowienieButtonActionPerformed
        DbskladanieZamowienia();
        JOptionPane.showMessageDialog(new Frame(), "Zamowienie zlozone.", "Uwaga", JOptionPane.PLAIN_MESSAGE);
        int ksize = koszyk.size();
        for(int i=0; i<ksize; i++){
            koszyk.remove(0);
        }
        klient.updateLiczbewKoszyku();
        this.setVisible(false);
    }//GEN-LAST:event_zlozZamowienieButtonActionPerformed

    private void usunButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunButton1ActionPerformed
        
        try{
            koszyk.remove(tabela_projekty_z_katalogu.getSelectedRow());
            
            DefaultTableModel model = (DefaultTableModel) tabela_projekty_z_katalogu.getModel();
            int k = model.getRowCount();
            for(int i=0; i<k; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        koszykDoTabeli();
    }//GEN-LAST:event_usunButton1ActionPerformed

    private void usun_polpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usun_polpButtonActionPerformed
        usunPolprodukt();
    }//GEN-LAST:event_usun_polpButtonActionPerformed

    private void wyswietl_polpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyswietl_polpActionPerformed
        wyswietlPolP();
    }//GEN-LAST:event_wyswietl_polpActionPerformed

    private void usun_projekt_klientaButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usun_projekt_klientaButton3ActionPerformed
        usunProjektKlienta();
    }//GEN-LAST:event_usun_projekt_klientaButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ekran_szczegolow_zamowienia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ekran_szczegolow_zamowienia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ekran_szczegolow_zamowienia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ekran_szczegolow_zamowienia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ekran_szczegolow_zamowienia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton powrotButton;
    private javax.swing.JTable tabela_polp;
    private javax.swing.JTable tabela_proj_klienta;
    private javax.swing.JTable tabela_projekty_z_katalogu;
    private javax.swing.JButton usunButton1;
    private javax.swing.JButton usun_polpButton;
    private javax.swing.JButton usun_projekt_klientaButton3;
    private javax.swing.JButton wyswietl_polp;
    private javax.swing.JButton zlozZamowienieButton;
    // End of variables declaration//GEN-END:variables
}
