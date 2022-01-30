/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ekrany;

import java.io.File;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import Dodatkowe.*;
/**
 *
 * @author Maciek
 */
public class Ekran_projektu_klienta extends javax.swing.JFrame {

    int l_rodzai_materialow = 5;

    ArrayList<Object[]> tkanina; //id_rodz 1
    ArrayList<Object[]> plyta; //id_rodz 2
    ArrayList<Object[]> drewno; // id_rodz 3
    ArrayList<Object[]> pianka; // id_rodz 4
    ArrayList<Object[]> okleina; //id_rodz 5

    private void pobierzMaterialy() {
        tkanina = new ArrayList<>();
        plyta = new ArrayList<>();
        drewno = new ArrayList<>();
        pianka = new ArrayList<>();
        okleina = new ArrayList<>();

        Object[] zero = {0, ""};

        tkanina.add(zero);
        plyta.add(zero);
        drewno.add(zero);
        pianka.add(zero);
        okleina.add(zero);

        for(int i = 1; i <= l_rodzai_materialow; i++) {
            try{
                Connection con = DriverManager.getConnection(  
                "jdbc:mysql://localhost:3307/firma?serverTimezone=UTC","root","root");   
                Statement stmt = con.createStatement();
                String zapytanie = 
                        "select  material.Id_Materialu, material.Nazwa\n" +  // material.Id_Rodzaju_materialu, rodzaj_materialu.Nazwa AS Rodzaj
                        "from material\n" +
                        "join rodzaj_materialu on rodzaj_materialu.Id_Rodzaju_materialu = material.Id_Rodzaju_materialu \n" +
                        "where material.Id_Rodzaju_materialu = " + i + "; \n";
    
                ResultSet rs=stmt.executeQuery(zapytanie);
    


                while(rs.next()){
                    Object[] ob = {rs.getInt(1), rs.getString(2)};
                    if (i == 1) {
                        tkanina.add(ob);
                    } else if (i == 2) {
                        plyta.add(ob);
                    } else if (i == 3) {
                        drewno.add(ob);
                    } else if (i == 4) {
                        pianka.add(ob);
                    } else {
                        okleina.add(ob);
                    }
                }         
                con.close(); 
            }catch(Exception e){ System.out.println(e);}  
        }


       ArrayList<String> nazwa = new ArrayList<String>();

        for (int i = 0; i < tkanina.size(); i++) {
            nazwa.add(tkanina.get(i)[1].toString());
        }
        tkaninaObiciowaBox.setModel(new javax.swing.DefaultComboBoxModel<>(nazwa.toArray(new String[0])));

        nazwa = new ArrayList<String>();
        for (int i = 0; i < plyta.size(); i++) {
            nazwa.add(plyta.get(i)[1].toString());
        }
        plytaMeblowaBox.setModel(new javax.swing.DefaultComboBoxModel<>(nazwa.toArray(new String[0])));

        nazwa = new ArrayList<String>();
        for (int i = 0; i < drewno.size(); i++) {
            nazwa.add(drewno.get(i)[1].toString());
        }
        drewnoBox.setModel(new javax.swing.DefaultComboBoxModel<>(nazwa.toArray(new String[0])));

        nazwa = new ArrayList<String>();
        for (int i = 0; i < pianka.size(); i++) {
            nazwa.add(pianka.get(i)[1].toString());
        }
        piankaTapicerskaBox.setModel(new javax.swing.DefaultComboBoxModel<>(nazwa.toArray(new String[0])));

        nazwa = new ArrayList<String>();
        for (int i = 0; i < okleina.size(); i++) {
            nazwa.add(okleina.get(i)[1].toString());
        }
        okleinaBox.setModel(new javax.swing.DefaultComboBoxModel<>(nazwa.toArray(new String[0])));
    }



    ArrayList<ProjektKlienta> koszyk;
    ProjektKlienta projekt;

    EkranKlienta ekran_klienta;

