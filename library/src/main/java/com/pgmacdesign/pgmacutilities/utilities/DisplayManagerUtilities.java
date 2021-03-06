package com.pgmacdesign.pgmacutilities.utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.pgmacdesign.pgmacutilities.R;

import java.lang.reflect.InvocationTargetException;

/**
 * This class helps to determine the screen height and width in Density Pixels (DP)
 * Created by pmacdowell on 8/15/2016.
 */
public class DisplayManagerUtilities {

    public static enum ScreenLayoutSizes {
        ldpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi
    }

    //Display display;
    private DisplayMetrics outMetrics, api17OutMetrics; //The latter is for APIs 17 or higher
    private WindowManager windowManager;
    private Display mDisplay;
    private Configuration mConfig;
    private float densityRatio, scaledDensity;
    private float dpWidth, dpHeight, xdpi, ydpi;
    private int pixelsWidth, pixelsHeight;
    private String totalScreenDimensionsPixels, totalScreenDimensionsDP;

    //Misc values
    private Context context;


    /**
     * This will get the width and height of the screen in DP. This constructor is used to define
     * the instance variables and set them while the other methods are used as accessor/ getters.
     * @param context Context from activity would be 'this' and from fragment would be 'getActivity()'
     */
    public DisplayManagerUtilities(Context context){
        this.context = context;
        init();
    }

    private void init(){
        outMetrics = new DisplayMetrics();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        mDisplay = windowManager.getDefaultDisplay();
        mConfig = context.getResources().getConfiguration();

        if(Build.VERSION.SDK_INT >= 17) {
            api17OutMetrics = new DisplayMetrics();
            mDisplay.getRealMetrics(api17OutMetrics);
        }

        //Height and width in pixels
        if(Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            mDisplay.getSize(point);
            pixelsWidth = point.x;
            pixelsHeight = point.y;
        } else {
            pixelsWidth = mDisplay.getWidth();
            pixelsHeight = mDisplay.getHeight();
        }
        totalScreenDimensionsPixels = pixelsWidth + "x" + pixelsHeight;

        //Height and weight in DP
        int tempx = -1, tempy = -1;
        if(Build.VERSION.SDK_INT >= 17) {
            tempx = api17OutMetrics.widthPixels;
            tempy = api17OutMetrics.heightPixels;
        }
        if(tempx > 0 && tempy >  0){
            //API 17+
            densityRatio = api17OutMetrics.density;
            scaledDensity = api17OutMetrics.scaledDensity;
            dpHeight = (float)((tempy / densityRatio) + 0.5);
            dpWidth = (float)((tempx / densityRatio) + 0.5);
            xdpi = api17OutMetrics.xdpi;
            ydpi = api17OutMetrics.ydpi;
        } else {
            //API 16-
            densityRatio = outMetrics.density;
            scaledDensity = outMetrics.scaledDensity;
            dpHeight = (float)((pixelsHeight / densityRatio) + 0.5);
            dpWidth = (float)((pixelsWidth / densityRatio) + 0.5);
            xdpi = outMetrics.xdpi;
            ydpi = outMetrics.ydpi;
        }
        totalScreenDimensionsDP = dpWidth + "x" + dpHeight;
    }


    /**
     * The exact physical pixels per inch of the screen in the X dimension.
     * @return float, width
     */
    public float getXdpi(){
        return this.xdpi;
    }

    /**
     * The exact physical pixels per inch of the screen in the Y dimension.
     * @return float, width
     */
    public float getYdpi(){
        return this.ydpi;
    }

    /**
     * Gets the width of the screen in DP
     * @return returns a float
     */
    public float getScreenWidthDP(){
        return dpWidth;
    }

    /**
     * Gets the height of the screen in DP
     * @return returns a float
     */
    public float getScreenHeightDP(){
        return dpHeight;
    }

    /**
     * Returns the width of the screen in pixels
     * @return int
     */
    public int getPixelsWidth(){
        return pixelsWidth;
    }

