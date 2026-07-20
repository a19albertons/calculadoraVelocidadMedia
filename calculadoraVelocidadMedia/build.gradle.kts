// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // plugin predeterminados
    alias(libs.plugins.android.application) apply false

    // Generación de documentación con Dokka
    alias(libs.plugins.kotlin.dokka)

    // Ktlint para formatear el código
    alias(libs.plugins.ktlint)
}
