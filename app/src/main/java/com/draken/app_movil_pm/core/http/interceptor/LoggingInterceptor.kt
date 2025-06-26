package com.draken.app_movil_pm.core.http.interceptor

import okhttp3.logging.HttpLoggingInterceptor


fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}