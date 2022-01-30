/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;
import Dodatkowe.dodanyProjekt;
import Dodatkowe.ProjektKlienta;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author huawei
 */
public class EkranKlienta extends javax.swing.JFrame {
    int id_klienta;
    java.lang.String login_klienta;
    int licznik = 0;

    Ekran_projektu_klienta ekran_projektu_klienta;
    
    /*public class dodanyProjekt{
        
        String Id_projektu, Nazwa, Typ, Cena, Material, OpcjonalneCzesci;
        
        public dodanyProjekt(String Id_projektu, String Nazwa, String Typ, String Cena, String Material, String OpcjonalneCzesci){
            this.setId(Id_projektu);
            this.setNazwa(Nazwa);
            this.setTyp(Typ);
            this.setCena(Cena);
            this.setMaterial(Material);
            this.setOpcjonalneCzesci(OpcjonalneCzesci);
        }
        
        public void setId(String Id_projektu){this.Id_projektu = Id_projektu;}
        public void setNazwa(String Nazwa){this.Nazwa = Nazwa;}
        public void setTyp(String Typ){this.Typ = Typ;}
        public void setCena(String Cena){this.Cena = Cena;}
        public void setMaterial(String Material){this.Material = Material;}
        public void setOpcjonalneCzesci(String OpcjonalneCzesci){this.OpcjonalneCzesci = OpcjonalneCzesci;}
        
    }*/

    ArrayList<ProjektKlienta> koszyk_projektow_klienta;
    ArrayList<dodanyProjekt> koszyk;

    public void setKoszyk(ArrayList<dodanyProjekt> k){
        this.koszyk = k;
    }

