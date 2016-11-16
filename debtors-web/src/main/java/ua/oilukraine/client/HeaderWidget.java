/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import java.util.Date;

/**
 *
 * @author u_gorbonos
 */
public class HeaderWidget extends Composite {

    public HeaderWidget() {
        // initWidget(this);

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
        ListBox lBox = new ListBox();
        for (Integer i = 1; i < 11; ++i) {
            lBox.addItem(i.toString());
        }

        HorizontalPanel hPanel = new HorizontalPanel();

        hPanel.setSpacing(10);
        hPanel.add(selectLabel);
        hPanel.add(lBox);
        //hPanel.add(datePicker);
        hPanel.add(selectLabel2);
        hPanel.add(getTimeLable(DateTimeFormat.getFormat("dd.MM.yyyy").format(new Date())));
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
        datePicker.setVisibleYearCount(51);
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

            public void onValueChange(ValueChangeEvent<Date> event) {
                // TODO Auto-generated method stub

                Date date = event.getValue();
                timebx.setText(DateTimeFormat.getFormat("dd.MM.yyyy").format(date));
                popupPanel.hide();
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

}
