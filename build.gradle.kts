import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
	kotlin("jvm") version "1.8.20"
	kotlin("plugin.serialization") version "1.8.20"
	id("net.minecraftforge.gradle") version "5.1.+"
	id("maven-publish")
	id("org.parchmentmc.librarian.forgegradle") version "1.+"
	id("org.spongepowered.mixin")
}

version = "1.19.2-1.0.0"
group = "dev.epicsquid"

val modid: String = "ranchersdelight"
archivesName.set(modid)

jarJar.enable()

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

mixin {
	add(sourceSets.main.get(), "ranchersdelight.refmap.json")
}

minecraft {
	mappings("parchment", "2022.11.27-1.19.2")

	runs {
		create("client") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")
			property("forge.enabledGameTestNamespaces", modid)
			arg("-mixin.config=$modid.mixins.json")

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}

		create("server") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")
			property("forge.enabledGameTestNamespaces", modid)
			arg("-mixin.config=$modid.mixins.json")

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}

		create("data") {
			workingDirectory(project.file("run"))

			property("forge.logging.markers", "REGISTRIES")
			property("forge.logging.console.level", "debug")
//			arg("-mixin.config=$modid.mixins.json")

			args(
				"--mod", modid,
				"--all",
				"--output", file("src/generated/resources/"),
				"--existing", file("src/main/resources/")
			)

			mods {
				create(modid) {
					source(sourceSets.main.get())
				}
			}
		}
	}
}

sourceSets {
	main {
		resources.srcDirs("src/generated/resources")
	}
}

repositories {
	// Curios
	maven("https://maven.theillusivec4.top/")
	// Registrate
	maven("https://maven.tterrag.com/")
	// Kotlin for Forge
	maven("https://thedarkcolour.github.io/KotlinForForge/")
	// JEI
	maven("https://dvs1.progwml6.com/files/maven/")
	// Botania
	maven("https://maven.blamejared.com")
	// Curseforge
	maven {
		name = "Curse Maven"
		url = uri("https://cursemaven.com")
		content {
			includeGroup("curse.maven")
		}
	}
	mavenCentral()
}

dependencies {
	val minecraftVersion = "1.19.2"
	minecraft("net.minecraftforge:forge:$minecraftVersion-43.2.8")
	implementation("thedarkcolour:kotlinforforge:3.11.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-Beta")

	// Registrate
	val registrateVersion = "MC1.19-1.1.5"
	jarJar(group = "com.tterrag.registrate", name = "Registrate", version = "[MC1.19-1.1.5,)") {
		jarJar.pin(this, registrateVersion)
	}
	implementation(fg.deobf("com.tterrag.registrate:Registrate:$registrateVersion"))

	// Jei
	runtimeOnly(fg.deobf("mezz.jei:jei-$minecraftVersion-forge:11.2.0.246"))

	// Other Mod Compatibility
	implementation(fg.deobf("curse.maven:farmers_delight-398521:3999157"))
}

tasks.withType(GenerateModuleMetadata::class.java) {
	enabled = false
}

tasks.register("jarJarRelease") {
	doLast {
		tasks.getByName("jarJar") {
			(this as Jar).archiveClassifier.set("")
		}
	}
	finalizedBy(tasks.getByName("jarJar"))
}

tasks.jar.get().finalizedBy("reobfJar")
tasks.named("jarJar").get().finalizedBy("reobfJarJar")

tasks.jar {
	manifest {
		attributes(
			"Specification-Title" to modid,
			"Specification-Vendor" to "epicsquid",
			"Specification-Version" to "1",
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to "epicsquid"
		)
	}
}

tasks.jar {
	finalizedBy("reobfJar")
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}
