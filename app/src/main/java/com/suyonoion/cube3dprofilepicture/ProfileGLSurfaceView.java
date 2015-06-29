package com.suyonoion.cube3dprofilepicture;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

public class ProfileGLSurfaceView extends GLSurfaceView
{
	private SuyonoRenderer mRenderer;
    private float mPreviousX;
    private float mPreviousY;
    private float mDensity;
    private ProfileGLSurfaceView mGLSurfaceView;

	public ProfileGLSurfaceView(Context context)
	{
		super(context);
        if (!isInEditMode()) {
            init();
        }
	}

	public ProfileGLSurfaceView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
        if (!isInEditMode()) {
            init();
        }
	}

    private void init() {
        mGLSurfaceView = (ProfileGLSurfaceView)findViewById(setResource("profileGLSurfaceView","id"));

        // Cek jika System mendukung OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        final WindowManager windowManager=(WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        if (supportsEs2)
        {
            // jika mendukung
            mGLSurfaceView.setEGLContextClientVersion(2);

            final DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            // Set the renderer
            mRenderer = new SuyonoRenderer(getContext());
            mGLSurfaceView.setRenderer(mRenderer, displayMetrics.density);
        }
        else
        {
            return;
        }


    }

    @Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event != null)
		{			
			float x = event.getX();
			float y = event.getY();
			
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				if (mRenderer != null)
				{
					float deltaX = (x - mPreviousX) / mDensity / 2f;
					float deltaY = (y - mPreviousY) / mDensity / 2f;
					
					mRenderer.mDeltaX += deltaX;
					mRenderer.mDeltaY += deltaY;												
				}
			}	
			
			mPreviousX = x;
			mPreviousY = y;
			
			return true;
		}
		else
		{
			return super.onTouchEvent(event);
		}		
	}

	// Hides superclass method.
	public void setRenderer(SuyonoRenderer renderer, float density) 
	{
		mRenderer = renderer;
		mDensity = density;
		super.setRenderer(renderer);
	}

    @Override
    public void onResume()
    {
        // onResume().
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    public void onPause()
    {
        // onPause().
        super.onPause();
        mGLSurfaceView.onPause();
    }
    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }
}
