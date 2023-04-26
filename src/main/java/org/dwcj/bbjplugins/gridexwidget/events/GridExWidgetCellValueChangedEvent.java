package org.dwcj.bbjplugins.gridexwidget.events;

import com.basiscomponents.db.ResultSet;
import com.basiscomponents.db.DataRow;
import org.dwcj.bbjplugins.gridexwidget.GridExWidget;
import org.dwcj.component.AbstractDwcComponent;
import org.dwcj.component.ComponentEvent;

public final class GridExWidgetCellValueChangedEvent implements ComponentEvent {

    private final GridExWidget control;
    private DataRow clientRow;

    public GridExWidgetCellValueChangedEvent(GridExWidget theGrid, String eventString) {
        this.control = theGrid;

        try {
            clientRow = DataRow.fromJson(eventString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AbstractDwcComponent getControl() {
        return control;
    }

    public DataRow getClientRow() {
        return clientRow;
    }

}
