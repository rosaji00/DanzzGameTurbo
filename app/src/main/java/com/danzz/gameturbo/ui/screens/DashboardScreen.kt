package com.danzz.gameturbo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danzz.gameturbo.data.DeviceInfo
import com.danzz.gameturbo.ui.theme.*
import com.danzz.gameturbo.util.DeviceUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    deviceInfo: DeviceInfo,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Danzz Game Turbo",
            style = MaterialTheme.typography.displayLarge,
            color = NeonBlue,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Performance Companion",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Target device note
        if (DeviceUtils.isLikelyInfinixHot60()) {
            InfoBanner(
                text = "Perangkat terdeteksi: Infinix Hot 60 series",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            InfoBanner(
                text = "Target utama: Infinix Hot 60 5G (perangkat lain tetap didukung)",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Device Info Card
        SectionTitle(title = "Informasi Perangkat")
        Spacer(modifier = Modifier.height(8.dp))

        InfoCard {
            InfoRow(
                icon = Icons.Default.PhoneAndroid,
                label = "Produsen",
                value = deviceInfo.manufacturer
            )
            InfoRow(
                icon = Icons.Default.PhoneAndroid,
                label = "Model",
                value = deviceInfo.model
            )
            InfoRow(
                icon = Icons.Default.Info,
                label = "Versi Android",
                value = "Android ${deviceInfo.androidVersion} (SDK ${deviceInfo.sdkInt})"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display & Refresh Rate
        SectionTitle(title = "Layar & Refresh Rate")
        Spacer(modifier = Modifier.height(8.dp))

        InfoCard {
            val refreshText = deviceInfo.refreshRateHz?.let {
                String.format("%.0f Hz", it)
            } ?: DeviceInfo.NOT_AVAILABLE

            InfoRow(
                icon = Icons.Default.Speed,
                label = "Refresh Rate Sistem",
                value = refreshText
            )
            Text(
                text = "Catatan: Refresh rate layar ≠ FPS di dalam game. Aplikasi ini tidak memaksa FPS game.",
                style = MaterialTheme.typography.labelMedium,
                color = TextMuted,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Battery
        SectionTitle(title = "Baterai")
        Spacer(modifier = Modifier.height(8.dp))

        InfoCard {
            val batteryText = deviceInfo.batteryPercent?.let { "$it%" } ?: DeviceInfo.NOT_AVAILABLE
            val chargingText = when (deviceInfo.isCharging) {
                true -> "Sedang mengisi"
                false -> "Tidak mengisi"
                null -> DeviceInfo.NOT_AVAILABLE
            }

            InfoRow(
                icon = Icons.Default.BatteryFull,
                label = "Level Baterai",
                value = batteryText
            )
            InfoRow(
                icon = Icons.Default.BatteryFull,
                label = "Status Pengisian",
                value = chargingText
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Disclaimer
        Text(
            text = "MVP Version — Fitur launcher, monitor performa lanjutan, dan profil game akan ditambahkan bertahap.",
            style = MaterialTheme.typography.labelMedium,
            color = TextMuted,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = NeonBlueLight,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun InfoCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(DarkCard)
            .padding(16.dp),
        content = content
    )
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = NeonBlue,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = TextSecondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = TextPrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun InfoBanner(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(NeonBlue.copy(alpha = 0.15f), NeonBlueDark.copy(alpha = 0.1f))
                )
            )
            .padding(12.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = NeonBlueLight
        )
    }
}
