package com.github.diamondminer88.testapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.github.diamondminer88.zip.ZipEntry
import com.github.diamondminer88.zip.ZipReader
import java.io.File

const val TAG = "zip-android-testapp"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            return requestPermissions()

        val baseDir = File(Environment.getExternalStorageDirectory(), "/zip-android")
        val zipFile = File(baseDir, "testzip.zip")
        baseDir.mkdir()
        zipFile.createNewFile()
        zipFile.writeBytes(resources.openRawResource(R.raw.testzip).readBytes())

        val zip = ZipReader(zipFile)
        Log.i(TAG, "zip entries: ${zip.entryCount}")

        val entry = zip.openEntry("abc.txt")
        Log.i(TAG, "entry name: ${entry.name} size: ${entry.size} content:")
    }

    private fun requestPermissions() {
        val requestId = 1
        val storagePerms = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            this,
            storagePerms,
            requestId
        )
    }
}