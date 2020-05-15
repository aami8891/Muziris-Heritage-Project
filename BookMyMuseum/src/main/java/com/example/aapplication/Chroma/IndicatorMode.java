package com.example.aapplication.Chroma;


public enum IndicatorMode {
    DECIMAL(0), HEX(1);

    private int i;

    IndicatorMode(int id) {
        i=id;
    }

    public int getId() {
        return i;
    }

    public static IndicatorMode getIndicatorModeFromId(int id) {
        switch (id) {
            default:
            case 0:
                return DECIMAL;
            case 1:
                return HEX;
        }
    }
}
