package arturo.fonseca.vaquitapp.Presentacion.AltaReproduccionVacas

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.icu.util.Calendar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import arturo.fonseca.vaquitapp.Presentacion.AltaToros.saveToros
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Reproduccion
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Toros
import arturo.fonseca.vaquitapp.R
import arturo.fonseca.vaquitapp.navigation.appScreens
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AltaVacasRepScreen(db: FirebaseFirestore, navController: NavController) {
    val siNo = remember { mutableStateOf(true) }
    var empadre by remember { mutableStateOf("") }
    var semental by remember { mutableStateOf("") }
    var semen by remember { mutableStateOf("") }
    val nacimiento by remember { mutableStateOf("") }
    var parto by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var observacion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .requiredWidth(width = 412.dp)
            .requiredHeight(height = 917.dp)
            .background(
                color = Color(0xFFFFFFFF),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(0xFFFFFFFF),
                )
                .padding(top = 55.dp, start = 32.dp, end = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFFFFFFF),
                    )
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                }
                Text(
                    "Alta Reproducción",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(end = 30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.logo), //Imagen del logo Vaquitapp
                    contentDescription = "LogoVaquitApp",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .requiredSize(size = 50.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de Nombre con botón Editar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = empadre,
                        onValueChange = {empadre = it},
                        label = { Text("Empadre") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black
                        ),
                        modifier = Modifier.weight(1f) // Ocupa el espacio restante
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el campo y el botón

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    // Campo "Semental"
                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(shape = RoundedCornerShape(5.dp))
                            .width(240.dp)
                    ) {
                        OutlinedTextField(
                            value = semental,
                            onValueChange = { semental = it },
                            label = { Text("Semental") },
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Blue,
                                unfocusedBorderColor = Black),
                            enabled = siNo.value,
                            modifier = Modifier
                                .width(240.dp)
                                .height(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(end = 35.dp)
                    ) {

                        Text(
                            text = "¿Tiene Semental?",
                            color = Color.Black,
                            fontSize = 12.sp,

                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        Switch(
                            checked = siNo.value,
                            onCheckedChange = { siNo.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color(0xFFFFFFFF),
                                checkedTrackColor = Color(0xFF000000),
                                uncheckedThumbColor = Color(0xFF000000),
                                uncheckedTrackColor = Color(0xFFFFFFFF)
                            ),
                            modifier = Modifier.size(20.dp) // Ajustar tamaño del switch
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = semen,
                        onValueChange = {semen = it},
                        label = { Text("Información del semen/ embrión") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        modifier = Modifier.weight(1f)
                    )
                }

                var selectedDate by remember { mutableStateOf("") } // Estado para guardar la fecha seleccionada
                val context = LocalContext.current
                // Abrir el selector de fecha
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        selectedDate = "$dayOfMonth/${month + 1}/$year"

                    },
                    2023, 0, 1 // Fecha inicial (año, mes, día)

                )

                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(50.dp)
                ) {

                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDatePicker4(context) { date ->
                                    selectedDate = date // Actualiza la fecha seleccionada
                                }
                            }
                            .padding(10.dp)
                            .background(Color.White, shape = RoundedCornerShape(5.dp))

                    ) {
                        Text(
                            text = if (selectedDate.isEmpty()) "Fecha de Nacimiento" else selectedDate,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )

                    }
                }


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    OutlinedTextField(
                        value = parto,
                        onValueChange = {parto = it},
                        label = { Text("Cantidad de partos") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        modifier = Modifier.weight(1f)
                    )

                }

                OutlinedTextField(
                    value = estado,
                    onValueChange = {estado = it},
                    label = { Text("Estado") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = observacion,
                    onValueChange = {observacion = it},
                    label = { Text("Observaciones") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier.fillMaxWidth()
                        .height(120.dp)
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {navController.navigate(appScreens.MenuSecundarioScreen.route)},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Fondo negro
                            contentColor = Color.White    // Texto blanco
                        ),
                        shape = RoundedCornerShape(16.dp), // Bordes redondeados
                        modifier = Modifier
                            .padding(8.dp) // Espaciado opcional
                            .width(150.dp)
                    ) {
                        Text(text = "Atrás")
                    }

                    Button(
                        onClick = {
                            saveRep( db, Reproduccion( empadre, semental, semen, nacimiento, parto, estado, observacion))
                            Toast.makeText(context , R.string.mensaje_exito, Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, // Fondo negro
                            contentColor = Color.White    // Texto blanco
                        ),
                        shape = RoundedCornerShape(16.dp), // Bordes redondeados
                        modifier = Modifier
                            .padding(8.dp) // Espaciado opcional
                            .width(150.dp)
                    ) {
                        Text(text = "Enviar")
                    }


                }
            }
        }
    }

}

fun saveRep(db: FirebaseFirestore, reproduccion: Reproduccion) {
    db.collection("reproducciones")
        .add(reproduccion)
        .addOnSuccessListener { documentReference ->
            Log.d("Vaquitapp", "SUCCESS added with ID: ${documentReference.id}")
        }
        .addOnFailureListener {
            Log.w("Vaquitapp", "Error adding document", it)
        }
        .addOnCompleteListener {
            Log.d("Vaquitapp", "Complete")
        }
}

fun showDatePicker4(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSelected(formattedDate)
        },
        year,
        month,
        day
    ).show()
}

//@Preview(showSystemUi = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun AltaReproduccionScreenPreview(modifier: Modifier = Modifier) {
//    AltaReproduccionScreen(navController = NavController(LocalContext.current))
//
//}