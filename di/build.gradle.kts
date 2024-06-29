import deps.Config
plugins {
    alias(libs.plugins.android.library.agp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.plugin)


}
android {
    compileSdk = Config.compileSdk
    namespace = "com.rowaad.app.di"


    defaultConfig {
        minSdk = Config.minsdk
        targetSdk = Config.targetsdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        /*javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }*/
    }



    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }



}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    //implementation fileTree(dir: "libs", include: ["*.jar"])

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation (libs.signpost.core)
    implementation (libs.signpost.commonshttp4)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.gson)
    implementation (libs.okHttp)


}
