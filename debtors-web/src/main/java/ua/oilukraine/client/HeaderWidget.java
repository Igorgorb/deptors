/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author u_gorbonos
 */
public class HeaderWidget extends Composite {

    private Vector<HeaderWidgetEventHandler> listeners;
    ListFirms listFirms;
    TextBox txtDateInfo;

    public HeaderWidget() {
        // initWidget(this);
        listeners = new Vector();

    }

    public FlowPanel Load() {
        // Create a basic date picker
//        DatePicker datePicker = new DatePicker();
//        final Label text = new Label();
//
//        // Set the value in the text box when the user selects a date
//        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
//            @Override
//            public void onValueChange(ValueChangeEvent<Date> event) {
//                Date date = event.getValue();
//                String dateString
//                        = DateTimeFormat.getFormat("dd.MM.yyyy").format(date);
//                text.setText(dateString);
//            }
//        });
//        // Set the default value
//        datePicker.setValue(new Date(), true);
//
//        // Create a DateBox
//        DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
//        DateBox dateBox = new DateBox();
//        dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));

        Label selectLabel = new Label("Фирмы:");
        Label selectLabel2 = new Label("Дата:");
        // Add widgets to the root panel.
        FlowPanel fPanel = new FlowPanel();
//        ListBox lBox = new ListBox();
//        for (Integer i = 1; i < 11; ++i) {
//            lBox.addItem(i.toString());
//        }

        listFirms = new ListFirms();
        listFirms.LoadData();

        listFirms.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                //Window.alert(listFirms.onChangeBodyList());
                fireChangeHeaderWidget();
            }
        });

        HorizontalPanel hPanel = new HorizontalPanel();

        hPanel.setSpacing(10);
        hPanel.add(selectLabel);
        hPanel.add(listFirms);
//        hPanel.add(lBox);
        //hPanel.add(datePicker);
        hPanel.add(selectLabel2);
        txtDateInfo = getTimeLable(DateTimeFormat.getFormat("dd.MM.yyyy").format(new Date()));
        hPanel.add(txtDateInfo);
        // initWidget(fPanel);
        fPanel.add(hPanel);
        return fPanel;
    }

    public TextBox getTimeLable(String datevalue) {
        // TODO Auto-generated method stub
        // System.out.println("date" + datevalue);
        final TextBox timebx = new TextBox();
        timebx.setReadOnly(true);
        timebx.setText(datevalue);
        final PopupPanel popupPanel = new PopupPanel(true);
        DatePicker datePicker = new DatePicker();
        datePicker.setYearArrowsVisible(true);
        datePicker.setYearAndMonthDropdownVisible(true);
        // show 51 years in the years dropdown. The range of years is centered on the selected date
        datePicker.setVisibleYearCount(21);
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

            public void onValueChange(ValueChangeEvent<Date> event) {
                // TODO Auto-generated method stub

                Date date = event.getValue();
                timebx.setText(DateTimeFormat.getFormat("dd.MM.yyyy").format(date));
                popupPanel.hide();
                fireChangeHeaderWidget();
            }
        });
        popupPanel.setWidget(datePicker);
        timebx.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                String strDate = timebx.getText();
                DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
                try {
                    Date selDate = (Date) format.parse(strDate);
                    datePicker.setValue(selDate, true);
                } catch (Exception pe) {
                    // setting current date
                    datePicker.setValue(new Date(), true);
                }

                int x = timebx.getAbsoluteLeft();
                int y = timebx.getAbsoluteTop();
                popupPanel.setPopupPosition(x, y + 20);
                popupPanel.show();
            }
        });

        return timebx;
    }

    /**
     * Register a listener for SunEvents
     */
    synchronized public void addHeaderWidgetHandler(HeaderWidgetEventHandler l) {
        if (listeners == null) {
            listeners = new Vector();
        }
        listeners.addElement(l);
    }

    /**
     * Remove a listener for SunEvents
     */
    synchronized public void removeHeaderWidgetHandler(HeaderWidgetEventHandler l) {
        if (listeners == null) {
            listeners = new Vector();
        }
        listeners.removeElement(l);
    }

    /**
     * Fire a SunEvent to all registered listeners long code, Date date
     */
    protected void fireChangeHeaderWidget() {
        // if we have no listeners, do nothing...
        if (listeners != null && !listeners.isEmpty()) {
            String strDate = txtDateInfo.getText();
            Date selDate;
            DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
            try {
                selDate = (Date) format.parse(strDate);
            } catch (Exception pe) {
                // setting current date
                selDate = new Date();
            }
            // create the event object to send
//            HeaderWidgetEvent event
//                    = new HeaderWidgetEvent(Long.valueOf(listFirms.onChangeBodyList()), selDate);
            HeaderWidgetEvent event
                    = new HeaderWidgetEvent(listFirms.onChangeBodyList(), selDate);

            // make a copy of the listener list in case
            //   anyone adds/removes listeners
            Vector targets;
            synchronized (this) {
                targets = (Vector) listeners.clone();
            }

            // walk through the listener list and
            //   call the sunMoved method in each
            Enumeration<HeaderWidgetEventHandler> e = targets.elements();
            while (e.hasMoreElements()) {
                HeaderWidgetEventHandler l = (HeaderWidgetEventHandler) e.nextElement();
                l.onChange(event);
            }
        }
    }

}
