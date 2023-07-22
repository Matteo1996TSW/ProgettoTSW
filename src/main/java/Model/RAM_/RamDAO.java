package Model.RAM_;

import Model.CASE_.Case;
import Model.ConPool;
import Model.MOBO_.Mobo;
import Model.PSU_.Psu;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class RamDAO{


    private ArrayList<Prodotto> doRetrive() {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con;
		try {
			con = ConPool.getConnection();
			
			Statement stmt = (Statement) con.createStatement();
	        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
	        pdstmt.setString(1, "RAM");
	        ResultSet rs = pdstmt.executeQuery();
	        while(rs.next()){
	            Ram ram = InitRamFromRs(rs);
	            list.add(ram);
	        }
	        return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }

    //Prende la lista di prodotti e fa il cast a lista di RAM
    public ArrayList<Ram> doRetriveByType() {
        RamDAO rDAO = new RamDAO();
        ArrayList<Prodotto> listP = rDAO.doRetrive();
        ArrayList<Ram> listR = new ArrayList<Ram>();
        for (Prodotto p : listP) {
            listR.add((Ram) p);
        }
        return listR;
    }

    public static void Upload(Ram r) {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			String insertion = "INSERT INTO Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Frequenza, url, Descrizione) " +
	                "VALUES (?,?,?,?,?,?,?,?)";
	        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
	        pdstmt.setString(1, r.getTipo());
	        pdstmt.setString(2, r.getMarca());
	        pdstmt.setString(3, r.getModello());
	        pdstmt.setDouble(4, r.getPrezzo());
	        pdstmt.setInt(5, r.getQuantita());
	        pdstmt.setFloat(6, r.getFrequenza());
	        pdstmt.setString(7, r.getUrl());
	        pdstmt.setString(8, r.getDescrizione());

	        pdstmt.executeUpdate();
	        ResultSet rs = pdstmt.getGeneratedKeys();
	        rs.next();
	        r.setID(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

    public static void Update(Ram r) {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "Frequenza = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement(updProd);

	        pdstmt.setString(1, r.getMarca());
	        pdstmt.setString(2, r.getModello());
	        pdstmt.setDouble(3, r.getPrezzo());
	        pdstmt.setInt(4, r.getQuantita());
	        pdstmt.setFloat(5, r.getFrequenza());
	        pdstmt.setString(6, r.getUrl());
	        pdstmt.setString(7, r.getDescrizione());
	        pdstmt.setInt(8, r.getID());
	        pdstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public static Ram InitRamFromRs(ResultSet rs) {
        Ram ram = new Ram();
        try {
			ram.setID(rs.getInt(1));
			ram.setMarca(rs.getString(2));
	        ram.setModello(rs.getString(3));
	        ram.setPrezzo(rs.getInt(4));
	        ram.setQuantita(rs.getInt(5));
	        ram.setFrequenza(rs.getInt(8));
	        ram.setUrl(rs.getString(18));
	        ram.setDescrizione(rs.getString(19));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return ram;
    }

}
