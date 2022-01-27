/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;

import Dodatkowe.dodanyProjekt;
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
    
    public void koszykDoTabeli(){
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        
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
        usunButton = new javax.swing.JButton();
        zlozZamowienieButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

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

        usunButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usunButton.setText("Usuń produkt");
        usunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunButtonActionPerformed(evt);
            }
        });
        jPanel2.add(usunButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 150, 50));

        zlozZamowienieButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        zlozZamowienieButton.setText("Złóż zamówienie");
        zlozZamowienieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zlozZamowienieButtonActionPerformed(evt);
            }
        });
        jPanel2.add(zlozZamowienieButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 150, 50));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 330, 270));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
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

    private void usunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunButtonActionPerformed

        
        System.out.println(jTable2.getSelectedRow());
        
        koszyk.remove(jTable2.getSelectedRow());
        try{
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            for(int i=1; i<10; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        
        
        koszykDoTabeli();
    }//GEN-LAST:event_usunButtonActionPerformed

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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton powrotButton;
    private javax.swing.JButton usunButton;
    private javax.swing.JButton zlozZamowienieButton;
    // End of variables declaration//GEN-END:variables
}
