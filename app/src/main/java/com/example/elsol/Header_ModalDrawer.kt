package com.example.elsol

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.elsol.ui.theme.fondoDatos

//Header sólo con imagen
//@Preview(showBackground = true)
@Composable
fun NavBarHeader(){
    Column (modifier = Modifier.fillMaxWidth().height(160.dp).padding(bottom = 10.dp),

        ){
        Image(painter = painterResource(id=R.drawable.sol2),
            contentDescription = "Imagen El Sol 2",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
}

//Función MODALDRAWER:
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyModalDrawer(
    estadoDrawer: DrawerState,
    contenido: @Composable () -> Unit
){
    //val corutina = rememberCoroutineScope()
    val sections = listOf("Build", "Info", "Email")
    val iconos = listOf(Icons.Filled.Build, Icons.Filled.Info,Icons.Filled.Email)

    val listaPares = sections.zip(iconos)

    ModalNavigationDrawer(
        drawerState = estadoDrawer,
        gesturesEnabled = true,//en true se cierra si se hace click fuera, en false, se mantiene abierto
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
        drawerContent = {
            ModalDrawerSheet (drawerContainerColor = Color.White,
               // modifier = Modifier.windowInsetsPadding(WindowInsets(bottom = 105.dp)),
                //windowInsets = WindowInsets.systemBars,
                drawerShape = RoundedCornerShape(topEndPercent = 5)
            ){
                NavBarHeader()

                listaPares.forEach {
                        (section, icono) ->
                    NavigationDrawerItem(
                        label = { Text(text = section) },
                        selected = false,
                        onClick = {

                        },
                        icon = { Icon(imageVector = icono, contentDescription = "icono $section") },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.White,
                        ),
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )
                }

            }
        },

    ) {
        contenido()
    }
}