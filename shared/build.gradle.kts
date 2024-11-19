plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.googleGmsGoogleServices)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    //dependencies versions
    val coroutinesVersion = "1.6.4"
    val koinVersion = "3.3.2"
    val datastoreVersion = "1.1.1"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                api("io.insert-koin:koin-core:$koinVersion")

                implementation("androidx.datastore:datastore-preferences-core:$datastoreVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

                implementation(libs.firebase.auth)
                implementation(libs.firebase.firestore)
                implementation(libs.firebase.auth.ktx)
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


    tasks.register("testClasses")

}

android {
    namespace = "com.example.kotlin_social"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

