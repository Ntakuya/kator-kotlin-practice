# Creating An Interactive WebSite

## table of contents

0. 環境
1. projectを作成する
2. ktorの依存関係を追加する

## 0. 環境

```terminal
% gradle --version
------------------------------------------------------------
Gradle 7.5.1
------------------------------------------------------------

Build time:   2022-08-05 21:17:56 UTC
Revision:     d1daa0cbf1a0103000b71484e1dbfe096e095918

Kotlin:       1.6.21
Groovy:       3.0.10
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          18.0.2.1 (Homebrew 18.0.2.1+0)
OS:           Mac OS X 12.4 x86_64

```

## 1. Projectを作成する


[Gradle](https://gradle.org/)を利用して、Kotlinのプロジェクトを作成する。

```terminal
% gradle init

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2


Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 4


Split functionality across multiple subprojects?:
  1: no - only one application project
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 1

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Kotlin) [1..2] 2

Generate build using new APIs and behavior (some features may change in the next
Project name (default: creating-an-interactive-website): app
Source package (default: app): app

```

設定されたProjectの構成は以下。


```terminal
% tree .
.
├── app
│   ├── build.gradle.kts
│   └── src
│       ├── main
│       │   ├── kotlin
│       │   │   └── app
│       │   │       └── App.kt
│       │   └── resources
│       └── test
│           ├── kotlin
│           │   └── app
│           │       └── AppTest.kt
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

一度、buildとrunを実施して確かめる。

```terminal
% gradle build
BUILD SUCCESSFUL in 20s
8 actionable tasks: 8 executed

% gradle run
> Task :app:run
Hello World!

BUILD SUCCESSFUL in 1s
2 actionable tasks: 1 executed, 1 up-to-date
```

kotlinのプロジェクトが生成できたので、次にktorのプロジェクトを作成する。

## 2.  ktorのプロジェクトを作成する

### 2-1. 依存関係の解決

`app/build.gradle.kts`と依存関係を解決するために、`app.gradle.properties`を変更していきます。

```app/gradle.properties(作成)
kotlin_version=1.7.10
ktor_version=2.1.0
logback_version=1.2.11
kotlin.code.style=official
```

```app/build.gradle
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

application {
  mainClass.set("app.AppKt")
}

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
}

```

利用するlibrary自体は、ktor-serverとktor-server-netty。
log library は入れておかないと、javaのlibraryを入れる必要がでてくるので、logbackを利用しています。

```app/src/main/kotlin/resources/application.conf
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{YYYY-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="trace">
        <appender-ref ref="STDOUT"/>
    </root>
    <logger name="org.eclipse.jetty" level="INFO"/>
    <logger name="io.netty" level="INFO"/>
</configuration>
```

依存関係の構築が完了したら、`AppKt`の編集と`application.conf`を作成していきます。

### 2-2. AppKtのmoduleを有効化する

```app/src/main/kotlin/app/App.kt
package app

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
  }
}

```

### 2-3. application.confを作成してApplication.moduleを有効化する

```app/src/main/kotlin/resources/application.conf
ktor {
  development = true
  deployment {
    port = 8080
    port = ${?PORT}
  }
  application {
    modules = [app.AppKt.module]
  }
}
```

application modulesで定義しないとmoduleが有効にならないためconfに追記しました。


### 2-4. Serverを動かす

```terminal
% gradle clean build
% gradle run --daemon
```

[localhost:8080](http://localhost:8080)でhello world が表示されたら確認終了。

## refs

- [server dependencies](https://ktor.io/docs/server-dependencies.html)
- [modules](https://ktor.io/docs/modules.html#embedded-server)
- [configure files](https://ktor.io/docs/configurations.html#configuration-file)
- [installs](https://ktor.io/docs/plugins.html#install)
- [kotlin platform JVM](https://kotlinlang.org/docs/jvm-get-started.html#what-s-next)