    private void projectFormEnable(Boolean state) {
        typMeblaBox.setEnabled(state);

        wysokosc_mebla.setEnabled(state);
        szerkosc_mebla.setEnabled(state);
        dlugosc_mebla.setEnabled(state);


        tkaninaObiciowaBox.setEnabled(state);
        plytaMeblowaBox.setEnabled(state);
        drewnoBox.setEnabled(state);
        piankaTapicerskaBox.setEnabled(state);
        okleinaBox.setEnabled(state);
        laczeniaBox.setEnabled(state);
        rysunek_nazwa.setEnabled(state);
        liczba_sztuk_projektu.setEnabled(state);
        dodaj_rysunek_projektu.setEnabled(state);


        dodaj_projekt.setEnabled(state);
    }

    private void polproduktFormEnable(Boolean state) {
        polp_nazwa.setEnabled(state);
        szerokosc_polp.setEnabled(state);
        wysokosc_polp.setEnabled(state);
        dlugosc_polp.setEnabled(state);
        polp_rodzaj.setEnabled(state);
        dodaj_rysunek_polp.setEnabled(state);
        dodaj_polp_button.setEnabled(state);

        rysunek_polp_nazwa.setEnabled(state);
        liczba_sztuk_polp.setEnabled(state);
    }

    public void clearFrameContent() {
        typMeblaBox.setSelectedIndex(0);
        szerkosc_mebla.setText("");
        wysokosc_mebla.setText("");
        dlugosc_mebla.setText("");

        tkaninaObiciowaBox.setSelectedIndex(0);
        plytaMeblowaBox.setSelectedIndex(0);
        drewnoBox.setSelectedIndex(0);
        piankaTapicerskaBox.setSelectedIndex(0);
        okleinaBox.setSelectedIndex(0);
        laczeniaBox.setSelectedIndex(0);
        rysunek_nazwa.setText("");
        liczba_sztuk_projektu.setValue(0);

        polp_nazwa.setText("");
        szerokosc_polp.setText("");
        wysokosc_polp.setText("");
        dlugosc_polp.setText("");
        rysunek_polp_nazwa.setText("");
        liczba_sztuk_polp.setValue(0);


        dodaj_do_koszyka.setEnabled(false);
        odrzuc_projekt.setEnabled(false);
        polproduktFormEnable(false);
    }

    /**
     * Creates new form Ekran_projektu_klienta
     */
    public Ekran_projektu_klienta() {
        initComponents();
        pobierzMaterialy();
        polproduktFormEnable(false);
        odrzuc_projekt.setEnabled(false);
    }

    public void setKoszyk(ArrayList<ProjektKlienta> koszyk) {
        this.koszyk = koszyk;
    }

