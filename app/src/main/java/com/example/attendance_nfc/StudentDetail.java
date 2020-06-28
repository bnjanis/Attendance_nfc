package com.example.attendance_nfc;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Struct;

public class StudentDetail {

    public static String uid, namaMh, prodi, tahun;
    Context context;

    StudentDetail(Context context) {
        this.context = context;
    }

    void setValues(JSONObject obj) {
        try {
            uid = obj.getString("IDUser");
            namaMh = obj.getString("NamaLengkap");
            prodi = obj.getString("Prodi");
            tahun = obj.getString("TahunMasuk");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    void refreshValuesFromSP(){
        SharedPreferences sp = context.getSharedPreferences("StudentDetail", Context.MODE_PRIVATE);
        String studentDetail = sp.getString("studentDetail", "");
        try {
            JSONObject obj = new JSONObject(studentDetail);
            setValues(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setValuesInSP(JSONObject obj) {
        SharedPreferences sp = context.getSharedPreferences("StudentDetail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("studentDetail", obj.toString());
        editor.apply();
    }

    boolean islogedIn() {
        SharedPreferences sp = context.getSharedPreferences("StudentDetail", Context.MODE_PRIVATE);
        String studentDetail = sp.getString("studentDetail", "");

        return !studentDetail.isEmpty();
    }

    void clearDetails() {
        SharedPreferences sp = context.getSharedPreferences("StudentDetail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

}
