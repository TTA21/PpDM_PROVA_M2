package com.example.provam2.data_structures;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private ArrayList<Teams> game = null;

    public ArrayList<Teams> getGame() {
        return game;
    }

    public void setGame(ArrayList<Teams> game) {
        this.game = game;
    }
}
