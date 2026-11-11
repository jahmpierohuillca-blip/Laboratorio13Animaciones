package com.example.laboratorio13animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio1Screen() {
    // Aquí guardamos si la mantita está puesta o no (true = se ve, false = no se ve)
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botón mágico para aparecer o esconder
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Esconder cuadro" else "Mostrar cuadro")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // La magia sucede aquí: AnimatedVisibility
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),  // Aparece suave como un fantasma
            exit = fadeOut()    // Desaparece suave
        ) {
            // El cuadro de color brillante que vamos a mostrar u ocultar
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color(0xFFFF5722)) // Un lindo color naranja
            )
        }
    }
}