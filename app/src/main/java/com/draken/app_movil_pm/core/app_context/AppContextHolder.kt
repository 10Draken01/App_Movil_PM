package com.draken.app_movil_pm.core.app_context

import android.content.Context

object AppContextHolder {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    fun get(): Context {
        check(::context.isInitialized) { "AppContextHolder no ha sido inicializado" }
        return context
    }
}