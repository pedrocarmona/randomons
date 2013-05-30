package com.example.others;


import com.example.activities.R;

public class Tools {

    private static Tools instance;

    private Tools() {
    }

    public static Tools getInstance() {

        if(instance == null)
            instance = new Tools();

        return instance;
    }


    public int getPicId(int serverId) {

        switch(serverId) {

            case 1:
                return R.drawable.canibalape;
            case 2:
                return R.drawable.cyclosnake;
            case 3:
                return R.drawable.ponycorn;
            case 4:
                return R.drawable.catzinga;
            case 5:
                return R.drawable.chinelong;
            case 6:
                return R.drawable.tetrauros;
            default: return 0;
        }
    }

}
