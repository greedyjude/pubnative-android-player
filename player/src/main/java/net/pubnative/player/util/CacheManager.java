package net.pubnative.player.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class CacheManager {
    static String TAG = CacheManager.class.getSimpleName();

    public static void put(Context context, String url, CacheProgressListener listener) {
        File file = new File(context.getCacheDir(), String.valueOf(url.hashCode()));
        HttpTools.NetworkRequestListener networkListener = new HttpTools.NetworkRequestListener() {
            @Override
            public void onRequestSuccess() {
                Log.d(TAG, "Cache Request success");
                listener.onCacheSuccess();
            }

            @Override
            public void onRequestFailed(Throwable t) {
                Log.d(TAG, "Cache Request failed", t);
                listener.onCacheFailed(t);
            }
        };
        if (!file.exists()) {
            try {
                file.createNewFile();
                HttpTools.downloadFile(url, file, networkListener);
            } catch (IOException e) {
                e.printStackTrace();
                listener.onCacheFailed(e);
                if (file.exists()) {
                    file.delete();
                }
            }
        }

    }

    public static File get(Context context, String url) {
        File file = new File(context.getCacheDir(), String.valueOf(url.hashCode()));
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    public static boolean has(Context context,String url) {
        return get(context,url) != null;
    }


    public interface CacheProgressListener {
        void onCacheSuccess();

        void onCacheFailed(Throwable t);
    }
}

