

plugins {


    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")


    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")

    id("com.google.devtools.ksp")


}





android {
    namespace  = "com.example.mazika"
    compileSdk = 34

    defaultConfig {
        applicationId  = "com.example.mazika"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        viewBinding =  true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility =  JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()

    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes =  true
    }


}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Material Design
    implementation("com.google.android.material:material:1.11.0")

    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    // Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.8.2")


    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-android-compiler:2.49")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")




    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore:24.10.0")

    // Firebase Storage KTX
    implementation("com.google.firebase:firebase-storage-ktx:20.3.0")

    // Firebase Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // ExoPlayer
    api ("com.google.android.exoplayer:exoplayer-core:2.14.0")
    api ("com.google.android.exoplayer:exoplayer-ui:2.14.0")
    api( "com.google.android.exoplayer:extension-mediasession:2.14.0")

    implementation ("androidx.work:work-runtime:2.9.0")


    implementation("com.github.marcinmoskala:ArcSeekBar:0.31")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    implementation("com.cuberto:liquid-swipe:1.0.0")

    val lottieVersion =  "6.0.1"
    implementation("com.airbnb.android:lottie:$lottieVersion")

    implementation("com.github.jgabrielfreitas:BlurImageView:1.0.1")


    val multidex_version = "2.0.1"
    implementation("androidx.multidex:multidex:$multidex_version")



}
