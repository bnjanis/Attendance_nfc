package com.example.attendance_nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private String username, password;
    Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        self = LoginActivity.this;

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        StudentDetail sd = new StudentDetail(self);
        if (sd.islogedIn()) {
            sd.refreshValuesFromSP();
            statAttendance();
        }
    }

    void statAttendance() {
        Intent intent = new Intent(self, Attendance.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    public void checkLogin(View arg0) {

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        new AsyncLogin(self, "login.inc.php");
    }

    private class AsyncLogin extends GlobalAsyncTask {

        AsyncLogin(Context context, String url) {
            super(context, url);
            execute();
        }

        @Override
        public Uri.Builder urlBuilder() {
            return new Uri.Builder()
                    .appendQueryParameter("IDUser", username)
                    .appendQueryParameter("password", password);
        }

        @Override
        public void goPostExecute(String result, String content) {

            if (content.equalsIgnoreCase("application/json")) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    JSONObject userObject = jsonArray.getJSONObject(0);

                    StudentDetail sd = new StudentDetail(self);
                    sd.setValues(userObject);
                    sd.setValuesInSP(userObject);
                    Toast.makeText(self, "Login Success", Toast.LENGTH_LONG).show();
                    statAttendance();
                } catch (JSONException e) {
                    Toast.makeText(self, e.toString(), Toast.LENGTH_LONG).show();

                }
            } else if (result.equalsIgnoreCase("false")) {
                Toast.makeText(self, "Invalid email or password", Toast.LENGTH_LONG).show();
            } else if (result.equalsIgnoreCase("values not set")) {
                Toast.makeText(self, "Values Not Set!", Toast.LENGTH_LONG).show();
            }
        }
    }
}