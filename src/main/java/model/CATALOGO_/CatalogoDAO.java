package model.CATALOGO_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConPool;
import model.Prodotto;
import model.ProdottoDAO;
import model.Archiviazione_.HDD_.Hdd;
import model.Archiviazione_.HDD_.HddDAO;
import model.Archiviazione_.SDD_.Ssd;
import model.Archiviazione_.SDD_.SsdDAO;
import model.CASE_.Case;
import model.CASE_.CaseDAO;
import model.CPU_.Cpu;
import model.CPU_.CpuDAO;
import model.Carrello_.Carrello;
import model.DISSIPATORE_.Dissipatore;
import model.DISSIPATORE_.DissipatoreDAO;
import model.GPU_.Gpu;
import model.GPU_.GpuDAO;
import model.MOBO_.Mobo;
import model.MOBO_.MoboDAO;
import model.PSU_.Psu;
import model.PSU_.PsuDAO;
import model.RAM_.Ram;
import model.RAM_.RamDAO;

public class CatalogoDAO {
    public List<Prodotto> doRetriveAll()  {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement("SELECT * FROM Pezzo");
	        ResultSet rs = pdstmt.executeQuery();
	        List<Prodotto> catalogo = new ArrayList<Prodotto>();
	        while (rs.next()) {
	            String type = rs.getString(7);
	            switch (type) {
	                case "CPU": {
	                    Cpu cpu = CpuDAO.InitCpuFromRs(rs);
	                    catalogo.add(cpu);
	                    break;
	                }
	                case "MOBO": {
	                    Mobo mobo = MoboDAO.InitMoboFromRs(rs);
	                    catalogo.add(mobo);
	                    break;
	                }
	                case "CASE": {
	                    Case case_ = CaseDAO.InitCaseFromRs(rs);
	                    catalogo.add(case_);
	                    break;
	                }
	                case "DISSIPATORE": {
	                    Dissipatore diss = DissipatoreDAO.InitDissipatoreFromRs(rs);
	                    catalogo.add(diss);
	                    break;
	                }
	                case "GPU": {
	                    Gpu gpu = GpuDAO.InitGpuFromRs(rs);
	                    catalogo.add(gpu);
	                    break;
	                }
	                case "PSU": {
	                    Psu psu = PsuDAO.InitPsuFromRs(rs);
	                    catalogo.add(psu);
	                    break;
	                }
	                case "RAM": {
	                    Ram ram = RamDAO.InitRamFromRs(rs);
	                    catalogo.add(ram);
	                    break;
	                }
	                case "HDD": {
	                    Hdd hdd = HddDAO.InitHddFromRs(rs);
	                    catalogo.add(hdd);
	                    break;
	                }
	                case "SSD": {
	                    Ssd ssd = SsdDAO.InitSsdFromRs(rs);
	                    catalogo.add(ssd);
	                    break;
	                }
	            }
	        }
	        return catalogo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
    }

    //Scala un prodotto dal db dato un id
    public void scalaProdotto(Prodotto p) {
        Connection con;
		try {
			con = ConPool.getConnection();
			
			PreparedStatement pdstmt = con.prepareStatement("UPDATE Pezzo SET Quantita = Quantita - ? WHERE Id = ?");
	        pdstmt.setInt(1, p.getQuantita());
	        pdstmt.setInt(2, p.getID());
	        pdstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    //Scala una lista di prodotti dal db dato un carrello
    public void scalaProdotti(Carrello c) {
        CatalogoDAO service = new CatalogoDAO();
        for(Prodotto p : c.getCarrello()){
            service.scalaProdotto(p);
        }
    }
}

