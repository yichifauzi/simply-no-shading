/******************************************************************************/
/* GRADLE PLUGINS                                                             */
/******************************************************************************/

plugins {
    id("fabric-loom")
}

/******************************************************************************/
/* PROJECT PROPERTIES                                                         */
/******************************************************************************/

val subgroup: String by rootProject.extra

val configureExtras: Action<Project> by rootProject.extra
configureExtras(project)

val configureJarTask: () -> Unit by extra
val configureJava: (Int) -> Action<JavaPluginExtension> by extra
val configureJavadocTask: () -> Unit by extra
val configureProcessResourcesTasks: () -> Unit by extra

group = subgroup
base.archivesName.set("simply-no-shading-fabric-1_18_2")

val gameVersion by extra("1.18.2")
val minecraftVersion by extra("1.18.2")

/******************************************************************************/
/* ADDITIONAL REPOSITORIES (see settings.gradle)                              */
/******************************************************************************/

repositories {
    maven {
        name = "Gegy Maven"
        url = uri("https://maven.gegy.dev")
        content {
            // spruce-ui
            includeGroup("dev.lambdaurora")
        }
    }

    maven {
        name = "shedaniel's Maven"
        url = uri("https://maven.shedaniel.me")
        content {
            // cloth-config-fabric
            includeGroup("me.shedaniel.cloth")
        }
    }

    maven {
        name = "Terraformers Maven"
        url = uri("https://maven.terraformersmc.com")
        content {
            // modmenu
            includeGroup("com.terraformersmc")
        }
    }

    // JitPack dependencies
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
        content {

        }
    }

    // mod dependencies without dedicated repositories
    maven {
        name = "Modrinth Maven"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }

    // mod dependencies that aren't on modrinth or have corrupt version numbers
    ivy {
        name = "GitHub Releases"
        url = uri("https://github.com")
        patternLayout {
            artifact("[organization]/releases/download/[revision]/[module](-[classifier]).[ext]")
            artifact("[organization]/releases/download/[revision]/[module]-[revision](-[classsifier]).[ext]")
            setM2compatible(true)
        }
        metadataSources {
            artifact()
        }
    }
}

/******************************************************************************/
/* DEPENDENCIES                                                               */
/******************************************************************************/

java(configureJava(17))

loom {
    runtimeOnlyLog4j = true

    splitEnvironmentSourceSets()
}

dependencies {
    val fabricApiVersion = "0.77.0+1.18.2"
    fun net.fabricmc.loom.configuration.FabricApiExtension.module(moduleName: String): Dependency =
        fabricApi.module(moduleName, fabricApiVersion)

    // BedrockIfy
    modCompileOnly("maven.modrinth:bedrockify:1.4.1+mc1.18.2")

    // Deobfuscation Mappings (required)
    mappings(loom.officialMojangMappings())

    // Fabric API
    modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${fabricApiVersion}")

    // Fabric Lifecycle Events (v1)
    modCompileOnly(fabricApi.module("fabric-lifecycle-events-v1"))

    // Fabric Loader (required)
    modImplementation(libs.fabric.loader)

    // Fabric Key Binding API (v1)
    modCompileOnly(fabricApi.module("fabric-key-binding-api-v1"))

    // Fabric Resource Loader (v0)
    modCompileOnly(fabricApi.module("fabric-resource-loader-v0"))

    // Minecraft (required)
    minecraft("com.mojang:minecraft:${minecraftVersion}")

    // ModMenu
    "modClientImplementation"("com.terraformersmc:modmenu:3.2.5") {
        exclude(mapOf("module" to "fabric-loader"))
    }

    // Sodium
    modCompileOnly("maven.modrinth:sodium:mc1.18.2-0.4.1")

    // SpruceUI
    include("modClientImplementation"("dev.lambdaurora:spruceui:3.3.3+1.18") {
        exclude(mapOf("module" to "fabric-loader"))
    })
}

/******************************************************************************/
/* TASK CONFIGURATIONS                                                        */
/******************************************************************************/

configureJarTask()
configureJavadocTask()
configureProcessResourcesTasks()
