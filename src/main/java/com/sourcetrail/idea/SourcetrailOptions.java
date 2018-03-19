package io.sourcetrail.idea;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
        name = "SourcetrailOptions",
        storages = {
                @Storage("sourcetrail.xml")
        }
)

public class SourcetrailOptions implements PersistentStateComponent<SourcetrailOptions> {

    private Integer mySourcetrailPort = 6667;
    private Integer myEditorPort = 6666;

    public static SourcetrailOptions getInstance() {
        return ServiceManager.getService(SourcetrailOptions.class);
    }

    @Nullable
    public SourcetrailOptions getState() {
        return this;
    }

    public void loadState(SourcetrailOptions state)
    {
        XmlSerializerUtil.copyBean(state, this);
    }

    public Integer getSourcetrailPort()
    {
        return mySourcetrailPort;
    }

    public Integer getEditorPort() {
        return myEditorPort;
    }

    public void setEditorPort(Integer port) {
        if (port != null)
            myEditorPort = port;
    }

    public void setSourcetrailPort(Integer port) {
        if (port != null)
            mySourcetrailPort = port;
    }
}
