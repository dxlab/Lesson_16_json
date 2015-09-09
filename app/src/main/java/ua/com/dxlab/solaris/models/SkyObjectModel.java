package ua.com.dxlab.solaris.models;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dima on 07.09.2015.
 */
abstract public class SkyObjectModel implements SkyModel {

    public String name;
    public String imgUrl;

    abstract public String getInfoText();

    public URL getImgUrl() {
        try {
            return new URL(imgUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
