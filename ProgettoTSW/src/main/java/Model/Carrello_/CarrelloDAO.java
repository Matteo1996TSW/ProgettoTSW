package Model.Carrello_;

import Model.Cliente_.Cliente;
import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO {

    public int createCarrello() throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Carrello (totale) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        int idCarrello = rs.getInt(1);
        return idCarrello;
    }
    public int doRetriveCarrelloCodByMailCLiente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true;");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        int codice = -1;
        while(rs.next()){
            codice = rs.getInt(1);
        }
        return codice;
    }

    public double doRetrivePrezzoByIdCarrello(int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Totale FROM Carrello WHERE Cod = ?;");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();
        double totale = -1;
        while(rs.next()){
            totale = rs.getInt(1);
        }
        return totale;
    }

    private int getComporreQuantita(int id, int carrelloCod) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Quantita FROM Comporre WHERE PezzoId = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, id);
        pdstmt.setInt(2, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();
        int quantita = -1;
        while(rs.next()){
            quantita = rs.getInt(1);
        }
        return quantita;
    }

    public void doUpdateQuantitaRichiestaById(int id, int carrelloCod, int quantita) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Comporre SET Quantita = ? WHERE PezzoID = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, quantita);
        pdstmt.setInt(2, id);
        pdstmt.setInt(3, carrelloCod);
        pdstmt.executeUpdate();
    }

    public Carrello doRetriveByMailCliente(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT PezzoID FROM Comporre WHERE CarrelloCod = (SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? AND Evaso != true);");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();
        ArrayList<Integer> prodotti = new ArrayList<Integer>();
        while(rs.next()){
            Integer i = rs.getInt(1);
            prodotti.add(i);
        }
        int carrelloCod = doRetriveCarrelloCodByMailCLiente(mail);
        double totaleCarrello = doRetrivePrezzoByIdCarrello(carrelloCod);
        List<Prodotto> listProdotti;
        listProdotti = Prodotto.doRetriveByIdLis(prodotti);
        for (Prodotto prodotto : listProdotti) {
            int quantitaRichiesta = getComporreQuantita(prodotto.getID(), carrelloCod);
            prodotto.setQuantita(quantitaRichiesta);
        }
        return new Carrello(listProdotti, carrelloCod, totaleCarrello);
    }

    public static void delCarrelloFromComporre(Carrello carrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("DELETE FROM Comporre WHERE CarrelloCod = ?");
        pdstmt.setInt(1, carrello.getCarrelloCod());
        pdstmt.executeUpdate();
        for(Prodotto p : carrello.getCarrello()){
            addCartDB(p, carrello.getCarrelloCod());
        }
    }
    private static void addCartDB(Prodotto p, int idCarrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Comporre VALUES (?, ?, ?, ?)");
        pdstmt.setInt(1, p.getID());
        pdstmt.setInt(2, idCarrello);
        pdstmt.setInt(3, p.getQuantita());
        pdstmt.setDouble(4, p.getPrezzo());
        pdstmt.executeUpdate();
    }

    public void updateTotalCarrello(Carrello carrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Carrello SET Totale = ? WHERE Cod = ?");
        pdstmt.setDouble(1, carrello.calculateTotal());
        pdstmt.setInt(2, carrello.getCarrelloCod());
        pdstmt.executeUpdate();
    }

    //Setta a vero l'attributo Evaso nella tabella Ordine
    public void setOrdineEvaso(int codCarrello, String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Ordine SET Evaso = ? WHERE ClienteMail = ? AND CarrelloCod = ?");
        pdstmt.setInt(1, 1);
        pdstmt.setString(2, mail);
        pdstmt.setInt(3, codCarrello);
        pdstmt.executeUpdate();
    }

    public void setIndirizzoOrdine(Cliente c, int codCarrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Ordine SET Via = ?, Provincia = ?, Citta = ?, Cap = ? WHERE ClienteMail = ? AND CarrelloCod = ?");
        pdstmt.setString(1,c.getVia());
        pdstmt.setString(2,c.getProvincia());
        pdstmt.setString(3,c.getCitta());
        pdstmt.setInt(4, c.getCap());
        pdstmt.setString(5, c.getMail());
        pdstmt.setInt(6,codCarrello);
        pdstmt.executeUpdate();
    }

    public void createNewOrdine(Cliente c, int idCarrello) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("INSERT INTO Ordine VALUES (?, ?, ?, ?, ?, ?, ?)");
        pdstmt.setString(1, c.getMail());
        pdstmt.setInt(2, idCarrello);
        pdstmt.setInt(3, 0);
        pdstmt.setString(4, c.getVia());
        pdstmt.setString(5,c.getProvincia());
        pdstmt.setString(6, c.getCitta());
        pdstmt.setInt(7, c.getCap());
        pdstmt.executeUpdate();
    }

    //Ritorna tutto lo storico dei carrelli sotto forma di carrello
    public static ArrayList<Carrello>  doRetriveStorico(String mail) throws SQLException {
        ArrayList<Integer> storicoCarrelli = doRetriveStoricoCarrelliIdEvasiByMail(mail);
        ArrayList<Carrello> listaCarrelli = new ArrayList<>();
        CarrelloDAO cDAO = new CarrelloDAO();

        //Recupera tutti i carrelli
        for(Integer i : storicoCarrelli){
            listaCarrelli.add(cDAO.doRetriveCarrelloById(i));
        }
        //Setta la lista di prodotti nel carrello
        for(Carrello c : listaCarrelli){
            c.setCarrello(cDAO.doRetriveProdottiByIdCarrello(c.getCarrelloCod()));
        }
        //Recupera la quantità di ogni prodotto nel carrello
        for(Carrello c : listaCarrelli){
            for(Prodotto p : c.getCarrello()){
                p.setQuantita(cDAO.getComporreQuantita(p.getID(), c.getCarrelloCod()));
            }
        }
        //Setta il prezzo corretto dei prodotti basandosi sulla ridondanza presente in comporre
        for(Carrello c : listaCarrelli){
            for(Prodotto p : c.getCarrello()){
                p.setPrezzo( cDAO.getPrezzoRidondanteDaComporre(c.getCarrelloCod(), p.getID()));
            }
        }

        if(listaCarrelli.size() == 0)
            return null;
        return listaCarrelli;
        //return listaCarrelli.size() == 0 ? null:listaCarrelli ;
    }

    private double getPrezzoRidondanteDaComporre(int carrelloCod, int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Prezzo FROM Comporre WHERE CarrelloCod = ? and PezzoID = ?");
        pdstmt.setInt(1, carrelloCod);
        pdstmt.setInt(2, id);
        ResultSet rs = pdstmt.executeQuery();

        if(rs.next()){
            return (rs.getDouble(1));
        }
        return 0;
    }

    private List<Prodotto> doRetriveProdottiByIdCarrello(int carrelloCod) throws SQLException {
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        ArrayList<Integer> listaCodiciProdotti = new ArrayList<Integer>();
        CarrelloDAO cDAO = new CarrelloDAO();

        listaCodiciProdotti = cDAO.doRetriveListaCodiciProdotti(carrelloCod);
        listaProdotti = ProdottoDAO.doRetriveListaIdProdotti(listaCodiciProdotti);

        return listaProdotti;
    }

    private ArrayList<Integer> doRetriveListaCodiciProdotti(int carrelloCod) throws SQLException {
        ArrayList<Integer> codProdotti = new ArrayList<Integer>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Comporre WHERE CarrelloCod = ?");
        pdstmt.setInt(1, carrelloCod);
        ResultSet rs = pdstmt.executeQuery();

        while(rs.next()){
            codProdotti.add(rs.getInt(1));
        }
        return codProdotti;
    }

    public Carrello doRetriveCarrelloById(Integer id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Cod,Totale FROM Carrello WHERE Cod = ?");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();

        if(rs.next()){
            Carrello carrello = new Carrello();
            carrello.setCarrelloCod(rs.getInt(1));
            carrello.setPrezzo(rs.getDouble(2));
            return carrello;
        }
        return null;
    }

    //Recupera tutti gli id dei carrelli che sono stati evasi dalla mail
    private static ArrayList<Integer> doRetriveStoricoCarrelliIdEvasiByMail(String mail) throws SQLException {
        ArrayList<Integer> codCarrelli = new ArrayList<Integer>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT CarrelloCod FROM Ordine WHERE ClienteMail = ? and Evaso = true");
        pdstmt.setString(1, mail);
        ResultSet rs = pdstmt.executeQuery();

        while(rs.next()){
            Integer i = rs.getInt(1);
            codCarrelli.add(i);
        }
        return codCarrelli;
    }

    public void modCart(Carrello cart, Prodotto p) throws SQLException {
        modComporreQuant( cart,  p);
        modCarrelloPrezzo(cart);
    }

    private void modCarrelloPrezzo(Carrello cart) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Carrello SET Totale = ? WHERE Cod = ?");
        pdstmt.setDouble(1, cart.getPrezzo());
        pdstmt.setInt(2, cart.getCarrelloCod());
        pdstmt.executeUpdate();
    }

    private void modComporreQuant(Carrello cart, Prodotto p) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Comporre SET Quantita = ?, Prezzo = ? WHERE CarrelloCod = ? AND PezzoID = ?");
        pdstmt.setInt(1, p.getQuantita());
        pdstmt.setInt(3, cart.getCarrelloCod());
        pdstmt.setDouble(2, p.getPrezzo()*p.getQuantita());
        pdstmt.setInt(4, p.getID());
        if(pdstmt.executeUpdate()==0) {
            throw new SQLException();
        }
    }

}
