package arturo.fonseca.vaquitapp.Presentacion.Login


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import arturo.fonseca.vaquitapp.R
import arturo.fonseca.vaquitapp.navigation.appScreens
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Login1(auth: FirebaseAuth, navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf("") } // State for login error


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp), // Added padding
        horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
        verticalArrangement = Arrangement.Center // Center content vertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LogoVaquitApp",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp)) // Add space

        Text(
            text = "Bienvenido a VaquitApp",color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(10.dp)) // Add space

        Text(
            text = "Una aplicación pensada en la productividad bovina",
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp)) // Add space

        // Input fields (usuario, password)
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = "Usuario") },
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Black),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp)) // Add space

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Black),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp)) // Add space

        // Buttons (Acceder, Registrarse)
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(usuario, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success")
                            val user = auth.currentUser
                            navController.navigate(appScreens.MenuPrincipalScreen.route)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithEmail:failure", task.exception)
                            loginError = "Error de inicio de sesión" // Update error state
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Acceder", color = Color.White)
        }

        // Display login error if any
        if (loginError.isNotEmpty()) {
            Text(text = loginError, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(10.dp)) // Add space

//        TextButton(onClick = {navController.navigate(appScreens.Registro.route)}) { // Replace with registration navigation
//            Text("¿Olvidaste tu Contraseña?", color = Color(0xFF2D54E1))
//        }
//        Spacer(modifier = Modifier.height(10.dp)) // Add space

        TextButton(onClick = {navController.navigate(appScreens.Registro.route)}) { // Replace with registration navigation
            Text("¿No tienes cuenta? Regístrate aquí.", color = Color(0xFF2D54E1))
        }
    }
}


//@Preview(widthDp = 412, heightDp = 917)
//@Composable
//fun Login1Preview() {
//    Login1(navController = NavHostController(LocalContext.current), auth = Firebase.auth)
//}
