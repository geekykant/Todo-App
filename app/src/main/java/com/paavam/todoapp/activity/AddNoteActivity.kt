package com.paavam.todoapp.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.paavam.todoapp.R

class AddNoteActivity : AppCompatActivity() {

    private lateinit var addNoteImage: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView

    private var picturePath: String = ""

    companion object {
        const val REQUEST_GALLERY_IMAGE = 101
        const val REQUEST_CAMERA_IMAGE = 401
        const val PERMISSION_CODE = 501
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_layout)

        init()
    }

    private fun init() {
        addNoteImage = findViewById(R.id.add_note_image)
        title = findViewById(R.id.add_note_title)
        description = findViewById(R.id.add_note_description)

        addNoteImage.setOnClickListener {
            pickImageDialog()
        }
    }

    private fun checkAndRequestPermission(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val storagePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        val permissionNeeded = ArrayList<String>()

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionNeeded.add(android.Manifest.permission.CAMERA)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            permissionNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionNeeded.toTypedArray(),
                    PERMISSION_CODE
            )

            return false;
        }

        return true;
    }

    private fun pickImageDialog() {
        if (!checkAndRequestPermission()) return

        val dialogList = arrayOf("Camera", "Gallery")
        val dialog = MaterialAlertDialogBuilder(this)
                .setItems(dialogList) { view, i ->
                    pickFromOptions(i)
                    view.dismiss()
                }
                .create()

        dialog.show()
    }

    private fun pickFromOptions(i: Int) {
        when (i) {
            1 -> {
                intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_GALLERY_IMAGE)
            }

            0 -> {
                intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, REQUEST_GALLERY_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_GALLERY_IMAGE -> {
                    val selectedImage = data?.data
                    val filePath = arrayOf(MediaStore.Images.Media.DATA)

                    val c = contentResolver.query(selectedImage!!, filePath, null, null, null)
                    c?.moveToFirst()
                    val columnIndex = c?.getColumnIndex(filePath[0])
                    picturePath = c?.getString(columnIndex!!)!!
                    c.close()

                    Glide.with(this).load(picturePath).into(addNoteImage)
                }
            }
        }

    }


}