    public void setEkranKlienta(EkranKlienta ekran_klienta) {
        this.ekran_klienta = ekran_klienta;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        drewnoBox = new javax.swing.JComboBox<>();
        typMeblaBox = new javax.swing.JComboBox<>();
        tkaninaObiciowaBox = new javax.swing.JComboBox<>();
        okleinaBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        plytaMeblowaBox = new javax.swing.JComboBox<>();
        dodaj_rysunek_projektu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        laczeniaBox = new javax.swing.JComboBox<>();
        piankaTapicerskaBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        polp_nazwa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        polp_rodzaj = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dodaj_rysunek_polp = new javax.swing.JButton();
        dodaj_polp_button = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rysunek_polp_nazwa = new javax.swing.JTextField();
        powrotButton = new javax.swing.JButton();
        dlugosc_polp = new javax.swing.JTextField();
        szerokosc_polp = new javax.swing.JTextField();
        wysokosc_polp = new javax.swing.JTextField();
        szerkosc_mebla = new javax.swing.JTextField();
        wysokosc_mebla = new javax.swing.JTextField();
        dlugosc_mebla = new javax.swing.JTextField();
        dodaj_do_koszyka = new javax.swing.JButton();
        dodaj_projekt = new javax.swing.JButton();
        liczba_sztuk_polp = new javax.swing.JSpinner();
        message_label2 = new javax.swing.JLabel();
        rysunek_nazwa = new javax.swing.JTextField();
        liczba_sztuk_projektu = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        message_label1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        odrzuc_projekt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Dodawanie półproduktów");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 340, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, 540, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Rodzaj drewna");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 150, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Typ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 180, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Materiały:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 180, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Tkanina obiciowa");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 150, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Płyta meblowa");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 150, 30));
        jPanel2.add(drewnoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 220, 30));

        typMeblaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Stol", "Krzeslo", "Fotel", "Lozko", "Sofa", "Biurko", "Szafa", "Komoda", "Szafka nocna", "Naroznik", "Regal", "Kredens" }));
        typMeblaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typMeblaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(typMeblaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 220, 30));

        tkaninaObiciowaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","Flok", "Welwet", "Ekoskóra", "Szenil" }));
        tkaninaObiciowaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkaninaObiciowaBoxActionPerformed(evt);
            }
        });
        jPanel2.add(tkaninaObiciowaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 220, 30));
        jPanel2.add(okleinaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 220, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Okleina");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 150, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Pianka tapicerska");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 150, 30));
        jPanel2.add(plytaMeblowaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 220, 30));

        dodaj_rysunek_projektu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        dodaj_rysunek_projektu.setText("+");
        dodaj_rysunek_projektu.setHideActionText(true);
        dodaj_rysunek_projektu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dodaj_rysunek_projektu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodaj_rysunek_projektuActionPerformed(evt);
            }
        });
        jPanel2.add(dodaj_rysunek_projektu, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 50, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Liczba sztuk");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 100, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Łączenia");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 150, 30));

        laczeniaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ","Wkręty", "Klej", "Wciskowe", "Kołki", "Mimośrody i trzpienie", "Gwintowane" }));
        jPanel2.add(laczeniaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 220, 30));
        jPanel2.add(piankaTapicerskaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 220, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Wymiary gabarytowe [mm]");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 220, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setText("Dodawanie własnego projektu");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 350, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 540, 10));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Nazwa");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 100, 30));

        polp_nazwa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polp_nazwaActionPerformed(evt);
            }
        });
        jPanel2.add(polp_nazwa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 220, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Wymiary");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 100, 30));

        polp_rodzaj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stelaż", "Rama", "Front", "Dekory", "Rodzaj Klienta"
            + ""}));
