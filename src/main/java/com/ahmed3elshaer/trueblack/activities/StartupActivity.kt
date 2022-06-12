package com.ahmed3elshaer.trueblack.activities

import com.ahmed3elshaer.trueblack.PluginMetadata
import com.ahmed3elshaer.trueblack.notifications.PluginNotification
import com.ahmed3elshaer.trueblack.settings.PluginMetaSettings
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class StartupActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val currentVersion = PluginMetadata.currentVersion() ?: "Version Error !"
        val setting = ServiceManager.getService(
            PluginMetaSettings::class.java
        )
        if (setting.version.isEmpty()) {
            setting.version = currentVersion
            PluginNotification.notifyWelcome(project)
            return
        }
        if (currentVersion != setting.version) {
            setting.version = currentVersion
            PluginNotification.notifyReleaseNote(project)
        }
    }
}