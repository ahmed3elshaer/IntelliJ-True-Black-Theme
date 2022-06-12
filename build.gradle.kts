import org.jetbrains.changelog.closure
import org.jetbrains.intellij.tasks.RunPluginVerifierTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.4.0"
    id("org.jetbrains.intellij") version "1.3.0"
    id("org.jetbrains.changelog") version "1.1.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

repositories {
    mavenCentral()
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    updateSinceUntilBuild.set(false)
}

changelog {
    version = properties("pluginVersion")
    path = "${project.projectDir}/CHANGELOG.md"
    header = closure { version }
    itemPrefix = "-"
    keepUnreleasedSection = false
    groups = emptyList()
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    buildSearchableOptions {
        enabled = false
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))

        val description = """
<div>
  <h2>True Black Theme</h2>
  <br />
  <p>
    <img src="https://raw.githubusercontent.com/ahmed3elshaer/IntelliJ-True-Black-Theme/main/assets/logo.svg" width="600" align="center" />
  </p>
  <p> A Pure Dark theme with the highest contrast ratio eyes has ever known. I have been struggling a lot with all the outdated IntelliJ UI components; looking at what is the industry offers, we still tied to 10 years old Java UI. Additionally, we have themes that make it harder to make the IDE look nicer on High-Resolution displays. Yet I decided to eliminate all the clutter in the IDE and use a Pure #000000 and Pure #FFFFFF based theme. </p>
  <h2>Setup</h2>
  <h2>Plugin marketplace (recommended) 🛒</h2>
  <p> Go to Preferences | Plugins | Marketplace and search for True Black theme Install the plugin <br /> When prompted, restart your IDE. <br />
  </p>
  <h2>Manual</h2>
  <p>- To install the plugin manually, use the following steps. <br /> - Download the ZIP file from the latest release on GitHub. <br /> - Go to Preferences | Plugins in your editor and click the gear icon at the top. <br /> - Click Install Plugin from Disk... <br /> - Select the ZIP file you downloaded. <br /> - When prompted, restart your IDE. <br />
  </p>
  <h2>Tips 🌟</h2>
  <p> - Use one of the awesome fonts in here!. <br /> - Make Sure to hide all the non-essential Windows Tools, go to View | Appearance | Uncheck what's not important; for myself, I keep only (Status Bar, Toolbar), and Keyboard Shortcuts is enough. <br /> - Atom Icons is recommended. <br /> - Use a good IDE font to Preferences | Appearance & Behavior | Appearance | Check "Use custom font" | Select "Futura" for example. <br />
  </p>
  <h2>Color Palette 🎨</h2>
  <p>
    <img alt="Color Palette" src="https://raw.githubusercontent.com/ahmed3elshaer/IntelliJ-True-Black-Theme/main/assets/code-color-scheme.png" height="600" width="300" />
  </p>
</div>
        """.trimIndent()

        pluginDescription.set(description)
        changeNotes.set(provider { changelog.getLatest().toHTML() })
    }

    runPluginVerifier {
        ideVersions.set(
            properties("pluginVerifierIdeVersions")
                .split(",")
                .map(String::trim)
                .filter(String::isNotEmpty)
        )
        failureLevel.set(
            listOf(
                RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                RunPluginVerifierTask.FailureLevel.INVALID_PLUGIN
            )
        )
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getProperty("jetbrains.token"))
    }
}
