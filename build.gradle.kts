plugins {
    kotlin("js") version "1.8.21"
}

group = "com.sknyazev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion = "2.2.4"

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-js:$ktorVersion")
}

kotlin {
    js(IR) {
        moduleName = group.toString()
        useCommonJs()
        generateTypeScriptDefinitions()
        binaries.library()
        binaries.executable()
        browser {
            testTask {
                useKarma {
                    useChrome()
                    //useFirefox()
                    //useSafari()
                }
            }
        }
    }
}
