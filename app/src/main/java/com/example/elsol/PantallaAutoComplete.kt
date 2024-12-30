package com.example.elsol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elsol.model.Planeta
import com.example.elsol.ui.theme.Purple40
import com.example.elsol.ui.theme.Secondary_L
import com.example.elsol.ui.theme.fondoDatos
import com.example.elsol.ui.theme.fondoTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPantallaAutoComplete(navController: NavHostController){
    var estadoExpandedPlaneta1 by rememberSaveable { mutableStateOf(false) }
    var estadoExpandedPlaneta2 by rememberSaveable { mutableStateOf(false) }
    var campoPlaneta1 by rememberSaveable { mutableStateOf("") }
    var campoPlaneta2 by rememberSaveable { mutableStateOf("") }
    val listaPlanetas = getPlaneta()
    Column (Modifier.fillMaxSize().background(Color.White).verticalScroll(rememberScrollState())) {
        Box(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth().height(65.dp).background(Secondary_L).padding(15.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "El Sol", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Medium)
            }
        }
        Column (Modifier.padding(start = 15.dp, top=10.dp, end = 15.dp)){
            Text(text = "Compara planetas",
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Medium
            )
            MySpacer(25)

            Row (Modifier.fillMaxWidth()){
                //ExposedDropDownMenu para planeta1
                ExposedDropdownMenuBox(
                    expanded = estadoExpandedPlaneta1,
                    onExpandedChange = {estadoExpandedPlaneta1 = !estadoExpandedPlaneta1},
                    modifier = Modifier.weight(1f)
                ) {
                //Con el BasicTextField ha sido posible evitar el padding interno que tiene el TextField para poder reducir el alto y que no desaparezcan las letras
                    BasicTextField(
                        value = campoPlaneta1,
                        onValueChange = {campoPlaneta1=it},
                        modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.CenterVertically)
                            .background(fondoTextField).menuAnchor(),
                        textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                        singleLine = true,
                       // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
                    )
/*
                    //Sale demasiado grande el TexField, si se quiere reducir el tamaño, las letras desaparecen,
                    //poner paddin a cero no funciona
                    TextField(
                        value = campoPlaneta1,
                        onValueChange = {campoPlaneta1 = it
                        },
                        modifier = Modifier.padding(top = 0.dp, bottom = 0.dp).fillMaxWidth().height(50.dp)
                            .menuAnchor(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        ),
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            unfocusedContainerColor = Color(0xFFAA00FF),
                            focusedContainerColor = Color(0xFFAA00FF),
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )*/
                    ExposedDropdownMenu(
                        expanded = estadoExpandedPlaneta1,
                        onDismissRequest = {estadoExpandedPlaneta1 = false},
                        modifier = Modifier.background(Color.White)
                    ) {
                        if (campoPlaneta1.isEmpty()){
                            listaPlanetas.forEach {
                                    planeta1 ->  DropdownMenuItem(
                                text ={ Text(text = planeta1.nom, fontSize = 16.sp) },
                                onClick = {
                                    campoPlaneta1 = planeta1.nom
                                    estadoExpandedPlaneta1 = false
                                },
                               // contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                            }
                        } else{
                            val listaFiltrada1 = listaPlanetas.filter {
                                planeta1 -> planeta1.nom.contains(campoPlaneta1, ignoreCase = true)
                            }
                            listaFiltrada1.forEach { planeta1 ->
                                DropdownMenuItem(
                                    text = { Text(text = planeta1.nom, fontSize = 16.sp) },
                                    onClick = {
                                        campoPlaneta1 = planeta1.nom
                                        estadoExpandedPlaneta1 = false
                                    },
                                    //contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }

                    }//Cierre ExposedDropDownMenu planeta1
                }//Cierre ExposedDropDownMenuBox planeta1
                MySpacer(10)

                //ExposedDropDownMenu para planeta2
                ExposedDropdownMenuBox(
                    expanded = estadoExpandedPlaneta2,
                    onExpandedChange = {estadoExpandedPlaneta2 = !estadoExpandedPlaneta2},
                    modifier = Modifier.weight(1f)
                ) {
                    /*TextField(
                        value = campoPlaneta2,
                        onValueChange = {campoPlaneta2 = it
                         },
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                            .menuAnchor(),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        ),
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            unfocusedContainerColor = Color(0xFFAA00FF),
                            focusedContainerColor = Color(0xFFAA00FF),
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )*/
                    
                    BasicTextField(
                        value = campoPlaneta2,
                        onValueChange = {campoPlaneta2 = it},
                        modifier = Modifier.fillMaxWidth().height(30.dp).align(Alignment.CenterVertically).background(
                            fondoTextField).menuAnchor(),
                        textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                        singleLine = true
                    )

                    ExposedDropdownMenu(
                        expanded = estadoExpandedPlaneta2,
                        onDismissRequest = {estadoExpandedPlaneta2 = false},
                        modifier = Modifier.background(Color.White)
                    ) {
                        if (campoPlaneta2.isEmpty()){
                            listaPlanetas.forEach {
                                    planeta2 ->  DropdownMenuItem(
                                text ={ Text(text = planeta2.nom, fontSize = 16.sp) },
                                onClick = {
                                    campoPlaneta2 = planeta2.nom
                                    estadoExpandedPlaneta2 = false
                                },
                              //  contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }else{
                            val listaFiltrada2 = listaPlanetas.filter {
                                planeta2 -> planeta2.nom.contains(campoPlaneta2, ignoreCase = true)
                            }
                            listaFiltrada2.forEach {
                                planeta2 -> DropdownMenuItem(
                                text ={ Text(text = planeta2.nom, fontSize = 16.sp)},
                                onClick = {
                                    campoPlaneta2 = planeta2.nom
                                    estadoExpandedPlaneta2 = false
                                },
                               // contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }

                    }//Cierre ExposedDropDownMenu planeta2
                }//Cierre ExposedDropDownMenuBox planeta2

            }//cierre de Row conteniendo ExposedDropdownMenuBox de ambos planetas
            MySpacer(20)

            val planetaEscogido1: Planeta? = listaPlanetas.find { it.nom == campoPlaneta1 }
            val planetaEscogido2: Planeta? = listaPlanetas.find { it.nom == campoPlaneta2 }
            Row (Modifier.fillMaxWidth()){
                TituloDatos("Diámetro")
            }//Cierre Row Texfield título diámetro
            Row (Modifier.fillMaxWidth().background(fondoDatos)){
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDiam(planetaEscogido1))
                }
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDiam(planetaEscogido2))
                }

            }//cierre de Row conteniendo Texts diámetros

            Row (Modifier.fillMaxWidth()){
                TituloDatos("Distancia al Sol")

            }//Cierre Row Texfield título Distancia al sol

            Row (Modifier.fillMaxWidth().background(fondoDatos)){
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDistancia(planetaEscogido1))
                }
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDistancia(planetaEscogido2))
                }
            }//cierre de Row conteniendo Texts distancia al sol

            Row (Modifier.fillMaxWidth()){
                TituloDatos("Densidad")

            }//Cierre Row Texfield título Densidad
            Row (Modifier.fillMaxWidth().background(fondoDatos)){
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDensidad(planetaEscogido1))
                }
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center){
                    Text(text = contenidoDatoDensidad(planetaEscogido2))
                }
            }//cierre de Row conteniendo Texts densidades


        }//Cierre de Column conteniendo Autocomplete TextFields

    }//Cierre de Column externo

}
//Función que retorna el diámetro del objeto Planeta pasado como parámetro
@Composable
fun contenidoDatoDiam(planeta: Planeta?): String{
    var texto =""
     if(planeta!=null)
        texto = planeta.diametro
    return texto
}
//Función que retorna la distancia del objeto Planeta pasado como parámetro
fun contenidoDatoDistancia(planeta: Planeta?): String{
    var texto =""
    if(planeta!=null)
        texto = planeta.distancia
    return texto
}
//Función que retorna la densidad del objeto Planeta pasado como parámetro
fun contenidoDatoDensidad(planeta: Planeta?): String{
    var texto =""
    if(planeta!=null)
        texto = planeta.densidad
    return texto
}
//Función para obtener y dar formato al título de los datos
@Composable
private fun TituloDatos(titulo:String) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = titulo, color = fondoDatos, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}

//Función que coloca espacios
@Composable
fun MySpacer(medida: Int){
    Spacer(modifier = Modifier.width(medida.dp).height(medida.dp))
}

//Función que retorna lista de objetos tipo Planeta
fun getPlaneta(): List<Planeta>{
    return listOf(
        Planeta(
            "Mercurio",
            "0.382",
            "0.387",
            "5400"
        ),
        Planeta(
            "Venus",
            "0.949",
            "0.723",
            "5250"
        ),
        Planeta(
            "Tierra",
            "1",
            "1",
            "5520"
        ),
        Planeta(
            "Marte",
            "0.53",
            "1.542",
            "3960"
        ),
        Planeta(
            "Júpiter",
            "11.2",
            "5.203",
            "1350"
        ),
        Planeta(
            "Saturno",
            "9.41",
            "9.539",
            "700"
        ),
        Planeta(
            "Urano",
            "3.38",
            "19.81",
            "1200"
        ),
        Planeta(
            "Neptuno",
            "3.81",
            "30.07",
            "500"
        ),
        Planeta(
            "Plutón",
            "???",
            "39.44",
            "5?"
        )

    )
}