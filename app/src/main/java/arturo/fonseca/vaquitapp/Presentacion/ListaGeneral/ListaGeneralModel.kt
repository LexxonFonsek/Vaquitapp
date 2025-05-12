package arturo.fonseca.vaquitapp.Presentacion.ListaGeneral

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Becerros
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Toros
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

class ListaGeneralModel: ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
    private val _becerros = MutableStateFlow <List<Becerros>>(emptyList())
    val becerros:StateFlow<List<Becerros>> = _becerros

    private val _vacas = MutableStateFlow <List<Vacas>>(emptyList())
    val vacas:StateFlow<List<Vacas>> = _vacas

    private val _toros = MutableStateFlow <List<Toros>>(emptyList())
    val toros:StateFlow<List<Toros>> = _toros

    init {

        getVacas()
        getBecerros()
        getToros()
    }

    private fun getBecerros() {
        viewModelScope.launch {
        val result = withContext(Dispatchers.IO){
            getAllBecerros()
        }
            _becerros.value = result
        }
    }

    suspend fun getAllBecerros(): List<Becerros> {
        return try {
            db.collection("becerros")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Becerros::class.java)
                }
        }catch (e : Exception){
            emptyList()
        }
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

    private fun getToros() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getAllToros()
            }
            _toros.value = result
        }
    }

    suspend fun getAllToros(): List<Toros> {
        return try {
            db.collection("toros")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Toros::class.java)
                }
        }catch (e : Exception){
            emptyList()
        }
    }
}