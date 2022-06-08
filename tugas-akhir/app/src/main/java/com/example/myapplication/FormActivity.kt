package com.example.myapplication

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.models.Transaksi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream


class FormActivity : AppCompatActivity() {
    private lateinit var btnSimpan: Button
    private lateinit var btnTakeCamera: Button
    private lateinit var imageCamera: ImageView
    private lateinit var autoCompleteJenisTransksi: AutoCompleteTextView
    private lateinit var textNamaTransaksi: TextView
    private lateinit var textBiaya: TextView
    private lateinit var mStorageRef: StorageReference
    private lateinit var imageTaken: Bitmap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val MY_CAMERA_PERMISSION_CODE = 100
    private val CAMERA_REQUEST = 1888
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        title = "Form Transaksi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        checkLocationPermission()
        autoCompleteJenisTransksi = findViewById(R.id.autoCompleteJenisTransaksi)
        textNamaTransaksi = findViewById(R.id.namaTransaksiTextView)
        textBiaya = findViewById(R.id.biayaTextView)
        mStorageRef = FirebaseStorage.getInstance().getReference("image_transaksi")
        btnSimpan = findViewById(R.id.btnSimpan)

        btnSimpan.setOnClickListener() {
            val ref = FirebaseDatabase.getInstance().getReference("transaksi")
            val trId = ref.push().key

            val baos = ByteArrayOutputStream()
            imageTaken.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val imageRef = mStorageRef.child("$trId.jpg")
            val uploadTask = imageRef.putBytes(data)

            val mDialog = ProgressDialog(this)
            mDialog.setMessage("Sedang Menyimpan Data")
            mDialog.setCancelable(false)
            mDialog.show()

            uploadTask.addOnFailureListener {
                mDialog.hide()
                Toast.makeText(this, "Gagal upload foto", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                val result = it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {
                    val imageLink = it.toString()

                    val transaksi = Transaksi(
                        trId,
                        imageLink,
                        autoCompleteJenisTransksi.text.toString().trim(),
                        textNamaTransaksi.text.toString().trim(),
                        textBiaya.text.toString().trim(),
                        this.latitude,
                        this.longitude
                    )

                    if(trId != null){
                        Log.i("msg", trId)
                        ref.child(trId).setValue(transaksi).addOnCompleteListener() {
                            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                            val formIntent = Intent(this, MainActivity::class.java)
                            mDialog.hide()
                            startActivity(formIntent)
                        }.addOnFailureListener() {
                            Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show()
                            mDialog.hide()
                        }
                    }else{
                        Log.i("error", "Tidak Masuk")
                    }
                }


            }
        }

        imageCamera = findViewById(R.id.previewFromCamera)

        btnTakeCamera = findViewById(R.id.btnAmbilCamera)
        btnTakeCamera.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }

        val jenisTransaksi = resources.getStringArray(R.array.jenisTransaksi)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, jenisTransaksi)
        autoCompleteJenisTransksi.setAdapter(arrayAdapter)
    }

    private fun checkLocationPermission() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener {
            if(it != null) {
                this.latitude = it.latitude
                this.longitude = it.longitude
            }else{
                this.latitude = 0.0
                this.longitude = 0.0
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            imageTaken = (data?.extras!!["data"] as Bitmap?)!!
            imageCamera.setImageBitmap(imageTaken)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}