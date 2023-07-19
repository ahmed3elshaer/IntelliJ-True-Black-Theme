package com.ahmed3elshaer.trueblack.notifications

import com.ahmed3elshaer.trueblack.PluginMetadata
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import org.intellij.lang.annotations.Language

object PluginNotification {
    @Language("HTML")
    private val RELEASE_NOTE = "<div>" +
            "<h3>What's New?</h3>" +
            "<ul>" +
            "<li>Support JetBrains 2023.1.3 and Later</li>" +
            "<li>Adding a new theme \"True Dark\"; a theme based on \"True Black\" with less contrasting black color.</li>" +
            "</ul>" +
            "<br>" +
            "<p><a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black/blob/main/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black\">Repository</a></p>" +
            "</div>"

    @Language("HTML")
    private val WELCOME_MESSAGE = "<div>" +
            "<p>Thank you for choosing <span>True Black Theme</span>.</p>" +
            "<br>" +
            "<p><a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black/blob/main/CHANGELOG.md\">Changelog</a> | <a href=\"https://github.com/ahmed3elshaer/Intelij-True-Black\">Repository</a></p>" +
            "</div>"
    private const val NOTIFICATION_GROUP_ID = "True Black Theme"
    private val icon = IconLoader.getIcon("/icons/logo.svg", PluginNotification::class.java)
    private val NOTIFICATION_GROUP = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
    fun notifyReleaseNote(project: Project?) {
        val notification = NOTIFICATION_GROUP.createNotification(
            "True Black Theme updated to v" + PluginMetadata.currentVersion(),
            RELEASE_NOTE,
            NotificationType.INFORMATION,
            NotificationListener.URL_OPENING_LISTENER
        )
        notification.icon = icon
        notification.notify(project)
    }

    fun notifyWelcome(project: Project?) {
        val notification = NOTIFICATION_GROUP.createNotification(
            "True Black Theme is installed",
            WELCOME_MESSAGE,
            NotificationType.INFORMATION,
            NotificationListener.URL_OPENING_LISTENER
        )
        notification.icon = icon
        notification.notify(project)
    }
}