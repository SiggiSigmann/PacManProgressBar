plugins {
  id("java")
  id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = "com.github.siggisigmann.PacManProgressBar"
version = "1.6"

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    create("IC", "2025.1")
    testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

    // Add necessary plugin dependencies for compilation here, example:
    // bundledPlugin("com.intellij.java")
  }
}

intellijPlatform {
  pluginConfiguration {
    ideaVersion {
      sinceBuild = "242"
    }
  }
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
  }
}