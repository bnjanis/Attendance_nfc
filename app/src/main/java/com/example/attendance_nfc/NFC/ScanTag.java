package com.example.attendance_nfc.NFC;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendance_nfc.Attendance;
import com.example.attendance_nfc.R;
import com.example.attendance_nfc.parser.NdefMessageParser;
import com.example.attendance_nfc.record.ParsedNdefRecord;

import java.util.List;


public class ScanTag extends AppCompatActivity {

    public static Attendance current;
    public static String IDuser;
    public static String student;
    Context self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_tag);
        setTitle("Scan NFC Tag");

        current = (Attendance) getIntent().getSerializableExtra("current");
    }

    public void scanNFC(View arg0) {

        String tagUID = "";
        String nim = "105011610030";
        long time = System.currentTimeMillis();
        SharedPreferences sp = self.getSharedPreferences("AttendanceStudentDetail", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NFC_NIM", nim);
        editor.putString("NFC_TimeMills", time + "");
        editor.putString("NFC_UID", tagUID);
        editor.apply();
    }

    // list of NFC technologies detected:
    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String tagUID = "" + ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));

//            ((TextView) findViewById(R.id.textInfo)).setText("NFC Tag\n" + tagUID);

            Toast.makeText(this, "NFC Detected", Toast.LENGTH_SHORT).show();
            Intent _intent = new Intent(this, ScanTag.class);
            _intent.putExtra("current", current);
            _intent.putExtra("tagUID", tagUID);

            startActivity(_intent);
            finish();
        }
    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }
}