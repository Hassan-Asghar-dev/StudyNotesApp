plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.studynotesapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.studynotesapp"
        minSdk = 24
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
    // This line gets the "annotationProcessor" function and makes it available.
    val annotationProcessor by configurations

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Room Dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    // Now we can use the annotationProcessor function
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.6")
}
