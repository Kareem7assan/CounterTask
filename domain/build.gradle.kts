import deps.Config
plugins {
    alias(libs.plugins.android.library.agp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.plugin)



}


android {
    compileSdk = Config.compileSdk
    namespace = "com.rowaad.app.domain"

    defaultConfig {
        minSdk = Config.minsdk
        targetSdk = Config.targetsdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kapt {
        correctErrorTypes = true
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

    implementation(project(":data"))

    //essentials
    implementation(libs.kotlin.stdlib)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    //retrofit
    implementation (libs.retrofit)
    implementation (libs.gson)
    implementation (libs.okHttp)


    //ktx
    implementation (libs.core.ktx)
    implementation (libs.activity.ktx.pref)

    //coroutines
    implementation (libs.coroutines.core)
    implementation (libs.coroutines.android)
    implementation (libs.lifecycle.android)
    //testImplementation 'org.jetbrains.kotlin:kotlin-test:1.6.0' // Adjust the version number according to your Kotlin version



    //unit test
    testImplementation (libs.junit)



    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.arch.test)
    testImplementation (libs.truth.test)
    testImplementation (libs.coroutine.test)
    testImplementation (libs.turbine)
    testImplementation (libs.robo.electric)
    testImplementation (libs.robo.electric.shadow)




//    testImplementation "org.jetbrains.kotlin:kotlin-test-junit:1.8.20"
    //androidTestImplementation(libs.ui.test.junit4)
    //androidTestImplementation(libs.androidx.junit)
    //debugImplementation(libs.ui.test.manifest)

/*
    androidTestImplementation (libs.test.rules)
    androidTestImplementation (libs.ham.crest)
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (libs.ui.automator)
    androidTestImplementation (libs.truth.test)
*/


}
