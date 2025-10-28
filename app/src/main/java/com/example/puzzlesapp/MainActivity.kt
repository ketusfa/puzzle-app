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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puzzlesapp.ui.theme.PuzzlesAppTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuzzlesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainMenuScreen()
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen() {
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
                onClick = { },
                iconRes = R.drawable.start_image // Ваша иконка для игры
            )

            // Кнопка "Настройки" с иконкой шестеренки
            GameButton(
                text = "Настройки",
                onClick = { },
                iconRes = R.drawable.sett_image // Ваша иконка настроек
            )

            // Кнопка "Выход" с иконкой выхода
            GameButton(
                text = "Выход",
                onClick = { },
                iconRes = R.drawable.exit_image // Ваша иконка выхода
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
        MainMenuScreen()
    }
}