package com.javiermelendez.mangosusa.data

import java.util.UUID

data class MangoPurchase(
    val id: String = UUID.randomUUID().toString(),
    val proveedor: String = "",
    val sector: String = "",
    val toneladas: Double = 0.0,
    val variedad: String = "", // Ataulfo, Tommy Atkins, Kent, Keitt
    val calidad: String = ""   // Primera, Segunda, Industrial
)