pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Klarna"
include(":app")
include(":feature")
include(":feature:weather")
include(":feature:weather:api")
include(":feature:weather:domain")
include(":feature:weather:presentation")
include(":domain")
include(":domain:network")
include(":common")
