package com.pgmacdesign.pgmacutilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pgmacdesign.pgmacutilities.adaptersandlisteners.GenericRecyclerviewAdapter;
import com.pgmacdesign.pgmacutilities.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmacutilities.customui.MultiColorLine;
import com.pgmacdesign.pgmacutilities.misc.PGMacUtilitiesConstants;
import com.pgmacdesign.pgmacutilities.networkclasses.retrofitutilities.serviceapiinterfaces.ProfantiyCheckerAPICalls;
import com.pgmacdesign.pgmacutilities.stackmanagement.StackManager;
import com.pgmacdesign.pgmacutilities.stackmanagement.StackManagerException;
import com.pgmacdesign.pgmacutilities.utilities.CameraMediaUtilities;
import com.pgmacdesign.pgmacutilities.utilities.ContactUtilities;
import com.pgmacdesign.pgmacutilities.utilities.DatabaseUtilities;
import com.pgmacdesign.pgmacutilities.utilities.L;
import com.pgmacdesign.pgmacutilities.utilities.MalwareUtilities;
import com.pgmacdesign.pgmacutilities.utilities.MiscUtilities;
import com.pgmacdesign.pgmacutilities.utilities.PermissionUtilities;
import com.pgmacdesign.pgmacutilities.utilities.ProgressBarUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pmacdowell on 8/12/2016.
 */
public class MyTestActivity extends Activity implements View.OnClickListener {

