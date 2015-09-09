package ua.com.dxlab.solaris.views;

import java.net.URL;

/**
 * Created by Dima on 01.09.2015.
 */
public class SolarSystemData {

    private String mInfoText;
    private URL mImgUrl;

    public SolarSystemData(URL _imgUrl, String _textInfo) {
        this.mImgUrl = _imgUrl;
        this.mInfoText = _textInfo;
    }

    public String getInfoText() {
        return mInfoText;
    }

    public URL getImgUrl() {
        return mImgUrl;
    }

}
