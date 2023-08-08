// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}


subprojects{
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt{
        toolVersion = "1.23.1"
        config.from(rootProject.files("config/detekt/detekt.yml"))
        autoCorrect = true
        // The directories where detekt looks for source files.
        // Defaults to `files("src/main/java", "src/test/java", "src/main/kotlin", "src/test/kotlin")`.
        source.setFrom("src/main/java", "src/main/kotlin")
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        include("**/*.kt", "**/*.kts")
        exclude("**/build/**")

        jvmTarget = JavaVersion.VERSION_17.toString()
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
            md.required.set(true)
        }
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")
        detektPlugins("com.twitter.compose.rules:detekt:0.0.26")
    }

}