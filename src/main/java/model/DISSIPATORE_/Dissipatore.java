package model.DISSIPATORE_;


import model.Prodotto;

public class Dissipatore extends Prodotto {

	 private short formaMobo;

	    public Dissipatore() {
	        super.setTipo("Dissipatore");
	    }

	 
	    public short getFormaMobo() { return formaMobo; }

	    public void setFormaMobo(short formaMobo) {this.formaMobo = formaMobo;}

	    public String toString() {
	        return  "Dissipatore{" +
	                "Marca= " + super.getMarca() +
	                ", Modello= "  + super.getModello() +
	                ", Prezzo= " + super.getPrezzo() +
	                ", Quantità disponibile= " + super.getQuantita() +
	                ", url= " + super.getUrl() +
	                ", Descrizione= " + super.getDescrizione() +
	                        ", Forma MOBO: " + formaMobo + "}\n";
	    }

	}


