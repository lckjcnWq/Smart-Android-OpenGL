package com.example.mvvm_opengl.view.utils

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


object  GLUtil {
    /**
     * float 数组转换成FloatBuffer，OpenGL才能使用
     * @param arr
     * @return
     */
    fun floatArray2FloatBuffer(arr: FloatArray): FloatBuffer? {
        val mBuffer: FloatBuffer
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4个字节
        val qbb: ByteBuffer = ByteBuffer.allocateDirect(arr.size * 4)
        // 数组排列用nativeOrder
        qbb.order(ByteOrder.nativeOrder())
        mBuffer = qbb.asFloatBuffer()
        mBuffer.put(arr)
        mBuffer.position(0)
        return mBuffer
    }

    fun loadShader(shaderType: Int, source: String?): Int {
        // 创造顶点着色器类型(GLES20.GL_VERTEX_SHADER)
        // 或者是片段着色器类型 (GLES20.GL_FRAGMENT_SHADER)
        val shader = GLES20.glCreateShader(shaderType)
        // 添加上面编写的着色器代码并编译它
        GLES20.glShaderSource(shader, source)
        GLES20.glCompileShader(shader)
        return shader
    }
}