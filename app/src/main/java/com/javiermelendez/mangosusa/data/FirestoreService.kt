package com.javiermelendez.mangosusa.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow



class FirestoreService {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("compras_mango")

    // Guarda el Post-it en la nube
    fun guardarCompra(compra: MangoPurchase, onResult: (Boolean) -> Unit) {
        collection.document(compra.id)
            .set(compra)
            .addOnCompleteListener { task -> onResult(task.isSuccessful) }
    }

    // Escucha cambios en tiempo real
    fun obtenerComprasEnTiempoReal(): Flow<List<MangoPurchase>> = callbackFlow {
        val subscription = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val lista = snapshot.toObjects(MangoPurchase::class.java)
                trySend(lista)
            }
        }
        awaitClose { subscription.remove() }
    }
}