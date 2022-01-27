/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dodatkowe;

/**
 *
 * @author Maciek
 */

// INSERT INTO PROJEKT_KLIENTA (Id_Proj_klient, Id_Ceny, Id_Typu_mebla, Id_Laczenia, Wymiary_Szerokosc, Wymiary_Wysokosc, Wymiary_Dlugosc, Nazwa_pliku_rysunku) VALUES
// (1, NULL, 1, 1, 900, 58, 2400, 'rysuneczek_stolu123.dwg'),


public final class Polprodukt {
// INSERT INTO PROJEKT_POLPRODUKTU(Id_Proj_polprod, Id_Proj_klient, Id_Proj_katalog, Id_Rodzaju_polproduktu, Nazwa, Rozmiar_Wysokosc, Rozmiar_Szerokosc, Rozmiar_Dlugosc, Cena, Nazwa_pliku_rysunku) VALUES
// (1, NULL, 7, 1, 'FUTURA NV', 60, 1000, 2000, 270, 'futura.dwg'),

    int id_polproduktu, id_rodzaju;
    String nazwa, szerokosc, wysokosc, dlugosc, rysunek_nazwa;
        
        public Polprodukt(int id_projektu, int id_rodzaju, String nazwa, String szerokosc, String wysokosc, String dlugosc, String rysunek_nazwa) {
            setId(id_projektu);
            setIdRodzaju(id_rodzaju);

            setNazwa(nazwa);
            setSzerokosc(szerokosc);
            setWysokosc(wysokosc);
            setDlugosc(dlugosc);
            setRysunekNazwa(rysunek_nazwa);
        }

        // public Polprodukt() {
        //     setId(0);
        //     setIdRodzaju(0);

        //     setNazwa("nazwa");
        //     setSzerokosc("szerokosc");
        //     setWysokosc("wysokosc");
        //     setDlugosc("dlugosc");
        //     setRysunekNazwa("rysunek_nazwa");
        // }
        
        public void setId(int id_polproduktu) {this.id_polproduktu = id_polproduktu;}
        public void setIdRodzaju(int id_rodzaju) {this.id_rodzaju = id_rodzaju;}
        public void setNazwa(String nazwa) {this.nazwa = nazwa;}
        public void setSzerokosc(String szerokosc) {this.szerokosc = szerokosc;}
        public void setWysokosc(String wysokosc) {this.wysokosc = wysokosc;}
        public void setDlugosc(String dlugosc) {this.dlugosc = dlugosc;}
        public void setRysunekNazwa(String rysunek_nazwa) {this.rysunek_nazwa = rysunek_nazwa;}

        public int getId() {return id_polproduktu;}
        public int getIdRodzaju() {return id_rodzaju;}
        public String getSzerokosc() {return szerokosc;}
        public String getWysokosc() {return wysokosc;}
        public String getDlugosc() {return dlugosc;}
        public String getRysunekNazwa() {return rysunek_nazwa;}
}
