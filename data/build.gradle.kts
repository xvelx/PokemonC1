plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.kapt.get().toString())
    libs.plugins.kotlin.serialization.get().run {
        kotlin(pluginId) version version.requiredVersion
    }
}

android {
    namespace = "io.github.xvelx.pokemonc1.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(project(":domain"))
    implementation(project(":network"))
    implementation(libs.jetpack.paging.core)

    testImplementation(libs.junit)
    testImplementation(platform(libs.strikt.bom))
    testImplementation(libs.bundles.test)
}