# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-dontobfuscate
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
    ** ordinal();
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Preserve Android support libraries` classes and interfaces
# apparently it's not in the default tools proguard conf
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

# Some code may make further use of introspection to figure out the enclosing methods of anonymous
# inner classes. In that case, the corresponding attribute has to be preserved as well.
-keepattributes EnclosingMethod

# support library
-dontwarn android.support.**
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

# Allow obfuscation of android.support.v7.internal.view.menu.**
# to avoid problem on Samsung 4.2.2 devices with appcompat v21
# see https://code.google.com/p/android/issues/detail?id=78377
-keep class !android.support.v7.internal.view.menu.**, android.support.** {*;}

-keepnames class * implements android.os.Parcelable {
  public static final ** CREATOR;
}

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

-keep public enum * {*;}
-keepclassmembernames public enum * {*;}
-keepclassmembers public enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
 }

-dontwarn javax.**

#Butterknife
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }
-keepnames class * { @butterknife.InjectView *;}
-keep class **$$ViewInjector { *; }


 #lang.management
 -dontwarn java.lang.management.**
 -keep class java.lang.management.** { *; }

 #Test cases
-dontwarn android.test.**

#Inner classes warning
-keepattributes InnerClasses
-dontoptimize

-keepattributes EnclosingMethod
-keepattributes InnerClasses
 #Apache
 -dontwarn org.apache.commons.**
 -dontwarn org.apache.http.**
 -dontwarn org.apache.**
 -dontwarn org.apache.commons.collections.BeanMap
 -dontwarn org.apache.lang.**
 -dontwarn org.apache.commons.logging.impl.**
 -dontwarn org.apache.http.conn.scheme.**
 -dontwarn org.apache.http.annotation.**
 -dontwarn java.beans.**
 -keep class org.apache.commons.logging.**  { *; }


