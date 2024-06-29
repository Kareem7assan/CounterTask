import deps.Config
import java.util.Properties

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
        android.buildFeatures.buildConfig = true
        buildConfigField("String", "TWITTER_CONSUMER_KEY", "\"${project.ext.get("TWITTER_CONSUMER_KEY")}\"")
        buildConfigField("String", "TWITTER_CONSUMER_SECRET", "\"${project.ext.get("TWITTER_CONSUMER_SECRET")}\"")
        buildConfigField("String", "TWITTER_TOKEN", "\"${project.ext.get("TWITTER_TOKEN")}\"")
        buildConfigField("String", "TWITTER_TOKEN_SECRET", "\"${project.ext.get("TWITTER_TOKEN_SECRET")}\"")

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
/*fun getProperty(key: String): String {
    return project.findProperty(key) as String
}*/

// Function to load properties from local.properties file
fun getProperty(key: String): String {
    val properties = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
    return properties.getProperty(key)
}

// Define the properties
val twitterConsumerKey: String by lazy { getProperty("TWITTER_CONSUMER_KEY") }
val twitterConsumerSecret: String by lazy { getProperty("TWITTER_CONSUMER_SECRET") }
val twitterToken: String by lazy { getProperty("TWITTER_TOKEN") }
val twitterTokenSecret: String by lazy { getProperty("TWITTER_TOKEN_SECRET") }

subprojects {
    extra["TWITTER_CONSUMER_KEY"] = twitterConsumerKey
    extra["TWITTER_CONSUMER_SECRET"] = twitterConsumerSecret
    extra["TWITTER_TOKEN"] = twitterToken
    extra["TWITTER_TOKEN_SECRET"] = twitterTokenSecret
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
