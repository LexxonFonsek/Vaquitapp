package arturo.fonseca.vaquitapp.Presentacion.ListaGeneral

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Vacas
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ListaVacasModelG: ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
     private val _vacas = MutableStateFlow <List<Vacas>>(emptyList())
     val vacas:StateFlow<List<Vacas>> = _vacas

    init {
        getVacas()
    }

    private fun getVacas() {
        viewModelScope.launch {
        val result = withContext(Dispatchers.IO){
            getAllVacas()
        }
            _vacas.value = result
        }
    }

    suspend fun getAllVacas(): List<Vacas> {
        return try {
            db.collection("vacas")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Vacas::class.java)
                }
        }catch (e : Exception){
            emptyList()
        }
    }
}