package com.github.xhan.mp3tageditor_android;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FilesArrayAdapter extends ArrayAdapter<File> {

	List<File> fileList;
	public FilesArrayAdapter(Context context,
			List<File> objects) {
		super(context, 0, objects);
		this.fileList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.listview_item, null);
		TextView tv = (TextView)itemView.findViewById(R.id.lvItemTextView);
		File file = fileList.get(position);
		tv.setText(file.getName());
		return itemView;
	}
	
	

}
