plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.apiferreteriamovil"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.apiferreteriamovil"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // Android base
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Retrofit (para consumir Web API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter (para convertir JSON <-> Objetos Java)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp Logging (para ver las solicitudes en Logcat)
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Coroutines (opcional para mejorar rendimiento, pero no obligatorio)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Testing (lo que ya tenías)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
