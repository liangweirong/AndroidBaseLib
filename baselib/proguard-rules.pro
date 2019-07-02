# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/liyuanke/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
## --  Android 默认 ----------

# 指定混淆级别
-optimizationpasses 5
# 优化时允许访问并修改有修饰符的类和类的成员
-allowaccessmodification
# 包名不混合大小写
-dontusemixedcaseclassnames
# 混淆时采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# 把混淆类中的方法名也混淆了
-useuniqueclassmembernames
# 关闭预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 混淆时记录日志
-verbose
# 保持注解
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Signature
# 忽略警告，继续执行
-ignorewarnings
# 不跳过(混淆) jars中的 非public classes 默认选项
-dontskipnonpubliclibraryclasses

#-mergeinterfacesaggressivel
# keep住源文件以及行号
#-keepattributes SourceFile,LineNumberTable


# 保持哪些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.Fragment
# 如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
# 有引用v4或者v7包，需添加
-keep public class * extends android.support.**
-keep public class * extends android.support.annotation.**

##--- For:Serializable ---
-keep class * implements java.io.Serializable {*;}
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {*;}


#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#枚举
-keepclassmembers enum * {
    **[] $VALUES;
    public *;
}

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# ------------------------------------ 保持自定义控件类不被混淆 ---------------------------
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
     public <fields>;
     private <fields>;
}
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#------------------------------------ 不混淆资源类 ---------------------------
-keepclassmembers class **.R$* {
   public static <fields>;
}
# ------------------------------------ 保留在Activity中的方法参数是view的方法 ---------------------------
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
# ------------------------------------ webView 处理 ---------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
# -------------- retrofit2 混淆设置  ------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# -------------- OkHttp3 混淆设置  ------------------
-dontwarn okhttp3.logging.**

# -------------- gson 2 --------
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}

# --------------------- gson 2 ------
# 这句非常重要，主要是滤掉 com.demo.demo.net.bean包下的所有.class文件不进行混淆
-keep class ubossmerchant.com.baselib.net.BaseNetBean

# --------------  RxLifeCycle --------------
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }


-keep class com.baselib.net.** { *; }

-keep class com.baselib.net.StatusBean { *; }

