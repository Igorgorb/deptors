/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ua.oilukraine.shared.Firm;
import java.util.List;

/**
 *
 * @author u_gorbonos
 */
public class ListFirms extends Composite {

    @UiField
    ListBoxExt listFirms;

    private static ListFirmsUiBinder uiBinder = GWT.create(ListFirmsUiBinder.class);

    interface ListFirmsUiBinder extends UiBinder<Widget, ListFirms> {
    }

    public ListFirms() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setList(List<Firm> list) {
        this.listFirms.setOptions(list);
        this.listFirms.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                onChangeBodyList();
            }
        });
    }

    public void LoadData() {
        DataServiceAsync ds = GWT.create(DataService.class);
        // (2) Create an asynchronous callback to handle the result.
        AsyncCallback acb = new AsyncCallback<List<Firm>>() {
            @Override
            public void onFailure(Throwable error) {
            }

            @Override
            public void onSuccess(List<Firm> result) {
                //logger.log(Level.SEVERE, "onSuccess ++++++++++++  ");
                if (null != result) {
                    setList(result);
                }
            }
        };
        // (3) Make the call. Control flow will continue immediately and later
        // 'callback' will be invoked when the RPC completes.
        ds.getFirms("dfh", acb);
    }

    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return this.listFirms.addChangeHandler(handler);
    }

    public String onChangeBodyList() {
        return listFirms.getValue(listFirms.getSelectedIndex());
    }
}
