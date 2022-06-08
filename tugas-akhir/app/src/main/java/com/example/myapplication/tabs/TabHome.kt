package com.example.myapplication.tabs

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.MapsActivity
import com.example.myapplication.R
import com.example.myapplication.models.Transaksi
import com.google.firebase.database.*

class TabHome : Fragment() {
    private lateinit var btnMaps: Button
    private lateinit var ref: DatabaseReference
    private lateinit var textViewJumlahTransaksi: TextView
    private lateinit var textViewJumlahUangKeluar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab_home, container, false)
        btnMaps = view.findViewById(R.id.btnMaps)
        btnMaps.setOnClickListener {
            val mapsIntent = Intent(view.context, MapsActivity::class.java)
            startActivity(mapsIntent)
        }

        val mDialog = ProgressDialog(view.context)
        mDialog.setMessage("Sedang Mengambil Data")
        mDialog.setCancelable(false)
        mDialog.show()

        ref = FirebaseDatabase.getInstance().getReference("transaksi")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(view.context, "Tidak bisa mendapatkan data", Toast.LENGTH_SHORT).show()
                mDialog.hide()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                textViewJumlahTransaksi = view.findViewById(R.id.textViewJumlahTransaksi)
                textViewJumlahUangKeluar = view.findViewById(R.id.textViewJumlahUangKeluar)

                if(snapshot.exists()){
                    var jumlahData: Int = 0;
                    var jumlahUangKeluar: Int = 0;

                    for (item in snapshot.children) {
                        val itemTransaksi = item.getValue(Transaksi::class.java)
                        if(itemTransaksi != null){
                            jumlahData++;
                            jumlahUangKeluar += itemTransaksi.harga.toInt()
                        }
                    }

                    textViewJumlahUangKeluar.text = "Jumlah Uang Keluar : Rp. " + jumlahUangKeluar.toString()
                    textViewJumlahTransaksi.text = "Jumlah Transaksi : " + jumlahData.toString()
                }else{
                    textViewJumlahTransaksi.text = "Jumlah Transaksi : 0"
                    textViewJumlahUangKeluar.text = "Jumlah Uang Keluar : Rp. 0"
                }
                mDialog.hide()
            }
        })

        return view
    }
}