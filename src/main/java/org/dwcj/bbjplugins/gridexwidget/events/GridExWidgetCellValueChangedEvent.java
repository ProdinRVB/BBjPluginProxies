package org.dwcj.bbjplugins.gridexwidget.events;

import com.basiscomponents.db.ResultSet;
import org.dwcj.bbjplugins.gridexwidget.GridExWidget;
import org.dwcj.component.AbstractDwcComponent;
import org.dwcj.component.ComponentEvent;

public final class GridExWidgetCellValueChangedEvent implements ComponentEvent {

    private final GridExWidget control;

    public GridExWidgetCellValueChangedEvent(GridExWidget theGrid, String eventString) {

        ResultSet selectionTmp = null;
        this.control = theGrid;
        /*
        try {
            selectionTmp = ResultSet.fromJson(eventString);
        } catch (IOException|ParseException e) {
            Environment.logError(e);
        }
        selection = selectionTmp;

         */
    }

    public GridExWidgetCellValueChangedEvent(GridExWidget theGrid, ResultSet selection) {
        this.control = theGrid;
    }

    @Override
    public AbstractDwcComponent getControl() {
        return control;
    }

}
