package arturo.fonseca.vaquitapp.Presentacion.ListaV

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arturo.fonseca.vaquitapp.Presentacion.ListaV.ListaVacasModel
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Vacas
import arturo.fonseca.vaquitapp.R
import com.google.firebase.firestore.FirebaseFirestore

//@Preview
@Composable
fun ListaVacas(db: FirebaseFirestore, viewModel: ListaVacasModel = ListaVacasModel()) {

    val vacas: State<List<Vacas>> = viewModel.vacas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
        "Vacas",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp // Use sp for font size
    )

        LazyColumn {
            items(vacas.value) {
                vacaItem(it)
            }
        }
    }
}

@Composable
fun vacaItem(vacas: Vacas) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.vaca), //Imagen del logo Vaquitapp
            contentDescription = "VacaIcon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text= vacas.nombre.toString(),color = Color.Black)

    }
}
