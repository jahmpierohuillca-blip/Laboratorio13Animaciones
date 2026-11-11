package com.example.laboratorio13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ejercicio3Screen()
        }
    }
}

@Composable
fun Ejercicio3Screen() {
    // Guardamos el estado de si el cuadro está movido/agrandado o no
    var isMoved by remember { mutableStateOf(false) }

    // Animamos la posición (desplazamiento en X)
    val offsetX by animateDpAsState(
        targetValue = if (isMoved) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "OffsetAnimation"
    )

    // Animamos el tamaño del cuadro
    val sizeDp by animateDpAsState(
        targetValue = if (isMoved) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 800),
        label = "SizeAnimation"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { isMoved = !isMoved }) {
            Text(if (isMoved) "Regresar" else "Mover y Agrandar")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Aplicamos offset y luego size para modificar posición y tamaño
        Box(
            modifier = Modifier
                .offset(x = offsetX)
                .size(sizeDp)
                .background(Color(0xFF9C27B0)) // Color morado
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio3Preview() {
    Ejercicio3Screen()
}