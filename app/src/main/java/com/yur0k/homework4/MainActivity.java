package com.yur0k.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonStop;
//    private Button buttonWhat;
    private TextView textView;
    private EditText editText;
    private Button buttonSafe;
    private TextView safeText;
    SharedPreferences mSettings;

    public String APP_PREFERENCES = "mysettings";
    public String SAFE_TEXT = "SAFE TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        safeText = findViewById(R.id.text_safe_text);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
//        buttonWhat = findViewById(R.id.buttonWhat);
        textView = findViewById(R.id.textViewSeconds);
        editText = findViewById(R.id.edit_text_safe);
        buttonSafe = findViewById(R.id.button_safe_text);

        if (mSettings.contains(APP_PREFERENCES)){
            safeText.setText(mSettings.getString(APP_PREFERENCES, "Нет ничего"));
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
                textView.setText("Запустили службу и она работает в отдельном потоке");
                buttonStart.setVisibility(View.INVISIBLE);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,MyService.class));
                textView.setText("Завершили службу");
                buttonStart.setVisibility(View.VISIBLE);
            }
        });

//        buttonWhat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        buttonSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String safeTextString = editText.getText().toString();
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(SAFE_TEXT, safeTextString).apply();
                safeText.setText(safeTextString);
            }
        });
    }

}
