package com.example.attendance_nfc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Attendance implements Serializable {

    String IDUser, NamaLengkap, Prodi, TahunMasuk, Photos, JamMasuk, JamKeluar;

    Attendance parseObject(JSONObject data) throws JSONException{
        Attendance att = new Attendance();

        att.IDUser = data.getString("IDUser");
        att.NamaLengkap = data.getString("NamaLengkap");
        att.TahunMasuk = data.getString("TahunMasuk");
        att.Prodi = data.getString("Prodi");
        att.Photos = data.getString("Photos");

        String jmasuk = data.getString("JamMasuk");
        String jkeluar = data.getString("JamKeluar");
        att.JamMasuk = jmasuk.substring(0, jmasuk.length() - 3);
        att.JamKeluar = jkeluar.substring(0, jkeluar.length() - 3);
        return att;
    }

    List<Attendance> parseList(JSONArray jsonArray) throws  JSONException {
        List<Attendance> list = new ArrayList<>();

        for (int i = 0; i<jsonArray.length(); i++){
            JSONObject json_data = jsonArray.getJSONObject(i);
            list.add(parseObject(json_data));
        }

        return list;
    }
}
