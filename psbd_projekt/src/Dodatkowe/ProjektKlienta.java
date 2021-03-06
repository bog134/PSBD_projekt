/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dodatkowe;

import java.util.ArrayList;
import Dodatkowe.*;

/**
 *
 * @author Maciek
 */

// INSERT INTO PROJEKT_KLIENTA (Id_Proj_klient, Id_Ceny, Id_Typu_mebla, Id_Laczenia, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc, Nazwa_pliku_rysunku) VALUES
// (1, NULL, 1, 1, 900, 58, 2400, 'rysuneczek_stolu123.dwg'),


public class ProjektKlienta {
    int liczba;
    int id_projektu, id_typu, id_laczenia;
    int id_tkanina, id_plyta_meblowa, id_drewna, id_pianki, id_okleiny;
    String szerokosc, wysokosc, dlugosc, rysunek_nazwa;

    ArrayList<Polprodukt> polprodukty;
         
    public ProjektKlienta(int liczba, int id_projektu, int id_typu, int id_laczenia, String szerokosc, String wysokosc, String dlugosc, String rysunek_nazwa,
                            int id_tkanina, int id_plyta_meblowa, int id_drewna, int id_pianki, int id_okleiny) {

        this.liczba = liczba;
        this.id_projektu = id_projektu;
        this.id_typu = id_typu;
        this.id_laczenia = id_laczenia;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.dlugosc = dlugosc;
        this.rysunek_nazwa = rysunek_nazwa;
        this.id_plyta_meblowa = id_plyta_meblowa;
        this.id_drewna = id_drewna;
        this.id_pianki = id_pianki;
        this.id_okleiny = id_okleiny;
        this.id_tkanina = id_tkanina;

        polprodukty = new ArrayList<Polprodukt>();
    }

    public ProjektKlienta() {
            setId(0);
            setIdTypu(0);
            setIdLaczenia(0);

            setSzerokosc(szerokosc);
            setWysokosc(wysokosc);
            setDlugosc(dlugosc);
            setRysunekNazwa(rysunek_nazwa);

            polprodukty = new ArrayList<Polprodukt>();
        }
     
    public void dodajPolprodukt(int liczba, int id_projektu, int id_rodzaju, String nazwa, String szerokosc, String wysokosc, String dlugosc, String rysunek_nazwa) {
        polprodukty.add(new Polprodukt(liczba, id_projektu, id_rodzaju, nazwa, szerokosc, wysokosc, dlugosc, rysunek_nazwa));
    }

    public ArrayList<Polprodukt> getPolprodukty() {
        return this.polprodukty;
    }

    public void setId(int id_projektu) {this.id_projektu = id_projektu;}
    public void setIdTypu(int id_typu) {this.id_typu = id_typu;}
    public void setIdLaczenia(int id_laczenia) {this.id_laczenia = id_laczenia;}
    public void setSzerokosc(String szerokosc) {this.szerokosc = szerokosc;}
    public void setWysokosc(String wysokosc) {this.wysokosc = wysokosc;}
    public void setDlugosc(String dlugosc) {this.dlugosc = dlugosc;}
    public void setRysunekNazwa(String rysunek_nazwa) {this.rysunek_nazwa = rysunek_nazwa;}

    public int getIlosc() {return liczba;}
    public int getId() {return id_projektu;}
    public int getIdTypu() {return id_typu;}
    public int getIdLaczenia() {return id_laczenia;}
    public String getSzerokosc() {return szerokosc;}
    public String getWysokosc() {return wysokosc;}
    public String getDlugosc() {return dlugosc;}
    public String getRysunekNazwa() {return rysunek_nazwa;}
    public int getIdPlyty() {return id_plyta_meblowa;}
    public int getIdDrewna() {return id_drewna;}
    public int getIdPianki() {return id_pianki;}
    public int getIdOkleiny() {return id_okleiny;} 
    public int getIdTkaninyy() {return id_tkanina;} 
}
