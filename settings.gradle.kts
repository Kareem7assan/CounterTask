pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://dl.bintray.com/poldz123/maven/") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { url = uri("https://maven.google.com") }

        gradlePluginPortal()    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { url = uri("https://jitpack.io") }
        gradlePluginPortal()

        mavenCentral()
    }
}

rootProject.name = "CounterTask"
include(":app")
include(":data")
include(":domain")
include(":di")
