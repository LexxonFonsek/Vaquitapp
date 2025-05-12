package arturo.fonseca.vaquitapp.Presentacion.AltaReproduccionVacas

import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
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
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Vacas
import arturo.fonseca.vaquitapp.R
import arturo.fonseca.vaquitapp.navigation.appScreens
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AltaVacasScreen(db: FirebaseFirestore, navController: NavController) {
    val siNo = remember { mutableStateOf(true) }
    var nombre by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var cruza by remember { mutableStateOf("") }
    var siniiga by remember { mutableStateOf("") }
    var campania by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var cornamenta by remember { mutableStateOf("") }
    var procedencia by remember { mutableStateOf("") }

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
                    "Alta Vacas",
                    color = Color.Black,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 60.dp)
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
                        value = nombre,
                        onValueChange = {nombre = it},
                        label = { Text("Nombre") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        modifier = Modifier.weight(1f) // Ocupa el espacio restante
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el campo y el botón
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Centra el texto y el botón
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Foto de la vaca",
                            style = MaterialTheme.typography.bodySmall, // Tamaño del texto ajustado
                            color = Color.Black
                        )
                        Button(
                            onClick = { /* Acción para editar nombre */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black, // Fondo negro
                                contentColor = Color.White    // Texto blanco
                            )
                        ) {
                            Text("Editar")
                        }
                    }
                }


                // Campo de Color con botón Editar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = color,
                        onValueChange = {color = it},
                        label = { Text("Color") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        modifier = Modifier.weight(1f) // Ocupa el espacio restante
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el campo y el botón
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Centra el texto y el botón
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Foto del hierro",
                            style = MaterialTheme.typography.bodySmall, // Tamaño del texto ajustado
                            color = Color.Black
                        )
                        Button(
                            onClick = { /* Acción para editar nombre */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black, // Fondo negro
                                contentColor = Color.White    // Texto blanco
                            )
                        ) {
                            Text("Editar")
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = raza,
                    onValueChange = {raza = it},
                    label = { Text("Raza") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = cruza,
                    onValueChange = {cruza = it},
                    label = { Text("Cruza") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier.weight(1f)
                )
            }

            var selectedDate by remember { mutableStateOf("") } // Estado para guardar la fecha seleccionada
            val context = LocalContext.current

            val datePickerDialog = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    selectedDate = "$dayOfMonth/${month + 1}/$year"

                },
                2023, 0, 1 // Fecha inicial (año, mes, día)

            )
            Spacer(modifier = Modifier.height(16.dp))


            Column(
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .height(50.dp)
            ) {

                // Caja que simula el campo de fecha con un clic
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDatePicker3(context) { date ->
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
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Campo Campaña
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = siniiga,
                        onValueChange = { siniiga = it },
                        label = { Text("Número SINIIGA") },
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        enabled = siNo.value,
                        modifier = Modifier
                            .width(240.dp)
                            .height(60.dp)
                    )

                }
                Switch(
                    checked = siNo.value,
                    onCheckedChange = { siNo.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFFFFFFFF),
                        checkedTrackColor = Color(0xFF000000),
                        uncheckedThumbColor = Color(0xFF000000),
                        uncheckedTrackColor = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier
                        .size(20.dp) // Ajustar tamaño del switch
                        .offset(
                        x = -30.dp,
                        y = 30.dp
                    )
                )

            }
            Spacer(modifier = Modifier.width(20.dp))


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)) {
                OutlinedTextField(
                    value = campania,
                    onValueChange = {campania = it},
                    label = { Text("Número de Campaña") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = peso,
                    onValueChange = {peso = it},
                    label = { Text("Peso en KG") },
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
                    modifier = Modifier
                        .width(120.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = cornamenta,
                onValueChange = {cornamenta = it},
                label = { Text("Cornamenta") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Black),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = procedencia,
                onValueChange = {procedencia = it},
                label = { Text("Lugar de procedencia") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Black),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {navController.navigate(appScreens.MenuSecundarioScreen.route) },
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
                        saveVaca(db, Vacas(nombre, color, raza, cruza, selectedDate, siniiga, campania, peso, cornamenta, procedencia))
                        Toast.makeText( context, "Vaca guardada", Toast.LENGTH_SHORT).show()
                         // Navegar a la pantalla listaVacas
                        navController.navigate("ListaVacas")
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
fun showDatePicker3(context: Context, onDateSelected: (String) -> Unit) {
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


fun saveVaca(db: FirebaseFirestore, vaca: Vacas) {
    db.collection("vacas")
        .add(vaca)
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

/*@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun AltaVacasScreenPreview() {
    AltaVacasScreen(navController = NavController(LocalContext.current))

}*/
