package arturo.fonseca.vaquitapp.navigation

import arturo.fonseca.vaquitapp.Presentacion.Listas.ListaBecerros

sealed class appScreens(val route: String) {
    object MenuPrincipalScreen: appScreens("Menu_principal")
    object MenuSecundarioScreen: appScreens("Secundario")
    object AltaVacasScreen: appScreens("alta_vacas")
    object Registro: appScreens("registro")
    object Login1: appScreens("login")
    object AltaBecerros: appScreens("Alta_becerros")
    object SplashScreen: appScreens("Splash_screen")
    object AltaVacasRepScreen: appScreens("RepVacasScreenA")
    object ListaBecerros: appScreens("ListaBecerros")

}