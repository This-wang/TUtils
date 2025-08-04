#!/bin/bash

# 配置信息
LIBRARY_MODULE="T-Utils" # 你的library模块名称
ALIYUN_MAVEN_URL="https://packages.aliyun.com/61108e7fadb2703b38cc5a8f/maven/2127297-release-5mc2rk" # 替换为你的阿里云Maven仓库地址

# 获取当前版本号 (适配Kotlin DSL)
CURRENT_VERSION=$(cat ${LIBRARY_MODULE}/build.gradle.kts | grep -E "version\s*=\s*\"[0-9]+\.[0-9]+\.[0-9]+\"" | awk -F"\"" '{print $2}')

# 拆分版本号
MAJOR=$(echo $CURRENT_VERSION | cut -d. -f1)
MINOR=$(echo $CURRENT_VERSION | cut -d. -f2)
PATCH=$(echo $CURRENT_VERSION | cut -d. -f3)

# 自动增加PATCH版本号
NEW_PATCH=$((PATCH + 1))
NEW_VERSION="${MAJOR}.${MINOR}.${NEW_PATCH}"

echo "Current version: $CURRENT_VERSION"
echo "New version: $NEW_VERSION"

# 更新gradle.properties中的版本号
sed -i "s/^tUtilsVersion=.*/tUtilsVersion=${NEW_VERSION}/" gradle.properties
# 更新README.md中的版本号
sed -i "s/${CURRENT_VERSION}/${NEW_VERSION}/g" README.md

# 提交版本号变更
git config --global user.name "GitHub Actions"
git config --global user.email "actions@github.com"
git add ${LIBRARY_MODULE}/build.gradle.kts
git add README.md
git commit -m "Auto-increment version to ${NEW_VERSION} [skip ci]"
git push

# 发布到阿里云Maven
./gradlew ${LIBRARY_MODULE}:clean ${LIBRARY_MODULE}:build ${LIBRARY_MODULE}:publish -PALIYUN_MAVEN_USERNAME=${ALIYUN_MAVEN_USERNAME} -PALIYUN_MAVEN_PASSWORD=${ALIYUN_MAVEN_PASSWORD}

echo "Library published to AliCloud Maven with version ${NEW_VERSION}"