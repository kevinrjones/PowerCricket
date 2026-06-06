import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(projects.shared)

        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutinesSwing)
        implementation(libs.koin.core)
        implementation(libs.koin.jvm)
        implementation(libs.jetbrains.compose.windowsizeclass)

        implementation(libs.compose.uiToolingPreview)
    }
}
compose.desktop {
    application {
        mainClass = "com.knowledgespike.powercricket.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.knowledgespike.powercricket"
            packageVersion = "1.0.0"
        }
    }
}