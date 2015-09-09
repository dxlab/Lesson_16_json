package ua.com.dxlab.solaris.models;

/**
 * Created by Dima on 06.09.2015.
 */
public final class CommentsProperties {
    private String mJsonCommentsUrl;
    private String mKeyCommentsNumber;
    private String mKeyBugs;
    private String mKeyComments;

    public CommentsProperties(String _jsonCommentsUrl, String _keyCommentsNumber, String _keyBugs, String _keyComments) {
        this.mJsonCommentsUrl = _jsonCommentsUrl;
        this.mKeyCommentsNumber = _keyCommentsNumber;
        this.mKeyBugs = _keyBugs;
        this.mKeyComments = _keyComments;
    }

    public CommentsProperties() {
    }

    public String getJsonCommentsUrl() {
        return mJsonCommentsUrl;
    }

    public void setJsonCommentsUrl(String _jsonCommentsUrl) {
        this.mJsonCommentsUrl = _jsonCommentsUrl;
    }

    public String getKeyCommentsNumber() {
        return mKeyCommentsNumber;
    }

    public void setKeyCommentsNumber(String _keyCommentsNumber) {
        this.mKeyCommentsNumber = _keyCommentsNumber;
    }

    public String getKeyBugs() {
        return mKeyBugs;
    }

    public void setKeyBugs(String _keyBugs) {
        this.mKeyBugs = _keyBugs;
    }

    public String getKeyComments() {
        return mKeyComments;
    }

    public void setKeyComments(String _keyComments) {
        this.mKeyComments = _keyComments;
    }


}
