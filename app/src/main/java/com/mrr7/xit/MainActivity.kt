package com.mrr7.xit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Back = Color(0xFF0A0A0D)
private val Panel = Color(0xFF17171D)
private val Red = Color(0xFFFF303D)
private val Muted = Color(0xFFB6B6C2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Mrr7App() }
    }
}

@Composable
fun Mrr7App() {
    var popup by remember { mutableStateOf(true) }
    var espDemo by remember { mutableStateOf(false) }
    var headGuide by remember { mutableStateOf(false) }
    var percent by remember { mutableFloatStateOf(50f) }
    var showLines by remember { mutableStateOf(true) }
    var showNames by remember { mutableStateOf(true) }

    MaterialTheme(colorScheme = darkColorScheme(
        primary = Red, background = Back, surface = Panel,
        onBackground = Color.White, onSurface = Color.White
    )) {
        Surface(Modifier.fillMaxSize(), color = Back) {
            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("XIT MRR7", color = Red, fontSize = 28.sp, fontWeight = FontWeight.Black)
                        Text("EXTERNAL CONTROL PANEL", color = Muted, fontSize = 11.sp, letterSpacing = 1.5.sp)
                    }
                    Text("UI DEMO", color = Red, fontSize = 11.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.background(Color(0xFF351217), RoundedCornerShape(8.dp)).padding(10.dp))
                }

                CardBlock("PAINEL FLUTUANTE") {
                    Text("Prévia do pop-up", color = Muted, fontSize = 13.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Mostrar painel compacto", Modifier.weight(1f))
                        Switch(checked = popup, onCheckedChange = { popup = it })
                    }
                    if (popup) {
                        Row(
                            Modifier.fillMaxWidth().background(Color(0xFF260D11), RoundedCornerShape(12.dp)).padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.size(10.dp).background(Red, RoundedCornerShape(50)))
                            Spacer(Modifier.width(10.dp))
                            Text("MRR7  •  PAINEL ATIVO", Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text("•••", color = Red, fontWeight = FontWeight.Black)
                        }
                    }
                }

                CardBlock("MIRA • AJUSTE") {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Guia de mira (demonstração)", Modifier.weight(1f))
                        Switch(checked = headGuide, onCheckedChange = { headGuide = it })
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Percentual", color = Muted)
                        Spacer(Modifier.weight(1f))
                        Text("${percent.toInt()}%", color = Red, fontSize = 22.sp, fontWeight = FontWeight.Black)
                    }
                    Slider(value = percent, onValueChange = { percent = it }, valueRange = 0f..100f)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("0%", color = Muted, fontSize = 11.sp)
                        Text("100%", color = Muted, fontSize = 11.sp)
                    }
                    Text("Este controle altera apenas a prévia visual do aplicativo.", color = Muted, fontSize = 12.sp)
                }

                CardBlock("ESP • CENÁRIO DE TESTE") {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Indicadores de demonstração")
                            Text("Não lê dados do Free Fire.", color = Muted, fontSize = 12.sp)
                        }
                        Switch(checked = espDemo, onCheckedChange = { espDemo = it })
                    }
                    if (espDemo) {
                        Divider(color = Color(0xFF393943))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Linhas de referência", Modifier.weight(1f))
                            Switch(checked = showLines, onCheckedChange = { showLines = it })
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Rótulos de exemplo", Modifier.weight(1f))
                            Switch(checked = showNames, onCheckedChange = { showNames = it })
                        }
                        Box(
                            Modifier.fillMaxWidth().height(130.dp)
                                .background(Color(0xFF101015), RoundedCornerShape(10.dp)).padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(if (showNames) "ALVO DE EXEMPLO" else "• • •", color = Red, fontWeight = FontWeight.Bold)
                                if (showLines) Text("──────────", color = Red)
                                Text("Prévia visual local", color = Muted, fontSize = 11.sp)
                            }
                        }
                    }
                }

                Button(
                    onClick = { popup = !popup },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Red),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (popup) "MINIMIZAR PRÉVIA" else "ABRIR PRÉVIA", fontWeight = FontWeight.Black)
                }
                Text("XIT MRR7 • versão 1.0 • interface local", color = Muted, fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
private fun CardBlock(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(16.dp)).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(title, color = Red, fontSize = 12.sp, fontWeight = FontWeight.Black, letterSpacing = 1.sp)
        content()
    }
}
