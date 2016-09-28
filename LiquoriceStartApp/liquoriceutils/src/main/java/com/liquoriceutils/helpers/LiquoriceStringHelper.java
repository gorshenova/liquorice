package com.liquoriceutils.helpers;

import android.text.Html;
import android.text.Spanned;

/**
 * LiquoriceStringHelper
 */

public class LiquoriceStringHelper {

    public static Spanned getHtmlString(String htmlString){
        Spanned htmlAsSpanned;
        String htmlstring1 = htmlString.replace("&lt;", "<").replace("&gt;", ">");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            htmlAsSpanned = Html.fromHtml(htmlstring1,Html.FROM_HTML_MODE_LEGACY);
        } else {
            htmlAsSpanned = Html.fromHtml(htmlstring1);
        }
        return htmlAsSpanned;
    }
}
