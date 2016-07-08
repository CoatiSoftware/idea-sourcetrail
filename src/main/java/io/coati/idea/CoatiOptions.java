package io.coati.idea;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
        name = "CoatiOptions",
        storages = {
                @Storage("coati.xml")
        }
)
public class CoatiOptions implements PersistentStateComponent<CoatiOptions>{

    private Integer myCoatiPort = 6667;
    private Integer myEditorPort = 6666;
    private String myIp = "localhost";

    public static CoatiOptions getInstance() {
        return ServiceManager.getService(CoatiOptions.class);
    }

    @Nullable
    public CoatiOptions getState() {
        return this;
    }

    public void loadState(CoatiOptions state)
    {
        XmlSerializerUtil.copyBean(state, this);
    }

    public Integer getCoatiPort()
    {
        return myCoatiPort;
    }

    public String getIp()
    {
        return myIp;
    }

    public Integer getEditorPort()
    {
        return myEditorPort;
    }

    public void setEditorPort(Integer port)
    {
        if (port != null)
            myEditorPort = port;
    }

    public void setIp(String ip)
    {
        if (ip != null)
            myIp = ip;
    }

    public void setCoatiPort(Integer port)
    {
        if (port != null)
            myCoatiPort = port;
    }
}
