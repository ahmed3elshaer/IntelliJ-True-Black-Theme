package com.ahmed3elshaer.trueblack.settings

import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "TrueBlackSettings",
    storages = [Storage("TrueBlackTheme.xml")]
)
@Service
class TrueBlackSettings : PersistentStateComponent<TrueBlackSettings> {
    var useCustomIcons: Boolean = true
    var scrollBarOpacity: Int = 100
    var enableBackgroundImage: Boolean = false
    var backgroundImagePath: String = ""
    var backgroundOpacity: Int = 5
    var useHighContrastBorders: Boolean = true
    
    override fun getState(): TrueBlackSettings = this
    
    override fun loadState(state: TrueBlackSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
    
    companion object {
        @JvmStatic
        fun getInstance(): TrueBlackSettings = service()
    }
} 