package ua.com.dxlab.solaris;

import ua.com.dxlab.solaris.models.SolarSystemModel;

/**
 * Created by Dima on 30.08.2015.
 */
public interface SolarSystemCallbackLoading {
    void onSuccess(SolarSystemModel solarSystemModel);
    void onFailure(String errorMessage);
}
