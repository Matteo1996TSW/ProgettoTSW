package model.CASE_;

import model.Prodotto;

public class Case extends Prodotto {
    private short formaMobo;

    public Case() {
        super.setTipo("CASE");
    }

   
    public short getFormaMobo() { return formaMobo; }

    public void setFormaMobo(short formaMobo) {this.formaMobo = formaMobo;}

    public String toString() {
        return  "Case{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                        ", Forma MOBO: " + formaMobo + "}\n";
    }

}
