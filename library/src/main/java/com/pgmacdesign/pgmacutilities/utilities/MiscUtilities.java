package com.pgmacdesign.pgmacutilities.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.pgmacdesign.pgmacutilities.BuildConfig;
import com.pgmacdesign.pgmacutilities.adaptersandlisteners.OnTaskCompleteListener;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pmacdowell on 8/15/2016.
 */
public class MiscUtilities {

    // https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest
    private static final String SHA = "SHA";

    public static void printOutMyHashKey(Context context, String packageName){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance(SHA);
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class will determine if the current loop being run is on the main thread or not
     * @return boolean, true if on main ui thread, false if not
     */
    public static boolean isRunningOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Checks a list for either being empty or containing objects within it
     * @param myArray array to check
     * @param <T> T extends object
     * @return boolean, true if it is null or empty, false it if is not
     */
    public static <T extends Object> boolean isArrayNullOrEmpty(T[] myArray){
        if(myArray == null){
            return true;
        }
        if(myArray.length <= 0){
            return true;
        }
        return false;
    }

    /**
     * Checks a list for either being empty or containing objects within it
     * @param myList List to check
     * @return Boolean, true if it is null or empty, false it if is not
     */
    public static boolean isListNullOrEmpty(List<?> myList){
        if(myList == null){
            return true;
        }
        if(myList.size() <= 0){
            return true;
        }
        return false;
    }

    /**
     * Checks system preferences for if user has 24 hour (18:04 == 6:04 pm) in their settings
     * @param context {@link Context}
     * @return boolean, true if they prefer 24 hour, false if they prefer 12 hour
     */
    public static boolean userPrefers24HourTimeFormat(Context context){
        try {
            return DateFormat.is24HourFormat(context);
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Checks a set for either being empty or containing objects within it
     * @param mySet set to check
     * @return Boolean, true if it is null or empty, false it if is not
     */
    public static boolean isSetNullOrEmpty(Set<?> mySet){
        if(mySet == null){
            return true;
        }
        if(mySet.size() <= 0){
            return true;
        }
        return false;
    }

    /**
     * Checks a boolean for null (returns false if it is null) and then returns actual
     * bool if not null
     * @param bool boolean to check
     * @return Boolean, true if it is null or empty, false it if is not
     */
    public static boolean isBooleanNullTrueFalse(Boolean bool){
        if(bool == null){
            return false;
        } else {
            return bool;
        }
    }

    /**
     * Checks a map for either being empty or containing objects within it
     * @param myMap map to check
     * @return Boolean, true if it is null or empty, false it if is not
     */
    public static boolean isMapNullOrEmpty(Map<?, ?> myMap){
        if(myMap == null){
            return true;
        }
        if(myMap.size() <= 0){
            return true;
        }
        return false;
    }

    /**
     * Print out a list of objects
     * @param myList
     */
    public static void printOutList(List<?> myList){
        if(isListNullOrEmpty(myList)){
            return;
        }
        int x = 0;
        for(Object item : myList){
            try {
                L.m(item.toString());
            } catch (Exception e){
                L.m("Could not print position " + x);
            }
            x++;
        }
    }

    /**
     * Print out an array of objects
     * @param myArray Array of objects
     */
    public static void printOutArray(Object[] myArray){
        if(isArrayNullOrEmpty(myArray)){
            return;
        }
        int x = 0;
        for(Object item : myArray){
            try {
                L.m(item.toString());
            } catch (Exception e){
                L.m("Could not print position " + x);
            }
            x++;
        }
    }

    /**
     * Print out a hashmap
     * @param myMap Map of type String, ?
     */
    public static void printOutHashMap(Map<?,?> myMap){
        if(myMap == null){
            return;
        }
        L.m("Printing out entire Hashmap:\n");
        for(Map.Entry<?,?> map : myMap.entrySet()){
            Object key = map.getKey();
            Object value = map.getValue();
            L.m(key.toString() + ", " + value.toString());
        }
        L.m("\nEnd printing out Hashmap:");
    }

    /**
     * Gets the package name. If null returned, send call again with context
     * @return
     */
    public static String getPackageName(){
        try {
            return BuildConfig.APPLICATION_ID;
        } catch (Exception e){
            return null;
        }
    }


    /**
     * Remove nulls from a list of list of objects
     * @param nestedListObject
     * @return remove the nulls and return objects
     */
    public static List<List<Object>> removeNullsFromLists(List<List<?>> nestedListObject){
        List<List<Object>> listsToReturn = new ArrayList<>();
        for(int i = 0; i < nestedListObject.size(); i++){
            try {
                List<Object> obj = listsToReturn.get(i);
                if(obj == null){
                    continue;
                }
                obj = removeNullsFromList(obj);
                if(obj != null){
                    listsToReturn.add(obj);
                }
            } catch (Exception e){}
        }
        return listsToReturn;
    }

    /**
     * Remove nulls from a list of objects
     * @param myList
     * @return remove the nulls and return objects
     */
    public static List<Object> removeNullsFromList (List<?> myList){
        if(myList == null){
            return null;
        }
        List<Object> listToReturn = new ArrayList<>();
        for(int i = 0; i < myList.size(); i++){
            try {
                Object obj = myList.get(i);
                if(obj != null){
                    listToReturn.add(obj);
                }
            } catch (Exception e){}
        }
        return listToReturn;
    }

    /**
     * Overloaded method in case getPackageName returns null
     * @param context context
     * @return
     */
    public static String getPackageName(Context context){

        String packageName = null;

        try {
            packageName = context.getPackageManager().getPackageInfo(
                    getPackageName(), 0).packageName;
        } catch (Exception e){}

        if(packageName != null){
            return packageName;
        }

        try{
            packageName = context.getPackageName();
        } catch (Exception e){}

        return packageName;
    }

    /*
    public static Class getClass(String classname) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null)
            classLoader = Singleton.class.getClassLoader();
        return (classLoader.loadClass(classname));
    }
    */

    //Not used atm. Re-working to fix exception issues. Supposed to work via reflection.
    //Link: http://stackoverflow.com/questions/6591665/merging-two-objects-in-java
    private Object mergeObjects(Object obj, Object update){
        if(!obj.getClass().isAssignableFrom(update.getClass())){
            return null;
        }

        Method[] methods = obj.getClass().getMethods();

        for(Method fromMethod: methods){
            if(fromMethod.getDeclaringClass().equals(obj.getClass())
                    && fromMethod.getName().startsWith("get")){

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                    Object value = fromMethod.invoke(update, (Object[])null);
                    if(value != null){
                        toMetod.invoke(obj, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Simple class for pausing a certain number of milliseconds. Useful for interacting with the
     * Main UI Thread when running on things like timers and can't cross threads
     */
    public static class PauseForXSeconds extends AsyncTask<Void, Void, Void> {

        private long numberOfMillisecondsToWait;
        private OnTaskCompleteListener listener;
        /**
         * Pause for X seconds on background thread and then pass back word of finishing on a
         * listener. Used mainly for interacting with the UI when you need to update a field (IE
         * a textview) but need to wait X seconds. Usually this would cause an exception where
         * you are calling it NOT on the main thread. This alleviates that
         * @param numberOfMillisecondsToWait long number of milliseconds to wait. Minimum 10, no max
         * @param listener Listener to pass back word of completion
         */
        public PauseForXSeconds(long numberOfMillisecondsToWait, OnTaskCompleteListener listener){
            this.listener = listener;
            this.numberOfMillisecondsToWait = numberOfMillisecondsToWait;
            if(this.numberOfMillisecondsToWait < 10){
                //Minimum of 10 milliseconds in case 0 is sent by accident
                this.numberOfMillisecondsToWait = 10;
            }
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(numberOfMillisecondsToWait);
            } catch (InterruptedException e){
                return null;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.onTaskComplete(null, -1);
        }
    }

    /**
     * Clear cookies
     * @param context context
     */
    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context)
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }

    /**
     * Checks if a user has the facebook application installed on their phone
     * @param context Context used to ge the package manager
     * @return boolean, true if they have it installed, false if they do not
     */
    public static boolean doesUserHaveFacebookAppInstalled(Context context){
        try{
            context.getPackageManager().getApplicationInfo("com.facebook.katana", 0 );
            return true;
        } catch( PackageManager.NameNotFoundException e ){
            return false;
        }
    }

    /**
     * Grab a list of all apps the user has installed and return them
     * @param context context
     * @param printResults Overloaded boolean, if true, will print out results in logcat
     * @return {@link ResolveInfo}
     */
    public static List<ResolveInfo> getAllInstalledApps(Context context, boolean printResults){
        try {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> pkgAppsList = context.getPackageManager()
                    .queryIntentActivities( mainIntent, 0);
            if(printResults){
                L.m("\nPrinting list of all installed apps:\n");
                for(ResolveInfo r : pkgAppsList){
                    if(r != null) {
                        L.m(r.toString());
                    }
                }
                L.m("\nFinished Printing list of all installed apps:\n");
            }
            return pkgAppsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Grab a list of all apps the user has installed and return them
     * @param context context
     * @return {@link ResolveInfo}
     */
    public static List<ResolveInfo> getAllInstalledApps(Context context){
        try {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> pkgAppsList = context.getPackageManager()
                    .queryIntentActivities( mainIntent, 0);
            return pkgAppsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Simple method to allow for less null checks in code
     * @param atomicInteger {@link AtomicInteger}
     * @return int. -1 if Atomic Integer is null, actual int else.
     */
    public static int getInt(AtomicInteger atomicInteger){
        if(atomicInteger == null){
            return -1;
        } else {
            return atomicInteger.get();
        }
    }
}