polp_rodzaj.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        polp_rodzajActionPerformed(evt);
    }
    });
    jPanel2.add(polp_rodzaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, 220, 30));

    jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel15.setText("Rodzaj");
    jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 100, 30));

    jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel16.setText("Rysunek");
    jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 590, 100, 30));

    dodaj_rysunek_polp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    dodaj_rysunek_polp.setText("+");
    dodaj_rysunek_polp.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dodaj_rysunek_polpActionPerformed(evt);
        }
    });
    jPanel2.add(dodaj_rysunek_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 590, 50, 30));

    dodaj_polp_button.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    dodaj_polp_button.setText("Dodaj półprodukt");
    dodaj_polp_button.setEnabled(false);
    dodaj_polp_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dodaj_polp_buttonActionPerformed(evt);
        }
    });
    jPanel2.add(dodaj_polp_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 150, 30));

    jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel17.setText("Rysunek");
    jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 150, 30));

    jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel18.setText("Liczba sztuk");
    jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 150, 30));

    rysunek_polp_nazwa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            rysunek_polp_nazwaActionPerformed(evt);
        }
    });
    jPanel2.add(rysunek_polp_nazwa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 590, 160, 30));

    powrotButton.setText("Powrot");
    powrotButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            powrotButtonActionPerformed(evt);
        }
    });
    jPanel2.add(powrotButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 90, 30));

    dlugosc_polp.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dlugosc_polpActionPerformed(evt);
        }
    });
    jPanel2.add(dlugosc_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 520, 80, 30));

    szerokosc_polp.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            szerokosc_polpActionPerformed(evt);
        }
    });
    jPanel2.add(szerokosc_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, 80, 30));

    wysokosc_polp.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            wysokosc_polpActionPerformed(evt);
        }
    });
    jPanel2.add(wysokosc_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, 80, 30));

    szerkosc_mebla.setToolTipText("");
    szerkosc_mebla.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            szerkosc_meblaActionPerformed(evt);
        }
    });
    jPanel2.add(szerkosc_mebla, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 80, 30));

    wysokosc_mebla.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            wysokosc_meblaActionPerformed(evt);
        }
    });
    jPanel2.add(wysokosc_mebla, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 80, 30));

    dlugosc_mebla.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dlugosc_meblaActionPerformed(evt);
        }
    });
    jPanel2.add(dlugosc_mebla, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 80, 30));

    dodaj_do_koszyka.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    dodaj_do_koszyka.setText("Dodaj do koszyka");
    dodaj_do_koszyka.setEnabled(false);
    dodaj_do_koszyka.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dodaj_do_koszykaActionPerformed(evt);
        }
    });
    jPanel2.add(dodaj_do_koszyka, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 710, 210, 40));

    dodaj_projekt.setText("Dodaj projekt");
    dodaj_projekt.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dodaj_projektActionPerformed(evt);
        }
    });
    jPanel2.add(dodaj_projekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 200, 30));

    liczba_sztuk_polp.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
    liczba_sztuk_polp.setEditor(new javax.swing.JSpinner.DefaultEditor(liczba_sztuk_polp));
    jPanel2.add(liczba_sztuk_polp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 620, 160, 30));

    message_label2.setForeground(new java.awt.Color(200, 0, 0));
    message_label2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jPanel2.add(message_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 240, 30));

    rysunek_nazwa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            rysunek_nazwaActionPerformed(evt);
        }
    });
    jPanel2.add(rysunek_nazwa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 160, 30));

    liczba_sztuk_projektu.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
    liczba_sztuk_projektu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    liczba_sztuk_projektu.setDoubleBuffered(true);
    liczba_sztuk_projektu.setEditor(new javax.swing.JSpinner.DefaultEditor(liczba_sztuk_projektu));
    jPanel2.add(liczba_sztuk_projektu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 160, 30));

    jLabel19.setText("długość:");
    jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, 20));

    jLabel20.setText("szerokość:");
    jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, 20));

    jLabel21.setText("wysokość:");
    jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, 20));

    jLabel22.setText("długość:");
    jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, -1, 20));

    jLabel23.setText("wysokość:");
    jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, -1, 20));

    jLabel24.setText("szerokość:");
    jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 60, 20));

    message_label1.setForeground(new java.awt.Color(200, 0, 0));
    message_label1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jPanel2.add(message_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 240, 30));
    jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 540, 10));

    odrzuc_projekt.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
    odrzuc_projekt.setText("Odrzuć");
    odrzuc_projekt.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            odrzuc_projektActionPerformed(evt);
        }
    });
    jPanel2.add(odrzuc_projekt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 710, 180, 40));

    jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 560, 770));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dodaj_rysunek_projektuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodaj_rysunek_projektuActionPerformed
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.showOpenDialog(null);
        File file = file_chooser.getSelectedFile();
        rysunek_nazwa.setText(file.getName());
    }//GEN-LAST:event_dodaj_rysunek_projektuActionPerformed

    private void polp_nazwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polp_nazwaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polp_nazwaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void dodaj_rysunek_polpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodaj_rysunek_polpActionPerformed
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.showOpenDialog(null);
        File file = file_chooser.getSelectedFile();
        rysunek_polp_nazwa.setText(file.getName());
    }//GEN-LAST:event_dodaj_rysunek_polpActionPerformed

    private void typMeblaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typMeblaBoxActionPerformed
        if (evt.getSource()==typMeblaBox){
            if(typMeblaBox.getSelectedItem()=="Stol"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(true);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(false);


                tkaninaObiciowaBox.setSelectedIndex(0);
                plytaMeblowaBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Krzeslo"){
                tkaninaObiciowaBox.setEnabled(true);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(true);
                piankaTapicerskaBox.setEnabled(true);
                okleinaBox.setEnabled(false);


                plytaMeblowaBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);
            }else if(typMeblaBox.getSelectedItem()=="Fotel"){
                tkaninaObiciowaBox.setEnabled(true);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(true);
                okleinaBox.setEnabled(false);

                plytaMeblowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Lozko"){
                tkaninaObiciowaBox.setEnabled(true);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(true);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(false);

                plytaMeblowaBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Sofa"){
                tkaninaObiciowaBox.setEnabled(true);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(true);
                okleinaBox.setEnabled(false);

                plytaMeblowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Biurko"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(true);
                drewnoBox.setEnabled(true);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(true);

                tkaninaObiciowaBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Szafa"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(true);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(true);

                tkaninaObiciowaBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Komoda"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(true);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(true);

                tkaninaObiciowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Szafka nocna"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(true);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(true);

                tkaninaObiciowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);
                
            }else if(typMeblaBox.getSelectedItem()=="Naroznik"){
                tkaninaObiciowaBox.setEnabled(true);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(true);
                okleinaBox.setEnabled(false);

                plytaMeblowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);

            }else if(typMeblaBox.getSelectedItem()=="Regal"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(true);
                drewnoBox.setEnabled(false);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(true);

                tkaninaObiciowaBox.setSelectedIndex(0);
                drewnoBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);
                
            }else if(typMeblaBox.getSelectedItem()=="Kredens"){
                tkaninaObiciowaBox.setEnabled(false);
                plytaMeblowaBox.setEnabled(false);
                drewnoBox.setEnabled(true);
                piankaTapicerskaBox.setEnabled(false);
                okleinaBox.setEnabled(false);

                tkaninaObiciowaBox.setSelectedIndex(0);
                plytaMeblowaBox.setSelectedIndex(0);
                piankaTapicerskaBox.setSelectedIndex(0);
                okleinaBox.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_typMeblaBoxActionPerformed

    private void dodaj_polp_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodaj_polp_buttonActionPerformed
        if (polp_nazwa.getText().equals("") || szerokosc_polp.getText().equals("") || wysokosc_polp.getText().equals("") ||
            dlugosc_polp.getText().equals("") || liczba_sztuk_polp.getValue() == "0") {
            message_label2.setText("Wprowadz wszystkie wymagane dane półproduktu");
        } else {
            projekt.dodajPolprodukt((int)liczba_sztuk_polp.getValue(), koszyk.size(), polp_rodzaj.getSelectedIndex()+1, polp_nazwa.getText(), szerokosc_polp.getText(), wysokosc_polp.getText(), dlugosc_polp.getText(), rysunek_polp_nazwa.getText());
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(this, "Półprodukt(y) zostaly dodane do zamówienia", "Potwierdzenie", JOptionPane.INFORMATION_MESSAGE);
            polp_nazwa.setText("");
            szerokosc_polp.setText("");
            wysokosc_polp.setText("");
            dlugosc_polp.setText("");
            rysunek_polp_nazwa.setText("");
            liczba_sztuk_polp.setValue(0);
        }
    }//GEN-LAST:event_dodaj_polp_buttonActionPerformed

    private void rysunek_polp_nazwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rysunek_polp_nazwaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rysunek_polp_nazwaActionPerformed

    private void polp_rodzajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polp_rodzajActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polp_rodzajActionPerformed
    private void powrotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powrotButtonActionPerformed
        ekran_klienta.setVisible(true);
        ekran_klienta.setKoszykProjektowKlienta(koszyk);
        this.setVisible(false);

    }//GEN-LAST:event_powrotButtonActionPerformed

    private void dlugosc_polpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlugosc_polpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dlugosc_polpActionPerformed

    private void szerokosc_polpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_szerokosc_polpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_szerokosc_polpActionPerformed

    private void wysokosc_polpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wysokosc_polpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wysokosc_polpActionPerformed

    private void szerkosc_meblaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_szerkosc_meblaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_szerkosc_meblaActionPerformed

    private void wysokosc_meblaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wysokosc_meblaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wysokosc_meblaActionPerformed

    private void dlugosc_meblaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlugosc_meblaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dlugosc_meblaActionPerformed

    private void dodaj_do_koszykaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodaj_do_koszykaActionPerformed
            try {
                koszyk.add(projekt);
                JOptionPane optionPane = new JOptionPane();
                optionPane.showMessageDialog(this, "Projekt został dodany do koszyka", "Potwierdzenie", JOptionPane.INFORMATION_MESSAGE);
                ekran_klienta.updateLiczbewKoszyku();
                clearFrameContent();
                projectFormEnable(true);
                polproduktFormEnable(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }//GEN-LAST:event_dodaj_do_koszykaActionPerformed

    private void dodaj_projektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodaj_projektActionPerformed
        if (typMeblaBox.getSelectedIndex() == 0 || rysunek_nazwa.getText().equals("") || (Integer)liczba_sztuk_projektu.getValue() == 0 ||
            wysokosc_mebla.getText().equals("") || szerkosc_mebla.getText().equals("") || dlugosc_mebla.getText().equals("") || laczeniaBox.getSelectedIndex() == 0) {
            System.out.println("Nieprawidłowe dane");
            message_label1.setText("Wprowadz wszystkie wymagane dane");
        } else {
            try {
                message_label1.setText("");
                int id_tkanina = (int)tkanina.get(tkaninaObiciowaBox.getSelectedIndex())[0];
                int id_plyta = (int)plyta.get(plytaMeblowaBox.getSelectedIndex())[0];
                int id_drewna = (int)drewno.get(drewnoBox.getSelectedIndex())[0];
                int id_pianka = (int)pianka.get(piankaTapicerskaBox.getSelectedIndex())[0]; 
                int id_okleina = (int)okleina.get(okleinaBox.getSelectedIndex())[0]; 



                projekt = new ProjektKlienta((int)liczba_sztuk_projektu.getValue() ,koszyk.size()+1, typMeblaBox.getSelectedIndex(), laczeniaBox.getSelectedIndex(), szerkosc_mebla.getText(), 
                wysokosc_mebla.getText(), dlugosc_mebla.getText(), rysunek_nazwa.getText(), id_tkanina, id_plyta,
                id_drewna, id_pianka, id_okleina);

                polproduktFormEnable(true);
                dodaj_do_koszyka.setEnabled(true);
                odrzuc_projekt.setEnabled(true);
                projectFormEnable(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        // projekt = new ProjektKlienta();
    }//GEN-LAST:event_dodaj_projektActionPerformed

    private void rysunek_nazwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rysunek_nazwaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rysunek_nazwaActionPerformed

    private void tkaninaObiciowaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkaninaObiciowaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkaninaObiciowaBoxActionPerformed

    private void odrzuc_projektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odrzuc_projektActionPerformed
        clearFrameContent();
        projectFormEnable(true);
        polproduktFormEnable(false);
        dodaj_do_koszyka.setEnabled(false);
        odrzuc_projekt.setEnabled(false);
    }//GEN-LAST:event_odrzuc_projektActionPerformed

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
            java.util.logging.Logger.getLogger(Ekran_projektu_klienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ekran_projektu_klienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ekran_projektu_klienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ekran_projektu_klienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ekran_projektu_klienta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dlugosc_mebla;
    private javax.swing.JTextField dlugosc_polp;
    private javax.swing.JButton dodaj_do_koszyka;
    private javax.swing.JButton dodaj_polp_button;
    private javax.swing.JButton dodaj_projekt;
    private javax.swing.JButton dodaj_rysunek_polp;
    private javax.swing.JButton dodaj_rysunek_projektu;
    private javax.swing.JComboBox<String> drewnoBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> laczeniaBox;
    private javax.swing.JSpinner liczba_sztuk_polp;
    private javax.swing.JSpinner liczba_sztuk_projektu;
    private javax.swing.JLabel message_label1;
    private javax.swing.JLabel message_label2;
    private javax.swing.JButton odrzuc_projekt;
    private javax.swing.JComboBox<String> okleinaBox;
    private javax.swing.JComboBox<String> piankaTapicerskaBox;
    private javax.swing.JComboBox<String> plytaMeblowaBox;
    private javax.swing.JTextField polp_nazwa;
    private javax.swing.JComboBox<String> polp_rodzaj;
    private javax.swing.JButton powrotButton;
    private javax.swing.JTextField rysunek_nazwa;
    private javax.swing.JTextField rysunek_polp_nazwa;
    private javax.swing.JTextField szerkosc_mebla;
    private javax.swing.JTextField szerokosc_polp;
    private javax.swing.JComboBox<String> tkaninaObiciowaBox;
    private javax.swing.JComboBox<String> typMeblaBox;
    private javax.swing.JTextField wysokosc_mebla;
    private javax.swing.JTextField wysokosc_polp;
    // End of variables declaration//GEN-END:variables
}
