package com.example.elsol


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elsol.ui.theme.PVariant_L
import com.example.elsol.ui.theme.Primary_L
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
@Composable
fun MyBottomAppBar(estadoDrawer: DrawerState){
    var counter by rememberSaveable { mutableStateOf(0) }
    val corutina = rememberCoroutineScope()
    BottomAppBar(
        actions = {
            IconButton(onClick = {
                corutina.launch {
                    estadoDrawer.apply {
                        if(isClosed) open() else close()
                    }
                }
            }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Boton ArrowBack"
                    )
            }
            //Button(onClick = { counter+=1}) {
                BadgedBox(

                    badge = {
                        Badge(containerColor = PVariant_L){
                            Text(text="$counter")
                        }
                    },
                    modifier = Modifier.clickable { counter+=1 }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "icono Favoritos",
                        modifier = Modifier.padding(6.dp)
                    )
                }
            //}

        },
        floatingActionButton = {MyFAB()},
        containerColor = Primary_L,
        contentColor = Color.White,
        //modifier = Modifier.padding(top = 5.dp)
    )
}
//Función que crea el FAB sin funcionalidad
@Composable
fun MyFAB(){
    FloatingActionButton(onClick = {}) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = "Add")
    }
}