    public void setKoszykProjektowKlienta(ArrayList<ProjektKlienta> k){
        this.koszyk_projektow_klienta = k;
    }

    
    public void DbWyswietlKatalog(String temp, String filtr){
        Object[] tab;
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            temp = '"'+temp+'"';
            String zapytanie = 
                    "SELECT tab1.Id_Proj_katalog, projekt_z_katalogu.Nazwa, typ_mebla.Nazwa, (tab1.Marza + COALESCE(tab1.CenaPolProd,0) + tab2.CenaMaterialow + tab3.CenaZadan) AS KosztCalkowity FROM projekt_z_katalogu\n" +
                    "LEFT JOIN\n" +
                    "(SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(projekt_z_katalogu.Marza) AS Marza, SUM(projekt_polproduktu.cena) AS CenaPolprod  FROM projekt_z_katalogu\n" +
                    "LEFT JOIN projekt_polproduktu ON projekt_polproduktu.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog\n" +
                    "GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab1 USING (Id_Proj_katalog)\n" +
                    "LEFT JOIN (\n" +
                    "SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(material.Cena) AS CenaMaterialow FROM projekt_z_katalogu\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog\n" +
                    "LEFT JOIN material ON material.Id_Materialu = material_proj_katalog.Id_Materialu\n" +
                    "GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab2 USING (Id_Proj_katalog)\n" +
                    "LEFT JOIN (\n" +
                    "SELECT projekt_z_katalogu.Id_Proj_katalog, SUM(definicja_zadania.Cena) AS CenaZadan FROM firma.projekt_z_katalogu\n" +
                    "LEFT JOIN definicja_zadania ON definicja_zadania.Id_Proj_katalog = projekt_z_katalogu.Id_Proj_katalog\n" +
                    "GROUP BY projekt_z_katalogu.Id_Proj_katalog) tab3 USING (Id_Proj_katalog)\n" +
                    "LEFT JOIN typ_mebla ON typ_mebla.Id_Typu_mebla = projekt_z_katalogu.Id_Typu_mebla\n" +
                    filtr+"WHERE typ_mebla.Nazwa = "+ temp;
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
                tab = new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4)};
                //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(tab);
            }
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
    }
    
    public void DbOpcjonalneCzesci(String indeks){
        
        
        ArrayList<String> lista = new ArrayList<>();
        
        lista.add(" ");
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();

            String zapytanie = 
                    "SELECT Nazwa FROM firma.opcjonalna_czesc\n" +
                    "WHERE Id_Proj_katalog ="+indeks;
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()){
                lista.add(rs.getString(1));
            }
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        //String[] array = (String[]) lista.toArray();
        opcjBox.setModel(new DefaultComboBoxModel<String>(lista.toArray(new String[0])));
    }
    
    public void DbMateialy(String indeks){
        
        
        
        //lista.add(" ");
        
        try{  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
            Statement stmt=con.createStatement();
            
            ArrayList<String> listaT = new ArrayList<>();
            //tkanina
            String zapytanie = 
                    "SELECT Nazwa FROM material\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Materialu = material.Id_Materialu\n" +
                    "WHERE material.Id_Rodzaju_materialu = 1 AND material_proj_katalog.Id_Proj_katalog = "+indeks;
            ResultSet rs=stmt.executeQuery(zapytanie);  
            while(rs.next()) {
                listaT.add(rs.getString(1));
            }
            if(listaT.isEmpty()){
                listaT.add(" ");
                tkaninaBox.setEnabled(false); 
            } else {tkaninaBox.setEnabled(true);}
            tkaninaBox.setModel(new DefaultComboBoxModel<String>(listaT.toArray(new String[0])));
            
            ArrayList<String> listaP = new ArrayList<>();
            //plyta
            zapytanie = 
                    "SELECT Nazwa FROM material\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Materialu = material.Id_Materialu\n" +
                    "WHERE material.Id_Rodzaju_materialu = 2 AND material_proj_katalog.Id_Proj_katalog = "+indeks;
            rs=stmt.executeQuery(zapytanie);  
            while(rs.next()) {
                listaP.add(rs.getString(1));
            }
            if(listaP.isEmpty()){
                listaP.add(" ");
                plytaBox.setEnabled(false); 
            } else {plytaBox.setEnabled(true);}
            plytaBox.setModel(new DefaultComboBoxModel<String>(listaP.toArray(new String[0])));
            
            ArrayList<String> listaD = new ArrayList<>();
            //drewno
            zapytanie = 
                    "SELECT Nazwa FROM material\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Materialu = material.Id_Materialu\n" +
                    "WHERE material.Id_Rodzaju_materialu = 3 AND material_proj_katalog.Id_Proj_katalog = "+indeks;
            rs=stmt.executeQuery(zapytanie);  
            while(rs.next()) {
                listaD.add(rs.getString(1));
            }
            if(listaD.isEmpty()){
                listaD.add(" ");
                drewnoBox.setEnabled(false); 
            } else {drewnoBox.setEnabled(true);}
            drewnoBox.setModel(new DefaultComboBoxModel<String>(listaD.toArray(new String[0])));
            
            ArrayList<String> listaPi = new ArrayList<>();
            //pianka
            zapytanie = 
                    "SELECT Nazwa FROM material\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Materialu = material.Id_Materialu\n" +
                    "WHERE material.Id_Rodzaju_materialu = 4 AND material_proj_katalog.Id_Proj_katalog = "+indeks;
            rs=stmt.executeQuery(zapytanie);  
            while(rs.next()) {
                listaPi.add(rs.getString(1));
            }
            if(listaPi.isEmpty()){
                listaPi.add(" ");
                piankaBox.setEnabled(false); 
            } else {piankaBox.setEnabled(true);}
            piankaBox.setModel(new DefaultComboBoxModel<String>(listaPi.toArray(new String[0])));
            
            ArrayList<String> listaO = new ArrayList<>();
            //okleina
            zapytanie = 
                    "SELECT Nazwa FROM material\n" +
                    "LEFT JOIN material_proj_katalog ON material_proj_katalog.Id_Materialu = material.Id_Materialu\n" +
                    "WHERE material.Id_Rodzaju_materialu = 5 AND material_proj_katalog.Id_Proj_katalog = "+indeks;
            rs=stmt.executeQuery(zapytanie);
            while(rs.next()) {
                listaO.add(rs.getString(1));
            }
            if(listaO.isEmpty()){
                listaO.add(" ");
                okleinaBox.setEnabled(false); 
            } else {okleinaBox.setEnabled(true);}
            okleinaBox.setModel(new DefaultComboBoxModel<String>(listaO.toArray(new String[0])));
            
            con.close(); 
        }catch(Exception e){ System.out.println(e);}
        
        //String[] array = (String[]) lista.toArray();
        
    }
    /**
     * Creates new form EkranKlienta
     */
    public EkranKlienta() {
        initComponents();
        id_klienta = 0;
        login_klienta = "Nieznany";  
        DbWyswietlKatalog(" ", " -- ");
        koszyk = new ArrayList<>();
        koszyk_projektow_klienta = new ArrayList<ProjektKlienta>();
    
    }
    
    public void  setCustomer(int id, String login){
        this.id_klienta = id;
        this.login_klienta = login;
        nazwa_użytkownika.setText(login_klienta);
    }
    
    public void updateLiczbewKoszyku(){
        
        l_rzeczy_w_koszyku.setText("Koszyk: " + String.valueOf(koszyk.size() + koszyk_projektow_klienta.size()));
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
        opcjBox = new javax.swing.JComboBox<>();
        dodajDoKoszykaButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tkaninaBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        plytaBox = new javax.swing.JComboBox<>();
        drewnoBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        piankaBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        okleinaBox = new javax.swing.JComboBox<>();
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

        l_rzeczy_w_koszyku.setText("Koszyk: 0");
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
        nazwa_użytkownika.setText(login_klienta);

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

            },
            new String [] {
                "Nr", "Nazwa", "Typ", "Cena"
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Płyta meblowa:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 164, 41));

        opcjBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        opcjBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        opcjBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcjBoxActionPerformed(evt);
            }
        });
        jPanel2.add(opcjBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 164, 41));

        dodajDoKoszykaButton.setText("Dodaj do koszyka");
        dodajDoKoszykaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajDoKoszykaButtonActionPerformed(evt);
            }
        });
        jPanel2.add(dodajDoKoszykaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 340, 41));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tkanina obiciowa:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 164, 41));

        tkaninaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        tkaninaBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tkaninaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkaninaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(tkaninaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 164, 41));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Opcjonalna część:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 164, 41));

        plytaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        plytaBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        plytaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plytaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(plytaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 164, 41));

        drewnoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        drewnoBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        drewnoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drewnoBoxActionPerformed(evt);
            }
        });
        jPanel2.add(drewnoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 164, 41));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Typ drewna");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 164, 41));

        piankaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        piankaBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        piankaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                piankaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(piankaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 164, 41));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Pianka tapicerska:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 164, 41));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Typ okleiny:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 164, 41));

        okleinaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        okleinaBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        okleinaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okleinaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(okleinaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 164, 41));

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, 700));

        kategorie_comb_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wszystkie" ,"Stoły", "Krzesła", "Fotele", "Łóżka", "Sofy",
            "Biurka", "Szafy", "Komody", "Szafki nocne", "Narożniki", "Regały", "Kredensy"}));
