import java.util.Properties
import java.io.File
import java.io.FileInputStream

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
        gradlePluginPortal()
        maven(url = "https://jitpack.io")

        val githubPropertiesFile = File(rootProject.projectDir, "github.properties")
        if (githubPropertiesFile.exists()) {
            val githubProperties = Properties().apply {
                FileInputStream(githubPropertiesFile).use { load(it) }
            }

            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Cuberto/liquid-swipe-android")
                credentials {
                    // Create 'github.properties' in the root project folder with
                    // gpr.usr=GITHUB_USER_ID & gpr.key=PERSONAL_ACCESS_TOKEN
                    // Or set environment variable GPR_USER & GPR_API_KEY if not adding a properties file
                    username = githubProperties.getProperty("gpr.usr") ?: System.getenv("GPR_USER")
                    password = githubProperties.getProperty("gpr.key") ?: System.getenv("GPR_API_KEY")
                }
            }
        }
    }
}

rootProject.name = "Mazika"
include(":app")
