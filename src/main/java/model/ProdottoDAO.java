package model;

import java.sql.*;
import java.util.ArrayList;

import model.Archiviazione_.HDD_.Hdd;
import model.Archiviazione_.HDD_.HddDAO;
import model.Archiviazione_.SDD_.Ssd;
import model.Archiviazione_.SDD_.SsdDAO;
import model.CASE_.Case;
import model.CASE_.CaseDAO;
import model.CPU_.Cpu;
import model.CPU_.CpuDAO;
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

public abstract class ProdottoDAO {

    //Carica un prodotto nel db

    //Metodo per prendere un prodotto dal database dato l'ID
    public static Prodotto doRetriveById(Integer ID) throws SQLException {
        int x = ID;
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Pezzo WHERE Id = ?");
        ps.setInt(1, x);
        ResultSet rs = ps.executeQuery();
        rs.next();
            String type = rs.getString(7);
            //In base al valore contenuto nel parametro type viene istanziato un oggetto di quella classe
            switch (type) {
                case "CPU":{
                    //return new Cpu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(8), rs.getInt(9), rs.getString(18), rs.getString(19));
                    return CpuDAO.InitCpuFromRs(rs);
                }
                case "MOBO":{
                    Mobo mobo = MoboDAO.InitMoboFromRs(rs);
                    return mobo;
                }
                case "CASE":{
                    return CaseDAO.InitCaseFromRs(rs);
                }
                case "DISSIPATORE":{
                    return DissipatoreDAO.InitDissipatoreFromRs(rs);
                }
                case "GPU":{
                    return GpuDAO.InitGpuFromRs(rs);
                }
                case "PSU":{
                    //return new Psu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(15), rs.getString(18), rs.getString(19));
                    return PsuDAO.InitPsuFromRs(rs);
                }
                case "RAM":{
                    return RamDAO.InitRamFromRs(rs);
                }
                case "HDD":{
                    return HddDAO.InitHddFromRs(rs);
                }
                case "SSD":{
                    return SsdDAO.InitSsdFromRs(rs);
                }
                default:{
                    return null;
                }
        }
    }

    //Elimina un prodotto dal DB
    public static void elimina(int id) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = con.createStatement();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Pezzo WHERE Id=?");
        ps.setString(1, String.valueOf(id));
        ps.executeUpdate();
    }

    public static boolean doCheckDisponibilita(Prodotto p) throws SQLException {
        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement("SELECT Quantita FROM Comporre WHERE PezzoID = ?");
        pdstmt.setInt(1, p.getID());
        ResultSet rs = pdstmt.executeQuery();
        Integer quantita = null;
        while (rs.next()) {
            quantita = rs.getInt(1);
        }
        assert quantita != null;//Evita null pointer exception
        return quantita < p.getQuantita();
    }

   
    public void Update (Prodotto p) throws SQLException {}

    public static ArrayList<Prodotto> doRetriveListaIdProdotti(ArrayList<Integer> listaCodiciProdotti) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        for(Integer i : listaCodiciProdotti){
            prodotti.add(doRetriveById(i));
        }
        return prodotti;
    }
}
