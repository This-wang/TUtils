plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.clj.t_utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 23

        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.exifinterface)
    implementation(libs.bundles.coil)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.clj.t-utils" // 替换为你的groupId
                artifactId = "t-utils" // 替换为你的artifactId
                version = property("tUtilsVersion").toString()
            }
        }

        repositories {
            maven {
                url = uri("https://packages.aliyun.com/61108e7fadb2703b38cc5a8f/maven/2127297-release-5mc2rk")
                credentials {
                    username = project.findProperty("ALIYUN_MAVEN_USERNAME") as String? ?: System.getenv("ALIYUN_MAVEN_USERNAME") ?: ""
                    password = project.findProperty("ALIYUN_MAVEN_PASSWORD") as String? ?: System.getenv("ALIYUN_MAVEN_PASSWORD") ?: ""
                }
            }
        }
    }
}