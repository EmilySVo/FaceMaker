/**
 * @author Emily Vo
 * Date: September 9th, 2020
 */

package edu.up.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //create the string of items wanted in the spinner
            String[] arraySpinner = new String[]{"Mohawk", "Reverse Mohawk", "Spiky", "Bald"};

        //create the spinner and attach it to the view id
            Spinner hairStyle = findViewById(R.id.spinner);
            //create the adapter so that the spinner can use the array as it's contents
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            hairStyle.setAdapter(adapter);

                /**
                 * External Citation
                 * Date: September 9, 2020
                 * Problem: Did not remember how to apply the array to the spinner
                 * Resource: https://stackoverflow.com/questions/2390102/
                 *           how-to-set-selected-item-of-spinner-by-value-not-by-position
                 * Solution: Example code helped me remember the function required to
                 *           apply the array to the spinner.
                 */
        }
    }