package arturo.fonseca.vaquitapp.Presentacion.ListaGeneral

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Toros
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ListaTorosModelG: ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
     private val _toros = MutableStateFlow <List<Toros>>(emptyList())
     val toros:StateFlow<List<Toros>> = _toros

    init {
        getToros()
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