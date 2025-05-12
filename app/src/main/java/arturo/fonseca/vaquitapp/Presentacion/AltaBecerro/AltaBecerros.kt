package arturo.fonseca.vaquitapp.Presentacion.AltaBecerro

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Becerros
import arturo.fonseca.vaquitapp.R

import arturo.fonseca.vaquitapp.navigation.appScreens
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar


@Composable
fun AltaBecerros(db: FirebaseFirestore, navController: NavController) {
    val siNo = remember { mutableStateOf(true) }
    var nombre by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    val nacimiento by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var madre by remember { mutableStateOf("") }
    var padre by remember { mutableStateOf("") }
    var embrion by remember { mutableStateOf("") }
    var procedencia by remember { mutableStateOf("") }
    var siniiga by remember { mutableStateOf("") }
    var campania by remember { mutableStateOf("") }

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
                    .padding(bottom = 10.dp)
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
                    "Alta Becerros",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(end = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.logo), //Imagen del logo Vaquitapp
                    contentDescription = "LogoVaquitApp",
                    modifier = Modifier

                        .offset(
                            x = 2.dp,
                            y = 10.dp
                        )
                        .requiredSize(size = 50.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .width(400.dp)
            ) {
                Column(
                    modifier = Modifier

                        .width(256.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(shape = RoundedCornerShape(5.dp))
                            .height(50.dp)
                            .width(200.dp)
                    ) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = {nombre = it},
                            label = {Text("Nombre")},
                            textStyle = TextStyle(color = Color.Black),
                            modifier = Modifier
                                .height(50.dp)


                        )


                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(shape = RoundedCornerShape(5.dp))
                            .width(200.dp)
                    ) {
                        OutlinedTextField(
                            value = sexo,
                            onValueChange = {sexo = it},
                            label = {Text("Sexo")},
                            textStyle = TextStyle(color = Color.Black),
                            modifier = Modifier
                                .height(50.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .width(250.dp)
                        .height(150.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.becerro),
                        contentDescription = "Becerros",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(170.dp)
                            .fillMaxWidth()
                            .requiredSize(size = 100.dp)
                    )
                    Text(
                        "Foto",
                        color = Color(0xFF000000),
                        fontSize = 14.sp
                    )
                }
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
                    .padding(bottom = 18.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
            ) {

                // Caja que simula el campo de fecha con un clic
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showDatePicker(context) { date ->
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

            Column(
                modifier = Modifier
                    .padding(bottom = 18.dp,)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = peso,
                    onValueChange = {peso = it},
                    label = {Text("Peso")},
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 4.dp,)
                    .fillMaxWidth()
            ) {

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 12.dp,)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clip(shape = RoundedCornerShape(5.dp))
                        .width(163.dp)
                ) {
                    OutlinedTextField(
                        value = madre,
                        onValueChange = {madre = it},
                        label = {Text("Madre")},
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )

                }
                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clip(shape = RoundedCornerShape(5.dp))
                        .width(162.dp)
                ) {
                    OutlinedTextField(
                        value = padre,
                        onValueChange = {padre = it},
                        label = {Text("Padre")},
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 3.dp,)
                    .fillMaxWidth()
            ) {
//                CoilImage(
//                    imageModel = {"https://i.imgur.com/1tMFzp8.png"},
//                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
//                    modifier = Modifier
//                        .padding(end = 9.dp,)
//                        .width(20.dp)
//                        .height(20.dp)
//                )
                var state by remember { mutableStateOf(true) }


                RadioButton(
                    selected = state,
                    onClick = { state = true },
                    modifier = Modifier.semantics {
                        contentDescription = "Localized Description"
                    }
                )
                Text(
                    "Inseminación artificial",
                    color = Color(0xFF000000),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(end = 4.dp,)
                )

                RadioButton(
                    selected = !state,
                    onClick = { state = true },
                    modifier = Modifier.semantics {
                        contentDescription = "Localized Description"
                    }
                )

                Text(
                    "Desconozco Padres",
                    color = Color(0xFF000000),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(end = 1.dp,)
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp,)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = embrion,
                    onValueChange = {embrion = it},
                    label = {Text("Información del semen/embrion")},
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

            }

            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp,)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(shape = RoundedCornerShape(5.dp))
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = procedencia,
                    onValueChange = {procedencia = it},
                    label = {Text("Procedencia")},
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 4.dp,)
                    .fillMaxWidth()
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                // Campo SIINIGA
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {
                    OutlinedTextField(
                        value = siniiga, // Remember text state
                        onValueChange = { siniiga = it },
                        label = { Text("Número SINIIGA") },
                        textStyle = TextStyle(color = Color.Black),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        maxLines = 1,

                        enabled = siNo.value, // Enable/disable based on switch state

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
                        .size(20.dp) // Ajustar el tamaño del Switch
                        .offset(
                            x = 300.dp,
                            y = -40.dp
                        )

                )
                Spacer(modifier = Modifier.height(8.dp)) // Separación entre los campos

                // Campo Campaña y Switch
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Campo Campaña
                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clip(shape = RoundedCornerShape(5.dp))
                            .fillMaxWidth() // Para ocupar el espacio disponible
                    ) {
                        OutlinedTextField(
                            value = campania,
                            onValueChange = { campania = it },
                            placeholder = { Text("Campaña") },
                            textStyle = TextStyle(color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el campo y el Switch

                    // Botón Switch

                }
            }

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
                        saveBecerro(db, Becerros( nombre, sexo, nacimiento, peso, madre, padre, embrion, procedencia, siniiga, campania))
                        Toast.makeText(context , R.string.mensaje_exito, Toast.LENGTH_SHORT).show()
                        // Navegar a la pantalla listaBecerros
                        navController.navigate("ListaBecerros")
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





fun showDatePicker(context: Context, onDateSelected: (String) -> Unit) {
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

fun saveBecerro(db: FirebaseFirestore, becerro: Becerros){
    db.collection("becerros")
        .add(becerro)
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
//
//@Preview(showSystemUi = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(widthDp = 412, heightDp = 917)
//@Composable
//internal fun AltaBecerrosPreview() {
//    AltaBecerros( navController = NavController(LocalContext.current))
//}
