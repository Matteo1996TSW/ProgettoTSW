package model.Cliente_;

public class Cliente {
    private String Mail, Pass, Nickname, Via, Provincia, Citta, Tel;
    private int Cap;
    private Boolean Administrator;

    public  Cliente () {}

   

    public void setPass(String pass) {
        Pass = pass;
    }

    public Boolean getAdministrator() {
        return Administrator;
    }

    public void setAdministrator(Boolean administrator) {
        Administrator = administrator;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPass() {
        return Pass;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getVia() {
        return Via;
    }

    public void setVia(String via) {
        Via = via;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public Boolean isAdministrator() {
        return Administrator;
    }

    public String getCitta() {
        return Citta;
    }

    public void setCitta(String citta) {
        Citta = citta;
    }

    public int getCap() {
        return Cap;
    }

    public void setCap(int cap) {
        Cap = cap;
    }

    public void setPassword(String password) {
        Pass = password;
    }
}
