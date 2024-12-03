plugins {
    kotlin("multiplatform")
    id("com.android.library")

    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.9.10"
}

kotlin {
    task("testClasses")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    //dependencies versions
    val coroutinesVersion = "1.6.4"
    val koinVersion = "3.3.2"
    val ktorVersion = "2.2.1"
    val datastoreVersion = "1.1.1"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                api("io.insert-koin:koin-core:$koinVersion")

                implementation("androidx.datastore:datastore-preferences-core:$datastoreVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

//                implementation("dev.gitlive:firebase-firestore:1.8.1") // This line
//                implementation("dev.gitlive:firebase-auth:2.1.0") // This line
//                implementation("dev.gitlive:firebase-storage:2.1.0") // This line
//                implementation("dev.gitlive:firebase-common:1.8.1")// This line
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // This line
            }
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }


        val androidMain by getting {
            dependencies {
                api("io.insert-koin:koin-android:$koinVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                api("androidx.datastore:datastore-preferences:$datastoreVersion")

            }
        }


        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting


        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies{
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.kotlin_social"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation(libs.firebase.auth.ktx)
}

