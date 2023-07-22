package model.Archiviazione_.SDD_;

import model.Archiviazione_.ArchivioDati;

public class Ssd extends ArchivioDati {

    public Ssd(){
        super.setTipo("SSD");
    }


    @Override
    public String toString() {
        return "SSD" + super.toString();
    }
}
