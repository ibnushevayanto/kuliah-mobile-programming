package com.example.myapplication.tabs

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DetailTransaksiActivity
import com.example.myapplication.FormActivity
import com.example.myapplication.R
import com.example.myapplication.models.Transaksi
import com.example.pertemuan5.ListJenisTransaksiAdapter
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 * Use the [TabTransaksi.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabTransaksi : Fragment() {
    private lateinit var btnTambah: Button
    private lateinit var rvTransaksi: RecyclerView
    private lateinit var ref: DatabaseReference
    private lateinit var transaksiList: MutableList<Transaksi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab_transaksi, container, false)
        btnTambah = view.findViewById(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val formIntent = Intent(view.context, FormActivity::class.java)
            startActivity(formIntent)
        }

        rvTransaksi = view.findViewById(R.id.list_transaksi)
        ref = FirebaseDatabase.getInstance().getReference("transaksi")
        transaksiList = mutableListOf()

        val mDialog = ProgressDialog(view.context)
        mDialog.setMessage("Sedang Mengambil Data")
        mDialog.setCancelable(false)
        mDialog.show()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(view.context, "Tidak bisa mendapatkan data", Toast.LENGTH_SHORT).show()
                mDialog.hide()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (item in snapshot.children) {
                        val itemTransaksi = item.getValue(Transaksi::class.java)
                        if(itemTransaksi != null){
                            transaksiList.add(itemTransaksi)
                        }
                    }

                    rvTransaksi.setHasFixedSize(true)
                    rvTransaksi.layoutManager = LinearLayoutManager( view.context)
                    val listTransaksiAdapter = ListJenisTransaksiAdapter(transaksiList)
                    rvTransaksi.adapter = listTransaksiAdapter

                    listTransaksiAdapter.setOnItemClickCallback(object: ListJenisTransaksiAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: Transaksi, index: Int) {
                            val detailIntent = Intent(view.context, DetailTransaksiActivity::class.java)
                            detailIntent.putExtra("jenis_transaksi", transaksiList[index].jenisTransaksi)
                            detailIntent.putExtra("nama_transaksi", transaksiList[index].namaTransaksi)
                            detailIntent.putExtra("harga", transaksiList[index].harga)
                            detailIntent.putExtra("image", transaksiList[index].image)
                            detailIntent.putExtra("id", transaksiList[index].id)
                            startActivity(detailIntent)
                        }
                    } )
                }
                mDialog.hide()
            }
        })

        return view
    }
}