// Versión de Room


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")                             // Plugin KAPT para Room
    //id("androidx.navigation.safeargs.kotlin")  // Safe Args plugin
}

android {
    namespace = "com.marino.receitas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.marino.receitas"
        minSdk = 24
        targetSdk = 35
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

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val roomVersion = "2.7.1"
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    //implementation(libs.androidx.navigation.ui.ktx)

    // Gson for JSON
    implementation(libs.gson)
    implementation(libs.support.annotations)
    implementation(libs.androidx.annotation)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room dependencies
    //val room_version = "2.7.1"

    implementation("androidx.room:room-runtime:$roomVersion")

    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
    //ksp("androidx.room:room-compiler:$room_version")

    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
    kapt("androidx.room:room-compiler:$roomVersion")   // Usar KAPT en lugar de annotationProcessor
    //annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // Opcionales
    implementation("androidx.room:room-rxjava2:$roomVersion")
    implementation("androidx.room:room-rxjava3:$roomVersion")
    implementation("androidx.room:room-guava:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
}
