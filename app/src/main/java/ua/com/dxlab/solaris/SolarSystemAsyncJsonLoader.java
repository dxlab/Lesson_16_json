package ua.com.dxlab.solaris;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ua.com.dxlab.solaris.models.SolarSystemModel;

/**
 * Created by Dima on 07.09.2015.
 */
public class SolarSystemAsyncJsonLoader  extends AsyncTask<Void, Void, SolarSystemModel> {

    private SolarSystemCallbackLoading mSolarSystemCallbackLoading;
    private Context mContext;

    public SolarSystemAsyncJsonLoader(SolarSystemCallbackLoading _solarSystemCallbackLoading, Context _context) {
        this.mSolarSystemCallbackLoading = _solarSystemCallbackLoading;
        this.mContext = _context;
        Log.d("SolarSystemAsync", "Created");
    }

    @Override
    protected SolarSystemModel doInBackground(Void... params) {
        SolarSystemModel solarSystemModel = null;

        try {
            solarSystemModel = loadData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("SolarSystemAsync", "doInBackground");
        return solarSystemModel;
    }

    @Override
    protected void onPostExecute(SolarSystemModel solarSystemModel) {
        super.onPostExecute(solarSystemModel);
        if (solarSystemModel != null){
            mSolarSystemCallbackLoading.onSuccess(solarSystemModel);
        } else mSolarSystemCallbackLoading.onFailure("Error parsing");
    }

    private SolarSystemModel loadData() throws IOException, JSONException {
        final String jsonString = getJsonString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        SolarSystemModel solarSystemModel = gson.fromJson(jsonString, SolarSystemModel.class);

        return solarSystemModel;
    }

    private String getJsonString() throws IOException {
        InputStream is = mContext.getAssets().open("json_solar_system.json");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null){
            sb.append(line);
        }
        return sb.toString();
    }
}
