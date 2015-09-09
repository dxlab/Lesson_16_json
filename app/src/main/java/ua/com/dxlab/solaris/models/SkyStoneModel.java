package ua.com.dxlab.solaris.models;

/**
 * Created by Dima on 08.09.2015.
 */
public class SkyStoneModel extends SkyObjectModel {

    public String place;
    public String type;
    public String mass;
    public String orbitalSpeed;
    public String orbitalPeriod;
    public String escapeVelocity;
    public String surfaceGravity;

    @Override
    public String getInfoText() {
        return "Name: " + name + '\n' +
                "Place: " + place + '\n' +
                "Type: " + type + '\n' +
                "Mass: " + mass + '\n' +
                "Orbital Speed: " + orbitalSpeed + '\n' +
                "Orbital Period: " + orbitalPeriod + '\n'+
                "Escape Velocity: " + escapeVelocity + '\n'+
                "Surface Gravity: " + surfaceGravity;
    }

}
