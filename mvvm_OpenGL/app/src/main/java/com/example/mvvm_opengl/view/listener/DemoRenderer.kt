package com.example.mvvm_opengl.view.listener

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.blankj.utilcode.util.LogUtils
import com.example.mvvm_opengl.view.bean.GLTriangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class DemoRenderer : GLSurfaceView.Renderer{
    private lateinit var mGlTriangle: GLTriangle

    override fun onDrawFrame(gl: GL10?) {
        // Redraw background color 重绘背景
        LogUtils.i("onDrawFrame")
        mGlTriangle.draw()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
        LogUtils.i("onSurfaceChanged")
        GLES20.glViewport(100,100,width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // 设置个红色背景
        LogUtils.i("onSurfaceCreated")
        mGlTriangle= GLTriangle()
    }
}