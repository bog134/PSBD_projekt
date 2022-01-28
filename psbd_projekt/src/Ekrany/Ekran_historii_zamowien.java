/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;

import java.awt.Frame;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huawei
 */
public class Ekran_historii_zamowien extends javax.swing.JFrame {
    
    String StanZamowienia = null;
    String IdMebla = null;
    String IdZamowienia = null;
    int IdKlienta = 3;
    String login_klienta = null;

    /**
     * Creates new form Ekran_historii_zamowien
     */
    public Ekran_historii_zamowien() {
        initComponents();
    }
    
    public void  setCustomer(int id, String login){
        this.IdKlienta = id;
        this.login_klienta = login;
        
    }
    
    public int DbCzasRealizacji(String id){
        int czasRealizacji = 0;
        int czasZlozeniaZamowienia = 0;
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "SELECT zamowienie_na_meble.Czas_realizacji_Data_zlozenia FROM firma.zamowienie_na_meble\n" +
                    "WHERE Id_Stanu_Realizacji = 1 AND zamowienie_na_meble.Id_Zamowienia = "+id;
            ResultSet rs=stmt.executeQuery(zapytanie);
            while(rs.next()){
                czasZlozeniaZamowienia = rs.getInt(1);
            }
            
            
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        return czasRealizacji;
    }
    
