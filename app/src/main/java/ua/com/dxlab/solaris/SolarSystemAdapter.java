package ua.com.dxlab.solaris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ua.com.dxlab.solaris.views.SolarSystemData;

/**
 * Created by Dima on 30.08.2015.
 */
public class SolarSystemAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<SolarSystemData>> mSolarSystemData;
    private Context mContext;

    public SolarSystemAdapter(Context context, ArrayList<ArrayList<SolarSystemData>> _solarSystemData){
        mContext = context;
        mSolarSystemData = _solarSystemData;
    }

    @Override
    public int getGroupCount() {
        return mSolarSystemData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mSolarSystemData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mSolarSystemData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mSolarSystemData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.solar_system_collapsed_view, null);
        }

        TextView textCollapsed = (TextView) convertView.findViewById(R.id.textCollapsed_SSCV);
        switch (groupPosition) {
            case 0:
                textCollapsed.setText("Planets");
                break;
            case 1:
                textCollapsed.setText("Comets");
                break;
            case 2:
                textCollapsed.setText("Dwarfs/TNO");
                break;
        }

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.solar_system_expanded_view, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgViewInfo_SSEV);
        TextView infoText = (TextView)convertView.findViewById(R.id.txtViewInfo_SSEV);
        URL imgUrl = mSolarSystemData.get(groupPosition).get(childPosition).getImgUrl();
        infoText.setText(mSolarSystemData.get(groupPosition).get(childPosition).getInfoText());

        new DownloadAsyncTask(imageView).execute(imgUrl);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class DownloadAsyncTask extends AsyncTask<URL, Void, Bitmap> {

        private ImageView mImageView;

        public DownloadAsyncTask(ImageView _imageView) {
            this.mImageView = _imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mImageView.setImageBitmap(null);
        }

        @Override
        protected Bitmap doInBackground(URL... params) {

            BitmapFactory.Options bitmapOptions;
            bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = 5;
            bitmapOptions.inScaled = true;

            Bitmap bitmap;
            BitmapCache bitmapCache = new BitmapCache(mContext);

            URL urlConn = params[0];
            String fileName = urlConn.getFile();
            bitmap = bitmapCache.loadFromFile(fileName);

            if (bitmap==null) {
            Log.d("File", "File " + fileName + " doesn't exist");
                InputStream input = null;

                try {
                    input = urlConn.openStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("error", "Downloading Image Failed");
                    bitmap = null;
                    return null;
                }
                bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
                bitmapCache.saveToFile(fileName, bitmap);

                Log.d("File", "Thumb Saved " + bitmapCache.getLastSegmentFromUrl(fileName));
            }else {
                Log.d("File","Thumb Loaded "+ bitmapCache.getLastSegmentFromUrl(fileName));
            }

            try {
                bitmap = scaleBitmapByFixedWidth(bitmap, 75);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap scaleBitmapByFixedWidth(Bitmap bitmap, int _fixedWidth) throws IOException {

        float aspectRatio = bitmap.getWidth() / (float) bitmap.getHeight();
        int height = Math.round(_fixedWidth / aspectRatio);

        return Bitmap.createScaledBitmap(bitmap, _fixedWidth, height, false);
    }


}
