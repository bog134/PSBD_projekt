/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;

import Dodatkowe.dodanyProjekt;
import Dodatkowe.ProjektKlienta;
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
          
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
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
            int id_typ = koszyk_proj_klient.get(i).getIdTypu();
            int id_laczenia = koszyk_proj_klient.get(i).getIdLaczenia();
            String dlugosc = koszyk_proj_klient.get(i).getDlugosc();
            String szerokosc = koszyk_proj_klient.get(i).getSzerokosc();
            String wysokosc = koszyk_proj_klient.get(i).getWysokosc();

            String laczenia;
            String typ;

            switch (id_typ) {
                case 1:{ typ = "Stół"; break;} 
                case 2: {typ = "Krzesło"; break;
                } case 3: { typ = "Fotel"; break;
                } case 4: {typ = "Łóżko"; break;
                } case 5: {typ = "Sofa";break;
                } case 6: {typ = "Buirko"; break;
                } case 7: {typ = "Szafa"; break;
                } case 8: {typ = "Komoda"; break;
                } case 9: {typ = "Szafka nocna"; break;
                } case 10: {typ = "Narożnik"; break;
                } case 11: {typ = "Regał"; break;
                } case 12: {typ = "Kredens"; break;}
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

            Object[] row = {nr_projektu, typ, laczenia, dlugosc, szerokosc, wysokosc};
            model.addRow(row);
        }
    }

    public void koszykDoTabeli(){
        DefaultTableModel model = (DefaultTableModel) tabela_polp.getModel();
        
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
        tabela_polp = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabela_proj_klienta = new javax.swing.JTable();
        zlozZamowienieButton1 = new javax.swing.JButton();
        usunButton1 = new javax.swing.JButton();
        usun_polpButton = new javax.swing.JButton();
        usun_projekt_klientaButton2 = new javax.swing.JButton();

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
        jLabel1.setText("Szczegóły zamówienia");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, 40));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 440, 30));

        powrotButton.setText("Powrót");
        powrotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powrotButtonActionPerformed(evt);
            }
        });
        jPanel2.add(powrotButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 100, 30));

        zlozZamowienieButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zlozZamowienieButton.setText("Złóż zamówienie");
        zlozZamowienieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zlozZamowienieButtonActionPerformed(evt);
            }
        });
        jPanel2.add(zlozZamowienieButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 150, 50));

        tabela_polp.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabela_polp);
        if (tabela_polp.getColumnModel().getColumnCount() > 0) {
            tabela_polp.getColumnModel().getColumn(0).setResizable(false);
            tabela_polp.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabela_polp.getColumnModel().getColumn(1).setResizable(false);
            tabela_polp.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 510, 160));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 330, 310));

        tabela_proj_klienta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr", "Typ", "Łączenia", "Długość", "Szerokość", "Wysokość"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
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
            tabela_proj_klienta.getColumnModel().getColumn(1).setResizable(false);
            tabela_proj_klienta.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 510, 140));

        zlozZamowienieButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zlozZamowienieButton1.setText("Złóż zamówienie");
        zlozZamowienieButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zlozZamowienieButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(zlozZamowienieButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 150, 50));

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
        jPanel2.add(usun_polpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 410, 150, 50));

        usun_projekt_klientaButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usun_projekt_klientaButton2.setText("Usuń projekt");
        usun_projekt_klientaButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usun_projekt_klientaButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(usun_projekt_klientaButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 410, 150, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1220, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        this.setVisible(false);
    }//GEN-LAST:event_zlozZamowienieButtonActionPerformed

    private void zlozZamowienieButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zlozZamowienieButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zlozZamowienieButton1ActionPerformed

    private void usunButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usunButton1ActionPerformed

    private void usun_polpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usun_polpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usun_polpButtonActionPerformed

    private void usun_projekt_klientaButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usun_projekt_klientaButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usun_projekt_klientaButton2ActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton powrotButton;
    private javax.swing.JTable tabela_polp;
    private javax.swing.JTable tabela_proj_klienta;
    private javax.swing.JButton usunButton1;
    private javax.swing.JButton usun_polpButton;
    private javax.swing.JButton usun_projekt_klientaButton2;
    private javax.swing.JButton zlozZamowienieButton;
    private javax.swing.JButton zlozZamowienieButton1;
    // End of variables declaration//GEN-END:variables
}
