<h1 align=center>Simply No Shading</h1>
<div align=justify>

&emsp;&emsp;Simply No Shading is a [Fabric]-based [modification] (<abbr>mod</abbr>) for the [Minecraft] [client]. This mod removes the darkening on sides or faces (also known as [shading]) to in-game objects such as blocks, clouds, and (block) entities (experimental) as illustrated below: TODO

&emsp;&emsp;Disabling shading results in a brighter environment, especially to blocks. Similar results for blocks can be achieved using [OptiFine], though **not** recommended for underpowered hardware, and by turning `Options > Video Settings > Shaders... > (internal) > Old Lighting` to `ON`. A similar simple mod [Flat Lighting] achieves similar results for blocks and supports Sodium as of version [`1.2.0`].

## Compatibility

&emsp;&emsp;Simply No Shading provides active compatibility for [BedrockIfy] and [Sodium]. Some mods that provide or replace stuff may work such as [Enhanced Block Entities] (<abbr>EBE</abbr>), where block entities acts like blocks and are affected as such when toggling shading. Some mods that replace rendering are most likely incompatible; exceptions are ones that have active support for such as Sodium, for example. One example incompatibility are shaders, usually applied using [Iris].

&emsp;&emsp;Simply No Shading recently added active support for the latest minor updates to major releases along with the some April Fool's snapshots. Following are all those versions: [`1.14.4`], [`1.15.2`], [`20w14infinite`], [`1.16.5`], [`1.17.1`], [`1.18.2`], [`22w13oneblockatatime`], [`1.19.4`], [`23w13a_or_b`], [`1.20`], [`1.20.1`], [`1.20.2`], and [`1.20.3`]. All minor releases for the latest major version is supported. Latest snapshot versions also get temporary support before the next major release.

## Features

&emsp;&emsp;Simply No Shading supports the following categories for toggling shading:
 - Blocks &mdash; this includes blocks, liquids, and <abbr title="Enhanced Block Entities">EBE</abbr>'s block entities
 - Clouds &mdash; this includes clouds
 - Entities (experimental) &mdash; this includes block entities, entities, and blocks, block entities, entities, and items in toasts (pop-ups; e.g. completing an [advancement]) and inventories and or Graphical User Interfaces (<abbr>GUI</abbr>s)

## Configuration

&emsp;&emsp;Simply No Shading has a configuration screen and it's accessible through:
 - a key mapping. It's unbound by default and can be bound at `Options > Controls > Keys > Simply No Shading > Open Config Screen`
 - [Mod Menu]. `Mods > Simply No Shading > Configure...`

&emsp;&emsp;Simply No Shading stores its configuration as a formatted <abbr title="JavaScript Object Notation">[JSON]</abbr> file at `<your minecraft folder>/config/simply-no-shading.json`.

## Source

&emsp;&emsp;Simply No Shading's sources, except the mod icon, is available under [MIT License]. [Gradle] along with the [Fabric Loom] [plugin] are the essential tools in developing and building the mod jar. The gradle build scripts are written in [Kotlin], the mod sources and mixins in [Java], and the [core shaders] contained in the included [resource pack] in [GLSL]. Java 17+ is required for loom to work. Gradle should be able to automatically get the right version of java that's used for compiling this mod.

### Building

&emsp;&emsp;Simply No Shading uses the Gradle build tool along with the Fabric Loom plugin. Loom applies the [Java Library plugin], meaning this mod can be built as if it was a regular java library. Here are some commands:
 - `./gradlew jar` &mdash; creates the mod jar and is exact jar to be placed in the `mods` folder
 - `./gradlew sourcesJar` &mdash; creates the mod sources jar and is useful when reading the soucre code
 - `./gradlew javadocJar` &mdash; creates the mod javadoc jar and is useful when reading the usage of certain code
 - `./gradlew build` &mdash; builds all three of the aforementioned jars

&emsp;&emsp;Loom also provides a way to test the mod in a live Minecraft client for the development environment by running `./gradlew runClient`.

### The Build Script

&emsp;&emsp;There are four files that are related to the build script:
 - [`gradle/libs.versions.toml`] &mdash; handles dependency version and coordinate informatiln
 - [`build.gradle.kts`] &mdash; handles plug-ins, task configurations, and dependency resolution and repositories
 - [`gradle.properties`] &mdash; only contains the mod's base version and essential properties
 - [`settings.gradle.kts`] &mdash; handles plug-in repositories

### Publishing

