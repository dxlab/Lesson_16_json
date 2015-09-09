package ua.com.dxlab.solaris;

import ua.com.dxlab.solaris.models.BugsModel;

/**
 * Created by Dima on 05.09.2015.
 */
public interface CommentsCallbackLoading {
    void onSuccess(BugsModel bugsModel);
    void onFailure(String errorMessage);
}
