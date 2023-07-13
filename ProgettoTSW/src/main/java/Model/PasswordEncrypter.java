package Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Cripta una stringa con SHA-512 per maggiore sicurezza
public class PasswordEncrypter {
    public static String encryptThisString(String input) {
        String hashtext = null;
        try {
            //Il metodo getnstance è chiamato con l'algoritmo SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            //Il metodo digest() è chiamato per calcolare il digest
            //dell'imput string, restituito come un array di byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Converte l'array di byte in un signum
            BigInteger no = new BigInteger(1, messageDigest);

            // Converte il digest in esadecimale
            hashtext = no.toString(16);

            // Aggiunge padding per arrivare a 32 cifre
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // return the HashText
        return hashtext;
    }
}
