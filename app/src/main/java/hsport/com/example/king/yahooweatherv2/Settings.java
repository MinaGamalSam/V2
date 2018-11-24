package hsport.com.example.king.yahooweatherv2;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private RadioButton fahrenheit, celisuis;
    private RadioGroup radioGroup;
    private String tempUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        fahrenheit=findViewById(R.id.b_fahren);
        celisuis=findViewById(R.id.b_celsius);
        radioGroup=findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.b_celsius:
                        Toast.makeText(Settings.this, "cl", Toast.LENGTH_LONG).show();
                      tempUnit="celsius";
                        break;
                    case R.id.b_fahren:
                        Toast.makeText(Settings.this, "fa", Toast.LENGTH_LONG).show();
                        tempUnit="fahren";

                        break;
                }


            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("temp",tempUnit);
        editor.commit();

      /*  if(celisuis.isChecked()){
            *//*double celisuisTemp =(temperature - 32) * 5/9;
            result.setText("Result is: "+ celisuisTemp +" C" );*//*

        }else if(fahrenheit.isChecked()){
           *//* double faherhnietTemp =  (temperature * 9/5) + 32;
            result.setText("Result is: " + faherhnietTemp +" F" );*//*

        }else {
            Toast.makeText(this, "You don't choose the unit", Toast.LENGTH_SHORT).show();
        }*/

    }


}
