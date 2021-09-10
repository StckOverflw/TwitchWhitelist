plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "tv.banko"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    // SpigotMC Dependency
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT") // Only used on compile time because we have a SpigotMC Server

    implementation("com.github.twitch4j:twitch4j:1.5.1")
}

tasks {
    shadowJar {
        dependencies {
            exclude(dependency("org.spigotmc:spigot-api:.*"))
            exclude(dependency("net.md-5:*:.*"))
        }
    }
    jar {
        enabled = false
    }
    build {
        finalizedBy(shadowJar)
    }
    withType<JavaCompile> {
        options.release.set(11)
    }
}