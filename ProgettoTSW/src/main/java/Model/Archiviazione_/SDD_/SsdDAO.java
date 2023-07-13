package Model.Archiviazione_.SDD_;

import Model.Archiviazione_.ArchivioDatiDAO;
import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.HDD_.HddDAO;
import Model.ConPool;
import Model.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class SsdDAO extends ArchivioDatiDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "SSD");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            //                                int ID,              String marca,             String modello,              double prezzo,            int quantità,               int MBs,                   String url,          String descrizione
            Ssd ssd = new Ssd();
            ssd.setID(rs.getInt(1));
            ssd.setMarca(rs.getString(2));
            ssd.setModello(rs.getString(3));
            ssd.setPrezzo(rs.getInt(4));
            ssd.setQuantita(rs.getInt(5));
            ssd.setMBs(rs.getInt(13));
            ssd.setUrl(rs.getString(18));
            ssd.setDescrizione(rs.getString(19));
            list.add(ssd);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di SSD
    public ArrayList<Ssd> doRetriveByType() throws SQLException{
        SsdDAO sDAO = new SsdDAO();
        ArrayList<Prodotto> listP = sDAO.doRetrive();
        ArrayList<Ssd> listS = new ArrayList<Ssd>();
        for(Prodotto p : listP){
            listS.add((Ssd) p);
        }
        return listS;
    }

    public static Ssd InitSsdFromRs(ResultSet rs) throws SQLException {
        Ssd ssd = new Ssd();
        ssd.setID(rs.getInt(1));
        ssd.setMarca(rs.getString(2));
        ssd.setModello(rs.getString(3));
        ssd.setPrezzo(rs.getInt(4));
        ssd.setQuantita(rs.getInt(5));
        ssd.setMBs(rs.getInt(13));
        ssd.setUrl(rs.getString(18));
        ssd.setDescrizione(rs.getString(19));
        return ssd;
    }

}
