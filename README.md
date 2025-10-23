# tUtils

# 设置仓库凭证
请在 setting.gradle.kts 中设置仓库的访问凭证。

dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        maven {
            url = uri(System.getenv("ALIYUN_MAVEN_T_UTILS"))
            credentials {
                username = System.getenv("ALIYUN_MAVEN_USERNAME")
                password = System.getenv("ALIYUN_MAVEN_PASSWORD")
            }
        }
    }
}

# 配置包信息

dependencies {
    compile 'com.clj.t-utils:t-utils:0.0.4'
}

<111111>
<22222>
<33333>