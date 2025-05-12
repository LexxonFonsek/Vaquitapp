package arturo.fonseca.vaquitapp.Presentacion.ListaGeneral

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Becerros
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Toros
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Vacas
import arturo.fonseca.vaquitapp.R
import com.google.firebase.firestore.FirebaseFirestore

//@Preview
@Composable
fun ListaGeneral(db: FirebaseFirestore, viewModel: ListaGeneralModel = ListaGeneralModel(), ListaTorosModelG: Any = ListaTorosModelG(),ListaVacasModelG: Any = ListaVacasModelG()) {

    val becerros: State<List<Becerros>> = viewModel.becerros.collectAsState()
    val vacas: State<List<Vacas>> = viewModel.vacas.collectAsState()
    val toros: State<List<Toros>> = viewModel.toros.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(becerros.value) {
                becerroItem(it)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(vacas.value) {
                vacaItem(it)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(toros.value) {
                toroItem(it)
            }
        }
    }
}

@Composable
fun becerroItem(becerros: Becerros) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.becerroicon), //Imagen del logo Vaquitapp
            contentDescription = "BecerroIcon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text= becerros.nombre.toString(),color = Color.Black)

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
@Composable
fun toroItem(toros: Toros){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.toro), //Imagen del logo Vaquitapp
            contentDescription = "toroIcon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text= toros.nombre.toString(),color = Color.Black)

    }
}