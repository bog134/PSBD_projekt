/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;
/**
 *
 * @author huawei
 */
public class EkranKlienta extends javax.swing.JFrame {

    /**
     * Creates new form EkranKlienta
     */
    public EkranKlienta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        wylogujButton = new javax.swing.JButton();
        historiaZamowienButton = new javax.swing.JButton();
        wlasnyProjektButton = new javax.swing.JButton();
        koszyk_pane = new javax.swing.JPanel();
        koszykButton = new javax.swing.JButton();
        ikona_wozka_sklepowego = new javax.swing.JLabel();
        l_rzeczy_w_koszyku = new javax.swing.JLabel();
        dane_użytkownika = new javax.swing.JPanel();
        zalogowano_jako = new javax.swing.JLabel();
        obraz_uzytkownika_label = new javax.swing.JLabel();
        nazwa_użytkownika = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        kategorie_comb_box = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wylogujButton.setText("Wyloguj");
        wylogujButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        wylogujButton.setFocusTraversalPolicyProvider(true);
        wylogujButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujButtonActionPerformed(evt);
            }
        });
        getContentPane().add(wylogujButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 13, 90, 50));

        historiaZamowienButton.setText("Wyświetl historię zamówień");
        historiaZamowienButton.setToolTipText("");
        historiaZamowienButton.setAutoscrolls(true);
        historiaZamowienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historiaZamowienButtonActionPerformed(evt);
            }
        });
        getContentPane().add(historiaZamowienButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 13, 156, 50));

        wlasnyProjektButton.setText("Własny projekt");
        wlasnyProjektButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wlasnyProjektButtonActionPerformed(evt);
            }
        });
        getContentPane().add(wlasnyProjektButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 13, -1, 50));

        koszyk_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        koszykButton.setText("Koszyk");
        koszykButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koszykButtonActionPerformed(evt);
            }
        });
        koszyk_pane.add(koszykButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 13, 150, 50));
        koszyk_pane.add(ikona_wozka_sklepowego, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 39, 37));
        javax.swing.ImageIcon icon2;
        try {
            icon2 = new javax.swing.ImageIcon(".\\src\\Icons\\shopping_cart.png");
            java.awt.Image im1 = icon2.getImage();
            //    java.awt.Image scaled_im = im1.getScaledInstance(obraz_uzytkownika_label.getSize().width, obraz_uzytkownika_label.getSize().height, java.awt.Image.SCALE_SMOOTH);
            java.awt.Image scaled_im = im1.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
            icon2 = new javax.swing.ImageIcon(scaled_im);
            ikona_wozka_sklepowego.setIcon(icon2);
        } catch (java.lang.Exception e) {
            java.lang.System.out.println("blad ladowania ikony");
        }

        l_rzeczy_w_koszyku.setText("Koszyk: l_sz");
        koszyk_pane.add(l_rzeczy_w_koszyku, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        getContentPane().add(koszyk_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 200, 120));

        zalogowano_jako.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        zalogowano_jako.setText("Zalogowano jako:");

        obraz_uzytkownika_label.setText("obraz użytkownika ");
        obraz_uzytkownika_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        obraz_uzytkownika_label.setPreferredSize(new java.awt.Dimension(100, 16));
        javax.swing.ImageIcon icon;

        try {
            icon = new javax.swing.ImageIcon(".\\src\\Icons\\profile_pic.jpg");
            java.awt.Image im1 = icon.getImage();
            //    java.awt.Image scaled_im = im1.getScaledInstance(obraz_uzytkownika_label.getSize().width, obraz_uzytkownika_label.getSize().height, java.awt.Image.SCALE_SMOOTH);
            java.awt.Image scaled_im = im1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon = new javax.swing.ImageIcon(scaled_im);
            obraz_uzytkownika_label.setIcon(icon);
        } catch (java.lang.Exception e) {
            java.lang.System.out.println("blad ladowania ikony");
        }

        nazwa_użytkownika.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nazwa_użytkownika.setText("JOHN");

        javax.swing.GroupLayout dane_użytkownikaLayout = new javax.swing.GroupLayout(dane_użytkownika);
        dane_użytkownika.setLayout(dane_użytkownikaLayout);
        dane_użytkownikaLayout.setHorizontalGroup(
            dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(zalogowano_jako)
            .addComponent(obraz_uzytkownika_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(nazwa_użytkownika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dane_użytkownikaLayout.setVerticalGroup(
            dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dane_użytkownikaLayout.createSequentialGroup()
                .addComponent(zalogowano_jako)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obraz_uzytkownika_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nazwa_użytkownika)
                .addContainerGap())
        );

        getContentPane().add(dane_użytkownika, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nr", "Nazwa", "Typ", "Ilość"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 3, 3));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Materiał:");
        jPanel2.add(jLabel9);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox3);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Opcjonalne części:");
        jLabel7.setToolTipText("");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel7);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(jComboBox2);

        jButton9.setText("Dodaj do koszyka");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);

        jButton10.setText("Odrzuć");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);

        jLabel8.setText("Konfiguracja mebla:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, 640));

        kategorie_comb_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stoły", "Krzesła", "Fotele", "Łóżka", "Sofy",
            "Biurka", "Szafy", "Komody", "Szafki nocne", "Narożniki", "Regały", "Kredensy"}));
kategorie_comb_box.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        kategorie_comb_boxActionPerformed(evt);
    }
    });
    getContentPane().add(kategorie_comb_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 140, 30));

    jLabel1.setText("Kategorie:");
    getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wylogujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujButtonActionPerformed
        new Ekran_glowny().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_wylogujButtonActionPerformed

    private void historiaZamowienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historiaZamowienButtonActionPerformed
        new Ekran_historii_zamowien().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_historiaZamowienButtonActionPerformed

    private void wlasnyProjektButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wlasnyProjektButtonActionPerformed
        new Ekran_projektu_klienta().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_wlasnyProjektButtonActionPerformed

    private void koszykButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koszykButtonActionPerformed
        new Ekran_szczegolow_zamowienia().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_koszykButtonActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void kategorie_comb_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategorie_comb_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategorie_comb_boxActionPerformed

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
            java.util.logging.Logger.getLogger(EkranKlienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EkranKlienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EkranKlienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EkranKlienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EkranKlienta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dane_użytkownika;
    private javax.swing.JButton historiaZamowienButton;
    private javax.swing.JLabel ikona_wozka_sklepowego;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> kategorie_comb_box;
    private javax.swing.JButton koszykButton;
    private javax.swing.JPanel koszyk_pane;
    private javax.swing.JLabel l_rzeczy_w_koszyku;
    private javax.swing.JLabel nazwa_użytkownika;
    private javax.swing.JLabel obraz_uzytkownika_label;
    private javax.swing.JButton wlasnyProjektButton;
    private javax.swing.JButton wylogujButton;
    private javax.swing.JLabel zalogowano_jako;
    // End of variables declaration//GEN-END:variables
}
