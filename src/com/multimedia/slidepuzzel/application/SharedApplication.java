package com.multimedia.slidepuzzel.application;

import com.multimedia.slidepuzzel.data.DataManager;

import android.app.Application;

public class SharedApplication extends Application{
	public int size;
	public String diff;
	public int mode;
	public DataManager dataManager;
}
