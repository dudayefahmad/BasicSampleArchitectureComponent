package com.ahmaddudayef.basicsample.ui;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */

public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
