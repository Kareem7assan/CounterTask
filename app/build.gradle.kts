import deps.Config

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.plugin)


}

android {
    namespace = Config.nameSpace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.id
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
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        viewBinding = true
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
    implementation(project(":di"))
    implementation(project(":domain"))

    //essentials
    implementation(libs.kotlin.stdlib)
    implementation(libs.fragment.navigation)
    implementation(libs.ui.navigation)
    implementation(libs.runtime.navigation)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.signpost.core)
    implementation (libs.signpost.commonshttp4)

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

}