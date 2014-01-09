package com.github.xhan.mp3tageditor_android;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HelloAndroidActivity extends Activity {

	private static final int SELECT_FOLDER_REQUEST = 100;
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectFolder();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.github.xhan.mp3tageditor_android.R.menu.main, menu);
	return true;
    }
    
    private void selectFolder() {
    	Intent intent = new Intent("com.estrongs.action.PICK_DIRECTORY");
    	intent.putExtra("com.estrongs.intent.extra.TITLE", "Select Directory");
    	startActivityForResult(intent, SELECT_FOLDER_REQUEST);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (SELECT_FOLDER_REQUEST == requestCode) {
			Toast.makeText(this, data.getDataString(), Toast.LENGTH_LONG).show();
			List<File> fileList = getFilesInFolder(data.getData().getPath());
			ListView lv = (ListView)this.findViewById(R.id.folderListView);
			lv.setAdapter(new FilesArrayAdapter(this, fileList));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
    private List<File> getFilesInFolder(String folder) {
    	File folderFile = new File(folder);
    	Log.i(null, "folder exist: " + folderFile.exists());
    	File[] files = folderFile.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String filename) {
				return filename.toLowerCase(Locale.getDefault()).endsWith("mp3");
			}
		});
    	if (files == null) {
    		return new ArrayList<File>();
    	}
    	return Arrays.asList(files);
    }
}

