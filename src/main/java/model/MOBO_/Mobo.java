package model.MOBO_;

import model.Prodotto;

public class Mobo extends Prodotto {
    private short forma;
    private int N_RAM;
    private int N_USB;
    private int N_PCI;
  

    public Mobo(){
        super.setTipo("MOBO");
    }

    public short getForma(){
        return forma;
    }
    public int getN_RAM(){
        return N_RAM;
    }
    public int getN_USB(){
        return N_USB;
    }
    public int getN_PCI(){
        return N_PCI;
    }
    public void setForma(short forma){
        this.forma = forma;
    }
    public void setN_RAM(int N_RAM){
        this.N_RAM = N_RAM;
    }
    public void setN_USB(int N_USB){
        this.N_USB = N_USB;
    }
    public void setN_PCI(int N_PCI){
        this.N_PCI = N_PCI;
    }
    public String toString(){
        return "ID: " + getID() + "\nMarca: " + getMarca() + "\nModello: " + getModello() +
                "\nPrezzo: " + getPrezzo() + "\nQuantità: " + getQuantita() + "\nURL: " +
                getUrl() + "\nDescrizione: " + getDescrizione() + "\nForma: " + getForma() +
                "\nN_RAM: " + getN_RAM() + "\nN_USB: " + getN_USB() + "\nN_PCI: " + getN_PCI();
    }
}


