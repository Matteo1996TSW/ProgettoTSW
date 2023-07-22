package Model.CASE_;


import Model.ConPool;
import Model.Prodotto;
import Model.ProdottoDAO;

import java.sql.*;
import java.util.ArrayList;

public class CaseDAO {

    private ArrayList<Prodotto> doRetrive() {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con;
		try {
			con = ConPool.getConnection();
			
			Statement stmt = (Statement) con.createStatement();
	        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM pezzo WHERE Tipo = ? ");
	        pdstmt.setString(1, "CASE");
	        ResultSet rs = pdstmt.executeQuery();
	        while(rs.next()){
	            Case case_ = InitCaseFromRs(rs);
	            list.add(case_);
	        }
	        return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
        
    }

    //Prende la lista di prodotti e fa il cast a lista di Case
    public ArrayList<Case> doRetriveByType(){
        CaseDAO cDAO = new CaseDAO();
        ArrayList<Prodotto> listP = cDAO.doRetrive();
        ArrayList<Case> listC = new ArrayList<Case>();
        for(Prodotto p : listP){
            listC.add((Case) p);
            System.out.println("eccomi"+p.getDescrizione());
        }
        return listC;
    }

    public static void Upload(Case c)  {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, FormaMobo, url, Descrizione) " +
	                "VALUES (?,?,?,?,?,?,?,?)";
	        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
	        pdstmt.setString(1, c.getTipo());
	        pdstmt.setString(2, c.getMarca());
	        pdstmt.setString(3, c.getModello());
	        pdstmt.setDouble(4, c.getPrezzo());
	        pdstmt.setInt(5, c.getQuantita());
	        pdstmt.setFloat(6, c.getFormaMobo());
	        pdstmt.setString(7, c.getUrl());
	        pdstmt.setString(8, c.getDescrizione());

	        pdstmt.executeUpdate();
	        ResultSet rs = pdstmt.getGeneratedKeys();
	        rs.next();
	        c.setID(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

    public static void Update(Case c) {
        String updProd = "UPDATE Pezzo "+
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "FormaMobo = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement(updProd);

	        pdstmt.setString(1, c.getMarca());
	        pdstmt.setString(2, c.getModello());
	        pdstmt.setDouble(3, c.getPrezzo());
	        pdstmt.setInt(4, c.getQuantita());
	        pdstmt.setInt(5, c.getFormaMobo());
	        pdstmt.setString(6, c.getUrl());
	        pdstmt.setString(7, c.getDescrizione());
	        pdstmt.setInt(8, c.getID());
	        pdstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public static Case InitCaseFromRs(ResultSet rs) {
        Case case_ = new Case();
        try {
        	case_.setID(rs.getInt(1));
            case_.setMarca(rs.getString(2));
            case_.setModello(rs.getString(3));
            case_.setPrezzo(rs.getInt(4));
            case_.setQuantita(rs.getInt(5));
            case_.setFormaMobo(rs.getShort(17));
            case_.setUrl(rs.getString(18));
            case_.setDescrizione(rs.getString(19));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return case_;
    }

}
