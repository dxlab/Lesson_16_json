package ua.com.dxlab.solaris;

/**
 * Created by Dima on 01.09.2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;

public class BitmapCache {

    public static final String CACHED_DIR = "bitmaps_cache";
    private File mCacheDir;

    /**
     * looks for the directory to save cached bitmaps
     * @param _context
     */
    public BitmapCache(Context _context){
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            mCacheDir = new File(android.os.Environment.getExternalStorageDirectory(),CACHED_DIR);
        else
            mCacheDir = _context.getCacheDir();

        if(!mCacheDir.exists())
            mCacheDir.mkdirs();
    }

    /**
     * loads from cached file
     * @param _filename
     * @return
     */
    public Bitmap loadFromFile(String _filename) {
        String tmpFileName = getLastSegmentFromUrl(_filename);
        //Log.d(MainActivity.TAG, _filename);
        String fullPathName = mCacheDir + File.separator + tmpFileName;
        //Log.d(MainActivity.TAG, fullPathName);
        try {
            File file = new File(fullPathName);
            //Log.d(MainActivity.TAG,Boolean.toString(file.exists()));
            if (!file.exists()) { return null; }
            Bitmap tmpBmp = BitmapFactory.decodeFile(fullPathName);
            return tmpBmp;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * checks whether does cached file exist
     * @param _filename
     * @return
     */
    public boolean isBitmapExists(String _filename) {
        String tmpFileName = getLastSegmentFromUrl(_filename);
        try {
            File file = new File(mCacheDir+File.separator+tmpFileName);
            return (!file.exists()) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * saves to cached file
     * @param _filename
     * @param _bmp
     */
    public void saveToFile(String _filename, Bitmap _bmp) {

        if (!isBitmapExists(_filename)) {
            String tmpFileName = getLastSegmentFromUrl(_filename);
            File destination = new File(mCacheDir, tmpFileName);
            try {
                FileOutputStream out = new FileOutputStream(destination);
                _bmp.compress(CompressFormat.JPEG, 80, out);
                out.flush();
                out.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * clears cache
     */
    public void clearCache(){
        File[] files=mCacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

    /**
     * static method to extract the last URL segment
     * @param url
     * @return
     */
    public static String getLastSegmentFromUrl(final String url){
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }
}
