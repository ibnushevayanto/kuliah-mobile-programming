package com.example.myapplication.models

class Transaksi(
    val id: String?,
    val image: String,
    val jenisTransaksi: String,
    val namaTransaksi: String,
    val harga: String,
    val latitude: Double,
    val longitude: Double
    ) {
    constructor(): this("", "", "", "", "", 0.0, 0.0) {

    }
}