package com.example.mvvm_opengl.view.bean

import android.opengl.GLES20
import com.example.mvvm_opengl.view.utils.GLUtil
import java.nio.FloatBuffer


class GLTriangle{

    // 顶点着色器的脚本
    var vertexShaderCode = " attribute vec4 vPosition;" +  // 应用程序传入顶点着色器的顶点位置
            " void main() {" +
            "     gl_Position = vPosition;" +  // 此次绘制此顶点位置
            " }"

    // 片元着色器的脚本
    var fragmentShaderCode = " precision mediump float;" +  // 设置工作精度
            " uniform vec4 vColor;" +  // 接收从顶点着色器过来的顶点颜色数据
            " void main() {" +
            "     gl_FragColor = vColor;" +  // 给此片元的填充色
            " }"

    private var vertexBuffer //顶点坐标数据要转化成FloatBuffer格式
            : FloatBuffer? = null

    // 数组中每3个值作为一个坐标点
    val COORDS_PER_VERTEX = 3

    //三角形的坐标数组
    var triangleCoords = floatArrayOf(
        0.0f, 0.5f, 0.0f,  // top
        -0.5f, -0.5f, 0.0f,  // bottom left
        0.5f, -0.5f, 0.0f // bottom right
    )

    //顶点个数，计算得出
    private val vertexCount: Int = triangleCoords.size / COORDS_PER_VERTEX

    //一个顶点有3个float，一个float是4个字节，所以一个顶点要12字节
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex


    //三角形的颜色数组，rgba
    private val mColor = floatArrayOf(
        0.0f, 1.0f, 0.0f, 1.0f
    )

    //当前绘制的顶点位置句柄
    private var vPosition = 0

    //片元着色器颜色句柄
    private var vColor = 0

    //这个可以理解为一个OpenGL程序句柄
    private var mProgram = 0


    init {
        /** 1、数据转换，顶点坐标数据float类型转换成OpenGL格式FloatBuffer，int和short同理 */
        vertexBuffer = GLUtil.floatArray2FloatBuffer(triangleCoords)
        /** 2、加载编译顶点着色器和片元着色器 */
        val vertexShader: Int = GLUtil.loadShader(
            GLES20.GL_VERTEX_SHADER,
            vertexShaderCode
        )
        val fragmentShader: Int = GLUtil.loadShader(
            GLES20.GL_FRAGMENT_SHADER,
            fragmentShaderCode
        )
        /** 3、创建空的OpenGL ES程序，并把着色器添加进去 */
        mProgram = GLES20.glCreateProgram()

        // 添加顶点着色器到程序中
        GLES20.glAttachShader(mProgram, vertexShader)

        // 添加片段着色器到程序中
        GLES20.glAttachShader(mProgram, fragmentShader)
        /** 4、链接程序 */
        GLES20.glLinkProgram(mProgram)
    }

    fun draw() {

        // 将程序添加到OpenGL ES环境
        GLES20.glUseProgram(mProgram)
        /***在什么位置显示什么颜色 */

        // 获取顶点着色器的位置的句柄（这里可以理解为当前绘制的顶点位置）
        vPosition = GLES20.glGetAttribLocation(mProgram, "vPosition")

        // 启用顶点属性
        GLES20.glEnableVertexAttribArray(vPosition)

        //准备三角形坐标数据
        GLES20.glVertexAttribPointer(
            vPosition, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )

        // 获取片段着色器的vColor属性
        vColor = GLES20.glGetUniformLocation(mProgram, "vColor")

        // 设置绘制三角形的颜色
        GLES20.glUniform4fv(vColor, 1, mColor, 0)

        // 绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        // 禁用顶点数组
        GLES20.glDisableVertexAttribArray(vPosition)
    }

}