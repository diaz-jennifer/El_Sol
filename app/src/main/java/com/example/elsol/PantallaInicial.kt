package com.example.elsol

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elsol.model.ImagenSolar
import com.example.elsol.model.Rutas


@Composable
fun Inicial(navController: NavHostController) {
    val context = LocalContext.current
  //  var estadoExpandedCardMenu by rememberSaveable { mutableStateOf(false) }
    var indiceCardSeleccionado by rememberSaveable { mutableIntStateOf(-1) } // -1 indica que ninguna card ha sido seleccionada
    var estadoExpanded by rememberSaveable { mutableStateOf(false) }
    var estadoListaImagenes by rememberSaveable { mutableStateOf(getImagenSolar()) } // estado de la lista

    //texto junto con un dropdown menu con un elemento para ir a la siguiente pantalla
    Column (Modifier.background(Color.White)){
        Box(Modifier.fillMaxWidth()){
            Row (Modifier.fillMaxWidth()){
                TextField(
                    value = "Sun images:",
                    onValueChange = {estadoExpanded=true},
                    modifier = Modifier.fillMaxWidth().clickable { estadoExpanded = true },
                    readOnly = true,
                    textStyle = TextStyle(
                        fontSize =25.sp
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            estadoExpanded = true
                        }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = ""
                            )
                            DropdownMenu(
                                expanded = estadoExpanded,
                                onDismissRequest = {estadoExpanded=false},
                                modifier = Modifier.background(Color.LightGray)
                            ) {
                                DropdownMenuItem(
                                    text = { Text( "Siguiente Pantalla") },
                                    onClick = {navController.navigate(Rutas.Pantalla2.route)
                                        estadoExpanded=false
                                    },
                                    trailingIcon = {
                                        Icon(
                                            Icons.Default.ArrowOutward,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }//Cierra DropDownMenu
                        }//Cierra IconButton
                    }//Cierra trailingIcon de Textfield
                )//Cierra TextField

            }//Cierra Row
        }//Cierra box

        //componente StaggeredGrid
        Column (Modifier.padding(10.dp)){

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp,
                content = {
                    items(estadoListaImagenes.size){
                            indice ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth().height(250.dp)
                                //.fillMaxHeight()
                                .clickable {
                                    Toast.makeText(context, estadoListaImagenes[indice].nombre, Toast.LENGTH_SHORT).show()
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
                        ){
                            Column {
                                Image(
                                    painter = painterResource(id = estadoListaImagenes[indice].photo),
                                    contentDescription = estadoListaImagenes[indice].nombre,
                                    modifier = Modifier.fillMaxWidth().height(197.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Box{
                                    Row(Modifier.fillMaxWidth().height(80.dp),
                                    ){
                                        Box(Modifier.weight(4f)){
                                            TextField(
                                                value = estadoListaImagenes[indice].nombre,
                                                onValueChange = { },
                                                enabled = false,
                                                readOnly = true,
                                                colors = TextFieldDefaults.colors(
                                                    focusedTextColor = Color.Black,
                                                    disabledTextColor = Color.Black,
                                                ),
                                                textStyle = TextStyle(
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )//Cierra TextField
                                        }//Ciera Box conteniendo TextField
                                        IconButton(onClick = {/*estadoExpandedCardMenu = true*/
                                            indiceCardSeleccionado = indice
                                        },
                                            modifier = Modifier.weight(1f),
                                            colors = IconButtonDefaults.iconButtonColors(
                                                contentColor = Color.Black
                                            )
                                        ) {
                                            Icon(
                                                Icons.Default.MoreVert,
                                                contentDescription = ""
                                            )
                                            DropdownMenu(
                                                expanded = /*estadoExpandedCardMenu*/  indiceCardSeleccionado == indice,
                                                onDismissRequest = {/*estadoExpandedCardMenu = false*/
                                                    indiceCardSeleccionado = -1
                                                },
                                            ) {
                                                DropdownMenuItem(
                                                    text = { Text("Copiar") },
                                                    onClick = { /*estadoExpanded = false*/
                                                        val imagenCopiada = estadoListaImagenes[indice].copy()
                                                        estadoListaImagenes= estadoListaImagenes.toMutableList().apply { add(imagenCopiada) }
                                                        indiceCardSeleccionado = -1
                                                    },
                                                )

                                                DropdownMenuItem(
                                                    text = { Text("Eliminar") },
                                                    onClick = {
                                                        estadoListaImagenes=estadoListaImagenes.toMutableList().apply { removeAt(indice)}
                                                        indiceCardSeleccionado = -1
                                                    },
                                                )

                                            }//Cierra DropDownMenu
                                        }//Cierra IconButton
                                    }//Cierra Row
                                }//Cierra Box

                            }//Cierra Column
                        }//Cierra Card
                    }//Cierra items
                }//Cierra content
            ) //Cierra LazyVerticalStaggeredGrid
        }//Cierre de Column

    }

}
//Función que retorna una lista de objetos tipo ImagenSolar
fun getImagenSolar(): List<ImagenSolar> {
    return listOf(
        ImagenSolar(
                "Corona solar",
                        R.drawable.corona_solar
        ),
        ImagenSolar(
            "Erupción solar",
            R.drawable.erupcionsolar
        ),
        ImagenSolar(
            "Espiculas",
            R.drawable.espiculas
        ),
        ImagenSolar(
            "Filamentos",
            R.drawable.filamentos
        ),
        ImagenSolar(
            "Magnetosfera",
            R.drawable.magnetosfera
        ),
        ImagenSolar(
            "Mancha solar",
            R.drawable.manchasolar
        )
    )
}




