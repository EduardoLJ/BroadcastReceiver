package com.example.broadcastreceiver

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.broadcastreceiver.ui.theme.BroadcastReceiverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BroadcastReceiverTheme {
                AutoReplySettingsScreen()
            }
        }
    }
}

@Composable
fun AutoReplySettingsScreen() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf(SharedPreferencesHelper.getPhoneNumber(context)) }
    var message by remember { mutableStateOf(SharedPreferencesHelper.getMessage(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Configuración de Respuesta Automática", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Número de Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Mensaje de Respuesta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                SharedPreferencesHelper.savePhoneNumber(context, phoneNumber)
                SharedPreferencesHelper.saveMessage(context, message)
                Toast.makeText(context, "Configuración guardada", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Configuración")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutoReplySettingsScreenPreview() {
    BroadcastReceiverTheme {
        AutoReplySettingsScreen()
    }
}
