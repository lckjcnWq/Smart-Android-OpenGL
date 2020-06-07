package com.example.myopengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.example.lib_opengl.vary.VaryRender;

/**
 *
 */

public class VaryActivity extends BaseActivity {

    private GLSurfaceView mGLView;
    private VaryRender render;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl);
        initGL();
    }

    public void initGL(){
        mGLView= (GLSurfaceView) findViewById(R.id.mGLView);
        mGLView.setEGLContextClientVersion(2);
        mGLView.setRenderer(render=new VaryRender(getResources()));
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

}
