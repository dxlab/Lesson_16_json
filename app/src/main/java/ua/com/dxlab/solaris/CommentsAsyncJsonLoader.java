package ua.com.dxlab.solaris;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import ua.com.dxlab.solaris.models.BugsModel;
import ua.com.dxlab.solaris.models.CommentsModel;
import ua.com.dxlab.solaris.models.CommentsProperties;

/**
 * Created by Dima on 05.09.2015.
 */
public class CommentsAsyncJsonLoader extends AsyncTask<Void, Void, BugsModel> {

    private CommentsCallbackLoading mCommentsCallbackLoading;
    private CommentsProperties mCommentsProperties;


    public CommentsAsyncJsonLoader(CommentsCallbackLoading _commentsCallbackLoading, CommentsProperties _commentsProperties) {
        this.mCommentsCallbackLoading = _commentsCallbackLoading;
        this.mCommentsProperties = _commentsProperties;
    }

    @Override
    protected BugsModel doInBackground(Void... params) {
        BugsModel bugsModel = null;
        try {
            bugsModel = loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bugsModel;
    }

    @Override
    protected void onPostExecute(BugsModel bugsModel) {
        super.onPostExecute(bugsModel);
        if (bugsModel != null){
            mCommentsCallbackLoading.onSuccess(bugsModel);
        } else mCommentsCallbackLoading.onFailure("Error parsing");
    }

    private BugsModel loadData() throws IOException, JSONException {
        BugsModel bugsModel = new BugsModel();
        bugsModel.commentItems = new ArrayList<>();

        final String jsonString = getJsonString(mCommentsProperties.getJsonCommentsUrl());

        JSONObject jsonObject       = new JSONObject(jsonString);
        JSONObject jsonBugsObject   = jsonObject.getJSONObject(mCommentsProperties.getKeyBugs());
        JSONObject jsonCommentsNumberObject   = jsonBugsObject.getJSONObject(mCommentsProperties.getKeyCommentsNumber());
        JSONArray jsonComments       = jsonCommentsNumberObject.getJSONArray(mCommentsProperties.getKeyComments());

        for (int i = 0; i < jsonComments.length(); i++){
            CommentsModel cm = getComment(jsonComments.getJSONObject(i));
            //Log.d("author", cm.author);
            bugsModel.commentItems.add(cm);
        }

        return bugsModel;
    }

    /**
     * gets full json string by json url
     * @param _jsonURL
     * @return
     */
    private String getJsonString (String _jsonURL) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(_jsonURL);
            InputStream is = url.openStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * builds CommentsModel object, fills all the fields
     * @param _jsonObject
     * @return
     */
    private CommentsModel getComment (JSONObject _jsonObject) throws JSONException {
        CommentsModel commentsModel = new CommentsModel();
        commentsModel.attachment_id  = _jsonObject.getString("attachment_id");
        commentsModel.author         = _jsonObject.getString("author");
        commentsModel.bug_id         = _jsonObject.getString("bug_id");
        commentsModel.creation_time  = _jsonObject.getString("creation_time");
        commentsModel.creator        = _jsonObject.getString("creator");
        commentsModel.id             = _jsonObject.getString("id");
        commentsModel.is_private     = _jsonObject.getString("is_private");
        commentsModel.raw_text       = _jsonObject.getString("raw_text");
        commentsModel.tags           = _jsonObject.getString("tags");
        commentsModel.text           = _jsonObject.getString("text");
        commentsModel.time           = _jsonObject.getString("time");
        return commentsModel;
    }
}
