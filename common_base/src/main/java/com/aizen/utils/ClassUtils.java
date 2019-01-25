package com.aizen.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ld on 2018/12/18.
 *
 * @author ld
 * @date 2018/12/18
 * 描    述：
 */
public class ClassUtils {
    private final static String TAG = "ClassUtils";
    private ClassUtils(){}

    /**
     * 返回AndroidManifest.xml中注册的所有Activity的class
     * @param context                环境
     * @param excludeList        排除class列表
     * @return
     */
    public final static List<Class> getActivitiesClass(Context context, List<Class> excludeList){
        String packageName = context.getPackageName();
        List<Class> returnClassList = new ArrayList<>();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if(packageInfo.activities!=null){
                Log.d(TAG, "Found "+packageInfo.activities.length +" activity in the AndroidManifest.xml");
                for(ActivityInfo ai: packageInfo.activities){
                    Class c;
                    try {
                        c = Class.forName(ai.name);
                        if(Activity.class.isAssignableFrom(c)){
                            returnClassList.add(c);
                            Log.d(TAG, ai.name +"...OK");
                        }
                    } catch (ClassNotFoundException e) {
                        Log.d(TAG, "Class Not Found:"+ai.name);
                    }
                }
                Log.d(TAG, "Filter out, left "+ returnClassList.size()  +" activity," +
                        Arrays.toString(returnClassList.toArray()));
                if(excludeList!=null){
                    returnClassList.removeAll(excludeList);
                    Log.d(TAG, "Exclude " + excludeList.size() +" activity,"+
                            Arrays.toString(excludeList.toArray()));
                }
                Log.d(TAG, "Return "+ returnClassList.size()  +" activity," +
                        Arrays.toString(returnClassList.toArray()));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return returnClassList;
    }
}
