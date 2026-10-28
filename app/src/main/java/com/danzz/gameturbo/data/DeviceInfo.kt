package com.danzz.gameturbo.data

/**
 * Data class yang menampung informasi perangkat yang dapat dibaca melalui API publik Android.
 * Nilai yang tidak tersedia akan berisi null atau string "Tidak tersedia".
 */
data class DeviceInfo(
    val manufacturer: String,
    val model: String,
    val device: String,
    val androidVersion: String,
    val sdkInt: Int,
    val refreshRateHz: Float?,          // null jika tidak dapat dibaca
    val batteryPercent: Int?,           // null jika tidak dapat dibaca
    val isCharging: Boolean?,
    val thermalStatus: String? = null   // hanya jika API mendukung
) {
    companion object {
        const val NOT_AVAILABLE = "Tidak tersedia"
    }
}
