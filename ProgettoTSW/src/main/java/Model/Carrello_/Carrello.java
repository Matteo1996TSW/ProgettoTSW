package Model.Carrello_;

import Model.CATALOGO_.Catalogo;
import Model.Cliente_.Cliente;
import Model.Prodotto;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.awt.SystemColor.info;

public class Carrello {
    private List<Prodotto> carrello;
    private int carrelloCod;
    private double prezzo;

    /*public Carrello(int carrelloCod, double prezzo) {
        this.carrelloCod = carrelloCod;
        this.prezzo = prezzo;
    }*/

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Carrello(){
        carrello=new ArrayList();
    }
    public Carrello(List<Prodotto> lista, int codice, double prezzo){
        this.carrello = lista;
        this.carrelloCod = codice;
        this.prezzo = prezzo;
    }

    public List<Prodotto> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<Prodotto> carrello) {
        this.carrello = carrello;
    }

    public int getCarrelloCod() {
        return carrelloCod;
    }

    public void setCarrelloCod(int carrelloCod) {
        this.carrelloCod = carrelloCod;
    }

    //Controlla se la quantità richiesta è compatibile con la quantità totale disponibile nel DB
    public void doCheckList(Catalogo catalogo) throws SQLException{
        List<Prodotto> newCart = new ArrayList<>();
        Prodotto ptmp = null;
        double prezzo = 0;

        for(Prodotto p : carrello){//Per ogni prodotto nel carrello
            ptmp = catalogo.doRetriveById(p.getID());//se il prodotto corrispondente nel catalogo
            if(ptmp.getQuantita() > 0) {//ha quantità maggiore di 0
                if(p.getQuantita() > ptmp.getQuantita())
                    p.setQuantita(ptmp.getQuantita());
                newCart.add(p);//lo aggiungo al carrello controllando di non averne richiesto troppo (ed in caso lo aggiusto)
                prezzo += p.getPrezzo();//e aggiurno il prezzo del carrello
            }
        }
        setCarrello(newCart);//Aggiorno il carrello
        setPrezzo(prezzo);//ed il prezzo
    }

    //Esegue la join tra questo carrello e un altro carrello (Del db in questo caso)
    //elimina prima i duplicati dal carrello nel DB mettendoli in questo (sommando le quantità dei prodotti)
    //Poi aggiunge i prodotti rimanenti nel carrello del DB in questo
    public void joinCarrelli(Carrello carrelloDB){
        Carrello carrelloSession = this;
        if(carrelloSession.getCarrello()!=null)
            for(int i = 0; i < carrelloSession.getCarrello().size(); i++){
                if(carrelloDB.getCarrello()!=null)
                    for(int j = 0; j < carrelloDB.getCarrello().size(); j++){
                        if(carrelloSession.getCarrello().get(i).getID() == carrelloDB.getCarrello().get(j).getID()){
                            int quantita = carrelloSession.getCarrello().get(i).getQuantita() +  carrelloDB.getCarrello().get(j).getQuantita();
                            carrelloSession.getCarrello().get(i).setQuantita(quantita);
                            carrelloDB.getCarrello().remove(j);
                        }
                    }
            }
        for(Prodotto p : carrelloDB.getCarrello()){
            carrelloSession.getCarrello().add(p);
        }
        carrelloSession.setCarrelloCod(carrelloDB.getCarrelloCod());
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Prodotto prodotto : carrello) {
            s.append("\n").append(prodotto.toString());
        }
        return s.toString();
    }

    public void addProduct(Prodotto prodotto) throws SQLException {
        boolean flag = false;
        for(Prodotto p : carrello){
            if(p.getID() == prodotto.getID()){
                //il prodotto era già presente all'interno del carrello quindi devo solo aggiornare la sua quantità andando a sommare a quella già presente nel carrello, quella che è stata richiesta in info-pezzo
                p.setQuantita(p.getQuantita()+prodotto.getQuantita());
                flag = true;
                break;
            }
        }
        if(!flag){
                //il prodotto non era presente all'interno del carrello quindi posso aggiungerlo direttamente
                carrello.add(prodotto);
        }
        CarrelloDAO.delCarrelloFromComporre(this);
    }

    public void addSessionProduct(Prodotto prodotto) {
            boolean flag = false;
            int index=-1;
            for(int i = 0; i < carrello.size(); i++){
                if(prodotto.getID()==carrello.get(i).getID()){
                    flag = true;
                    index=i;
                }
            }
            CarrelloDAO service = new CarrelloDAO();
            if(!flag){
                //il prodotto non era presente all'interno del carrello quindi posso aggiungerlo direttamente
                carrello.add(prodotto);
            }
            else {
                //il prodotto era già presente all'interno del carrello quindi devo solo aggiornare la sua quantità andando a sommare a quella già presente nel carrello, quella che è stata richiesta in info-pezzo
                carrello.get(index).setQuantita(carrello.get(index).getQuantita() + prodotto.getQuantita());
            }
    }

    public Boolean isEmpty(){
        if(carrello!=null)
            return carrello.isEmpty();
        return null;
    }

    public double calculateTotal(){
        double sum = 0.0;
        for(Prodotto p : carrello){
            sum += (p.getPrezzo() * p.getQuantita());
        }
        return sum;
    }

    //Rimuove un prodotto del carrello dalla sessione e ritorna la quantità del prododotto rimossa
    //è necessario ritornare la quantità per riaggiungerlo al catalogo di sessione
    public int removeProductByIdFromSession(int id){
        int quantity = 0;
        for(int i = 0; i < carrello.size(); i++){
            if(carrello.get(i).getID() == id){
                quantity = carrello.get(i).getQuantita();
                carrello.remove(i);
            }
        }
        return quantity;
    }

    public static Carrello createNewCarrello(Cliente c) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        int idCarrello = service.createCarrello();
        service.createNewOrdine(c, idCarrello);
        Carrello newCarrello = new Carrello();
        newCarrello.setCarrelloCod(idCarrello);
        return newCarrello;
    }

    public void forwardOrderWithDifferentAddress(int idCarrello, Cliente c) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        service.setOrdineEvaso(idCarrello, c.getMail());
        service.setIndirizzoOrdine(c, idCarrello);
    }

    public void forwardOrder(int idCarrello, Cliente c) throws SQLException {
        CarrelloDAO service = new CarrelloDAO();
        service.setOrdineEvaso(idCarrello, c.getMail());
    }

    public int doRetriveQuantitaProdottoById(int id){
        for(Prodotto p : carrello){
            if(p.getID() == id){
                return p.getQuantita();
            }
        }
        return 0;
    }

}
