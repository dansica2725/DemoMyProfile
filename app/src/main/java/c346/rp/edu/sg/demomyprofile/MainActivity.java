package c346.rp.edu.sg.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText nameEt, gpaEt;
    RadioGroup rgGender;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    String name, gpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.nameEt);
        gpaEt = findViewById(R.id.gpaEt);
        rgGender = findViewById(R.id.rgGender);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (nameEt.getText().toString().length() > 0 && gpaEt.getText().toString().length() > 0) {
            name = nameEt.getText().toString();
            gpa = gpaEt.getText().toString();
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        editor = prefs.edit();

        editor.putString("Name", name);
        editor.putString("GPA", gpa);
        editor.putInt("Gender", rgGender.getCheckedRadioButtonId());

        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name = prefs.getString("Name", "");
        String gpa = prefs.getString("GPA", "");
        int gender = prefs.getInt("Gender", 0);
        nameEt.setText(name);
        gpaEt.setText(gpa);
        rgGender.check(gender);

    }
}
