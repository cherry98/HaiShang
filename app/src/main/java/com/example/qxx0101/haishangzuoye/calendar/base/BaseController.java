package com.example.qxx0101.haishangzuoye.calendar.base;

import android.content.Context;
import android.content.Intent;

/**
 * @author zzl
 * Created 16-5-16.
 */
public class BaseController {
    protected Context context;

    public BaseController(Context context) {
        this.context = context;
    }

    public void onActivityCreate() {
    }

    public void onActivityResume() {
    }

    public void onActivityPause() {
    }

    public void onActivityDestroy() {
    }

    public void onBackPressed() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onclickHomeKey() {
    }
}