&emsp;&emsp;Simply No Shading is available on [JitPack], [GitHub Releases], and [Modrinth] especially the published releases. JitPack supports depending on specific commits and even pull requests. Releases that were exclusive for github were mostly alpha testing, especially ones for testing automated publishing. Modrinth is the preferred platform for downloading mod jars used in-game.

&emsp;&emsp;Simply No Shading follows [semantic versioning] and usually in the format `<major>.<minor>.<patch>-mc<minecraft>` for releases; `-alpha` and `-beta` are inserted after the patch version number with additional information separated with periods (`.`) for alpha and beta releases respectively. Non-releases, usually local builds, 

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    modImplementation("com.github.StartsMercury:simply-no-shading:<version>")
}
```

```gradle
repositories {
    maven { url = uri("https://api.modrinth.com/maven") }
}

dependencies {
    modImplementation("maven.modrinth:simply-no-shading:<version>")
}
```

### Mixins

&emsp;&emsp;[SpongePowered Mixins] inject, transform, or overwrite pre-existing java code such as Minecraft's code; modifying, adding, or disabling functionality. 

[`1.14.4`]: https://minecraft.wiki/w/Java_Edition_1.14.4
[`1.15.2`]: https://minecraft.wiki/w/Java_Edition_1.15.2
[`20w14infinite`]: https://minecraft.wiki/w/Java_Edition_20w14%E2%88%9E
[`1.16.5`]: https://minecraft.wiki/w/Java_Edition_1.16.5
[`1.17.1`]: https://minecraft.wiki/w/Java_Edition_1.17.1
[`1.18.2`]: https://minecraft.wiki/w/Java_Edition_1.18.2
[`22w13oneblockatatime`]: https://minecraft.wiki/w/Java_Edition_22w13oneBlockAtATime
[`1.19.4`]: https://minecraft.wiki/w/Java_Edition_1.19.4
[`23w13a_or_b`]: https://minecraft.wiki/w/Java_Edition_23w13a_or_b
[`1.20`]: https://minecraft.wiki/w/Java_Edition_1.20
[`1.20.1`]: https://minecraft.wiki/w/Java_Edition_1.20.1
[`1.20.2`]: https://minecraft.wiki/w/Java_Edition_1.20.2
[`1.20.3`]: https://minecraft.wiki/w/Java_Edition_1.20.3

[`gradle/libs.versions.toml`]: /gradle/libs.versions.toml
[`build.gradle.kts`]: /build.gradle.kts
[`gradle.properties`]: /gradle.properties
[`settings.gradle.kts`]: /settings.gradle.kts

[`1.2.0`]: https://modrinth.com/mod/flat-lighting/version/1.2.0
[advancement]: https://minecraft.wiki/w/Advancement
[BedrockIfy]: https://modrinth.com/mod/bedrockify
[client]: https://minecraft.wiki/w/Mods#Client-based
[core shaders]: https://minecraft.wiki/w/Shaders
[Enhanced Block Entities]: https://modrinth.com/mod/ebe
[Fabric]: https://fabricmc.net
[Fabric Loom]: https://github.com/FabricMC/fabric-loom
[Flat Lighting]: https://modrinth.com/mod/flat-lighting
[GLSL]: https://www.khronos.org/opengl/wiki/OpenGL_Shading_Language
[Gradle]: https://gradle.com
[GitHub Releases]: https://github.com/StartsMercury/simply-no-shading/releases
[Iris]: https://modrinth.com/mod/iris
[Java]: https://en.m.wikipedia.org/wiki/Java_(programming_language)
[Java Library plugin]: https://docs.gradle.org/current/userguide/java_library_plugin.html
[JitPack]: https://jitpack.io/#StartsMercury/simply-no-shading
[JSON]: https://en.m.wikipedia.org/wiki/JSON
[Kotlin]: https://kotlinlang.org/
[Minecraft]: https://minecraft.net
[MIT License]: /LICENSE
[modification]: https://minecraft.wiki/w/Mods
[Mod Menu]: https://modrinth.com/mod/modmenu
[Modrinth]: https://modrinth.com/mod/simply-no-shading/versions
[OptiFine]: https://optifine.net
[plugin]: https://docs.gradle.org/current/userguide/plugins.html
[resource pack]: https://minecraft.wiki/w/Resource_Packs
[shading]: https://en.m.wikipedia.org/wiki/Shading
[semantic versioning]: https://semver.org
[Sodium]: https://modrinth.com/mod/sodium
[SpongePowered Mixins]: https://github.com/SpongePowered/Mixin/wiki
