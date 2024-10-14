package com.example.safesocietyalertsystem.Model;

public class User {

    String Dated;
    String Id;
    String Timed;
    String TurbiditySensor;
    String Waterlavel;


    public User(String dated, String IRvalue, String id, String timed, String turbiditySensor, String waterlavel) {
        Dated = dated;
        Id = id;
        Timed = timed;
        TurbiditySensor = turbiditySensor;
        Waterlavel = waterlavel;
    }

    public User() {
    }

    public String getDated() {
        return Dated;
    }

    public void setDated(String dated) {
        Dated = dated;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTimed() {
        return Timed;
    }

    public void setTimed(String timed) {
        Timed = timed;
    }

    public String getTurbiditySensor() {
        return TurbiditySensor;
    }

    public void setTurbiditySensor(String turbiditySensor) {
        TurbiditySensor = turbiditySensor;
    }

    public String getWaterlavel() {
        return Waterlavel;
    }

    public void setWaterlavel(String waterlavel) {
        Waterlavel = waterlavel;
    }
}
