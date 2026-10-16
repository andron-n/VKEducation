package com.example.vkeducation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.Color

object MockApps {
    val items: List<AppInfo> = listOf(
        AppInfo(1, "Почта", "Читайте и отправляйте письма с любого ящика", "Общение", Icons.Default.Email, Color(0xFF1E88E5)),
        AppInfo(2, "Мессенджер", "Быстрые сообщения, звонки и групповые чаты", "Общение", Icons.Default.Phone, Color(0xFF43A047)),
        AppInfo(3, "Карты", "Навигация, пробки и общественный транспорт", "Путешествия", Icons.Default.Place, Color(0xFFE53935)),
        AppInfo(4, "Маркет", "Покупки с доставкой и удобным возвратом", "Покупки", Icons.Default.ShoppingCart, Color(0xFFFB8C00)),
        AppInfo(5, "Календарь", "События, напоминания и совместные планы", "Продуктивность", Icons.Default.DateRange, Color(0xFF8E24AA)),
        AppInfo(6, "Здоровье", "Шаги, пульс, сон и тренировки в одном месте", "Здоровье", Icons.Default.Favorite, Color(0xFFD81B60)),
        AppInfo(7, "Поиск", "Быстрый поиск по сети, картинкам и новостям", "Инструменты", Icons.Default.Search, Color(0xFF3949AB)),
        AppInfo(8, "Пароли", "Безопасное хранилище паролей и заметок", "Безопасность", Icons.Default.Lock, Color(0xFF546E7A)),
        AppInfo(9, "Уведомления", "Единый центр оповещений от всех сервисов", "Инструменты", Icons.Default.Notifications, Color(0xFFF4511E)),
        AppInfo(10, "Друзья", "Находите друзей и делитесь моментами", "Соцсети", Icons.Default.Person, Color(0xFF00897B)),
        AppInfo(11, "Обмен файлами", "Передавайте файлы между устройствами без проводов", "Инструменты", Icons.Default.Share, Color(0xFF039BE5)),
        AppInfo(12, "Настройки", "Тонкая настройка устройства и аккаунта", "Система", Icons.Default.Settings, Color(0xFF6D4C41)),
    )

    // «Витрина»: 9 приложений = 3 страницы по 3
    val showcase: List<AppInfo> get() = items.take(9)
}