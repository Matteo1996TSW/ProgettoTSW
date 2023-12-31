package model.CPU_;

import java.sql.*;
import java.util.ArrayList;

import model.ConPool;
import model.Prodotto;
import model.ProdottoDAO;
import model.CASE_.Case;
import model.RAM_.Ram;

public class CpuDAO {

    public CpuDAO(){
        super();
    }

    //Interroga il DB per avere una lista di prodotti di tipo CPU
    private ArrayList<Prodotto> doRetrive()  {
        ArrayList<Prodotto> list = new ArrayList<>();
        Connection con;
		try {
			con = ConPool.getConnection();
			
	        PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo WHERE Tipo = ? ");
	        pdstmt.setString(1, "CPU");
	        ResultSet rs = pdstmt.executeQuery();
	        while(rs.next()){
	            Cpu cpu = InitCpuFromRs(rs);
	            list.add(cpu);
	        }
	        return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }

    //Prende la lista di prodotti e fa il cast a lista di cpu
    public ArrayList<Cpu> doRetriveByType(){
        CpuDAO cDAO = new CpuDAO();
        ArrayList<Prodotto> listP = cDAO.doRetrive();
        ArrayList<Cpu> listC = new ArrayList<Cpu>();
        for(Prodotto p : listP){
            listC.add((Cpu) p);
        }
        return listC;
    }
    public static void Upload(Cpu c) {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			String insertion = "insert into Pezzo (tipo, Marca, Modello, Prezzo, Quantita, Wattaggio, Frequenza, N_Core, url, Descrizione) " +
	                "VALUES (?,?,?,?,?,?,?,?,?,?)";
	        PreparedStatement pdstmt = con.prepareStatement(insertion, Statement.RETURN_GENERATED_KEYS);
	        pdstmt.setString(1, c.getTipo());
	        pdstmt.setString(2, c.getMarca());
	        pdstmt.setString(3, c.getModello());
	        pdstmt.setDouble(4, c.getPrezzo());
	        pdstmt.setInt(5, c.getQuantita());
	        pdstmt.setInt(6, c.getWattaggio());
	        pdstmt.setFloat(7, c.getFrequenza());
	        pdstmt.setInt(8, c.getN_Core());
	        pdstmt.setString(9, c.getUrl());
	        pdstmt.setString(10, c.getDescrizione());

	        pdstmt.executeUpdate();
	        ResultSet rs = pdstmt.getGeneratedKeys();
	        rs.next();
	        c.setID(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

    public static void Update(Cpu c) {
        String updProd = "UPDATE Pezzo " +
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "Wattaggio = ?, Frequenza = ?, N_Core = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement(updProd);

	        pdstmt.setString(1, c.getMarca());
	        pdstmt.setString(2, c.getModello());
	        pdstmt.setDouble(3, c.getPrezzo());
	        pdstmt.setInt(4, c.getQuantita());
	        pdstmt.setDouble(5, c.getWattaggio());
	        pdstmt.setFloat(6, c.getFrequenza());
	        pdstmt.setInt(7, c.getN_Core());
	        pdstmt.setString(8, c.getUrl());
	        pdstmt.setString(9, c.getDescrizione());
	        pdstmt.setInt(10, c.getID());
	        pdstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    public static Cpu InitCpuFromRs(ResultSet rs) {
        Cpu cpu = new Cpu();
        try {
			cpu.setID(rs.getInt(1));
			cpu.setMarca(rs.getString(2));
		    cpu.setModello(rs.getString(3));
		    cpu.setPrezzo(rs.getInt(4));
		    cpu.setQuantita(rs.getInt(5));
		    cpu.setWattaggio(rs.getInt(6));
		    cpu.setFrequenza(rs.getFloat(8));
		    cpu.setN_Core(rs.getInt(9));
		    cpu.setUrl(rs.getString(18));
		    cpu.setDescrizione(rs.getString(19));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        return cpu;

    }

}
