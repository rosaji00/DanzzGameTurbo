# Danzz Game Turbo

**Performance Companion** khusus ditujukan untuk Infinix Hot 60 5G (dan perangkat Android lain).

## Status Saat Ini: MVP (Tahap 2)

Fitur yang sudah diimplementasikan:
- Dashboard gelap dengan aksen biru neon
- Deteksi informasi perangkat (Manufacturer, Model, Android version, SDK)
- Pembacaan Refresh Rate layar sistem
- Informasi baterai (level + status charging)
- Navigasi dasar (single screen untuk MVP)
- Penanganan nilai "Tidak tersedia" jika API gagal

**Belum diimplementasikan** (akan dikerjakan bertahap):
- Game Launcher
- Performance Monitor lanjutan
- Game Profiles
- Session tracking
- Shortcut pengaturan sistem
- Overlay (opsional)

## Catatan Penting

Aplikasi ini **tidak** mengklaim dapat:
- Meningkatkan FPS game secara ajaib
- Memaksa Ultra settings
- Mengubah CPU/GPU governor
- Membypass batasan thermal OEM

Semua data yang ditampilkan berasal dari API publik Android.

## Cara Build (di komputer / CI)

1. Buka proyek ini di **Android Studio** (versi terbaru direkomendasikan).
2. Sync Gradle.
3. Build → Build Bundle(s) / APK(s) → Build APK(s).

Atau gunakan GitHub Actions (lihat file `.github/workflows/build.yml` yang akan ditambahkan nanti).

## Package Name
`com.danzz.gameturbo`

## Min SDK
26 (Android 8.0)

## Target SDK
35
