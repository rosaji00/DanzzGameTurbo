package com.danzz.gameturbo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.danzz.gameturbo.ui.screens.DashboardScreen
import com.danzz.gameturbo.ui.theme.DanzzGameTurboTheme
import com.danzz.gameturbo.util.DeviceUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DanzzGameTurboTheme {
                DanzzGameTurboApp()
            }
        }
    }
}

@Composable
fun DanzzGameTurboApp() {
    val context = LocalContext.current
    val deviceInfo = remember {
        DeviceUtils.getDeviceInfo(context)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        DashboardScreen(
            deviceInfo = deviceInfo,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
