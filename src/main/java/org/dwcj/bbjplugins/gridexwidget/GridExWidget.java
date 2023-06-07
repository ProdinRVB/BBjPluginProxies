package org.dwcj.bbjplugins.gridexwidget;

import com.basis.bbj.proxies.sysgui.BBjChildWindow;
import com.basis.bbj.proxies.sysgui.BBjWindow;
import com.basis.startup.type.BBjVector;
import com.basiscomponents.db.DataRow;
import com.basiscomponents.db.ResultSet;
import org.dwcj.App;
import org.dwcj.Environment;
import org.dwcj.bbjplugins.gridexwidget.events.GridExWidgetSelectEvent;
import org.dwcj.bbjplugins.gridexwidget.events.GridExWidgetCellValueChangedEvent;
import org.dwcj.bbjplugins.gridexwidget.sinks.GridExWidgetDoubleClickEventSink;
import org.dwcj.bbjplugins.gridexwidget.sinks.GridExWidgetSelectEventSink;
import org.dwcj.bbjplugins.gridexwidget.sinks.GridExWidgetCellValueChangedEventSink;
import org.dwcj.bridge.WindowAccessor;
import org.dwcj.component.AbstractDwcComponent;
import org.dwcj.component.window.AbstractWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("java:S3740")
public final class GridExWidget extends AbstractDwcComponent {

    @Override
    protected void create(AbstractWindow p) {

        byte[] flags = new byte[]{(byte) 0x00, (byte) 0x10, (byte) 0x88, (byte) 0x00};

        try {
            BBjWindow w = WindowAccessor.getDefault().getBBjWindow(p);
            //todo: honor visibility flag, if set before adding the control to the form, so it's created invisibly right away
            BBjChildWindow cw = w.addChildWindow(w.getAvailableControlID(), BASISNUMBER_1, BASISNUMBER_1, BASISNUMBER_1, BASISNUMBER_1, "", flags, Environment.getInstance().getSysGui().getAvailableContext());
            super.control = Environment.getInstance().getDwcjHelper().createWidget("::BBjGridExWidget/BBjGridExWidget.bbj::BBjGridExWidget", cw);
            catchUp();
        } catch (Exception e) {
            Environment.logError(e);
            App.consoleLog(e.getMessage());
        }
    }

    /**
     * @param rs - a com.basiscomponents.db.ResultSet holding the data to display in the grid
     * @return the widget itself
     */
    public GridExWidget setData(ResultSet rs) {
        ArrayList args = new ArrayList();
        args.add(rs);
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setData", args);
        return this;
    }

    public GridExWidget setData(ResultSet rs, int render, Boolean addAll) {
        ArrayList args = new ArrayList();
        args.add(rs);
        args.add(render);
        args.add(addAll);

        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setData", args);

        return this;
    }

