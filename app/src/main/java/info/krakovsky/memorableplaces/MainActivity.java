package info.krakovsky.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView myListView = findViewById(R.id.plcaeListView);
        final ArrayList<String> myPlacesNames = new ArrayList(asList("Mall Beer Yaakov","Tse'elon School"));
        final ArrayList<Double> myPlacesLat = new ArrayList(asList(31.9418461,31.9434914));
        final ArrayList<Double> myPlacesLon = new ArrayList(asList(34.8359396,34.8372788));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myPlacesNames);
        myListView.setAdapter(arrayAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("name",myPlacesNames.get(position));
                intent.putExtra("lat",myPlacesLat.get(position));
                intent.putExtra("lon",myPlacesLon.get(position));
                startActivity(intent);
            }
        });
    }

    public void goToMap(View view) {
        Intent mapIntent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(mapIntent);
    }
}
