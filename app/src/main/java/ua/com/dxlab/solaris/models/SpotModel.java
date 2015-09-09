package ua.com.dxlab.solaris.models;

import android.graphics.Bitmap;

/**
 * Created by Dima on 06.09.2015.
 */
public final class SpotModel extends SkyObjectModel {

    public String age;
    public String mass;
    public String planetsCount;
    public String orbitalSpeed;

    @Override
    public String getInfoText() {
       return  "Name: " + name + '\n' +
                "Age: " + age + '\n' +
                "Mass: " + mass + '\n' +
                "Planets: " + planetsCount + '\n' +
                "Orbital Speed: " + orbitalSpeed;
    }
}
