package org.dwcj.bbjplugins.gridexwidget.sinks;

import com.basis.bbj.proxies.sysgui.BBjControl;
import org.dwcj.Environment;
import org.dwcj.bbjplugins.gridexwidget.GridExWidget;
import org.dwcj.bbjplugins.gridexwidget.events.GridExWidgetCellValueChangedEvent;
import org.dwcj.bridge.ControlAccessor;

import java.util.function.Consumer;

public final class GridExWidgetCellValueChangedEventSink {

    private final Consumer<GridExWidgetCellValueChangedEvent> target;
    private final GridExWidget grid;

    @SuppressWarnings({"static-access"})
    public GridExWidgetCellValueChangedEventSink(GridExWidget grid, Consumer<GridExWidgetCellValueChangedEvent> target) {
        this.target = target;
        this.grid = grid;

        BBjControl bbjctrl = null;
        try {
            bbjctrl=ControlAccessor.getDefault().getBBjControl(grid);
            bbjctrl.setCallback(Environment.getInstance().getBBjAPI().ON_GRID_CELL_MODIFY, Environment.getInstance().getDwcjHelper().getEventProxy(this, "onEvent", "::BBjGridExWidgetEventProxies.bbj::BBjGridExWidgetCellValueChangedEventProxy"), "onEvent");
        } catch (Exception e) {
            Environment.logError(e);
        }
    }

    public void onEvent(String eventString) {
        GridExWidgetCellValueChangedEvent dwcEv = new GridExWidgetCellValueChangedEvent(grid, eventString);
        target.accept(dwcEv);
    }

}