    public GridExWidget updateData(ResultSet rs) {
        ArrayList args = new ArrayList();
        args.add(rs);
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "updateData", args);
        return this;
    }

    public GridExWidget clearData() {
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "clearData", null);
        return this;
    }

    public ResultSet getRows() {
        ResultSet rs = new ResultSet();

        Object object = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getRows", null);

        if (object != null) {
            BBjVector rows = (BBjVector) object;
            
            if (rows != null && rows.size() > 0) {
                for (int i = 0; i < rows.size(); i++) {
                    Object row = rows.get(i);
    
                    Object rowAsDataRow = Environment.getInstance().getDwcjHelper().invokeMethod(row, "asDataRow", null);
                    if (rowAsDataRow != null) {
                        DataRow dr = (DataRow) rowAsDataRow;
                        rs.addItem(dr);
                    }
                }
            }
        }

        return rs;
    }

    public ResultSet getSelectedRows() {
        ResultSet rs = new ResultSet();

        Object object = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getSelectedRows", null);

        if (object != null) {
            BBjVector rows = (BBjVector) object;

            if (rows != null && rows.size() > 0) {
                for (int i = 0; i < rows.size(); i++) {
                    Object row = rows.get(i);
    
                    Object rowAsDataRow = Environment.getInstance().getDwcjHelper().invokeMethod(row, "asDataRow", null);
                    if (rowAsDataRow != null) {
                        DataRow dr = (DataRow) rowAsDataRow;
                        rs.addItem(dr);
                    }
                }
            }
        }

        return rs;
    }

    public DataRow getSelectedRow() {
        DataRow dr = null;

        ResultSet rs = getSelectedRows();
        if (rs != null && rs.size() > 0) dr = rs.get(0);

        return dr;
    }

    public void setSelectedRow(DataRow row) {
        ArrayList args = new ArrayList();
        args.add(row.getRowKey());

        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setSelectedRow", args);
    }

    public void deselectAll() {
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "deselectAll", null);
    }

    public Object addColumn(String field, String label, int type) {
        ArrayList args = new ArrayList();
        args.add(field);
        args.add(label);
        args.add(type);

        return Environment.getInstance().getDwcjHelper().invokeMethod(control, "addColumn", args);
    }

    public Object addColumnGroup(String id, String label, List<String> columns, Boolean marryChildren, String cssClass) {
        BBjVector vector = new BBjVector();
        for (String column : columns) {
            vector.addItem(column);
        }

        ArrayList args = new ArrayList();
        args.add(id);
        args.add(label);
        args.add(vector);
        args.add(marryChildren);
        args.add(cssClass);

        return Environment.getInstance().getDwcjHelper().invokeMethod(control, "addColumnGroup", args);
    }

    public Object addRow(DataRow row) {
        ArrayList args = new ArrayList();
        args.add(row);

        return Environment.getInstance().getDwcjHelper().invokeMethod(control, "addRow", args);
    }

    public Object updateRow(DataRow row) {
        ArrayList args = new ArrayList();
        args.add(row);

        return Environment.getInstance().getDwcjHelper().invokeMethod(control, "updateRow", args);
    }

    public Object removeRow(DataRow row) {
        ArrayList args = new ArrayList();
        args.add(row);

        return Environment.getInstance().getDwcjHelper().invokeMethod(control, "removeRow", args);
    }

    public void setFitToGrid() {
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setFitToGrid", null);
    }

    public void autoSizeColumns() {
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "autoSizeColumns", null);
    }

    public void setColumnAlignment(String field, int alignment) {
        ArrayList args = new ArrayList();
        args.add(field);
        args.add(alignment);

        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setColumnAlignment", args);
    }

    public void setColumnMask(String field, String mask) {
        ArrayList args = new ArrayList();
        args.add(field);
        args.add(mask);

        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setColumnMask", args);
    }

    public void setState(String state) {
        ArrayList args = new ArrayList();
        args.add(state);

        Object gxState = Environment.getInstance().getDwcjHelper().createInstance("::BBjGridExWidget/GxState.bbj::GxState");
        Environment.getInstance().getDwcjHelper().invokeMethod(gxState, "setString", args);

        args = new ArrayList();
        args.add(gxState);
        Environment.getInstance().getDwcjHelper().invokeMethod(control, "setState", args);
    }

    public String getState() {
        Object gxState = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getState", null);
        
        if (gxState != null) {
            Object state = Environment.getInstance().getDwcjHelper().invokeMethod(gxState, "getString", null);
            if (state != null) return state.toString();
        }

        return null;
    }

    public void setMultipleSelection(Boolean multipleSelection) {
        ArrayList args = new ArrayList();
        args.add(multipleSelection);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setMultipleSelection", args);
    }

    public void setShowSelectionCheckbox(Boolean showSelectionCheckbox) {
        ArrayList args = new ArrayList();
        args.add(showSelectionCheckbox);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setShowSelectionCheckbox", args);
    }

    public void setShowHeaderSelectionCheckbox(Boolean showHeaderSelectionCheckbox) {
        ArrayList args = new ArrayList();
        args.add(showHeaderSelectionCheckbox);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setShowHeaderSelectionCheckbox", args);
    }

    public void setGridEditable(Boolean editable) {
        ArrayList args = new ArrayList();
        args.add(editable);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setEditable", args);
    }

    public void setColumnEditable(String column, Boolean editable) {
        ArrayList args = new ArrayList();
        args.add(column);

        Object gxColumn = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getColumn", args);
        if (gxColumn != null) {
            args = new ArrayList();
            args.add(editable);
            
            Environment.getInstance().getDwcjHelper().invokeMethod(gxColumn, "setEditable", args);
        }
    }

    public void setEditType(int editType) {
        ArrayList args = new ArrayList();
        args.add(editType);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setEditType", args);
    }
    
    public void setEnableGridFilter(Boolean enableFilter) {
        ArrayList args = new ArrayList();
        args.add(enableFilter);

        Object gxOptions = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getOptions", null);
        if (gxOptions != null) Environment.getInstance().getDwcjHelper().invokeMethod(gxOptions, "setEnableFilter", args);
    }

    public void setEnableColumnFilter(String column, Boolean enableFilter) {
        ArrayList args = new ArrayList();
        args.add(column);

        Object gxColumn = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getColumn", args);
        if (gxColumn != null) {
            args = new ArrayList();
            args.add(enableFilter);

            Environment.getInstance().getDwcjHelper().invokeMethod(gxColumn, "setEnableFilter", args);
        }
    }

    public void addStatusBarTotalRowCountComponent() {
        Object gxStatusBar = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getStatusbar", null);
        Object gxStatusBarTotalRowCountComponent = Environment.getInstance().getDwcjHelper().createInstance("::BBjGridExWidget/GxStatusBar.bbj::GxStatusBarTotalRowCountComponent");

        ArrayList args = new ArrayList();
        args.add(gxStatusBarTotalRowCountComponent);

        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBar, "addComponent", args);
    }

    public void addStatusBarFilteredRowCountComponent() {
        Object gxStatusBar = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getStatusbar", null);
        Object gxStatusBarFilteredRowCountComponent = Environment.getInstance().getDwcjHelper().createInstance("::BBjGridExWidget/GxStatusBar.bbj::GxStatusBarFilteredRowCountComponent");

        ArrayList args = new ArrayList();
        args.add(gxStatusBarFilteredRowCountComponent);

        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBar, "addComponent", args);
    }

    public void addStatusBarTotalAndFilteredRowCountComponent(String alignment) {
        Object gxStatusBar = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getStatusbar", null);
        Object gxStatusBarTotalAndFilteredRowCountComponent = Environment.getInstance().getDwcjHelper().createInstance("::BBjGridExWidget/GxStatusBar.bbj::GxStatusBarTotalAndFilteredRowCountComponent");
        
        ArrayList args = new ArrayList();
        args.add(alignment);
        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBarTotalAndFilteredRowCountComponent, "setAlignment", args);

        args = new ArrayList();
        args.add(gxStatusBarTotalAndFilteredRowCountComponent);

        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBar, "addComponent", args);
    }

    public void addStatusBarSelectedRowCountComponent(String alignment) {
        Object gxStatusBar = Environment.getInstance().getDwcjHelper().invokeMethod(control, "getStatusbar", null);
        Object gxStatusBarSelectedRowCountComponent = Environment.getInstance().getDwcjHelper().createInstance("::BBjGridExWidget/GxStatusBar.bbj::GxStatusBarSelectedRowCountComponent");
        
        ArrayList args = new ArrayList();
        args.add(alignment);
        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBarSelectedRowCountComponent, "setAlignment", args);

        args = new ArrayList();
        args.add(gxStatusBarSelectedRowCountComponent);

        Environment.getInstance().getDwcjHelper().invokeMethod(gxStatusBar, "addComponent", args);
    }

    /**
     * Register an event callback to be executed when the user selects a row in the grid
     *
     * @param callback - the consumer method that will be invoked
     * @return - the widget itself
     */
    public GridExWidget onSelect(Consumer<GridExWidgetSelectEvent> callback) {
        new GridExWidgetSelectEventSink(this, callback);
        return this;
    }

    /**
     * Register an event callback to be executed when the user doubleclicks a cell in the grid
     *
     * @param callback - the consumer method that will be invoked
     * @return - the widget itself
     */
    public GridExWidget onDoubleClick(Consumer<GridExWidgetSelectEvent> callback) {
        new GridExWidgetDoubleClickEventSink(this, callback);
        return this;
    }

    public GridExWidget onCellValueChanged(Consumer<GridExWidgetCellValueChangedEvent> callback) {
        new GridExWidgetCellValueChangedEventSink(this, callback);
        return this;
    }

    @Override
    public GridExWidget setText(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public GridExWidget setVisible(Boolean visible){
        super.setVisible(visible);
        return this;
    }

    @Override
    public GridExWidget setTooltipText(String text) {
        super.setTooltipText(text);
        return this;
    }

    @Override
    public GridExWidget setAttribute(String attribute, String value){
        super.setAttribute(attribute, value);
        return this;
    }

    @Override
    public GridExWidget setStyle(String property, String value) {
        super.setStyle(property, value);
        return this;
    }
    
    @Override
    public GridExWidget addClassName(String selector) {
        super.addClassName(selector);
        return this;
    }

    @Override
    public GridExWidget removeClassName(String selector) {
        super.removeClassName(selector);
        return this;
    }


}
