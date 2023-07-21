package Model.GPU_;


import Model.Prodotto;

public class Gpu extends Prodotto {
    private int wattaggio;
    private float frequenza;
    private int vRam;

    /*public Gpu(int ID, String marca, String modello, double prezzo, int quantita,
               int wattaggio, float frequenza, int vRam, String url, String descrizione) {
        super(ID, marca, modello, prezzo, quantita, "GPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.vRam = vRam;
    }

    public Gpu(String marca, String modello, double prezzo, int quantita,
               int wattaggio, float frequenza, int vRam, String url, String descrizione) {
        super(marca, modello, prezzo, quantita, "GPU", url, descrizione);
        this.wattaggio = wattaggio;
        this.frequenza = frequenza;
        this.vRam = vRam;
    }*/

    public Gpu() {
        super.setTipo("GPU");
    }

    public int getWattaggio() {
        return wattaggio;
    }

    public float getFrequenza() {
        return frequenza;
    }

    public int getVRam() {
        return vRam;
    }

    public void setWattaggio(int wattaggio) {
        this.wattaggio = wattaggio;
    }

    public void setFrequenza(float frequenza) {
        this.frequenza = frequenza;
    }

    public void setVRam(int vRam) {
        this.vRam = vRam;
    }

    @Override
    public String toString() {
        return  "Gpu{" +
                "Marca= " + super.getMarca() +
                ", Modello= "  + super.getModello() +
                ", Prezzo= " + super.getPrezzo() +
                ", Quantità disponibile= " + super.getQuantita() +
                ", url= " + super.getUrl() +
                ", Descrizione= " + super.getDescrizione() +
                ", wattaggio=" + wattaggio +
                ", frequenza=" + frequenza +
                ", vRam=" + vRam +
                "}\n";
    }
}
