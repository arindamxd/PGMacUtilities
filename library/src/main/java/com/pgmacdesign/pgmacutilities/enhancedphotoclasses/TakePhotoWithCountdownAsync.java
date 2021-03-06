package com.pgmacdesign.pgmacutilities.enhancedphotoclasses;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.pgmacdesign.pgmacutilities.misc.PGMacUtilitiesConstants;
import com.pgmacdesign.pgmacutilities.utilities.AnimationUtilities;
import com.pgmacdesign.pgmacutilities.utilities.L;

/**
 * Created by PatrickSSD2 on 9/21/2016.
 */
public class TakePhotoWithCountdownAsync <T extends TextView> extends AsyncTask<Void, Integer, Void> {

    private int numSecondsCountdown;
    private boolean bailOut;
    private T textView;
    private CustomPhotoListener listener;

    TakePhotoWithCountdownAsync(@NonNull CustomPhotoListener listener,
                                Integer numSecondsCountdown, T textView){
        if(numSecondsCountdown == null){
            numSecondsCountdown = 0;
        }
        this.numSecondsCountdown = numSecondsCountdown;
        if(this.numSecondsCountdown < 0){
            this.numSecondsCountdown = 0;
        }
        this.listener = listener;
        this.textView = textView;
        this.bailOut = false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        bailOut = true;
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        bailOut = true;
        handleCancel();
    }

    private void handleCancel(){
        this.onPostExecute(null);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if(numSecondsCountdown <= 0 || isCancelled()){
            return null;
        } else {
            for (int i = numSecondsCountdown; i > 0; i--) {
                //If cancel, bail out here
                if(isCancelled()){
                    return null;
                }
                //Otherwise, continue on
                publishProgress(i);
                try {
                    Thread.sleep(PGMacUtilitiesConstants.ONE_SECOND);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int x = values[0];
        textView.setText("" + x);
        if(textView != null) {
            AnimationUtilities.animateMyView(textView,
                    (int) (PGMacUtilitiesConstants.ONE_SECOND * 0.75),
                    PGMacUtilitiesConstants.OUT_ZOOM);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(bailOut || isCancelled()){
            L.m("CANCELED");
            listener.countdownFinished(false);
        } else {
            L.m("COMPLETED");
            listener.countdownFinished(true);
        }
    }
}