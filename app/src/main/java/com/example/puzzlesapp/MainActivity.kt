package com.example.puzzlesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.puzzlesapp.ui.theme.PuzzlesAppTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuzzlesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PuzzlesApp()
                }
            }
        }
    }
}

@Composable
fun PuzzlesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_menu" // Стартовый экран
    ) {
        // Главное меню
        composable("main_menu") {
            MainMenuScreen(navController = navController)
        }
        // Экран настроек
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        // Экран игры
        composable("game") {
            GameScreen(navController = navController)
        }
    }
}

@Composable
fun MainMenuScreen(navController: NavHostController) {

    val showExitDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Фон меню
        Image(
            painter = painterResource(id = R.drawable.menu_image), // ваше фоновое изображение
            contentDescription = "Game background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // или FillBounds, FillWidth, FillHeight, Inside
        )

        GameTitle()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Кнопка "Начать игру" с иконкой игры
            GameButton(
                text = "Начать игру",
                onClick = { navController.navigate("game") },
                iconRes = R.drawable.start_image // Ваша иконка для игры
            )

            // Кнопка "Настройки" с иконкой шестеренки
            GameButton(
                text = "Настройки",
                onClick = { navController.navigate("settings") },
                iconRes = R.drawable.sett_image // Ваша иконка настроек
            )

            // Кнопка "Выход" с иконкой выхода
            GameButton(
                text = "Выход",
                onClick = {
                    showExitDialog.value = true // Показываем диалог
                },
                iconRes = R.drawable.exit_image
            )

            // Диалоговое окно подтверждения выхода
            if (showExitDialog.value) {
                ExitConfirmationDialog(
                    onConfirm = {
                        // Закрываем приложение
                        android.os.Process.killProcess(android.os.Process.myPid())
                    },
                    onDismiss = {
                        showExitDialog.value = false // Скрываем диалог
                    }
                )
            }
        }
    }
}

// Диалоговое окно подтверждения выхода
@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss, // Закрытие при клике вне диалога
        title = {
            Text(
                text = "Выход из игры",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        text = {
            Text("Вы действительно хотите выйти из игры?")
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F) // Красный цвет для подтверждения
                )
            ) {
                Text("Да")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Нет")
            }
        }
    )
}

@Composable
fun GameScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A2E)), // Синий фон
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Игровой экран",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Здесь будет игра-пазл",
                color = Color.White,
                fontSize = 18.sp
            )

            // Кнопка возврата в меню
            GameButton(
                text = "Назад в меню",
                onClick = {
                    navController.popBackStack() // ВОЗВРАТ НАЗАД
                }
            )
        }
    }
}

@Composable
fun GameButton(
    text: String,
    onClick: () -> Unit,
    iconRes: Int? = null // Добавляем необязательный параметр для иконки
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFA1906E),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(60.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 3.dp,
            color = Color(0xFF000000)
        )
    ) {
        // Если есть иконка - показываем Row с текстом и иконкой
        if (iconRes != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically), // Занимает основное пространство
                    textAlign = TextAlign.Center// Центрируем текст
                )
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null, // Декоративная иконка
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
            // Если иконки нет - просто текст
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically), // Занимает основное пространство
                textAlign = TextAlign.Center // Центрируем текст
            )
        }
    }
}

@Composable
fun GameTitle() {
    Box(
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 176.dp)
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(16.dp), // Внутренние отступы
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "О моем перерождении в приложение-пазл в новом мире в качестве курсовой работы о мобильной разработке", // Замените на ваше название
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuPreview() {
    PuzzlesAppTheme {
        MainMenuScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GamePreview() {
    PuzzlesAppTheme {
        GameScreen(navController = rememberNavController())
    }
}