package info.krakovsky.memorableplaces;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class Locations {
    private static final Locations ourInstance = new Locations();

    public static Locations getInstance() {
        return ourInstance;
    }



    private ArrayList<String> mPlacesNames = new ArrayList(asList("Mall Beer Yaakov","Tse'elon School"));
    private ArrayList<Double> mPlacesLat = new ArrayList(asList(31.9418461,31.9434914));
    private ArrayList<Double> mPlacesLon = new ArrayList(asList(31.9393398,34.8297042));

    private Locations() {
    }

    public ArrayList<String> getPlacesNames() {
        return mPlacesNames;
    }
    public ArrayList<Double> getPlacesLat() {
        return mPlacesLat;
    }
    public ArrayList<Double> getPlacesLon() {
        return mPlacesLon;
    }
    public void addLocation(String name,double lat,double lon){
        mPlacesNames.add(name);
        mPlacesLat.add(lat);
        mPlacesLon.add(lon);
    }
}
