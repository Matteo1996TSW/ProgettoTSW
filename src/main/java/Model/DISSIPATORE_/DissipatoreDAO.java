package Model.DISSIPATORE_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

import java.sql.*;
import java.util.ArrayList;

public class DissipatoreDAO {
    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "DISSIPATORE");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            Dissipatore d = InitDissipatoreFromRs(rs);
            list.add(d);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di Dissipatore
    public ArrayList<Dissipatore> doRetriveByType() throws SQLException{
        DissipatoreDAO dDAO = new DissipatoreDAO();
        ArrayList<Prodotto> listP = dDAO.doRetrive();
        ArrayList<Dissipatore> listD = new ArrayList<Dissipatore>();
        for(Prodotto p : listP){
            listD.add((Dissipatore) p);
        }
        return listD;
    }

    public static void Upload(Dissipatore d) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, d.getTipo());
        pdstmt.setString(2, d.getMarca());
        pdstmt.setString(3, d.getModello());
        pdstmt.setDouble(4, d.getPrezzo());
        pdstmt.setInt(5, d.getQuantita());
        pdstmt.setFloat(7, d.getFormaMobo());
        pdstmt.setString(8, d.getUrl());
        pdstmt.setString(9, d.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        d.setID(rs.getInt(1));;
    }

    public static void Update(Dissipatore d) throws SQLException {
        String updProd = "UPDATE Pezzo "+
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "W_CPU = ?, url = ?, Descrizione = ? WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);

        pdstmt.setString(1, d.getMarca());
        pdstmt.setString(2, d.getModello());
        pdstmt.setDouble(3, d.getPrezzo());
        pdstmt.setInt(4, d.getQuantita());
        pdstmt.setInt(5, d.getFormaMobo());
        pdstmt.setString(6, d.getUrl());
        pdstmt.setString(7, d.getDescrizione());
        pdstmt.setInt(8, d.getID());
        pdstmt.executeUpdate();
    }

    public static Dissipatore InitDissipatoreFromRs(ResultSet rs) throws SQLException {
    	Dissipatore  dissipatore_ = new Dissipatore();
      
    	dissipatore_.setID(rs.getInt(1));
    	dissipatore_.setMarca(rs.getString(2));
    	dissipatore_.setModello(rs.getString(3));
    	dissipatore_.setPrezzo(rs.getInt(4));
    	dissipatore_.setQuantita(rs.getInt(5));
    	dissipatore_.setFormaMobo(rs.getShort(17));
    	dissipatore_.setUrl(rs.getString(18));
    	dissipatore_.setDescrizione(rs.getString(19));
        return dissipatore_;
    }

}