    public void DbHistoriaZamowien(){
        
        Object[] tab;
        String filtr = " ", filtr2 = " ", filtr3 = " ";
        
        if((jTextField3.getText().equals("Od")||jTextField3.getText().equals(""))&&(jTextField2.getText().equals("Do")||jTextField2.getText().equals(""))){
            filtr = " -- ";
            filtr2 = " ";
        }else if((jTextField3.getText().equals("Od")||jTextField3.getText().equals(""))){
            filtr = " /* ";
            filtr2 = " */ ";
        }else if((jTextField2.getText().equals("Do")||jTextField2.getText().equals(""))){
            filtr = " ";
            filtr2 = " -- ";
        }else{
            filtr = " ";
            filtr2 = " ";
        }
        
        if(jComboBox1.getSelectedItem().toString().equals(" ")){
            filtr3 = " -- ";
        }else{
            filtr3 = " ";
        }
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "SELECT zamowienie_na_meble.Id_Zamowienia, zamowienie_na_meble.Czas_realizacji_Data_zlozenia, stan_realizacji.Nazwa_Stanu, ROUND(COALESCE(SUM(definicja_zadania.Czas_wykonania),0)/3600,1) FROM zamowienie_na_meble\n" +
                    "LEFT JOIN stan_realizacji ON stan_realizacji.Id_Stanu_realizacji = zamowienie_na_meble.Id_Stanu_Realizacji\n" +
                    "LEFT JOIN mebel ON mebel.Id_Zamowienia = zamowienie_na_meble.Id_Zamowienia\n" +
                    "LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog\n" +
                    "LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog\n" +
                    "WHERE zamowienie_na_meble.Id_Klienta = "+IdKlienta+" "+filtr+" AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia > DATE("+'"'+jTextField3.getText()+'"'+") "+filtr2+" AND zamowienie_na_meble.Czas_realizacji_Data_zlozenia < DATE("+'"'+jTextField2.getText()+'"'+") \n" +
                    "GROUP BY zamowienie_na_meble.Id_Zamowienia\n"+
                    "" +filtr3+ "HAVING stan_realizacji.Nazwa_Stanu LIKE "+'"'+ jComboBox1.getSelectedItem().toString()+'"';
            ResultSet rs=stmt.executeQuery(zapytanie);
            while(rs.next()){
                tab = new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4)};
                //System.out.println(rs.getObject(1)+"  "+rs.getObject(2)+"  "+rs.getObject(3));  
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(tab);
            }
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        int rowCount = jTable1.getModel().getRowCount();
        for(int i=0; i<rowCount; i++){
            int column = 0;
            String nr = jTable1.getModel().getValueAt(rowCount, column).toString();
            int data = DbCzasRealizacji(nr);
            jTable1.getModel().setValueAt(data, rowCount, column);
        }
        
    }
    
    
    public void DbSzczegolyZamowienia(String value){
        
        Object[] tab;
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "SELECT COALESCE(mebel.Id_Proj_katalog,0) AS a, mebel.Id_Mebla, typ_mebla.Nazwa, (projekt_z_katalogu.Marza + cenaRobociznyTab.Robocizna + COALESCE(cenaMaterialuTab.CenaMaterialu,0) + COALESCE(cenaPolprTab.cenaPolprod,0)) AS Cena, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel\n" +
                    "LEFT JOIN projekt_z_katalogu ON projekt_z_katalogu.Id_Proj_katalog = mebel.Id_Proj_katalog\n" +
                    "LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla\n" +
                    "LEFT JOIN (\n" +
                    "SELECT mebel.Id_Mebla, SUM(definicja_zadania.Cena) AS Robocizna FROM mebel\n" +
                    "LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = mebel.Id_Proj_katalog\n" +
                    "-- WHERE mebel.Id_Zamowienia = \"parametr\"\n" +
                    "GROUP BY mebel.Id_Mebla) AS cenaRobociznyTab ON cenaRobociznyTab.Id_Mebla = mebel.Id_Mebla \n" +
                    "LEFT JOIN (\n" +
                    "SELECT mebel.Id_Mebla, SUM(material.Cena) AS CenaMaterialu FROM mebel\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = mebel.Id_Proj_katalog\n" +
                    "LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu\n" +
                    "-- WHERE mebel.Id_Zamowienia = \"parametr\"\n" +
                    "GROUP BY mebel.Id_Mebla) AS cenaMaterialuTab ON cenaMaterialuTab.Id_Mebla = mebel.Id_Mebla\n" +
                    "LEFT JOIN (\n" +
                    "SELECT mebel.Id_Mebla, SUM(projekt_polproduktu.Cena) AS CenaPolprod FROM polprodukt\n" +
                    "LEFT JOIN mebel ON mebel.Id_Mebla = polprodukt.Id_Mebla\n" +
                    "LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_polprod = polprodukt.Id_Proj_polprod\n" +
                    "-- WHERE mebel.Id_Zamowienia = \"parametr\"\n" +
                    "GROUP BY mebel.Id_Mebla) AS cenaPolprTab ON cenaPolprTab.Id_Mebla = mebel.Id_Mebla\n" +
                    "LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia\n" +
                    "WHERE mebel.Id_Zamowienia = "+value+"\n" +
                    "HAVING a>0";
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
                tab = new Object[]{rs.getObject(2), rs.getObject(3), rs.getObject(4), "Projekt z katalogu"};
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.addRow(tab);
            }
            
            zapytanie = 
                    "SELECT COALESCE(mebel.Id_Proj_klient,0) AS a, mebel.Id_Mebla, typ_mebla.Nazwa, (cena.Koszt_robocizny + cena.Koszt_surowcow + cena.Marza) AS Cena, zamowienie_na_meble.Id_Stanu_Realizacji FROM mebel\n" +
                    "LEFT JOIN projekt_klienta ON projekt_klienta.Id_Proj_klient = mebel.Id_Proj_klient\n" +
                    "LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_klienta.Id_Typu_mebla\n" +
                    "LEFT JOIN cena ON cena.Id_Ceny = projekt_klienta.Id_Ceny\n" +
                    "LEFT JOIN zamowienie_na_meble ON zamowienie_na_meble.Id_Zamowienia = mebel.Id_Zamowienia\n" +
                    "WHERE mebel.Id_Zamowienia = "+value+"\n" +
                    "HAVING a >0 ";
            rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
                tab = new Object[]{rs.getObject(2), rs.getObject(3), rs.getObject(4), "Projekt klienta"};
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.addRow(tab);
            }
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        int liczbarzedow = jTable2.getModel().getRowCount();
        double koszt = 0;
        
        for(int i=0; i<liczbarzedow; i++){
            int column = 2;
            int row = i;
            koszt = koszt + Double.valueOf(jTable2.getModel().getValueAt(row, column).toString());
            
        }
        cenaLabel.setText("Koszt zamówienia: "+koszt);
        
        
    }
    
    public void DbAkceptuj(){
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "UPDATE zamowienie_na_meble\n" +
                    "SET Id_stanu_realizacji = 1\n" +
                    "WHERE zamowienie_na_meble.Id_Zamowienia = " + IdZamowienia;
            
            
            stmt.executeUpdate(zapytanie);
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    }
    
    public void DbAnuluj(){
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            String zapytanie = 
                    "UPDATE zamowienie_na_meble\n" +
                    "SET Id_stanu_realizacji = 5\n" +
                    "WHERE zamowienie_na_meble.Id_Zamowienia = " + IdZamowienia;
            
            
            stmt.executeUpdate(zapytanie);
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        powrotButton = new javax.swing.JButton();
        reklamacjaButton = new javax.swing.JButton();
        anulujButton = new javax.swing.JButton();
        zaakceptujButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        szukajButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        cenaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Historia Zamówień");
        jLabel1.setAutoscrolls(true);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr.", "Data złożenia", "Status zamowienia", "Przewidywany czas realizacji [h]"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 360));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Szczegóły Zamówienia");
        jLabel2.setAutoscrolls(true);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr.", "Mebel", "Cena", "Wg. projektu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jScrollPane3.setViewportView(jScrollPane4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 350));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 480, 80));

        powrotButton.setText("Powrót");
        powrotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powrotButtonActionPerformed(evt);
            }
        });
        getContentPane().add(powrotButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 110, 40));

        reklamacjaButton.setText("Złóż reklamację");
        reklamacjaButton.setEnabled(false);
        reklamacjaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reklamacjaButtonActionPerformed(evt);
            }
        });
        getContentPane().add(reklamacjaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 670, 150, 40));

        anulujButton.setText("Anuluj Zamówienie");
        anulujButton.setToolTipText("");
        anulujButton.setEnabled(false);
        anulujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anulujButtonActionPerformed(evt);
            }
        });
        getContentPane().add(anulujButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 810, 220, 50));

        zaakceptujButton.setText("Zaakceptuj Zamówienie");
        zaakceptujButton.setEnabled(false);
        zaakceptujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zaakceptujButtonActionPerformed(evt);
            }
        });
        getContentPane().add(zaakceptujButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 810, 220, 50));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Od");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Do");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("-");

        szukajButton.setText("Szukaj");
        szukajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                szukajButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Data:");

        jLabel5.setText("Status zamowienia:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "W realizacji", "Gotowe do odebrania", "Oczekuje na zatwierdzenie", "Wyceniono", "Odrzucono", "Odebrano" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(szukajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(61, 61, 61))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(szukajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 210, 180));
        getContentPane().add(cenaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 730, 210, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void powrotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powrotButtonActionPerformed
        //EkranKlienta klient = new EkranKlienta();
        //klient.setCustomer(IdKlienta, login_klienta);
        //klient.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_powrotButtonActionPerformed

    private void anulujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anulujButtonActionPerformed
        DbAnuluj();
        JOptionPane.showMessageDialog(new Frame(), "Zamówienie anulowane", "Uwaga", JOptionPane.PLAIN_MESSAGE);
        
        reklamacjaButton.setEnabled(false);
        zaakceptujButton.setEnabled(false);
        anulujButton.setEnabled(false);
        jTextField1.setText(" ");
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        DbHistoriaZamowien();
    }//GEN-LAST:event_anulujButtonActionPerformed

    private void zaakceptujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zaakceptujButtonActionPerformed
        DbAkceptuj();
        JOptionPane.showMessageDialog(new Frame(), "Reklamacja zaakceptowane", "Uwaga", JOptionPane.PLAIN_MESSAGE);
        
        reklamacjaButton.setEnabled(false);
        zaakceptujButton.setEnabled(false);
        anulujButton.setEnabled(false);
        jTextField1.setText(" ");
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        DbHistoriaZamowien();
        
    }//GEN-LAST:event_zaakceptujButtonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void reklamacjaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reklamacjaButtonActionPerformed
        Arkusz_Reklamacyjny arkusz = new Arkusz_Reklamacyjny();
        arkusz.setIdMebla(IdMebla);
        arkusz.setVisible(true);
    }//GEN-LAST:event_reklamacjaButtonActionPerformed

    private void szukajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_szukajButtonActionPerformed
        
        reklamacjaButton.setEnabled(false);
        zaakceptujButton.setEnabled(false);
        anulujButton.setEnabled(false);
        jTextField1.setText(" ");
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        DbHistoriaZamowien();
    }//GEN-LAST:event_szukajButtonActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        
        reklamacjaButton.setEnabled(false);
        zaakceptujButton.setEnabled(false);
        anulujButton.setEnabled(false);
        jTextField1.setText(" ");
        
        try{
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        int column = 0;
        int row = jTable1.getSelectedRow();
        IdZamowienia = jTable1.getModel().getValueAt(row, column).toString();
        //System.out.println(value);
        
        DbSzczegolyZamowienia(IdZamowienia);
        
        column = 2;
        row = jTable1.getSelectedRow();
        StanZamowienia = jTable1.getModel().getValueAt(row, column).toString();
        System.out.println(StanZamowienia);
        
        if(StanZamowienia.equals("Wyceniono")){
            jTextField1.setText("Twoje zamówienie zostało wycenione. Zaakceptować?");
            zaakceptujButton.setEnabled(true);
            anulujButton.setEnabled(true);
        }
        
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        
        reklamacjaButton.setEnabled(false);
        
        
        int column = 0;
        int row = jTable2.getSelectedRow();
        IdMebla = jTable2.getModel().getValueAt(row, column).toString();
        if(StanZamowienia.equals("Odebrano")){
            reklamacjaButton.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_jTable2MousePressed

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
            java.util.logging.Logger.getLogger(Ekran_historii_zamowien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ekran_historii_zamowien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ekran_historii_zamowien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ekran_historii_zamowien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ekran_historii_zamowien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anulujButton;
    private javax.swing.JLabel cenaLabel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton powrotButton;
    private javax.swing.JButton reklamacjaButton;
    private javax.swing.JButton szukajButton;
    private javax.swing.JButton zaakceptujButton;
    // End of variables declaration//GEN-END:variables
}
