package com.suyonoion.cube3dprofilepicture;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Suyono on 6/28/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */
public class LinearLayoutView extends LinearLayout {



    public LinearLayoutView(Context context) {
        super(context);

        if (!isInEditMode()) {
            kode();
        }
    }

    public LinearLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            kode();
        }
    }

    private void kode() {
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View anu = layoutInflater.inflate(setResource("profile_glsurfaceview_layout","layout"), (ViewGroup) findViewById(setResource("prof_text","id")));
        addView(anu,lp);
    }
    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }
}

