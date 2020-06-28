package com.example.attendance_nfc.NFC;

import android.app.Application;

public class StringRes extends Application {

    private String HOSTNAME = "10.218.79.113/db_skripsi/";

    public void setHOSTNAME(String HOSTNAME) {
        this.HOSTNAME = HOSTNAME;
    }

    public String getHOSTNAME() {
        return HOSTNAME;
    }
}
