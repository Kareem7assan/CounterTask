import deps.Config
plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.library.agp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kapt)
}

android {
    compileSdk = Config.compileSdk
    namespace = "com.rowaad.app.data"


    defaultConfig {
        minSdk = Config.minsdk
        targetSdk = Config.targetsdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       /* javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }*/
    }


   /*
    configurations.all {
        resolutionStrategy {
            force = 'androidx.core:core-ktx:1.6.0'
        }
    }*/
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




}
