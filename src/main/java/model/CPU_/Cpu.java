package model.CPU_;

import java.util.ArrayList;

import model.Prodotto;

public class Cpu extends Prodotto {


    private int N_Core;
    private float frequenza;
    private int wattaggio;

    public Cpu(){
        super.setTipo("CPU");
    }



    public String listCpus(ArrayList<Prodotto> list){
        return super.toString() + " Wattaggio: " + wattaggio + " Frequenza: " + frequenza + " N_Core: " + N_Core + "\n";
    }

    public int getN_Core() {
        return N_Core;
    }

    public void setN_Core(int n_Core) {
        N_Core = n_Core;
    }

    public float getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(float frequenza) {
        this.frequenza = frequenza;
    }

    public int getWattaggio() {return wattaggio;}

    public void setWattaggio(int wattaggio) {this.wattaggio = wattaggio; }

    @Override
    public String toString() {
               return  "Cpu{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                       ", url= "  + super.getUrl() +
                       ", Descrizione= " + super.getDescrizione() +
                ", N_Core= " + N_Core +
                ", frequenza= " + frequenza +
                ", wattaggio= " + wattaggio +
                '}' + "\n";
    }
}
