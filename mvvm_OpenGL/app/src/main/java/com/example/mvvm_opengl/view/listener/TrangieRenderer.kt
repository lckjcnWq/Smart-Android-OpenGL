package com.example.mvvm_opengl.view.listener

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.blankj.utilcode.util.LogUtils
import com.example.mvvm_opengl.view.bean.GLTriangle02
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TrangieRenderer : GLSurfaceView.Renderer{
    private lateinit var mGlTriangle: GLTriangle02
    private val mMVPMatrix = FloatArray(16) //变换矩阵（投影矩阵*相机矩阵的结果，最终要传递给顶点着色器）
    private val mProjectionMatrix = FloatArray(16) //投影矩阵
    private val mViewMatrix = FloatArray(16) //相机位置矩阵


    override fun onDrawFrame(gl: GL10?) {
        // Redraw background color 重绘背景
        LogUtils.i("onDrawFrame")
        mGlTriangle.draw()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
        // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)

        // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
        GLES20.glViewport(0, 0, width, height)
        /**投影和相机视图相关**/
        /**投影和相机视图相关 */
        val ratio = width.toFloat() / height
        //设置正交投影
//        Matrix.orthoM (mProjectionMatrix, 0, -ratio, ratio, -1, 2, 3, 7);
        //设置正交投影
//        Matrix.orthoM (mProjectionMatrix, 0, -ratio, ratio, -1, 2, 3, 7);
        Matrix.orthoM(mProjectionMatrix, 0, 0f, width.toFloat(), height.toFloat(), 0f,
            (-width).toFloat(), width.toFloat()) //这个投影会跟屏幕坐标关联上

        //设置透视投影（观察点越远，视图越小），这个投影矩阵被应用于对象坐标在onDrawFrame（）方法中
//        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 2, 3, 7);

        //设置相机位置
        //设置透视投影（观察点越远，视图越小），这个投影矩阵被应用于对象坐标在onDrawFrame（）方法中
//        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 2, 3, 7);

        //设置相机位置
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 7f, 0f, 0f, 0f, 0f, 1.0f, 1.0f)
        //计算变换矩阵,最终mMVPMatrix要传递给顶点着色器
        //计算变换矩阵,最终mMVPMatrix要传递给顶点着色器
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // 设置个红色背景
        LogUtils.i("onSurfaceCreated")
        mGlTriangle= GLTriangle02()
    }
}