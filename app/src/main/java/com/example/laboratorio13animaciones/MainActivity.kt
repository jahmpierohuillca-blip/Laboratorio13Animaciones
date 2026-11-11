package com.example.laboratorio13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class EstadoJuego {
    Inicio, Jugando, Victoria
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioFinalScreen()
        }
    }
}

@Composable
fun EjercicioFinalScreen() {
    var estadoJuego by remember { mutableStateOf(EstadoJuego.Inicio) }
    var modoPoder by remember { mutableStateOf(false) }
    var posicionY by remember { mutableStateOf(0.dp) }

    // 1. animateColorAsState
    val colorPersonaje by animateColorAsState(
        targetValue = if (modoPoder) Color(0xFFFF9800) else Color(0xFF2196F3),
        animationSpec = tween(500),
        label = "ColorAnim"
    )

    // 2. animateDpAsState
    val offsetY by animateDpAsState(
        targetValue = posicionY,
        animationSpec = tween(300),
        label = "PosicionAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 3. AnimatedContent
        AnimatedContent(
            targetState = estadoJuego,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
            },
            label = "EstadoAnim"
        ) { target ->
            when (target) {
                EstadoJuego.Inicio -> Text("Nivel 1: Inicio", fontSize = 24.sp)
                EstadoJuego.Jugando -> Text("En curso...", fontSize = 24.sp)
                EstadoJuego.Victoria -> Text("¡Juego Terminado!", fontSize = 24.sp)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = offsetY)
            ) {
                // 4. AnimatedVisibility
                AnimatedVisibility(
                    visible = modoPoder,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Text("Poder Activado", color = Color(0xFFFF9800), fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(colorPersonaje)
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { modoPoder = !modoPoder }) {
                    Text("Potenciador")
                }

                Button(onClick = { posicionY = if (posicionY == 0.dp) (-40).dp else 0.dp }) {
                    Text("Mover")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                estadoJuego = when (estadoJuego) {
                    EstadoJuego.Inicio -> EstadoJuego.Jugando
                    EstadoJuego.Jugando -> EstadoJuego.Victoria
                    EstadoJuego.Victoria -> EstadoJuego.Inicio
                }
            }) {
                Text("Siguiente Estado")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EjercicioFinalPreview() {
    EjercicioFinalScreen()
}