kategorie_comb_box.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mousePressed(java.awt.event.MouseEvent evt) {
        kategorie_comb_boxMousePressed(evt);
    }
    });
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
        Ekran_historii_zamowien histZam = null;
        histZam = new Ekran_historii_zamowien();
        histZam.setCustomer(id_klienta, login_klienta);
        histZam.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_historiaZamowienButtonActionPerformed

    private void wlasnyProjektButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wlasnyProjektButtonActionPerformed
        Ekran_projektu_klienta ekran_projektu_klienta = new Ekran_projektu_klienta();
        ekran_projektu_klienta.setVisible(true);
        ekran_projektu_klienta.setEkranKlienta(this);
        ekran_projektu_klienta.setKoszyk(koszyk_projektow_klienta);
        this.setVisible(false);
    }//GEN-LAST:event_wlasnyProjektButtonActionPerformed

    private void koszykButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koszykButtonActionPerformed
        Ekran_szczegolow_zamowienia ekranSZ = new Ekran_szczegolow_zamowienia();
        ekranSZ.setKoszyk(koszyk);
        ekranSZ.setKoszykProjektuKlienta(koszyk_projektow_klienta);
        ekranSZ.koszykDoTabeli();
        ekranSZ.koszykProjektowKlientaDoTabeli();
        ekranSZ.setVisible(true);
        ekranSZ.setEkranKlienta(this);
        
        //this.setVisible(false);
    }//GEN-LAST:event_koszykButtonActionPerformed

    private void dodajDoKoszykaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajDoKoszykaButtonActionPerformed
        
        int column = 0;
        int row = jTable1.getSelectedRow();
        String numer = jTable1.getModel().getValueAt(row, column).toString();
        
        column = 1;
        row = jTable1.getSelectedRow();
        String nazwa = jTable1.getModel().getValueAt(row, column).toString();
        
        column = 2;
        row = jTable1.getSelectedRow();
        String typ = jTable1.getModel().getValueAt(row, column).toString();
        
        column = 3;
        row = jTable1.getSelectedRow();
        String cena = jTable1.getModel().getValueAt(row, column).toString();
        
        String opcj = opcjBox.getSelectedItem().toString();
        
        ArrayList<String> materialy = new ArrayList<>();
        
        materialy.add(tkaninaBox.getSelectedItem().toString());
        
        materialy.add(plytaBox.getSelectedItem().toString());
        
        materialy.add(drewnoBox.getSelectedItem().toString());
        
        materialy.add(piankaBox.getSelectedItem().toString());
        
        materialy.add(okleinaBox.getSelectedItem().toString());
        
        System.out.println(materialy);
        
        
        koszyk.add(new dodanyProjekt(numer, nazwa, typ, cena, materialy, opcj));
        updateLiczbewKoszyku();
    }//GEN-LAST:event_dodajDoKoszykaButtonActionPerformed

    private void opcjBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcjBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcjBoxActionPerformed

    private void kategorie_comb_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategorie_comb_boxActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            for(int i=1; i<100; i++){
                model.removeRow(0);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        
        String kategoria = kategorie_comb_box.getSelectedItem().toString();
        
        switch(kategoria){
            case "Stoły": kategoria = "Stół";
            break;
            case "Krzesła": kategoria = "Krzesło";
            break;
            case "Fotele": kategoria = "Fotel";
            break;
            case "Łóżka": kategoria = "Łóżko";
            break;
            case "Sofy": kategoria = "Sofa";
            break;
            case "Biurka": kategoria = "Biurko";
            break;
            case "Szafy": kategoria = "Szafa";
            break;
            case "Komody": kategoria = "Komoda";
            break;
            case "Szafki nocne": kategoria = "Szafka nocna";
            break;
            case "Narożniki": kategoria = "Narożnik";
            break;
            case "Regały": kategoria = "Regał";
            break;
            case "Kredensy": kategoria = "Kredens";
            break;
            
        }
        
        if(kategoria.equals("Wszystkie")){
            DbWyswietlKatalog(kategoria, " -- ");
        }else{
            DbWyswietlKatalog(kategoria, " ");
        }
        
        
    }//GEN-LAST:event_kategorie_comb_boxActionPerformed

    private void kategorie_comb_boxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategorie_comb_boxMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategorie_comb_boxMousePressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        int column = 0;
        int row = jTable1.getSelectedRow();
        String numer = jTable1.getModel().getValueAt(row, column).toString();
        System.out.println(numer);
        
        DbOpcjonalneCzesci(numer);
        DbMateialy(numer);
        
    }//GEN-LAST:event_jTable1MousePressed

    private void tkaninaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkaninaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkaninaBoxActionPerformed

    private void plytaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plytaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plytaBoxActionPerformed

    private void drewnoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drewnoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drewnoBoxActionPerformed

    private void piankaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_piankaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_piankaBoxActionPerformed

    private void okleinaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okleinaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okleinaBoxActionPerformed

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
    private javax.swing.JButton dodajDoKoszykaButton;
    private javax.swing.JComboBox<String> drewnoBox;
    private javax.swing.JButton historiaZamowienButton;
    private javax.swing.JLabel ikona_wozka_sklepowego;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JComboBox<String> okleinaBox;
    private javax.swing.JComboBox<String> opcjBox;
    private javax.swing.JComboBox<String> piankaBox;
    private javax.swing.JComboBox<String> plytaBox;
    private javax.swing.JComboBox<String> tkaninaBox;
    private javax.swing.JButton wlasnyProjektButton;
    private javax.swing.JButton wylogujButton;
    private javax.swing.JLabel zalogowano_jako;
    // End of variables declaration//GEN-END:variables
}
