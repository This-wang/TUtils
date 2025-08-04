pluginManagement {
    repositories {
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        maven {
            url = uri(System.getenv("ALIYUN_MAVEN_T_UTILS"))
            credentials {
                username = System.getenv("ALIYUN_MAVEN_USERNAME")
                password = System.getenv("ALIYUN_MAVEN_PASSWORD")
            }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "T-Utils"
include(":app")
include(":T-Utils")
