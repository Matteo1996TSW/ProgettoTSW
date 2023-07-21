package Model.CPU_;

import Model.Prodotto;

import java.util.ArrayList;

public class Cpu extends Prodotto {


    private int N_Core;
    private float frequenza;
    private int wattaggio;

    public Cpu(){
        super.setTipo("CPU");
    }

    /*public Cpu(int ID, String marca, String modello, double prezzo, int quantita, int wattaggio, float frequenza, int N_Core, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantita, "CPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.N_Core = N_Core;
    }

    public Cpu(String marca, String modello, double prezzo, int quantità, int wattaggio, float frequenza, int N_Core, String url, String descrizione) {
        super(marca, modello, prezzo, quantità, "CPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.N_Core = N_Core;
    }*/

    public String listCpus(ArrayList<Prodotto> lista){
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
