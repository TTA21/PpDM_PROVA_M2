package com.example.provam2.data_structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teams {

    private ArrayList<Users> team = null;

    public ArrayList<Users> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Users> team) {
        this.team = team;
    }
}
