package model.Archiviazione_;

import java.sql.SQLException;

import model.Prodotto;
import model.ProdottoDAO;

public class ArchivioDati extends Prodotto {
//ArchivioDati estende prodotto ed è superclasse per HDD e SSD che contengono fondamentalmente gli stessi valori
//quindi hanno metodi e variabili comuni. La divisione data, sia nel db sia nella classe
//è logica.
    private int MBs;

    public ArchivioDati() {}


    public int getMBs() {
        return MBs;
    }

    public void setMBs(int MBs) {
        this.MBs = MBs;
    }

    @Override
    public String toString() {
        return  "{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                ", Forma MOBO: " + MBs + "}\n";
    }



}
