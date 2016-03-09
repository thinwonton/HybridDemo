package org.apache.cordova.plugin;

import android.content.Intent;

import com.github.thinwonton.hybriddemo.TestActivityPluginActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ActivityPlugin extends CordovaPlugin {

	private static final String ACTION = "start";

	private CallbackContext mCallbackContext;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
	        throws JSONException {

		if (ACTION.equals(action)) {
			Intent intent = new Intent(cordova.getActivity(), TestActivityPluginActivity.class);
			cordova.setActivityResultCallback(this);
			cordova.getActivity().startActivityForResult(intent, 1);
			mCallbackContext = callbackContext;
			return true;
		}

		return false;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (mCallbackContext != null) {
            String ret = intent.getStringExtra("ret");
            mCallbackContext.success(ret);
        }
    }
}
