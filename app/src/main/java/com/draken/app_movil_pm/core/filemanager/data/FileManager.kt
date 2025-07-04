package com.draken.app_movil_pm.core.filemanager.data

import android.content.Context
import android.net.Uri
import com.draken.app_movil_pm.core.filemanager.domain.FileManagerRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class FileManager(private val context: Context): FileManagerRepository {

    override fun uriToMultipartBody(uri: Uri, partName: String): MultipartBody.Part? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")

            inputStream?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }

            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(partName, file.name, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}