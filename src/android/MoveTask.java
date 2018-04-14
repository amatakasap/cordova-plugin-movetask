package com.cordova.plugins.movetask;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class MoveTask extends CordovaPlugin {
  private static final String TAG = "MoveTask";

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
    Log.d(TAG, "[execute]");

    if (action.equals("toFront")) {
      toFront();
    }

    return true;
  }

  private void toFront () {
    int id = getMyAppId();
    if (id > 0) {
      ActivityManager activityManager = (ActivityManager)this.cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
      activityManager.moveTaskToFront(id, ActivityManager.MOVE_TASK_WITH_HOME);
    }
  }

  private int getMyAppId () {
    ActivityManager activityManager = (ActivityManager)this.cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

    for (int i = 0; i < recentTasks.size(); i++) {
      if (recentTasks.get(i).baseActivity.getPackageName().equals(this.cordova.getActivity().getPackageName())) {
        return recentTasks.get(i).id;
      }
    }

    return -1;
  }
}
