package com.ahmed3elshaer.trueblack.activities

import com.ahmed3elshaer.trueblack.PluginMetadata
import com.ahmed3elshaer.trueblack.notifications.PluginNotification
import com.ahmed3elshaer.trueblack.settings.PluginMetaSettings
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.startup.StartupActivity

class StartupActivity : ProjectActivity, DumbAware {

    override suspend fun execute(project: Project) {
        val currentVersion = PluginMetadata.currentVersion() ?: "Version Error !"
        // get plugin current version
        val version = PluginManagerCore.getPlugin(PluginId.getId("com.ahmed3elshaer.trueblack"))?.version ?: ""

        if (currentVersion == version) {
            PluginNotification.notifyWelcome(project)
            return
        }
        PluginNotification.notifyReleaseNote(project)
    }
}