package com.minji.mydiary.utils;

import android.content.Context;

public class ContextHolder {
    private static Context context;

    public static void setApplicationContext(Context ctxt) {
        context = ctxt;
    }

    public synchronized static Context getApplicationContext() {
        return context;
    }
}
