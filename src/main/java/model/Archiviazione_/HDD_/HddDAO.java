package model.Archiviazione_.HDD_;

import java.sql.*;
import java.util.ArrayList;

import model.ConPool;
import model.Prodotto;
import model.ProdottoDAO;
import model.Archiviazione_.ArchivioDati;
import model.Archiviazione_.ArchivioDatiDAO;
import model.RAM_.Ram;

public class HddDAO extends ArchivioDatiDAO {

    private ArrayList<Prodotto> doRetrive() {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con;
		try {
			con = ConPool.getConnection();
			
		
		        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
		        pdstmt.setString(1, "HDD");
		        ResultSet rs = pdstmt.executeQuery();
		        while(rs.next()){
		            Hdd hdd = InitHddFromRs(rs);
		            list.add(hdd);
		        }
		        return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
       
    }

    //Prende la lista di prodotti e fa il cast a lista di HDD
    public ArrayList<Hdd> doRetriveByType(){
        HddDAO hDAO = new HddDAO();
        ArrayList<Prodotto> listP = hDAO.doRetrive();
        ArrayList<Hdd> listH = new ArrayList<Hdd>();
        for(Prodotto p : listP){
            listH.add((Hdd) p);
        }
        return listH;
    }

    public static Hdd InitHddFromRs(ResultSet rs)  {
        Hdd hdd = new Hdd();
        try {
			hdd.setID(rs.getInt(1));
			hdd.setMarca(rs.getString(2));
	        hdd.setModello(rs.getString(3));
	        hdd.setPrezzo(rs.getInt(4));
	        hdd.setQuantita(rs.getInt(5));
	        hdd.setMBs(rs.getInt(13));
	        hdd.setUrl(rs.getString(18));
	        hdd.setDescrizione(rs.getString(19));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return hdd;
    }

}