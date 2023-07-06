import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.rp.c346.id21007466.p08.DBHelper;
import sg.edu.rp.c346.id21007466.p08.R;
import sg.edu.rp.c346.id21007466.p08.Song;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etSingers, etYear;
    private RadioGroup radioGroup;
    private Button btnSave, btnView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);

        dbHelper = new DBHelper(MainActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String singers = etSingers.getText().toString().trim();
                int year = Integer.parseInt(etYear.getText().toString().trim());

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                int stars = 0;
                if (selectedRadioButtonId != -1) {
                    switch (selectedRadioButtonId) {
                        case R.id.rb1:
                            stars = 1;
                            break;
                        case R.id.rb2:
                            stars = 2;
                            break;
                        case R.id.rb3:
                            stars = 3;
                            break;
                        case R.id.rb4:
                            stars = 4;
                            break;
                        case R.id.rb5:
                            stars = 5;
                            break;
                    }
                }

                dbHelper.insertSong(title, singers, year, stars);
                Toast.makeText(MainActivity.this, "Song saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Song.class);
                startActivity(intent);
            }
        });
    }
}