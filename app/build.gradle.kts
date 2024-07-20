plugins {
    id("com.android.application")
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.rohan1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rohan1"
        minSdk = 25
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
        multiDexEnabled  = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-messaging:23.4.1")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.airbnb.android:lottie:6.4.0")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("com.android.support:support-annotations:28.0.0")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation ("androidx.databinding:databinding-runtime:8.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    //for pd
    implementation ("com.google.firebase:firebase-storage:20.3.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

    // cloud messaging
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))

//     Add the dependencies for the Firebase Cloud Messaging and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")


}