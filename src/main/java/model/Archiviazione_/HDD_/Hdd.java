package model.Archiviazione_.HDD_;

import model.Archiviazione_.ArchivioDati;
import model.Archiviazione_.ArchivioDatiDAO;

public class Hdd extends ArchivioDati {

    public Hdd(){
        super.setTipo("HDD");
    }

   
    @Override
    public String toString() {
        return "HDD" + super.toString();
    }
}