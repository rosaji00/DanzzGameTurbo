package com.danzz.gameturbo.util

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.view.WindowManager
import com.danzz.gameturbo.data.DeviceInfo

/**
 * Utility untuk membaca informasi perangkat menggunakan API publik Android saja.
 * Tidak menggunakan API tersembunyi atau reflection yang berbahaya.
 */
object DeviceUtils {

    fun getDeviceInfo(context: Context): DeviceInfo {
        val manufacturer = Build.MANUFACTURER.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
        val model = Build.MODEL
        val device = Build.DEVICE
        val androidVersion = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT

        val refreshRate = getRefreshRate(context)
        val batteryInfo = getBatteryInfo(context)

        return DeviceInfo(
            manufacturer = manufacturer,
            model = model,
            device = device,
            androidVersion = androidVersion,
            sdkInt = sdkInt,
            refreshRateHz = refreshRate,
            batteryPercent = batteryInfo.first,
            isCharging = batteryInfo.second
        )
    }

    /**
     * Membaca refresh rate layar yang dilaporkan sistem.
     * Mengembalikan null jika tidak dapat dibaca.
     */
    private fun getRefreshRate(context: Context): Float? {
        return try {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // API 30+
                windowManager?.defaultDisplay?.mode?.refreshRate
            } else {
                @Suppress("DEPRECATION")
                windowManager?.defaultDisplay?.refreshRate
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Membaca informasi baterai melalui sticky broadcast.
     * @return Pair(percentage, isCharging) atau Pair(null, null) jika gagal
     */
    private fun getBatteryInfo(context: Context): Pair<Int?, Boolean?> {
        return try {
            val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus = context.registerReceiver(null, intentFilter)

            if (batteryStatus != null) {
                val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

                val percent = if (level >= 0 && scale > 0) {
                    (level * 100) / scale
                } else null

                val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL

                Pair(percent, isCharging)
            } else {
                Pair(null, null)
            }
        } catch (e: Exception) {
            Pair(null, null)
        }
    }

    /**
     * Mengecek apakah perangkat kemungkinan adalah Infinix Hot 60 5G
     * (hanya untuk informasi, bukan hard requirement)
     */
    fun isLikelyInfinixHot60(): Boolean {
        val model = Build.MODEL.lowercase()
        val device = Build.DEVICE.lowercase()
        val manufacturer = Build.MANUFACTURER.lowercase()

        return manufacturer.contains("infinix") &&
                (model.contains("hot 60") || model.contains("x688") || device.contains("x688"))
    }
}
