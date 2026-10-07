package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducation.ui.theme.VKEducationTheme
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import android.content.ActivityNotFoundException
import android.net.Uri
import android.widget.Toast

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// необязательный +, затем 5-15 цифр
private val PHONE_REGEX = Regex("^\\+?[0-9]{5,15}$")

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Возвращает текст или null, если поле пустое (и показывает ошибку)
    fun validatedInput(): String? {
        val text = input.trim()
        if (text.isEmpty()) {
            error = "Поле не должно быть пустым"
            return null
        }
        return text
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                error = null
            },
            label = { Text("Введите текст или номер телефона") },
            singleLine = true,
            isError = error != null,
            supportingText = { error?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val text = validatedInput() ?: return@Button
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.EXTRA_TEXT, text)
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = {
                val raw = validatedInput() ?: return@Button
                // убираем пробелы, дефисы и скобки: "+7(900) 123-45-67" -> "+79001234567   "
                val phone = raw.replace(Regex("[\\s\\-()]"), "")

                if (!PHONE_REGEX.matches(phone)) {
                    error = "Некорректный номер телефона"
                    return@Button
                }

                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Приложение для звонков не найдено", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = {
                val text = validatedInput() ?: return@Button

                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                try {
                    context.startActivity(Intent.createChooser(sendIntent, "Поделиться через…"))
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Нет приложений для отправки текста", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Поделиться текстом")
        }
    }
}