/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import ua.oilukraine.shared.DebtorsData;

/**
 *
 * @author u_gorbonos
 */
public class TabelView implements EntryPoint { // extends Composite {

    /**
     * A simple data type that represents a contact.
     */
    private static class Contact {

        private final String address;
        private final Date birthday;
        private final String name;

        public Contact(String name, Date birthday, String address) {
            this.name = name;
            this.birthday = birthday;
            this.address = address;
        }
    }
    /**
     * The list of data to display.
     */
    private static final List<Contact> CONTACTS = Arrays.asList(
            new Contact("John", new Date(80, 4, 12), "123 Fourth Avenue"),
            new Contact("Joe", new Date(85, 2, 22), "22 Lance Ln"),
            new Contact("George", new Date(46, 6, 6), "1600 Pennsylvania Avenue"));

    private static TabelViewUiBinder uiBinder = GWT.create(TabelViewUiBinder.class);

    @UiField(provided = true)
    CellTable<DebtorsData> cellTable;

    interface TabelViewUiBinder extends UiBinder<Widget, TabelView> {
    }

    public TabelView() {
        //   initWidget(uiBinder.createAndBindUi(this));
    }

    void BuildColumns() {
        cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

    }

    public void onModuleLoad() {
        // Create a CellTable.
        CellTable<Contact> table = new CellTable<Contact>();
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        // Add a text column to show the name.
        TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact object) {
                return object.name;
            }
        };
        table.addColumn(nameColumn, "Name");
        // Add a date column to show the birthday.
        DateCell dateCell = new DateCell();
        Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateCell) {
            @Override
            public Date getValue(Contact object) {
                return object.birthday;
            }
        };
        table.addColumn(dateColumn, "Birthday");
        // Add a text column to show the address.
        TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact object) {
                return object.address;
            }
        };
        table.addColumn(addressColumn, "Address");
        // Add a selection model to handle user selection.
        final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
        table.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                Contact selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    Window.alert("You selected: " + selected.name);
                }
            }
        });
    // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        table.setRowCount(CONTACTS.size(), true);
        // Push the data into the widget.
        table.setRowData(0, CONTACTS);
    // Add it to the root panel.
        //     RootPanel.get().add(table);
    }
}
