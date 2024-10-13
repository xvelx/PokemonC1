plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)

    libs.plugins.kotlin.serialization.get().run {
        kotlin(pluginId) version version.requiredVersion
    }
}

java {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

//kotlin {
//    compilerOptions {
//        jvmTarget.set(JvmTarget.JVM_1_8)
//    }
//}

dependencies {
    implementation(libs.javax.inject)

    implementation(platform(libs.retrofit.bom))
    api(libs.bundles.network)
}