package model.CATALOGO_;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prodotto;
import model.CASE_.CaseDAO;
import model.Carrello_.Carrello;
import model.DISSIPATORE_.DissipatoreDAO;

public class Catalogo {
    private List<Prodotto> catalogo;

    public Catalogo(){
        catalogo = new ArrayList<Prodotto>();
    }

    public List<Prodotto> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Prodotto> catalgo) {
        this.catalogo = catalgo;
    }

    //Rimuove la quantità richiesta di ogni prodotto nel carrello
    public void aggiornaQuantita(Carrello carrello){
        //Le quantità relative ai pezzi nel carrello sono le quantità richieste
        //Le quantità relative ai pezzi del catalo sono le quantità disponibili
        for(int i = 0; i < catalogo.size(); i++){
            for(int j = 0; j < carrello.getCarrello().size(); j++){
               if(getCatalogo().get(i).getID() == carrello.getCarrello().get(j).getID()){
                    catalogo.get(i).setQuantita(catalogo.get(i).getQuantita() - carrello.getCarrello().get(j).getQuantita());
                }
            }
        }
    }

    //Rimuove la quantità di prodotto richiesta
    public void aggiornaQuantita(Prodotto p){
        //Le quantità relative ai pezzi nel carrello sono le quantità richieste
        //Le quantità relative ai pezzi del catalogo sono le quantità disponibili
        for (Prodotto prodotto : catalogo) {
            if (prodotto.getID() == p.getID()) {
                prodotto.setQuantita((prodotto.getQuantita() - p.getQuantita()));
            }
        }
    }

    public List<?> doRetriveByType(String type){
        List<Prodotto> list = new ArrayList<>();
        
        for(Prodotto p : catalogo) {
        	switch (type) {
            case "CPU":
                if (p.getTipo().equals("CPU"))
                        list.add(p);
                break;
            case "MOBO":
                if(p.getTipo().equals("MOBO"))
                	list.add(p);
                break;
            case "CASE":
            	if(p.getTipo().equals("CASE"))
            			list.add(p);
                break;
            case "DISSIPATORE":
            	if(p.getTipo().equals("DISSIPATORE"))
            			list.add(p);        		
                break;
            case "GPU":
                if(p.getTipo().equals("GPU"))
                        list.add(p);
                break;
            case "PSU":
                if(p.getTipo().equals("PSU"))
                        list.add(p);
                break;
            case "RAM":
                if(p.getTipo().equals("RAM"))
                        list.add(p);
                break;
            case "HDD":
                if(p.getTipo().equals("HDD"))
                        list.add(p);
                break;
            case "SSD":
                if(p.getTipo().equals("SSD"))
                        list.add(p);
                break;
            default:
                list = null;
                break;
        	}
        	
        }
        
        return list;
    }

    public Prodotto doRetriveById(int id){
        for (Prodotto prodotto : catalogo) {
            if (id == prodotto.getID()) {
                return prodotto;
            }
        }
        return null;
    }

    public void addProdotto(Prodotto p){
        catalogo.add(p);
    }

    public Catalogo filterByMarca(String marca){
        Catalogo newCatalogo = new Catalogo();
        for(Prodotto p : catalogo){
            if((p.getMarca().toLowerCase()).contains(marca.toLowerCase())) {
                newCatalogo.addProdotto(p);
            }
        }
        return newCatalogo;
    }
    public Catalogo filterByModello(String modello) {
        Catalogo newCatalogo = new Catalogo();
        for(Prodotto p : catalogo){
            if((p.getModello().toLowerCase()).contains(modello.toLowerCase()))
                newCatalogo.addProdotto(p);
        }
        return newCatalogo;
    }

    public Catalogo filterByPrezzo(int val) {
        Catalogo c = new Catalogo();
        for(Prodotto p : catalogo){
            if(p.getPrezzo() < val)
                c.addProdotto(p);
        }
        return c;
    }

    public int getMaxPrice() {
        int max = -1;
        for(Prodotto p : catalogo)
            if(p.getPrezzo() > max)
                max = (int) p.getPrezzo();
        return max + 1;
    }
    public int getMinPrice(){
        int min = Integer.MAX_VALUE;
        for(Prodotto p : catalogo){
            if(p.getPrezzo() < min)
                min = (int) p.getPrezzo();
        }
        return min;
    }

    public boolean isEmpty(){
        return catalogo.size() == 0;
        //Fa quanto scritto sotto ma scritto semplificato
        /*if(catalogo.size() == 0)
            return true;
        return false;*/
    }

    //Rimuoviamo il prodotto dal carello di sessione e riaggiungiamo la quantità al catalogo di sessione
    public void updateQuantity(int quantity, int id) {
        for(Prodotto p : catalogo){
            if(p.getID() == id){
                p.setQuantita(p.getQuantita()+quantity);
            }
        }
    }

    public int getQuantità(int id) {
        for(Prodotto p : catalogo){
            if(p.getID() == id)
                return p.getQuantita();
        }
        return 0;
    }
}