    // TODO: 2017-03-07 need to rewrite ripples to be within bounds
    private DatabaseUtilities dbUtilities;
    private CameraMediaUtilities cam;
    private Button button;
    private RecyclerView testing_layout_recyclerview;
   // private MultipurposeEditText et;
    private static final String CUSTOM_STRING = "-PAT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_layout);
        //et = (MultipurposeEditText) this.findViewById(R.id.et);
        //et.setState(MultipurposeEditText.EditTextState.FOCUSED);
        TextView tv1 = new TextView(this);
        tv1.setTextColor(getResources().getColor(R.color.black));
        button = (Button) this.findViewById(R.id.button);
        button.setTag("button");
        button.setTransformationMethod(null);
        button.setOnClickListener(this);
	    testing_layout_recyclerview = (RecyclerView) this.findViewById(
	    		R.id.testing_layout_recyclerview);
	    testing_layout_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //init();
        init2();
    }

    private void init2(){
	    GenericRecyclerviewAdapter adapter = new GenericRecyclerviewAdapter(new GenericRecyclerviewAdapter.MultipurposeRecyclerviewLink() {
		    @Override
		    public void onBindViewTriggered(RecyclerView.ViewHolder holder0, int position) {
		    	L.m("onBindView triggered from the activity side, working?");
			    MyTestHolder holder = (MyTestHolder) holder0;
			    holder.button.setText("AWESOMESAUCE");
		    }
	    }, this, R.layout.testing_layout, MyTestHolder.class);
	    testing_layout_recyclerview.setHasFixedSize(true);
	    testing_layout_recyclerview.setAdapter(adapter);
	    SamplePojo sp1 = new SamplePojo();
	    List<SamplePojo> samplePojos = new ArrayList<>();
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    samplePojos.add(sp1);
	    adapter.setListObjects(samplePojos);
    }
	
	public static class MyTestHolder extends RecyclerView.ViewHolder {
    	private Button button;
    	private MultiColorLine multi_color_line;
		public MyTestHolder(View itemView) {
			super(itemView);
			multi_color_line = (MultiColorLine) itemView.findViewById(R.id.multi_color_line);
			button = (Button) itemView.findViewById(R.id.button);
		}
	}
	
    private <E extends Enum<E>> void  init(){

        //contactQuery();
        //temp();
        //temp2();



        //Custom stuff here
        dbUtilities = new DatabaseUtilities(this);

        //writeDBStuff();
        //moveDBFile();
        //queryDB();
        //deleteStuff();
        //deleteCustom();
        //deleteAll();
        //superDeleteEverything();
	
	    List<Enum> testEnum1s = new ArrayList<>();
	    testEnum1s.add(TestEnum1.ONE);
	    testEnum1s.add(TestEnum1.TWO);
	    testEnum1s.add(TestEnum1.THREE);
	    testEnum1s.add(TestEnum1.FOUR);
	    testEnum1s.add(TestEnum1.FIVE);
	    List<Enum> testEnum2s = new ArrayList<>();
	    testEnum2s.add(TestEnum2.A);
	    testEnum2s.add(TestEnum2.B);
	    testEnum2s.add(TestEnum2.C);
	    testEnum2s.add(TestEnum2.D);
	    List<Enum> testEnum3s = new ArrayList<>();
	    testEnum3s.add(TestEnum3.Pat);
	    testEnum3s.add(TestEnum3.Mac);
	    
	    Map<Integer, List<Enum>> myEnums = new HashMap<>();
	    myEnums.put(1, testEnum1s);
	    myEnums.put(2, testEnum2s);
	    myEnums.put(3, testEnum3s);
	
	    Map<Integer, Enum> myInitialEnums = new HashMap<>();
	    myInitialEnums.put(1, TestEnum1.ONE);
	    myInitialEnums.put(2, TestEnum2.A);
	    myInitialEnums.put(3, TestEnum3.Pat);
	    try {
		    StackManager s = new StackManager(myEnums, myInitialEnums);
		    s.appendToTheStack(1, TestEnum1.THREE);
		    
	    } catch (StackManagerException e1){
	    	L.m("e1 == " + e1.toString());
	    }
    }

    public static enum TestEnum1 {
        ONE, TWO, THREE, FOUR, FIVE
    }
	public static enum TestEnum2 {
		A, B, C, D
	}
	public static enum TestEnum3 {
		Pat, Mac
	}
	
	
	private void moveDBFile(){
        //Check camera permissions
        PermissionUtilities perm = PermissionUtilities.getInstance(this);
        if(perm.startPermissionsRequest(new PermissionUtilities.permissionsEnum[]{
                PermissionUtilities.permissionsEnum.WRITE_EXTERNAL_STORAGE,
                PermissionUtilities.permissionsEnum.READ_EXTERNAL_STORAGE})) {
            dbUtilities.copyDBToDownloadDirectory(null);
        }
    }

    private void temp(){
        L.m(MiscUtilities.getPackageName());

    }
    private void temp2(){
        L.m("further package testing");
        //4 - test
        ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
        int counter = 0;
        for(PackageInfo packageInfo : res){
            L.m("position " + counter + " - " + packageInfo.packageName);
            // TODO: 8/18/2016 read through package info in docs sometime. tons of info
        }
        //PackageManager pm = getApplicationContext().getPackageManager();
        //List<PackageInfo> packs = pm.getInstalledPackages(0);
    }
    public String getPackageName(Context context) {
        return context.getPackageName();
    }
    private void contactQuery(){
        ContactUtilities.ContactQueryAsync async = new ContactUtilities.ContactQueryAsync(
                new OnTaskCompleteListener() {
                    @Override
                    public void onTaskComplete(Object result, int customTag) {
                        List<ContactUtilities.Contact> myContacts =
                                (List<ContactUtilities.Contact>) result;
                        L.m("result size = " + myContacts.size());
                    }
                }, this, null, null,
                new ContactUtilities.SearchTypes[]{
                        ContactUtilities.SearchTypes.NAME, ContactUtilities.SearchTypes.PHONE,
                        ContactUtilities.SearchTypes.EMAIL},
                new ContactUtilities.SearchQueryFlags[]{
                        ContactUtilities.SearchQueryFlags.ADD_ALPHABET_HEADERS,
                        ContactUtilities.SearchQueryFlags.USE_ALL_ALPHABET_LETTERS,
                        ContactUtilities.SearchQueryFlags.MOVE_FAVORITES_TO_TOP_OF_LIST}
        );
        async.execute();
        L.m("async started");
    }


    private void deleteAll(){
        dbUtilities.deleteAllPersistedObjects(true, false);
    }


    private void superDeleteEverything(){
        dbUtilities.deleteEntireDB(true, false);
    }

    private void testPhoto(){
        cam = new CameraMediaUtilities(this, this, new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                L.m("custom tag = " + customTag);
            }
        });
        cam.startPhotoProcess(CameraMediaUtilities.SourceType.CAMERA_SELF_PHOTO);
    }

    private void testLoadingAnimation(){
        //Removed on 2017-07-05 Due to problems with compiling
        //Dialog progressDialog = PGMacCustomProgressBar.buildCaliforniaSVGDialog(this, true);
        //progressDialog.show();
    }

    private void doWebCall(){


        ProfantiyCheckerAPICalls.checkProfanityAsynchronous(this,
                new OnTaskCompleteListener() {
                    @Override
                    public void onTaskComplete(Object result, int customTag) {
                        L.m("web call done");
                        if(customTag == PGMacUtilitiesConstants.TAG_RETROFIT_CALL_SUCCESS_STRING){
                            L.m("result == " + result.toString());
                        } else if(customTag == PGMacUtilitiesConstants.TAG_RETROFIT_CALL_SUCCESS_BOOLEAN){
                            L.m("result == " + ((Boolean)result).toString());
                        }
                    }
                }, "word");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(CameraMediaUtilities.doesCodeBelongToUtility(requestCode)){
            cam.afterOnActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkMalware(){
        List<String> mylist = MalwareUtilities.checkForMalware(this);
        L.m("Number of infections: " + mylist.size());
        L.Toast(this, "Number of infections: " + mylist.size());
    }
    @Override
    public void onClick(View view) {
        //doWebCall();
        /*
        TimerUtilities.startTimer(new OnTaskCompleteListener() {
            @Override
            public void onTaskComplete(Object result, int customTag) {
                L.m("result received from timerutilities");
                testLoadingAnimation();
            }
        });
        */
        doWebCall();
        //showGIFLoader();

    }

    private void makeMultiColorLine() {
        MultiColorLine line = (MultiColorLine) this.findViewById(R.id.multi_color_line);
        line.setAnimateStrokes(true, 1000);
        line.setDrawAsSingleLine(true);
        line.setDrawBoarderWithLine(false);
        line.setDrawDiagonally(false);
        line.setFps(MultiColorLine.FPS.FPS_90);
        line.setWidthOfLineStroke(40);
        line.setWidthOfBoarderStroke(8);
        line.setColorOfBoarderStroke(getResources().getColor(R.color.aqua));
        List<MultiColorLine.CustomStrokeObject> strokes = new ArrayList<>();

        MultiColorLine.CustomStrokeObject l1 = new MultiColorLine.CustomStrokeObject(
                50, 0, getResources().getColor(R.color.red)
        );
        MultiColorLine.CustomStrokeObject l2 = new MultiColorLine.CustomStrokeObject(
                50, 50, getResources().getColor(R.color.blue)
        );
        strokes.add(l1);
        strokes.add(l2);
        line.setLineStrokes(strokes);
    }

    private void showGIFLoader(){
        ProgressBarUtilities.showGIFProgressDialog(this, R.drawable.got_fighttex_house_stark);
        //ProgressBarUtilities.showGIFProgressDialog(this, R.drawable.but_why_gif);
    }

}
