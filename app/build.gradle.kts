plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.resturentsilverspoons"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.resturentsilverspoons"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.github.transferwise:sequence-layout:1.2.0")
    implementation ("co.ankurg.expressview:expressview:0.0.3")
    implementation ("com.makeramen:roundedimageview:2.3.0")
    implementation ("com.razorpay:checkout:1.6.33")
    implementation ("com.github.shuhart:stepview:1.5.1")
    implementation ("com.github.dhaval2404:imagepicker:2.1")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.github.yasserakbbach:PhoneNumberPicker:1.0.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation("com.airbnb.android:lottie:6.2.0")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}