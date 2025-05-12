package arturo.fonseca.vaquitapp.Presentacion.AltaToros

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import arturo.fonseca.vaquitapp.Presentacion.AltaBecerro.showDatePicker
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Becerros
import arturo.fonseca.vaquitapp.Presentacion.Modelo.Toros
import arturo.fonseca.vaquitapp.R
import arturo.fonseca.vaquitapp.navigation.appScreens
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AltaTorosScreen(db: FirebaseFirestore,navController: NavController) {
    val siNo = remember { mutableStateOf(true) }
    var nombre by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var nacimiento by remember { mutableStateOf("") }
    var cruza by remember { mutableStateOf("") }
    var cornamenta by remember { mutableStateOf("") }
    var hierro by remember { mutableStateOf("") }
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
                    "Alta Toros",
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
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Blue,
                                unfocusedBorderColor = Black
                            ),
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
                            value = color,
                            onValueChange = {color = it},
                            label = {Text("Color")},
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Blue,
                                unfocusedBorderColor = Black),
                            modifier = Modifier
                                .height(50.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .width(220.dp)
                        .height(100.dp)

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
                    value = raza,
                    onValueChange = {raza = it},
                    label = {Text("Raza")},
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
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
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = cruza,
                        onValueChange = {cruza = it},
                        label = {Text("Cruza")},
                        textStyle = TextStyle(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )

                }

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
                    value = cornamenta,
                    onValueChange = {cornamenta = it},
                    label = {Text("Cornamenta")},
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Blue,
                        unfocusedBorderColor = Black),
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
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Blue,
                            unfocusedBorderColor = Black),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        maxLines = 1,

                        enabled = siNo.value, // Enable/disable based on switch state
                        modifier = Modifier
                            .width(240.dp)
                            .height(50.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Separación entre los campos

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
                            y = -60.dp
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
                            .weight(1f) // Para ocupar el espacio disponible
                    ) {
                        OutlinedTextField(
                            value = campania,
                            onValueChange = { campania = it },
                            placeholder = { Text("Campaña") },
                            textStyle = TextStyle(color = Color.Black),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Blue,
                                unfocusedBorderColor = Black),
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
                        saveToros( db, Toros( nombre, color, raza, nacimiento, procedencia, cornamenta, siniiga, campania, hierro))
                        Toast.makeText(context , R.string.mensaje_exito, Toast.LENGTH_SHORT).show()
                         // Navegar a la pantalla listaToros
                        navController.navigate("ListaToros")
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







fun saveToros(db: FirebaseFirestore, toro: Toros){
    db.collection("toros")
        .add(toro)
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


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun AltaTorosPreview() {
    AltaTorosScreen(db = Firebase.firestore,navController = NavController(LocalContext.current))
}
