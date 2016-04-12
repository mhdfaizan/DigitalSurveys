package com.example.mohammadfaizan.marsroverproblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText upper_right_coordinates, rover_one_start, rover_one_input, rover_two_start, rover_two_input;
    TextView output;
    Button getOutput;
    int grid_position_x = 0;
    int grid_position_y = 0;
    int position_x = 0;
    int position_y = 0;
    String direction;
    String turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upper_right_coordinates = (EditText) findViewById(R.id.upper_right_coordinates);
        rover_one_start = (EditText) findViewById(R.id.rover_one_start);
        rover_one_input = (EditText) findViewById(R.id.rover_one_input);
        rover_two_start = (EditText) findViewById(R.id.rover_two_start);
        rover_two_input = (EditText) findViewById(R.id.rover_two_input);
        getOutput = (Button) findViewById(R.id.getOutput);
        output = (TextView) findViewById(R.id.output);

        getOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    explorationSolver();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void explorationSolver(){
        try {
            String coordinates = upper_right_coordinates.getText().toString();
            grid_position_x = Integer.valueOf(coordinates.substring(0,1));
            grid_position_y = Integer.valueOf(coordinates.substring(2,2));
            Toast.makeText(MainActivity.this, grid_position_x + " " +grid_position_y, Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
