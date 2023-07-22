package model.MOBO_;

import java.sql.*;
import java.util.ArrayList;

import model.ConPool;
import model.Prodotto;
import model.ProdottoDAO;
import model.CASE_.Case;
import model.GPU_.Gpu;
import model.PSU_.Psu;

public class MoboDAO {

    //Interroga il DB per avere una lista di prodotti di tipo MOBO
    private ArrayList<Prodotto> doRetrive()  {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con;
		try {
			con = ConPool.getConnection();
	        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
	        pdstmt.setString(1, "MOBO");
	        ResultSet rs = pdstmt.executeQuery();
	        while(rs.next()){
	            Mobo mobo = InitMoboFromRs(rs);
	            list.add(mobo);
	        }
	        return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }

    //Prende la lista di prodotti e fa il cast a lista di schede madri
    public ArrayList<Mobo> doRetriveByType() {
        MoboDAO mDAO = new MoboDAO();
        ArrayList<Prodotto> listP = mDAO.doRetrive();
        ArrayList<Mobo> listM = new ArrayList<Mobo>();
        for(Prodotto p : listP){
            listM.add((Mobo) p);
        }
        return listM;
    }

    public static void Upload(Mobo m)  {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, N_RAM, N_USB, N_PCI, formaMobo, url, Descrizione) " +
	                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
	        pdstmt.setString(1, m.getTipo());
	        pdstmt.setString(2, m.getMarca());
	        pdstmt.setString(3, m.getModello());
	        pdstmt.setDouble(4, m.getPrezzo());
	        pdstmt.setInt(5, m.getQuantita());
	        pdstmt.setInt(6, m.getN_RAM());
	        pdstmt.setInt(7, m.getN_USB());
	        pdstmt.setInt(8, m.getN_PCI());
	        pdstmt.setInt(9, m.getForma());
	        pdstmt.setString(10, m.getUrl());
	        pdstmt.setString(11, m.getDescrizione());

	        pdstmt.executeUpdate();
	        ResultSet rs = pdstmt.getGeneratedKeys();
	        rs.next();
	        m.setID(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

    public static void Update(Mobo m) {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "N_RAM = ?, N_USB = ?, N_PCI = ?, FormaMobo = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement(updProd);

	        pdstmt.setString(1, m.getMarca());
	        pdstmt.setString(2, m.getModello());
	        pdstmt.setDouble(3, m.getPrezzo());
	        pdstmt.setInt(4, m.getQuantita());
	        pdstmt.setInt(5, m.getN_RAM());
	        pdstmt.setInt(6, m.getN_USB());
	        pdstmt.setInt(7, m.getN_PCI());
	        pdstmt.setInt(8, m.getForma());
	        pdstmt.setString(9, m.getUrl());
	        pdstmt.setString(10, m.getDescrizione());
	        pdstmt.setInt(11, m.getID());
	        pdstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public static Mobo InitMoboFromRs(ResultSet rs) {
        Mobo mobo = new Mobo();
        try {
			mobo.setID(rs.getInt(1));
			mobo.setMarca(rs.getString(2));
	        mobo.setModello(rs.getString(3));
	        mobo.setPrezzo(rs.getInt(4));
	        mobo.setQuantita(rs.getInt(5));
	        mobo.setForma(rs.getShort(6));
	        mobo.setN_RAM(rs.getInt(10));
	        mobo.setN_USB(rs.getInt(11));
	        mobo.setN_PCI(rs.getInt(12));
	        mobo.setUrl(rs.getString(18));
	        mobo.setDescrizione(rs.getString(19));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return mobo;
    }

}
