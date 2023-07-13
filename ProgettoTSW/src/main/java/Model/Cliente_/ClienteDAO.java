package Model.Cliente_;


import Model.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class ClienteDAO {

    public void uploadCliente(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        String ins = "INSERT INTO Cliente (Nickname, Mail, Pass, Tel, Via, Provincia, Citta, Cap) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pdstmt = con.prepareStatement(ins);
        pdstmt.setString(1, c.getNickname());
        pdstmt.setString(2, c.getMail());
        pdstmt.setString(3, c.getPass());
        pdstmt.setString(4, c.getTel());
        pdstmt.setString(5, c.getVia());
        pdstmt.setString(6, c.getProvincia());
        pdstmt.setString(7, c.getCitta());
        pdstmt.setInt(8, c.getCap());
        pdstmt.executeUpdate();
    }

    public boolean updateInfoSpedizioneCliente(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        String upd = "UPDATE Cliente SET Via=?, Provincia=?, Citta=?, Cap=? WHERE Mail=?";
        PreparedStatement pdstmt = con.prepareStatement(upd);
        pdstmt.setString(1, c.getVia());
        pdstmt.setString(2, c.getProvincia());
        pdstmt.setString(3, c.getCitta());
        pdstmt.setInt(4, c.getCap());
        pdstmt.setString(5, c.getMail());

        int success = pdstmt.executeUpdate();
        //Return success if != 0
        return success != 0;
    }

    public boolean updateInfoPersonaliCliente(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        //Controllo se il nick richiesto è disponibile
        if(isUniqueNick(c.getNickname())) {
            String upd = "UPDATE Cliente SET Nickname=?, Tel=? WHERE Mail=?";
            PreparedStatement pdstmt = con.prepareStatement(upd);
            pdstmt.setString(1, c.getNickname());
            pdstmt.setString(2, c.getTel());
            pdstmt.setString(3, c.getMail());
            int success = pdstmt.executeUpdate();
            //Return true if != 0
            return success != 0;
        }
        return false;
    }

    private boolean isUniqueNick(String nickname) throws SQLException {
        Connection con = ConPool.getConnection();
        String ceck = "SELECT Nickname FROM Cliente WHERE Nickname=?";
        PreparedStatement pdstmt = con.prepareStatement(ceck);
        pdstmt.setString(1, nickname);
        ResultSet rs = pdstmt.executeQuery();

        int cont = 0;
        while (rs.next()) {
            cont++;
        }
        //True se <= 0
        return cont <= 0;
    }

    public Cliente doRetrieveByMail(String m) throws SQLException {
        Cliente cliente = null;
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String select = "SELECT * FROM Cliente WHERE Mail = '" + m + "'";
        PreparedStatement pdstmt = con.prepareStatement(select);
        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            cliente = InitClienteFromRs(rs);
        }
        return cliente;
    }

    public ArrayList<Cliente> doRetrieveAll() throws SQLException{
        ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String select = "SELECT * FROM Cliente";
        PreparedStatement pdstmt = con.prepareStatement(select);
        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            Cliente c = InitClienteFromRs(rs);
            listaClienti.add(c);
        }
        return listaClienti;
    }

    public void doRemove(String mail) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        String delete = "DELETE FROM Cliente where Mail = " + mail + " ;";
        PreparedStatement pdstmt = con.prepareStatement(delete);
        ResultSet rs = pdstmt.executeQuery();
    }

    public boolean isCorrectLogin(String mail, String pass) throws SQLException {
            Connection con = ConPool.getConnection();
            Statement stmt = (Statement) con.createStatement();
            PreparedStatement pdstmt = con.prepareStatement("SELECT Pass FROM Cliente WHERE Mail = ? ");
            pdstmt.setString(1, mail);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(pass)) {
                    return true;
                }
            }
        return false;
    }

    public static boolean isAdministrator(String mail) {
        try {
            Connection con = ConPool.getConnection();
            Statement stmt = (Statement) con.createStatement();
            PreparedStatement pdstmt = con.prepareStatement("SELECT Administrator FROM Cliente WHERE Mail = ? ");
            pdstmt.setString(1, mail);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean(1)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void updatePassword(Cliente c) throws SQLException {
        Connection con = ConPool.getConnection();
        Statement stmt = (Statement) con.createStatement();
        PreparedStatement pdstmt = con.prepareStatement("UPDATE Cliente SET Pass=? WHERE Mail = ? ");
        pdstmt.setString(1, c.getPass());
        pdstmt.setString(2, c.getMail());
        pdstmt.executeUpdate();
    }

    public static Cliente InitClienteFromRs(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setMail(rs.getString(1));
        cliente.setPass(rs.getString(2));
        cliente.setNickname(rs.getString(3));
        cliente.setTel(rs.getString(4));
        cliente.setVia(rs.getString(5));
        cliente.setProvincia(rs.getString(6));
        cliente.setCitta(rs.getString(7));
        cliente.setCap(rs.getInt(8));
        cliente.setAdministrator(rs.getBoolean(9));
        return cliente;
    }
}
