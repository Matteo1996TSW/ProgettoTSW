package Model.Archiviazione_;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.SDD_.Ssd;
import Model.ConPool;
import Model.ProdottoDAO;

import java.sql.*;

public abstract class ArchivioDatiDAO {

    public static void Upload(ArchivioDati a) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "INSERT INTO Pezzo (tipo, Marca, Modello, Prezzo, Quantita, MBs, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, a.getTipo());
        pdstmt.setString(2, a.getMarca());
        pdstmt.setString(3, a.getModello());
        pdstmt.setDouble(4, a.getPrezzo());
        pdstmt.setInt(5, a.getQuantita());
        pdstmt.setFloat(6, a.getMBs());
        pdstmt.setString(7, a.getUrl());
        pdstmt.setString(8, a.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        a.setID(rs.getInt(1));
    }

    public static void Update(ArchivioDati a) throws SQLException {
        Connection con = ConPool.getConnection();
        String updProd = "UPDATE Pezzo "+
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "MBs = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        PreparedStatement pdstmt = con.prepareStatement(updProd);
        pdstmt.setString(1, a.getMarca());
        pdstmt.setString(2, a.getModello());
        pdstmt.setDouble(3, a.getPrezzo());
        pdstmt.setInt(4, a.getQuantita());
        pdstmt.setInt(5, a.getMBs());
        pdstmt.setString(6, a.getUrl());
        pdstmt.setString(7, a.getDescrizione());
        pdstmt.setInt(8, a.getID());
        pdstmt.executeUpdate();
    }


}
