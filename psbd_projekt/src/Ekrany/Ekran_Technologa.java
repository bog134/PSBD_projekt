package Ekrany;
package SQL.Ekran_Technologa;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Ekran_Technologa extends javax.swing.JFrame{

    private String id_techn="2";
    private String id;
    private ArrayList<String[]> lista_projektow_dane;
    private ArrayList<String[]> lista_materialow;
    /**
     * Creates new form Ekran_Technologa
     */
    public Ekran_Technologa() {
        initComponents();
        lista_projektow_dane = SQL.Ekran_Technologa.wyswietlenie_listy_projektow_klienta();
        wyswietlenie_listy_projektow_klienta();
    }
    
    private void wyswietlenie_listy_projektow_klienta()
    {     
        DefaultTableModel model = (DefaultTableModel) lista_projektow_jTabel2.getModel();
        
        while(model.getRowCount()!=0) {
        model.removeRow(model.getRowCount()-1);
        }
        
        for (int i=0; i<lista_projektow_dane.size(); i++)
        {
            Object[] data = new Object[lista_projektow_dane.get(0).length];
            for(int j=0; j<lista_projektow_dane.get(0).length; j++)
            {
                data[j]=lista_projektow_dane.get(i)[j];
            }
            model.addRow(data);            
        }
        robociznajTextField1.setText("");
        robociznajTextField1.setEnabled(false);
        materialyjTextField2.setText("");
        materialyjTextField2.setEnabled(false);
        marzajTextField3.setText("");
        marzajTextField3.setEnabled(false);
        

    }
    private void modyfikacja_listy_projektow_w_zaleznosci_od_filtra()
    {
        
        String data1= data1_jTextField2.getText();
        String data2= data2_jTextField2.getText();
        String parametr= parametr_jTextField4.getText();

        lista_projektow_dane = SQL.Ekran_Technologa.modyfikacja_listy_projektow_w_zaleznosci_od_filtra(data1,data2,parametr);
        wyswietlenie_listy_projektow_klienta();
    } 
    private void wyswietlenie_szczegolow_projektu()
    {
        ArrayList<String[]> lista_szczegolow_projektu=SQL.Ekran_Technologa.wyswietlenie_szczegolow_projektu(id);
        typ_jTextField1.setText(lista_szczegolow_projektu.get(0)[0]);
        wymiary_jTextField5.setText(lista_szczegolow_projektu.get(0)[1]);
        laczenia_jTextField6.setText(lista_szczegolow_projektu.get(0)[2]);
        ilosc_jTextField7.setText(lista_szczegolow_projektu.get(0)[3]);
        rysunek_jTextField8.setText(lista_szczegolow_projektu.get(0)[4]);
        
        definicje_zadan_tabela.setEnabled(true);
        robociznajTextField1.setEnabled(true);
        materialyjTextField2.setEnabled(true);
        marzajTextField3.setEnabled(true);   
        
    }
    private void wyswietlenie_projektow_polproduktow()
    {
        polprodukty_jComboBox1.removeAllItems();
        ArrayList<String[]> lista_polproduktow=SQL.Ekran_Technologa.wyswietlenie_projektow_polproduktow(id);
        for (int i=0; i<lista_polproduktow.size();i++)
        {
            polprodukty_jComboBox1.addItem(lista_polproduktow.get(i)[0]); 
        }
        
    }
    private void wyswietlenie_materialow()
    {
        lista_materialow = SQL.Ekran_Technologa.wyswietlenie_materialow(id);
        
        DefaultTableModel model = (DefaultTableModel) tabela_materialy.getModel();
        for (int i=0; i<lista_materialow.size(); i++)
        {
            Object[] data = new Object[lista_materialow.get(0).length+1];
            for(int j=0; j<lista_materialow.get(0).length-1; j++)
            {
                data[j]=lista_materialow.get(i)[j+1];
            }
            model.addRow(data);            
        }
         
    }
    private void wyświetlenie_szczegółów_wybranego_półproduktu(String nazwa)
    {
        ArrayList<String[]> lista_szczegolow_polproduktu = SQL.Ekran_Technologa.wyświetlenie_szczegółów_wybranego_półproduktu(id, nazwa);
        nazwa_jTextField8.setText(lista_szczegolow_polproduktu.get(0)[0]);
        rodzajjTextField9.setText(lista_szczegolow_polproduktu.get(0)[1]);
        iloscPolproduktujTextField11.setText(lista_szczegolow_polproduktu.get(0)[2]);
        wymiaryPolproduktujTextField10.setText(lista_szczegolow_polproduktu.get(0)[3]);
        rysunekPolproduktu_jTextField9.setText(lista_szczegolow_polproduktu.get(0)[4]);
        
    }
    private void dodawanie_definicji_zadań()
    {        
        DefaultTableModel model = (DefaultTableModel) definicje_zadan_tabela.getModel();
        Object[] data = new Object[2];
        model.addRow(data);            
    }    
    class Keychecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent evt) {

        if(evt.getKeyCode() == KeyEvent.VK_DOWN) dodawanie_definicji_zadań();
        if(evt.getKeyCode() == KeyEvent.VK_UP) {
            DefaultTableModel model = (DefaultTableModel) definicje_zadan_tabela.getModel();
            model.removeRow(model.getRowCount()-1);
        }
    }

}
    private String obliczenie_materialow() throws Exception
    {
        float koszt=(float) 0.0;
        DefaultTableModel model = (DefaultTableModel) tabela_materialy.getModel();
        for(int i=0; i<lista_materialow.size(); i++)  {
            ArrayList<String[]> lista_cena=SQL.Ekran_Technologa.pobranie_ceny_danego_materialu(lista_materialow.get(i)[0]);
            float cena=Float.parseFloat(lista_cena.get(0)[0]);            
            String ilosc= String.valueOf(model.getValueAt(i, model.getColumnCount()-1));
            koszt+=cena*Float.parseFloat(ilosc);
            if (ilosc==null) throw new Exception("Nie wypelniono ilosci materialow");
        }
        return String.valueOf(koszt);
    }
    private String obliczenie_robocizny() throws Exception
    {
        ArrayList<String[]> lista_cena=SQL.Ekran_Technologa.pobranie_ceny_osobogodziny();
        float cena=Float.parseFloat(lista_cena.get(0)[0]);
        float koszt=(float) 0.0;
        
        DefaultTableModel model = (DefaultTableModel)definicje_zadan_tabela.getModel();
        if (model.getRowCount()==0) throw new Exception("Nie wypelniono definicji zadan");
        for(int i=0; i<model.getRowCount(); i++)  {
            koszt += Integer.parseInt(String.valueOf(model.getValueAt(i, 1)))*cena;
        }
        return String.valueOf(koszt);
    }
    
    private void zaaktualizowanie_ilości_materialow() throws Exception
    {
        DefaultTableModel model = (DefaultTableModel) tabela_materialy.getModel();
        for(int i=0; i<lista_materialow.size(); i++)  {
            String ilosc= String.valueOf(model.getValueAt(i, model.getColumnCount()-1));
            if (ilosc==null) throw new Exception("Nie wypelniono ilosci materialow");
            SQL.Ekran_Technologa.zaaktualizowanie_ilości_materialow(ilosc, lista_materialow.get(i)[0]);
        }
        
    }
    private void utworzenie_definicji_zadań() throws Exception
    {
        DefaultTableModel model = (DefaultTableModel)definicje_zadan_tabela.getModel();
        if (model.getRowCount()==0) throw new Exception("Nie wypelniono definicji zadan");
        for(int i=0; i<model.getRowCount(); i++)  {
            SQL.Ekran_Technologa.utworzenie_definicji_zadań(id,String.valueOf(model.getValueAt(i, 0)),String.valueOf(model.getValueAt(i, 1)));
        }
    }
    private void utworzenie_ceny () throws Exception
    {
        String robocizna=robociznajTextField1.getText().toString();
        String materialy=materialyjTextField2.getText().toString();
        String marza=marzajTextField3.getText().toString();
        
        if ("".equals(robocizna) || "".equals(materialy) || "".equals(marza)) throw new Exception("Nie wypelniono ceny");
        SQL.Ekran_Technologa.utworzenie_ceny(id_techn, id,robocizna,materialy,marza);
    
    }
    private void czyszczenie()
    {
        typ_jTextField1.setText("");
        wymiary_jTextField5.setText("");
        laczenia_jTextField6.setText("");
        ilosc_jTextField7.setText("");
        rysunek_jTextField8.setText("");
        try {
        polprodukty_jComboBox1.removeAllItems();
        } catch(Exception e){};
        
        DefaultTableModel model = (DefaultTableModel) tabela_materialy.getModel();
        while(model.getRowCount()!=0) {
            model.removeRow(model.getRowCount()-1);
        }
        
        nazwa_jTextField8.setText("");
        rodzajjTextField9.setText("");
        iloscPolproduktujTextField11.setText("");
        wymiaryPolproduktujTextField10.setText("");
        rysunekPolproduktu_jTextField9.setText("");
        
        model = (DefaultTableModel)definicje_zadan_tabela.getModel();
        while(model.getRowCount()!=0) {
            model.removeRow(model.getRowCount()-1);
        }
        definicje_zadan_tabela.setEnabled(false);
        
        robociznajTextField1.setText("");
        robociznajTextField1.setEnabled(false);
        materialyjTextField2.setText("");
        materialyjTextField2.setEnabled(false);
        marzajTextField3.setText("");
        marzajTextField3.setEnabled(false);
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
        lista_projektow = new javax.swing.JScrollPane();
        lista_projektow_jTabel2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        data1_jTextField2 = new javax.swing.JTextField();
        data2_jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        parametr_jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Szukaj_przycisk = new javax.swing.JButton();
        pokaz_wszystko_Szukaj_przycisk1 = new javax.swing.JButton();
        dane_użytkownika = new javax.swing.JPanel();
        zalogowano_jako = new javax.swing.JLabel();
        obraz_uzytkownika_label = new javax.swing.JLabel();
        OdrzucButton = new javax.swing.JButton();
        wylogujButton1 = new javax.swing.JButton();
        ZaakceptujButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela_materialy = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nazwa_jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rodzajjTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        wymiaryPolproduktujTextField10 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        iloscPolproduktujTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        rysunekPolproduktu_jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        definicje_zadan_tabela = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        robociznajTextField1 = new javax.swing.JTextField();
        materialyjTextField2 = new javax.swing.JTextField();
        marzajTextField3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        typ_jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        wymiary_jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        laczenia_jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ilosc_jTextField7 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rysunek_jTextField8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        polprodukty_jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Ustal_cenejButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(200, 200, 200));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista Projektów");
        jLabel1.setAutoscrolls(true);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lista_projektow.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        lista_projektow_jTabel2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numer", "Data złożenia", "Typ mebla"
            }
        ));
        lista_projektow_jTabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_projektow_jTabel2MouseClicked(evt);
            }
        });
        lista_projektow.setViewportView(lista_projektow_jTabel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        data1_jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        data1_jTextField2.setText("Od");
        data1_jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data1_jTextField2ActionPerformed(evt);
            }
        });

        data2_jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        data2_jTextField2.setText("Do");
        data2_jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data2_jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("-");

        parametr_jTextField4.setText("Nazwa zawiera");
        parametr_jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametr_jTextField4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Data:");

        Szukaj_przycisk.setText("Szukaj");
        Szukaj_przycisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Szukaj_przyciskActionPerformed(evt);
            }
        });

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
                            .addComponent(parametr_jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(data1_jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(data2_jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Szukaj_przycisk, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Szukaj_przycisk, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(data1_jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(data2_jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parametr_jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pokaz_wszystko_Szukaj_przycisk1.setText("Pokaż wszystko");
        pokaz_wszystko_Szukaj_przycisk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokaz_wszystko_Szukaj_przycisk1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lista_projektow, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pokaz_wszystko_Szukaj_przycisk1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lista_projektow, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pokaz_wszystko_Szukaj_przycisk1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 570));

        zalogowano_jako.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zalogowano_jako.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zalogowano_jako.setText("Technolog");

        obraz_uzytkownika_label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        obraz_uzytkownika_label.setPreferredSize(new java.awt.Dimension(100, 16));
        javax.swing.ImageIcon icon;

        try {
            icon = new javax.swing.ImageIcon(".\\src\\Icons\\profile_pic.jpg");
            java.awt.Image im1 = icon.getImage();
            //    java.awt.Image scaled_im = im1.getScaledInstance(obraz_uzytkownika_label.getSize().width, obraz_uzytkownika_label.getSize().height, java.awt.Image.SCALE_SMOOTH);
            java.awt.Image scaled_im = im1.getScaledInstance(88, 88, java.awt.Image.SCALE_SMOOTH);
            icon = new javax.swing.ImageIcon(scaled_im);
            obraz_uzytkownika_label.setIcon(icon);
        } catch (java.lang.Exception e) {
            java.lang.System.out.println("blad ladowania ikony");
        }

        OdrzucButton.setText("Odrzuć");
        OdrzucButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odrzucButtonActionPerformed(evt);
            }
        });

        wylogujButton1.setText("Wyloguj");
        wylogujButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wylogujButton1ActionPerformed(evt);
            }
        });

        ZaakceptujButton2.setText("Zaakceptuj");
        ZaakceptujButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZaakceptujButton2zaakceptujButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dane_użytkownikaLayout = new javax.swing.GroupLayout(dane_użytkownika);
        dane_użytkownika.setLayout(dane_użytkownikaLayout);
        dane_użytkownikaLayout.setHorizontalGroup(
            dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dane_użytkownikaLayout.createSequentialGroup()
                .addGroup(dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ZaakceptujButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OdrzucButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zalogowano_jako, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dane_użytkownikaLayout.createSequentialGroup()
                        .addComponent(wylogujButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(obraz_uzytkownika_label, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        dane_użytkownikaLayout.setVerticalGroup(
            dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dane_użytkownikaLayout.createSequentialGroup()
                .addGroup(dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ZaakceptujButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zalogowano_jako, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dane_użytkownikaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OdrzucButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wylogujButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(dane_użytkownikaLayout.createSequentialGroup()
                .addComponent(obraz_uzytkownika_label, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(dane_użytkownika, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 350, 90));

        jPanel5.setBackground(new java.awt.Color(200, 200, 200));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Materiały:");
        jLabel16.setAutoscrolls(true);
        jLabel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel16.setMaximumSize(new java.awt.Dimension(300, 18));
        jLabel16.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel16.setOpaque(true);
        jLabel16.setPreferredSize(new java.awt.Dimension(200, 18));

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabela_materialy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nazwa", "Rodzaj", "Wzór", "Klasa", "Ilość"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabela_materialy);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 340, 210));

        jPanel6.setBackground(new java.awt.Color(200, 200, 200));

        jPanel7.setBackground(new java.awt.Color(220, 220, 220));
        jPanel7.setLayout(new java.awt.GridLayout(5, 2));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Nazwa");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel13);

        nazwa_jTextField8.setEditable(false);
        nazwa_jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nazwa_jTextField8ActionPerformed(evt);
            }
        });
        jPanel7.add(nazwa_jTextField8);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Rodzaj");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel11);

        rodzajjTextField9.setEditable(false);
        jPanel7.add(rodzajjTextField9);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Wymiary gabarytowe");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel12);

        wymiaryPolproduktujTextField10.setEditable(false);
        jPanel7.add(wymiaryPolproduktujTextField10);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ilość");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel14);

        iloscPolproduktujTextField11.setEditable(false);
        jPanel7.add(iloscPolproduktujTextField11);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Rysunek");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel22);

        rysunekPolproduktu_jTextField9.setEditable(false);
        jPanel7.add(rysunekPolproduktu_jTextField9);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Szczegóły Wybranego Półproduktu");
        jLabel17.setAutoscrolls(true);
        jLabel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel17.setMaximumSize(new java.awt.Dimension(300, 18));
        jLabel17.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel17.setOpaque(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, -1, 180));

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        definicje_zadan_tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Opis", "Czas wykonania"
            }
        ));
        jScrollPane4.setViewportView(definicje_zadan_tabela);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Definicja zadań");
        jLabel23.setAutoscrolls(true);
        jLabel23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel23.setMaximumSize(new java.awt.Dimension(300, 18));
        jLabel23.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel23.setOpaque(true);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 520, 340, 180));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new java.awt.GridLayout(2, 3));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("robocizna");
        jLabel19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(jLabel19);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("materiały");
        jLabel20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(jLabel20);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("marża");
        jLabel18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.add(jLabel18);
        jPanel9.add(robociznajTextField1);
        jPanel9.add(materialyjTextField2);
        jPanel9.add(marzajTextField3);

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 740, 330, 60));

        jPanel4.setBackground(new java.awt.Color(200, 200, 200));

        jPanel2.setBackground(new java.awt.Color(220, 220, 220));
        jPanel2.setLayout(new java.awt.GridLayout(6, 2));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Typ");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel5);

        typ_jTextField1.setEditable(false);
        typ_jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typ_jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(typ_jTextField1);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Wymiary gabatytowe");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel6);

        wymiary_jTextField5.setEditable(false);
        jPanel2.add(wymiary_jTextField5);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Łączenia");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel7);

        laczenia_jTextField6.setEditable(false);
        jPanel2.add(laczenia_jTextField6);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ilość");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel9);

        ilosc_jTextField7.setEditable(false);
        jPanel2.add(ilosc_jTextField7);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Rysunek");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel15);

        rysunek_jTextField8.setEditable(false);
        jPanel2.add(rysunek_jTextField8);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Półprodukty ");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jLabel8);

        polprodukty_jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polprodukty_jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(polprodukty_jComboBox1);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Szczegóły Projektu");
        jLabel10.setAutoscrolls(true);
        jLabel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel10.setMaximumSize(new java.awt.Dimension(300, 18));
        jLabel10.setMinimumSize(new java.awt.Dimension(200, 30));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(200, 18));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, -1, 220));

        Ustal_cenejButton1.setText("Ustal cenę");
        Ustal_cenejButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ustal_cenejButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(Ustal_cenejButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 710, 330, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void data1_jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data1_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_data1_jTextField2ActionPerformed

    private void data2_jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data2_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_data2_jTextField2ActionPerformed

    private void parametr_jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametr_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parametr_jTextField4ActionPerformed

    private void Szukaj_przyciskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Szukaj_przyciskActionPerformed
        modyfikacja_listy_projektow_w_zaleznosci_od_filtra();        
    }//GEN-LAST:event_Szukaj_przyciskActionPerformed

    private void odrzucButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odrzucButtonActionPerformed
        try {
            if(id==null) throw new Exception("Nie wybrano projektu");
            czyszczenie();
            lista_projektow_dane = SQL.Ekran_Technologa.wyswietlenie_listy_projektow_klienta();
            SQL.Ekran_Technologa.odrzucenie_zamowienia_na_meble(id);
            id=null;
            lista_projektow_dane = SQL.Ekran_Technologa.wyswietlenie_listy_projektow_klienta();
            wyswietlenie_listy_projektow_klienta();
        } catch (Exception e) { JOptionPane.showMessageDialog(null,e.getMessage(),"", JOptionPane.INFORMATION_MESSAGE);}
    }//GEN-LAST:event_odrzucButtonActionPerformed

    private void polprodukty_jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polprodukty_jComboBox1ActionPerformed
        wyświetlenie_szczegółów_wybranego_półproduktu(polprodukty_jComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_polprodukty_jComboBox1ActionPerformed

    private void typ_jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typ_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typ_jTextField1ActionPerformed

    private void nazwa_jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nazwa_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nazwa_jTextField8ActionPerformed

    private void wylogujButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wylogujButton1ActionPerformed
        czyszczenie(); 
        id=null;
        new Ekran_glowny().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_wylogujButton1ActionPerformed

    private void ZaakceptujButton2zaakceptujButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZaakceptujButton2zaakceptujButtonActionPerformed
        try {
        zaaktualizowanie_ilości_materialow();
        utworzenie_definicji_zadań();
        utworzenie_ceny();
        SQL.Ekran_Technologa.zaakceptowanie_zamowienia_na_meble(id);
        czyszczenie();
        id=null;
        lista_projektow_dane = SQL.Ekran_Technologa.wyswietlenie_listy_projektow_klienta();
        wyswietlenie_listy_projektow_klienta();
        } catch (Exception e) {
            if(e.getMessage()==null)
                JOptionPane.showMessageDialog(null, "Nie wypelniono wszystkich pol","", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, e.getMessage(),"Tytul", JOptionPane.INFORMATION_MESSAGE);
        }           
    }//GEN-LAST:event_ZaakceptujButton2zaakceptujButtonActionPerformed

    private void lista_projektow_jTabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_projektow_jTabel2MouseClicked
        
        int row = lista_projektow_jTabel2.getSelectedRow();
        id = lista_projektow_jTabel2.getModel().getValueAt(row,0).toString();      
        wyswietlenie_szczegolow_projektu();
        wyswietlenie_projektow_polproduktow();
        wyswietlenie_materialow();
        dodawanie_definicji_zadań();
        definicje_zadan_tabela.addKeyListener(new Keychecker());
    }//GEN-LAST:event_lista_projektow_jTabel2MouseClicked

    private void pokaz_wszystko_Szukaj_przycisk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokaz_wszystko_Szukaj_przycisk1ActionPerformed
        lista_projektow_dane = SQL.Ekran_Technologa.wyswietlenie_listy_projektow_klienta();
        wyswietlenie_listy_projektow_klienta();
    }//GEN-LAST:event_pokaz_wszystko_Szukaj_przycisk1ActionPerformed

    private void Ustal_cenejButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ustal_cenejButton1ActionPerformed
        try {
            robociznajTextField1.setText(obliczenie_robocizny());
            materialyjTextField2.setText(obliczenie_materialow());
        } catch (Exception ex) {
            if(ex.getMessage()==null)
                JOptionPane.showMessageDialog(null, "Nie wypelniono wszystkich pol","", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Tytul", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_Ustal_cenejButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Ekran_Technologa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ekran_Technologa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ekran_Technologa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ekran_Technologa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ekran_Technologa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OdrzucButton;
    private javax.swing.JButton Szukaj_przycisk;
    private javax.swing.JButton Ustal_cenejButton1;
    private javax.swing.JButton ZaakceptujButton2;
    private javax.swing.JPanel dane_użytkownika;
    private javax.swing.JTextField data1_jTextField2;
    private javax.swing.JTextField data2_jTextField2;
    private javax.swing.JTable definicje_zadan_tabela;
    private javax.swing.JTextField iloscPolproduktujTextField11;
    private javax.swing.JTextField ilosc_jTextField7;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField laczenia_jTextField6;
    private javax.swing.JScrollPane lista_projektow;
    private javax.swing.JTable lista_projektow_jTabel2;
    private javax.swing.JTextField marzajTextField3;
    private javax.swing.JTextField materialyjTextField2;
    private javax.swing.JTextField nazwa_jTextField8;
    private javax.swing.JLabel obraz_uzytkownika_label;
    private javax.swing.JTextField parametr_jTextField4;
    private javax.swing.JButton pokaz_wszystko_Szukaj_przycisk1;
    private javax.swing.JComboBox<String> polprodukty_jComboBox1;
    private javax.swing.JTextField robociznajTextField1;
    private javax.swing.JTextField rodzajjTextField9;
    private javax.swing.JTextField rysunekPolproduktu_jTextField9;
    private javax.swing.JTextField rysunek_jTextField8;
    private javax.swing.JTable tabela_materialy;
    private javax.swing.JTextField typ_jTextField1;
    private javax.swing.JButton wylogujButton1;
    private javax.swing.JTextField wymiaryPolproduktujTextField10;
    private javax.swing.JTextField wymiary_jTextField5;
    private javax.swing.JLabel zalogowano_jako;
    // End of variables declaration//GEN-END:variables

}