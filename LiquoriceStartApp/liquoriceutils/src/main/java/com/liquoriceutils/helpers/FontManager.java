package com.liquoriceutils.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * FontManager
 */
public class FontManager {

    private static FontManager instance;

    private Map<String, SoftReference<Typeface>> typefaces = new HashMap<>();

    private FontManager() {

    }

    public static FontManager getInstance() {
        if (instance == null) {
            synchronized (FontManager.class) {
                if (instance == null) {
                    instance = new FontManager();
                }
            }
        }
        return instance;
    }

    /**
     * Load fonts into memory
     *
     * @param context        activity&application&service context
     * @param fontAssetPaths array of paths in asserts
     */
    public void initialize(Context context, String[] fontAssetPaths) {
        Context applicationContext = context.getApplicationContext();
        AssetManager assetManager = applicationContext.getAssets();
        for (String path : fontAssetPaths) {
            add(assetManager, path);
        }
    }

    /**
     * Get the Typeface. If it hasn't been cached, cache it then return it.
     *
     * @param assetManager {@link AssetManager}
     * @param path         Font path in asserts folder
     * @return The Typeface associated with path
     */
    public Typeface getTypeface(AssetManager assetManager, String path) {
        Typeface typeface = null;
        SoftReference<Typeface> typefaceSoftReference = typefaces.get(path);
        if (typefaceSoftReference != null) {
            typeface = typefaceSoftReference.get();
        }
        if (typeface == null) {
            typeface = add(assetManager, path);
        }
        return typeface;
    }

    private Typeface add(AssetManager assetManager, String path) {
        Typeface typeface = Typeface.createFromAsset(assetManager, path);
        SoftReference<Typeface> typefaceSoftReference = new SoftReference<Typeface>(typeface);
        typefaces.put(path, typefaceSoftReference);
        return typeface;
    }

    public void remove(String path) {
        typefaces.remove(path);
    }

    public void clear() {
        typefaces.clear();
    }

}
