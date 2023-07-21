package Model;

import Model.Archiviazione_.HDD_.Hdd;
import Model.Archiviazione_.HDD_.HddDAO;
import Model.Archiviazione_.SDD_.Ssd;
import Model.Archiviazione_.SDD_.SsdDAO;
import Model.CASE_.Case;
import Model.CASE_.CaseDAO;
import Model.CPU_.Cpu;
import Model.CPU_.CpuDAO;
import Model.DISSIPATORE_.Dissipatore;
import Model.DISSIPATORE_.DissipatoreDAO;
import Model.GPU_.Gpu;
import Model.GPU_.GpuDAO;
import Model.MOBO_.Mobo;
import Model.MOBO_.MoboDAO;
import Model.PSU_.Psu;
import Model.PSU_.PsuDAO;
import Model.RAM_.Ram;
import Model.RAM_.RamDAO;

import java.sql.*;
import java.util.ArrayList;

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

    //Aggiorna info prodotto (riservato admin)
    /*public static void Update(int id, String marca, String modello,
                              Double prezzo, Integer quantita, Integer wattaggio,
                              Float frequenza, Integer N_Core, Integer N_Ram, Integer N_Usb, Integer N_Pci,
                              Integer MBs, Integer Vram, Integer N_Watt, Integer W_Cpu, Short formaMobo,
                              String url, String desc) throws SQLException {
        //Inizializza la stringa di connessione
        String updProd = "UPDATE Pezzo "+
                "SET Marca = ?, Modello = ?, Prezzo = ?, Quantita = ?, " +
                "Wattaggio = ?, Frequenza = ?, N_Core = ?, N_Ram = ?, N_USB = ?, N_PCI = ?, MBs = ?, " +
                "VRAM = ?, N_Watt = ?, W_Cpu = ?, FormaMobo = ?, url = ?, Descrizione = ? " +
                "WHERE Id = ?";

        Connection con = ConPool.getConnection();
        PreparedStatement pdstmt = con.prepareStatement(updProd);
        //Se l'oggetto passato non è null ne viene inserito il valore nela query, altrimenti viene inserito null in quella posizione
        if (marca != null) pdstmt.setString(1, marca);
        else pdstmt.setNull(1, Types.VARCHAR);

        if (modello != null) pdstmt.setString(2, modello);
        else pdstmt.setNull(2, Types.VARCHAR);

        if (prezzo != null) pdstmt.setDouble(3, prezzo);
        else pdstmt.setNull(3, Types.DOUBLE);

        if (quantita != null) pdstmt.setInt(4, quantita);
        else pdstmt.setNull(4, Types.INTEGER);

        if (wattaggio != null) pdstmt.setInt(5, wattaggio);
        else pdstmt.setNull(5, Types.INTEGER);

        if (frequenza != null) pdstmt.setFloat(6, frequenza);
        else pdstmt.setNull(6, Types.FLOAT);

        if (N_Core != null) pdstmt.setInt(7, N_Core);
        else pdstmt.setNull(7, Types.INTEGER);

        if (N_Ram != null) pdstmt.setInt(8, N_Ram);
        else pdstmt.setNull(8, Types.INTEGER);

        if (N_Usb != null) pdstmt.setInt(9, N_Usb);
        else pdstmt.setNull(9, Types.INTEGER);

        if (N_Pci != null) pdstmt.setInt(10, N_Pci);
        else pdstmt.setNull(10, Types.INTEGER);

        if (MBs != null) pdstmt.setInt(11, MBs);
        else pdstmt.setNull(11, Types.INTEGER);

        if (Vram != null) pdstmt.setInt(12, Vram);
        else pdstmt.setNull(12, Types.INTEGER);

        if (N_Watt != null) pdstmt.setInt(13, N_Watt);
        else pdstmt.setNull(13, Types.INTEGER);

        if (W_Cpu != null) pdstmt.setInt(14, W_Cpu);
        else pdstmt.setNull(14, Types.INTEGER);

        if (formaMobo != null) pdstmt.setShort(15, formaMobo);
        else pdstmt.setNull(15, Types.SMALLINT);

        if (url != null) pdstmt.setString(16, url);
        else pdstmt.setNull(16, Types.VARCHAR);

        if (desc != null) pdstmt.setString(17, desc);
        else pdstmt.setNull(17, Types.VARCHAR);

        pdstmt.setInt(18, id);

        pdstmt.executeUpdate();*/
    public void Update (Prodotto p) throws SQLException {

    }

    public static ArrayList<Prodotto> doRetriveListaIdProdotti(ArrayList<Integer> listaCodiciProdotti) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
        for(Integer i : listaCodiciProdotti){
            prodotti.add(doRetriveById(i));
        }
        return prodotti;
    }
}
