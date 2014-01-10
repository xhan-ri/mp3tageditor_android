package com.github.xhan.mp3tageditor_android;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.github.xhan.mp3tageditor_android.fragments.BasicTagEditFragment;
import com.github.xhan.mp3tageditor_android.fragments.BasicTagEditFragment.OnFragmentInteractionListener;

public class TagEditorActivity extends FragmentActivity implements OnFragmentInteractionListener {

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
        setContentView(R.layout.activity_tag_editor);
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
			loadTagEditor();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void loadTagEditor() {
		BasicTagEditFragment tagEditFragment = new BasicTagEditFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction trans = fragmentManager.beginTransaction();
		trans.add(R.id.tagEditorContainer, tagEditFragment);
		trans.commit();
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

	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}
}

