package org.dwcj.bbjplugins.gridexwidget.sinks;

import com.basis.bbj.proxies.sysgui.BBjControl;
import org.dwcj.Environment;
import org.dwcj.bbjplugins.gridexwidget.GridExWidget;
import org.dwcj.bbjplugins.gridexwidget.events.GridExWidgetSelectEvent;
import org.dwcj.bridge.ComponentAccessor;


import java.util.function.Consumer;

public final class GridExWidgetDoubleClickEventSink {

    private final Consumer<GridExWidgetSelectEvent> target;
    private final GridExWidget grid;

    @SuppressWarnings({"static-access"})
    public GridExWidgetDoubleClickEventSink(GridExWidget grid, Consumer<GridExWidgetSelectEvent> target) {
        this.target = target;
        this.grid = grid;

        BBjControl bbjctrl = null;
        try {
            bbjctrl= ComponentAccessor.getDefault().getBBjControl(grid);
            bbjctrl.setCallback(Environment.getCurrent().getBBjAPI().ON_GRID_DOUBLE_CLICK, Environment.getCurrent().getDwcjHelper().getEventProxy(this, "onEvent", "::BBjGridExWidgetEventProxies.bbj::BBjGridExWidgetDoubleClickEventProxy"), "onEvent");
        } catch (Exception e) {
            Environment.logError(e);
        }
    }

    public void onEvent(String eventString) {
        GridExWidgetSelectEvent dwcEv = new GridExWidgetSelectEvent(grid, eventString);
        target.accept(dwcEv);
    }

}
