package com.ahmed3elshaer.trueblack.settings

import com.ahmed3elshaer.trueblack.settings.PluginMetaSettings.MetaState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "PluginMetaSettings", storages = [Storage("true-black-theme.xml")])
class PluginMetaSettings : PersistentStateComponent<MetaState> {
    class MetaState {
        var version: String = ""
    }

    private var myState = MetaState()
    var version: String
        get() = myState.version
        set(version) {
            myState.version = version
        }

    override fun getState(): MetaState {
        return myState
    }

    override fun loadState(state: MetaState) {
        myState = state
    }
}