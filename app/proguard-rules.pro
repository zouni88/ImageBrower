# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable
# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile
#-ignorewarning     #忽略所有警告  不建议使用
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
#忽略警告
-dontwarn com.meetu.gxs.**  #忽略某个包的警告
-dontwarn org.apache.http.**
#################保持自己定义的类不被混淆（如json,model,webview等等）###
-keep public class top.smallc.picturebrower.model.**{*;}
-keep public class top.smallc.picturebrower.view.**{*;}
#################系统特定包不需要混淆##############################
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留R下面的资源
-keep class **.R$* {*;}
-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}

###################第三方依赖库不被混淆(部分)############################
-dontwarn com.shuyu.gsyvideoplayer.**
-keep public class com.shuyu.gsyvideoplayer.**{*;}

-dontwarn app.dinus.com.loadingdrawable.**
-keep public class app.dinus.com.loadingdrawable.**{*;}

-dontwarn me.drakeet.multitype.**
-keep public class me.drakeet.multitype.**{*;}

#Gson
#如果有用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

#高德定位
-dontwarn com.amap.api.**
-keep class com.amap.api.** {*;}

#Fresco
#-dontwarn com.facebook.**
-keep class com.facebook.** {*;}

#腾讯bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}

#极光推送
-dontwarn com.
#友盟
-keep class com.umeng.commonsdk.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep class me.relex.photodraweeview.**
-keep class com.facebook.drawee.**
