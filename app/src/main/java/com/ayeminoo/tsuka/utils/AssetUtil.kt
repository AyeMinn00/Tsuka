package com.ayeminoo.tsuka.utils

import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets
import timber.log.Timber

object AssetUtil {

    fun readTextFileFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            Timber.d(e.message)
            null
        }
    }

}