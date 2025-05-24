# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

This is an IntelliJ IDEA theme plugin called "True Dark Theme" that provides three high-contrast themes: TrueBlack, TrueDark, and TrueLight. The plugin is built with Kotlin using the IntelliJ Platform SDK and provides pure black (#000000) and white (#FFFFFF) based themes for optimal contrast.

## Development Commands

### Build and Test
```bash
# Build the plugin
./gradlew build

# Run the plugin in a test IDE instance
./gradlew runIde

# Verify plugin compatibility across IDE versions
./gradlew runPluginVerifier

# Run tests (if any)
./gradlew test
```

### Publishing
```bash
# Patch plugin.xml with changelog and build plugin
./gradlew patchPluginXml

# Build distributable plugin ZIP
./gradlew buildPlugin

# Publish to JetBrains Marketplace (requires token)
./gradlew publishPlugin
```

## Project Architecture

### Core Components
- **Plugin Configuration**: `src/main/resources/META-INF/plugin.xml` - Defines plugin metadata, dependencies, and extension points
- **Theme Definitions**: JSON files in `src/main/resources/` (TrueBlack.theme.json, TrueDark.theme.json, TrueLight.theme.json)
- **Color Schemes**: XML files in `src/main/resources/` (TrueBlack.xml, TrueDark.xml, TrueLight.xml)
- **Kotlin Classes**: Located in `src/main/java/com/ahmed3elshaer/trueblack/`

### Key Files
- `build.gradle.kts` - Gradle build configuration using IntelliJ Platform Plugin
- `gradle.properties` - Plugin version, compatibility, and platform configuration
- `plugin.xml` - Plugin manifest with theme providers and service registrations
- `PluginMetadata.kt` - Version information utility
- `StartupActivity.kt` - Plugin initialization logic
- `PluginMetaSettings.kt` - Plugin settings management

### Theme Structure
Each theme consists of:
1. **Theme JSON file** - UI component styling, colors, and visual properties
2. **Color scheme XML file** - Code editor syntax highlighting colors
3. **Theme provider registration** in plugin.xml

The themes use a color palette based on pure black/white with specific accent colors:
- Primary: #000000 (pure black)
- Secondary: #000000 
- Active selection: #2A2A2A
- Border colors: #1c1c1c

### Version Compatibility
- Supports IntelliJ builds 223.* to 233.*
- Uses Java 17 as target JVM version
- Built with Kotlin 1.8.20
- Uses IntelliJ Platform Plugin 1.15.0

### Publishing Configuration
Plugin is published to JetBrains Marketplace with ID `com.ahmed3elshaer.true-black-theme`. Version information is managed through gradle.properties and automatically updated in plugin.xml during build.