package Model.GPU_;

import Model.Archiviazione_.SDD_.Ssd;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.ConPool;
import Model.DISSIPATORE_.Dissipatore;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.RAM_.Ram;

import java.sql.*;
import java.util.ArrayList;

public class GpuDAO {

    private ArrayList<Prodotto> doRetrive() throws SQLException {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
        pdstmt.setString(1, "GPU");
        ResultSet rs = pdstmt.executeQuery();
        while(rs.next()){
            Gpu ssd = InitGpuFromRs(rs);
            list.add(ssd);
        }
        return list;
    }

    //Prende la lista di prodotti e fa il cast a lista di GPU
    public ArrayList<Gpu> doRetriveByType() throws SQLException{
        GpuDAO gDAO = new GpuDAO();
        ArrayList<Prodotto> listP = gDAO.doRetrive();
        ArrayList<Gpu> listG = new ArrayList<Gpu>();
        for(Prodotto p : listP){
            listG.add((Gpu) p);
        }
        return listG;
    }

    public static void Upload(Gpu g) throws SQLException {
        Connection con = ConPool.getConnection();

        String insertion = "INSERT INTO Pezzo (Tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, VRAM, url, Descrizione) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, g.getTipo());
        pdstmt.setString(2, g.getMarca());
        pdstmt.setString(3, g.getModello());
        pdstmt.setDouble(4, g.getPrezzo());
        pdstmt.setInt(5, g.getQuantita());
        pdstmt.setInt(6, g.getWattaggio());
        pdstmt.setFloat(7, g.getFrequenza());
        pdstmt.setInt(8, g.getVRam());
        pdstmt.setString(9, g.getUrl());
        pdstmt.setString(10, g.getDescrizione());

        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        g.setID(rs.getInt(1));
    }

    public static void Update(Gpu g) throws SQLException {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "Wattaggio = ?, Frequenza = ?, VRAM = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);

        pdstmt.setString(1, g.getMarca());
        pdstmt.setString(2, g.getModello());
        pdstmt.setDouble(3, g.getPrezzo());
        pdstmt.setInt(4, g.getQuantita());
        pdstmt.setInt(5, g.getWattaggio());
        pdstmt.setFloat(6, g.getFrequenza());
        pdstmt.setInt(7, g.getVRam());
        pdstmt.setString(8, g.getUrl());
        pdstmt.setString(9, g.getDescrizione());
        pdstmt.setInt(10, g.getID());
        pdstmt.executeUpdate();
    }

    public static Gpu InitGpuFromRs(ResultSet rs) throws SQLException {
        Gpu gpu = new Gpu();
        gpu.setID(rs.getInt(1));
        gpu.setMarca(rs.getString(2));
        gpu.setModello(rs.getString(3));
        gpu.setPrezzo(rs.getInt(4));
        gpu.setQuantita(rs.getInt(5));
        gpu.setWattaggio(rs.getInt(6));
        gpu.setFrequenza(rs.getFloat(8));
        gpu.setVRam(rs.getInt(14));
        gpu.setUrl(rs.getString(18));
        gpu.setDescrizione(rs.getString(19));
        return gpu;
    }

}
