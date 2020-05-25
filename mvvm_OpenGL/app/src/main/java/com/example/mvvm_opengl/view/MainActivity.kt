package com.example.mvvm_opengl.view

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import com.example.mvvm_opengl.R
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions


class MainActivity : BaseActivity<NoViewModel, ViewDataBinding>() {
    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        applyPermissions()
    }

    private fun applyPermissions() {
        XXPermissions.with(this)
                .constantRequest()
                .permission(Permission.READ_EXTERNAL_STORAGE,Permission.WRITE_EXTERNAL_STORAGE,Permission.CAMERA,Permission.RECORD_AUDIO)
                .request(object : OnPermission {
                    override fun hasPermission(granted: List<String>, all: Boolean) {}
                    override fun noPermission(denied: List<String>, quick: Boolean) {}
                })
    }

    override fun initData() {
    }
}