package com.example.elsol

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elsol.model.Rutas
import com.example.elsol.ui.theme.ElSolTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElSolTheme {
                val myDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed) //estado del ModalDrawer

                MyModalDrawer(estadoDrawer = myDrawerState)  {

                    Scaffold(modifier = Modifier.fillMaxSize(),
                        bottomBar = { MyBottomAppBar(myDrawerState) }

                    ) { innerPadding ->

                        Column (Modifier.padding(innerPadding)){
                            // Inicial()
                            // MyPantallaAutoComplete()
                            val navController = rememberNavController()
                            NavHost(navController = navController, startDestination = Rutas.Pantalla1.route){
                                composable(Rutas.Pantalla1.route){ Inicial(navController)}
                                composable(Rutas.Pantalla2.route){ MyPantallaAutoComplete(navController)}

                            }
                        }
                    }
                }

            }
        }
    }
}

