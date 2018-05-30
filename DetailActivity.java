package org.targetyou.targetyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.MalformedURLException;

public class DetailActivity extends AppCompatActivity {

    private EditText name;
    String email, password, sex, age, high, weight;
    private Spinner spinner1, spinner2, spinner3, spinner4;
    private Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("                        ABOUT YOU");
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        spinner1 = findViewById(R.id.sex_spinner);
        spinner2 = findViewById(R.id.weight_spinner);
        spinner3 = findViewById(R.id.age_spinner);
        spinner4 = findViewById(R.id.height_spinner);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.weight, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this, R.array.height, android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = (String) spinner1.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weight = (String) spinner2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age = (String) spinner3.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner4.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                high = (String) spinner4.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        NetworkUtil.setNetworkPolicy();
        name = findViewById(R.id.editName);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PHPRequest request = new PHPRequest("phpURL");
                    String result = request.PhPtest(String.valueOf(name.getText()),sex, age, high, weight, email, password);
                    if(result.equals("success")){
                        Toast.makeText(getApplication(),"저장되었습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplication(),"저장에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}


