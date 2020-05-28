package com.example.mvvm_opengl.view.view

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.example.mvvm_opengl.view.listener.TrangieRenderer


class TriangleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var glSurfaceView = GLSurfaceView(this)
//        glSurfaceView.setRenderer(TrangieRenderer())
//        glSurfaceView.setRenderer(DemoRenderer())
        glSurfaceView.setRenderer(TrangieRenderer())
        setContentView(glSurfaceView)
    }
}