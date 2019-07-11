package com.twbgc.apkdownloader;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

@SuppressLint("NewApi")
public class PlayStoreMonitor extends AccessibilityService {
    private static final String TAG = "APK_Downloader";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        int eventType = event.getEventType();

        Log.i(TAG, Integer.toString(eventType));

        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();


        if (nodeInfo != null) {
            if (!checkIfUnInstall(nodeInfo)) {
                iterateNodes(nodeInfo);
            }
        }
    }

    private Boolean checkIfUnInstall(AccessibilityNodeInfo nodeInfo) {
        List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByText("UNINSTALL");
        return infos != null && !infos.isEmpty();
    }

    private void iterateNodes(AccessibilityNodeInfo nodeInfo) {
        String[] clickTexts = {"INSTALL"};
        for (String clickText : clickTexts) {
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByText(clickText);
            for (AccessibilityNodeInfo info : infos) {
                if (info.isClickable() && info.isEnabled()) {
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    @Override
    protected void onServiceConnected() {
        Log.e(TAG, "Start the AccessibilityService.");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "Close the AccessibilityService.");
        return super.onUnbind(intent);
    }

    @Override
    public void onInterrupt() {
    }
}