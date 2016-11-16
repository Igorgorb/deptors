/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author igor
 */
public class NewUiBinder extends Composite {

    @UiField
    ListBox listBox;

    private static NewUiBinderUiBinder uiBinder = GWT.create(NewUiBinderUiBinder.class);

    interface NewUiBinderUiBinder extends UiBinder<Widget, NewUiBinder> {
    }

    public NewUiBinder(String... names) {
        initWidget(uiBinder.createAndBindUi(this));

        // sets listBox
        for (String name : names) {
            listBox.addItem(name);
        }
    }
}
