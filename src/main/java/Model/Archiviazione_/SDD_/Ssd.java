package Model.Archiviazione_.SDD_;

import Model.Archiviazione_.ArchivioDati;

public class Ssd extends ArchivioDati {

    public Ssd(){
        super.setTipo("SSD");
    }

    /*public Ssd(int ID, String marca, String modello, double prezzo, int quantità, int MBs, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantità, "SSD", MBs, url, descrizione);
    }

    public Ssd(String marca, String modello, double prezzo, int quantità, int MBs, String url, String descrizione) {
        super(marca, modello, prezzo, quantità, "SSD", MBs, url, descrizione);
    }*/


    @Override
    public String toString() {
        return "SSD" + super.toString();
    }
}
