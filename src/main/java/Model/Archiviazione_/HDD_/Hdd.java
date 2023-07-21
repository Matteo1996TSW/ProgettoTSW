package Model.Archiviazione_.HDD_;

import Model.Archiviazione_.ArchivioDati;
import Model.Archiviazione_.ArchivioDatiDAO;

public class Hdd extends ArchivioDati {

    public Hdd(){
        super.setTipo("HDD");
    }

    /*public Hdd(int ID, String marca, String modello, double prezzo, int quantita, int MBs, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantita, "HDD", MBs, url, descrizione);
    }

    public Hdd(String marca, String modello, double prezzo, int quantita, Integer MBs, String url, String descrizione) {
        super(marca, modello, prezzo, quantita, "HDD", MBs, url, descrizione);
    }*/

    @Override
    public String toString() {
        return "HDD" + super.toString();
    }
}