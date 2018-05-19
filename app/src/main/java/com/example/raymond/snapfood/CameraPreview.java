package com.example.raymond.snapfood;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.v4.math.MathUtils.clamp;

/** A basic Camera preview class */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;


    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
        //mCamera.stopPreview();
        //mCamera.release();
        holder.removeCallback(this);
        //mCamera.setPreviewCallback(null);
        //mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        //List<Camera.Size>  camerasize = mCamera.getParameters().getSupportedPreviewSizes();
        /*Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        Camera.Size size = sizes.get(0);
        for(int i=0;i<sizes.size();i++)
        {
            if(sizes.get(i).width > size.width)
                size = sizes.get(i);
        }
        parameters.setPictureSize(size.width, size.height);*/

        // start preview with new settings
       try {

            Camera.Parameters parameters = mCamera.getParameters();

            for (Camera.Size size : parameters.getSupportedPictureSizes()) {
                // 640 480
                // 960 720
                // 1024 768
                // 1280 720
                // 1600 1200
                // 2560 1920
                // 3264 2448
                // 2048 1536
                // 3264 1836
                // 2048 1152
                // 3264 2176
                Log.d("Preview sizes","Preview size: " + size.width + ", " + size.height);

            }

            parameters.setPreviewSize(1920, 1080);
            parameters.setPictureSize(4032, 3024);
            List<String> focusModes = parameters.getSupportedFocusModes();
            if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else
            if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }

           parameters.setPreviewSize(1920, 1080);
           parameters.setPictureSize(4032, 3024);
           mCamera.setParameters(parameters);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }

        /*setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Camera camera = mCamera.getCamera();
                mCamera.cancelAutoFocus();
                Rect focusRect = calculateTapArea(event.getX(), event.getY(), 1f);

                Camera.Parameters parameters = mCamera.getParameters();
                if (parameters.getFocusMode().equals(
                        Camera.Parameters.FOCUS_MODE_AUTO)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                }


                if (parameters.getMaxNumFocusAreas() > 0) {
                    List<Camera.Area> mylist = new ArrayList<Camera.Area>();
                    mylist.add(new Camera.Area(focusRect, 1000));
                    parameters.setFocusAreas(mylist);
                }

                try {
                    mCamera.cancelAutoFocus();
                    mCamera.setParameters(parameters);
                    mCamera.startPreview();
                    mCamera.autoFocus(new Camera.AutoFocusCallback() {
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                            if (camera.getParameters().getFocusMode().equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                                Camera.Parameters parameters = camera.getParameters();
                                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                                if (parameters.getMaxNumFocusAreas() > 0) {
                                    parameters.setFocusAreas(null);
                                }
                                camera.setParameters(parameters);
                                camera.startPreview();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return true;
            }
        });*/
    }

/*    private static final float FOCUS_AREA_SIZE = 75f;

    private Rect calculateTapArea(float x, float y, float coefficient) {
        int areaSize = Float.valueOf(FOCUS_AREA_SIZE * coefficient).intValue();

        int left = clamp((int) x - areaSize / 2, 0, getWidth() - areaSize);
        int top = clamp((int) y - areaSize / 2, 0, getHeight() - areaSize);

        RectF rect = new RectF(left, top, left + areaSize, top + areaSize);
        //Log.d("tap: " + rect.toShortString());

        return round(rect);
    }

    private Rect round(RectF rect) {
        return new Rect(Math.round(rect.left), Math.round(rect.top), Math.round(rect.right), Math.round(rect.bottom));
    }*/
}