    /**
     * Returns the height of the screen in pixels
     * @return int
     */
    public int getPixelsHeight(){
        return pixelsHeight;
    }

    public float getDensityRatio(){
        return densityRatio;
    }

    public float getScaledDensity() {
        return scaledDensity;
    }

    /**
     * This returns the size of the navigation bar height
     * @Deprecated this was deprecated when it was shown that screens without a visible
     * action bar will still return values. See link here for details:
     * https://stackoverflow.com/questions/20264268/how-to-get-height-and-width-of-navigation-bar-programmatically
     * @return an int (in pixels);
     */
    @Deprecated
    public int getNavigationBarSizeOld(){
        try {
            float x = context.getResources().getDimension(
                    R.dimen.abc_action_bar_default_height_material);
            return (int) x;
        } catch (Exception e){
            try {
                return (getNavigationBarSize().y);
            } catch (Exception e1){
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * Pulling the screen size.
     * {@link Configuration}
     * @return ScreenLayouts {@link DisplayManagerUtilities.ScreenLayoutSizes}
     */
    public ScreenLayoutSizes getScreenSize(){
        try {
            int x = mConfig.densityDpi;
            if(x <= 120){
                return ScreenLayoutSizes.ldpi;
            } else if(x > 120 && x <= 160){
                return ScreenLayoutSizes.mdpi;
            } else if(x > 160 && x <= 240){
                return ScreenLayoutSizes.hdpi;
            } else if(x > 240 && x <= 320){
                return ScreenLayoutSizes.xhdpi;
            } else if(x > 320 && x <= 480){
                return ScreenLayoutSizes.xxhdpi;
            } else if(x > 480 && x <= 640){
                return ScreenLayoutSizes.xxxhdpi;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        //Default
        return ScreenLayoutSizes.xhdpi;
    }


    /**
     * Get scaled density SP for setting textviews. You would pass in a base number (IE 20)
     * @param baseSize Base number for test size in pixels
     * @return float adjusted text size in SP (scalable pixels)
     */
    public float getScalablePixelTextSize(int baseSize){
        float x = baseSize * densityRatio;
        return x;
    }

    /**
     * This method converts device specific pixels to densityRatio independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public float convertPixelsToDp(float px){
        float dp;
        if(Build.VERSION.SDK_INT >= 17) {
            dp = (float) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, px, api17OutMetrics);
        } else {
            dp = (float) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, px, outMetrics);
        }
        return dp;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device densityRatio.
     *
     * @param dp A value in dp (densityRatio independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device densityRatio
     */
    public float convertDpToPixel(float dp){
        float px = (float)(convertToPixels(ComplexUnits.COMPLEX_UNIT_DIP, dp));
        return px;
    }

    /**
     * Builds and returns the radius of the screen for a circle to be drawn in the center.
     * If you want to make this smaller, simply divide it by X%
     * @return
     */

    public float getWidthRadius(){
        return (pixelsWidth / 2);
    }
    /**
     * Calculates the % opacity I want to use. higher number means less see-through
     * @param percent % to convert, 0 means transparent, 100 means completely blocking background
     * @return int, used like this, view.getBackground().setAlpha(opacityPercent(55));
     */
    public static int opacityPercent(float percent){
        float x = 255;
        if(percent < 0 || percent > 100){
            return 255;
        } else {
            int y = (int) (x * (percent/100));
            return y;
        }
    }

    /**
     * Gets the height of the status bar (Above toolbar)
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier(
                    "status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e){
            //Unable to find Identifier!
        }
        return result;
    }

    public static enum ComplexUnits {
        COMPLEX_UNIT_PX (0),
        COMPLEX_UNIT_DIP (1),
        COMPLEX_UNIT_SP (2),
        COMPLEX_UNIT_PT (3),
        COMPLEX_UNIT_IN (4),
        COMPLEX_UNIT_MM (5);

        private int unitNum;
        ComplexUnits (int x){
            this.unitNum = x;
        }
    }

    /**
     * Get a pixels value for the dimension passed.
     * @param unit Unit of measurement:
     *             COMPLEX_UNIT_PX = 0; == Raw Pixels
     *             COMPLEX_UNIT_DIP = 1; == Device Independent Pixel (DP)
     *             COMPLEX_UNIT_SP = 2; == Scaled Pixels
     *             COMPLEX_UNIT_PT = 3; == Points
     *             COMPLEX_UNIT_IN = 4; == Inches
     *             COMPLEX_UNIT_MM = 5; == Millimeters
     *             {@link TypedValue#TYPE_DIMENSION}
     * @param value Value to convert
     * @return float, in pixels, of converted value
     */
    public float convertToPixels(ComplexUnits unit, float value) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){ //17
            return TypedValue.applyDimension(unit.unitNum, value, api17OutMetrics);
        } else {
            return TypedValue.applyDimension(unit.unitNum, value, outMetrics);
        }
    }

    /**
     * Overloaded method.
     * @param unit {@link TypedValue}
     * @param value Value to convert
     * @return float, in pixels, of converted value
     */
    public float convertToPixels(int unit, float value) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){ //17
            return TypedValue.applyDimension(unit, value, api17OutMetrics);
        } else {
            return TypedValue.applyDimension(unit, value, outMetrics);
        }
    }

    /**
     * Return the displayMetrics
     * @return {@link DisplayMetrics}
     */
    public DisplayMetrics getDisplayMetrics(){
        if(Build.VERSION.SDK_INT >= 17) {
            return api17OutMetrics; //API 17 or higher
        } else {
            return outMetrics;
        }
    }

    public Point getCenterXYCoordinates(){
        int width = getPixelsWidth();
        int height = getPixelsHeight();
        return new Point((int)(width / 2) , (int)(height / 2));
    }

    /**
     * Gets the size of the navigation bar (bar at the bottom with the back, home, menu options)
     * @return {@link Point} X is the width, Y is the height
     */
    public Point getNavigationBarSize() {
        Point appUsableSize = getAppUsableScreenSize();
        Point realScreenSize = getRealScreenSize();

        // navigation bar on the right
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    /**
     * Gets the useable screen size. This would be applicable if there were an action bar
     * covering an area, which reduces usable screen size
     * @return {@link Point} X is the width, Y is the height
     */
    public Point getAppUsableScreenSize() {
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Get the real screen size
     * @return {@link Point} X is the width, Y is the height
     */
    public Point getRealScreenSize() {
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {}
        }

        return size;
    }

    /**
     * Print out all of the display metrics available in the logcat
     */
    public void printOutAllDisplayMetrics(){
        if(this.windowManager == null){
            return;
        }
        L.m("\nPrinting All Display Metrics: \n");
        L.m("Screen width in pixels == " + getPixelsWidth());
        L.m("Screen height in pixels == " + getPixelsHeight());
        if(getAppUsableScreenSize() != null){
            L.m("Useable width in pixels == " + getAppUsableScreenSize().x);
            L.m("Useable height in pixels == " + getAppUsableScreenSize().y);
        }
        if(getCenterXYCoordinates() != null) {
            L.m("Center X,Y Coordinates == " + getCenterXYCoordinates().x
                    + "," + getCenterXYCoordinates().y);
        }
        L.m("Density Ratio == " + getDensityRatio());
        L.m("Scaled Density == " +  getScaledDensity());
        L.m("Screen width in DP == " + getScreenWidthDP());
        L.m("Screen height in DP == " + getScreenHeightDP());
        L.m("Navigation Bar Size in pixels == " + getNavigationBarSize());
        L.m("xdpi == " + getXdpi());
        L.m("ydpi == " + getYdpi());
        L.m("\nFinished Printing All Display Metrics: \n");
    }
}
