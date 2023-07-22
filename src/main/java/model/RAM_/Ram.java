package model.RAM_;

import model.Prodotto;

public class Ram extends Prodotto {

    private float frequenza;

    public Ram(){
        super.setTipo("RAM");
    }

    

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
