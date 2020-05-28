package com.example.mvvm_opengl.view.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import com.blankj.utilcode.util.ToastUtils
import com.example.mvvm_opengl.R
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<NoViewModel, ViewDataBinding>(), View.OnClickListener {
    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        btn_draw_triangle.setOnClickListener(this)
        btn_formal_draw_triangle.setOnClickListener(this)
    }

    override fun initData() {
        applyPermissions()
    }

    private fun applyPermissions() {
        XXPermissions.with(this)
            .constantRequest()
            .permission(
                Permission.READ_EXTERNAL_STORAGE,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.CAMERA,
                Permission.RECORD_AUDIO)
            .request(object : OnPermission {
                override fun hasPermission(granted: List<String>, all: Boolean) {}
                override fun noPermission(denied: List<String>, quick: Boolean) {}
            })

    }

    override fun onClick(v: View?) {
        when(v?.id){
           R.id.btn_draw_triangle  ->{
               ToastUtils.showShort("常規三角形")
               startActivity(Intent(this,TriangleActivity::class.java))
           }
            R.id.btn_formal_draw_triangle ->{
                ToastUtils.showShort("正規三角形")
            }
        }
    }
}