package Model.RAM_;

import Model.Prodotto;

public class Ram extends Prodotto {

    private float frequenza;

    public Ram(){
        super.setTipo("RAM");
    }

    /*public Ram(int ID, String marca, String modello, double prezzo, int quantita, float frequenza, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantita, "RAM", url, descrizione);
        this.frequenza = frequenza;
    }

    public Ram(String marca, String modello, double prezzo, int quantita, float frequenza, String url, String descrizione) {
        super(marca, modello, prezzo, quantita, "RAM", url, descrizione);
        this.frequenza = frequenza;
    }*/

    public float getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(float frequenza) {
        this.frequenza = frequenza;
    }

    public String toString() {
        return "RAM{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                "Frequenza: " + frequenza + "}\n";
    }

}
