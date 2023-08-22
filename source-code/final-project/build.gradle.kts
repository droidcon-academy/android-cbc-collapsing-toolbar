buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.gradle.plugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
