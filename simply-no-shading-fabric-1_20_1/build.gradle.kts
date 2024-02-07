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
val createCompatTest: (String, Array<out String>) -> Unit by extra

group = subgroup
base.archivesName.set("simply-no-shading-fabric-1_20_1")

val gameVersion by extra("1.20.1")
val minecraftVersion by extra("1.20.1")

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

createCompatTest("bedrockify", arrayOf())
createCompatTest("enhancedblockentities", arrayOf())
createCompatTest("sodium", arrayOf())
createCompatTest("indium", arrayOf("sodium"))

dependencies {
    val fabricApiVersion = "0.91.0+1.20.1"
    fun net.fabricmc.loom.configuration.FabricApiExtension.module(moduleName: String): Dependency =
        fabricApi.module(moduleName, fabricApiVersion)

    // ARRP
    "modEnhancedblockentitiesCompatTestClientRuntimeOnly"("maven.modrinth:arrp:0.6.7") {
        exclude(mapOf("module" to "fabric-loader"))
    }

    // BedrockIfy
    "modBedrockifyAuto"("maven.modrinth:bedrockify:1.9.1+mc1.20")

    // Cloth Config
    "modBedrockifyCompatTestClientRuntimeOnly"("me.shedaniel.cloth:cloth-config-fabric:11.0.98") {
        exclude(mapOf("group" to "net.fabricmc.fabric-api"))
        exclude(mapOf("module" to "fabric-loader"))
        exclude(mapOf("module" to "gson"))
    }

    // Deobfuscation Mappings (required)
    mappings(loom.officialMojangMappings())

    // Enhanced Block Entities
    "modEnhancedblockentitiesClientAuto"("maven.modrinth:ebe:0.9+1.20")

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

    // Indium
    "modIndiumClientAuto"("maven.modrinth:indium:1.0.27+mc1.20.1")

    // Minecraft (required)
    minecraft("com.mojang:minecraft:${minecraftVersion}")

    // ModMenu
    "modClientImplementation"("com.terraformersmc:modmenu:7.2.2") {
        exclude(mapOf("module" to "fabric-loader"))
    }

    // Sodium
    "modSodiumClientAuto"("maven.modrinth:sodium:mc1.20.1-0.5.3")

    // SpruceUI
    include("modClientImplementation"("dev.lambdaurora:spruceui:5.0.2+1.20") {
        exclude(mapOf("module" to "fabric-loader"))
    })
}

/******************************************************************************/
/* TASK CONFIGURATIONS                                                        */
/******************************************************************************/

configureJarTask()
configureJavadocTask()
configureProcessResourcesTasks()