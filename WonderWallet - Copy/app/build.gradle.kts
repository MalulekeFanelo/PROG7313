plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.wonderwallet"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.wonderwallet"
        minSdk = 26
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //  Camera (fixed)
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")

    // Charts
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //  Room (clean KSP setup)
    implementation("androidx.room:room-runtime:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")
}



