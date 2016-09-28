package com.liquoriceutils.helpers.io;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.liquoriceutils.core.LiquoriceConstants;
import com.liquoriceutils.core.R;
import com.liquoriceutils.core.LiquoriceApplication;
import com.liquoriceutils.helpers.log.Logger;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils
 */

public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);

    public static final String TEMP_SUBDIRECTORY = "/.temp/";
    public static final String ASSETS_PAGES_DIR = "pages";
    public static final String PATH_DIV = "/";

    public static File getCacheDir() {
        return LiquoriceApplication.getContext().getExternalCacheDir();
    }

    public static File createCacheFile(String name) {
        return createFileIfNotExist(getCacheDir(), name);
    }

    private static File createFileIfNotExist(File dir, String name) {
        return createFile(new File(dir, name));
    }

    private static File createFile(File file) {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    logger.warn(LiquoriceApplication.getContext().getString(R.string.error_override_dir));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception ignored) {
        }
    }

    public static List<String> getFileLinesFromAssets(Context context,
                                                      String assetsPath) {
        InputStream is = null;
        List<String> buf = null;
        BufferedReader br = null;

        try {
            if (assetsPath.startsWith(LiquoriceConstants.ASSETS_ROOT)) {
                int start = LiquoriceConstants.ASSETS_ROOT.length();
                int end = assetsPath.length();
                assetsPath = assetsPath.substring(start, end);
            }

            is = context.getAssets().open(assetsPath);
            buf = new ArrayList<>();
            br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                buf.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            Logger.error(FileUtils.class,
                    "CAUGHT EXCEPTION WHEN READ FROM ASSETS: " + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                Logger.error(FileUtils.class,
                        "CAUGHT EXCEPTION WHEN CLOSING OPENED STREAMS: " + e);
            }
        }

        return buf;
    }

    public static boolean isFileExistInAssets(Context context, String assetsPath) {
        InputStream is = null;
        boolean exist = false;

        try {
            if (assetsPath.startsWith(LiquoriceConstants.ASSETS_ROOT)) {
                int start = LiquoriceConstants.ASSETS_ROOT.length();
                int end = assetsPath.length();
                assetsPath = assetsPath.substring(start, end);
            }

            is = context.getAssets().open(assetsPath);

            exist = true;
        } catch (IOException ignored) {
        } finally {
            FileUtils.close(is);
        }

        return exist;
    }

    public static boolean isFilePathToAssets(String filePath) {
        return filePath.startsWith(LiquoriceConstants.ASSETS_ROOT);
    }

    public static String trimAssetsPath(String filePath) {
        int start = LiquoriceConstants.ASSETS_ROOT.length();
        int end = filePath.length();

        return filePath.substring(start, end);
    }


    public static void saveImageToFile(Bitmap originalBitmap, File file, int desiredWidth, int desiredHeight) {
        final int QUALITY = 100; // percents
        Bitmap scaledBitmap = null;
        if(originalBitmap.getWidth() <= desiredWidth || originalBitmap.getHeight() <= desiredHeight) {
            scaledBitmap = originalBitmap;
        } else {
            scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, desiredWidth, desiredHeight, false);
        }
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, QUALITY, fOut);
            fOut.flush();
            fOut.close();
            originalBitmap.recycle();
            originalBitmap = null;
            scaledBitmap.recycle();
            scaledBitmap = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getFileLinesFromRawFile(Context context, int resourceId) {
        BufferedReader is = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));
        try  {
            List<String> result = new ArrayList<>();
            String line;
            while ((line = is.readLine()) != null) {
                result.add(line);
            }
            return result;
        } catch (IOException ignored) {
        } finally {
            FileUtils.close(is);
        }
        return new ArrayList<>();
    }

    public static String getFileNameByLocale(String fileName, Context context) {
        String language = context.getResources().getConfiguration().locale.getLanguage();
        String dir = ASSETS_PAGES_DIR + PATH_DIV;
        switch (language) {
            case "en": {
                return dir + fileName + "-en.html";
            }
            case "es": {
                return dir + fileName + "-es.html";
            }
            default: {
                return dir + fileName + "-en.html";
            }
        }
    }

    public static String getFileDataFromAssets(String fileName, Context context) {
        AssetManager manager = context.getAssets();
        InputStream fileStream = null;
        String data = null;
        try {
            fileStream = manager.open(fileName, AssetManager.ACCESS_BUFFER);
            data = IOUtils.toString(fileStream, "UTF-8");;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileStream);
        }
        return data;
    }

    public static String replaceHtmlSymbols(String str) {
        String res = str;
        if (res.contains("%2F")) {
            res = res.replace("%2F", "/");
        }

        if (res.contains("%3F")) {
            res = res.replace("%3F", "?");
        }

        if (res.contains("%3D")) {
            res = res.replace("%3D", "=");
        }

        if (res.contains("%26")) {
            res = res.replace("%26", "&");
        }

        if (res.contains("%2523")) {
            res = res.replace("%2523", "#");
        }
        return res;
    }

    public static void copyFile(File sourceFile, File destFile) {
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(sourceFile).getChannel();
                destination = new FileOutputStream(destFile).getChannel();
                destination.transferFrom(source, 0, source.size());
            } finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
