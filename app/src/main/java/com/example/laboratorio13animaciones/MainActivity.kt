package com.example.laboratorio13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Tres estados definidos
enum class EstadoApp {
    Cargando, Contenido, Error
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ejercicio4Screen()
        }
    }
}

@Composable
fun Ejercicio4Screen() {
    var estadoActual by remember { mutableStateOf(EstadoApp.Cargando) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            estadoActual = when (estadoActual) {
                EstadoApp.Cargando -> EstadoApp.Contenido
                EstadoApp.Contenido -> EstadoApp.Error
                EstadoApp.Error -> EstadoApp.Cargando
            }
        }) {
            Text("Cambiar Estado")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 2 y 3. AnimatedContent con fadeIn y fadeOut en tiempo personalizado (600ms)
        AnimatedContent(
            targetState = estadoActual,
            transitionSpec = {
                fadeIn(animationSpec = tween(600)) togetherWith fadeOut(animationSpec = tween(600))
            },
            label = "StateAnimation"
        ) { targetState ->
            when (targetState) {
                EstadoApp.Cargando -> {
                    Text("⏳ Cargando datos...", fontSize = 20.sp, color = Color.Gray)
                }
                EstadoApp.Contenido -> {
                    Text("🟢 ¡Carga exitosa! Aquí está la información.", fontSize = 20.sp, color = Color(0xFF2E7D32))
                }
                EstadoApp.Error -> {
                    Text("❌ ¡Error! Ocurrió un problema de conexión.", fontSize = 20.sp, color = Color.Red)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Ejercicio4Preview() {
    Ejercicio4Screen()
}