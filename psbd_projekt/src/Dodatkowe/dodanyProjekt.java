/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dodatkowe;

import java.util.ArrayList;

/**
 *
 * @author Maciek
 */
public final class dodanyProjekt {
    String Id_projektu, Nazwa, Typ, Cena, OpcjonalneCzesci;
    ArrayList<String> Materialy;
        
        public dodanyProjekt(String Id_projektu, String Nazwa, String Typ, String Cena, ArrayList<String> Materialy, String OpcjonalneCzesci){
            this.setId(Id_projektu);
            this.setNazwa(Nazwa);
            this.setTyp(Typ);
            this.setCena(Cena);
            this.setMaterial(Materialy);
            this.setOpcjonalneCzesci(OpcjonalneCzesci);
        }
        
        public void setId(String Id_projektu){this.Id_projektu = Id_projektu;}
        public void setNazwa(String Nazwa){this.Nazwa = Nazwa;}
        public void setTyp(String Typ){this.Typ = Typ;}
        public void setCena(String Cena){this.Cena = Cena;}
        public void setMaterial(ArrayList<String> Materialy){this.Materialy = Materialy;}
        public void setOpcjonalneCzesci(String OpcjonalneCzesci){this.OpcjonalneCzesci = OpcjonalneCzesci;}
        
        public String getId(){return Id_projektu;}
        public String getNazwa(){return Nazwa;}
        public String getTyp(){return Typ;}
        public String getCena(){return Cena;}
        public ArrayList<String> getMaterial(){return Materialy;}
        public String getOpcjonalneCzesci(){return OpcjonalneCzesci;}
}
