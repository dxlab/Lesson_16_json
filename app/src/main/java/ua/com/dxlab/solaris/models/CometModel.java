package ua.com.dxlab.solaris.models;

/**
 * Created by Dima on 06.09.2015.
 */
public final class CometModel extends SkyObjectModel {

    public String place;
    public String orbitalPeriod;
    public String mass;
    public String escapeVelocity;


    @Override
    public String getInfoText() {
        return "Name: " + name + '\n' +
                "Place: " + place + '\n' +
                "Orbital Period: " + orbitalPeriod+
                "Mass: " + mass + '\n' +
                "Escape Velocity: " + escapeVelocity;
    }
}
