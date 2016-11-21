/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import ua.oilukraine.shared.DebtorsData;

/**
 *
 * @author u_gorbonos
 */
//public class TabelView implements EntryPoint { // extends Composite {
public class TabelView extends Composite implements HeaderWidgetEventHandler{

    /**
     * A simple data type that represents a contact.
     */
//    private static class Contact {
//
//        private final String address;
//        private final Date birthday;
//        private final String name;
//
//        public Contact(String name, Date birthday, String address) {
//            this.name = name;
//            this.birthday = birthday;
//            this.address = address;
//        }
//    }
//    /**
//     * The list of data to display.
//     */
//    private static final List<Contact> CONTACTS = Arrays.asList(
//            new Contact("John", new Date(80, 4, 12), "123 Fourth Avenue"),
//            new Contact("Joe", new Date(85, 2, 22), "22 Lance Ln"),
//            new Contact("George", new Date(46, 6, 6), "1600 Pennsylvania Avenue"));
    private static TabelViewUiBinder uiBinder = GWT.create(TabelViewUiBinder.class);

    @UiField(provided = true)
    CellTable<DebtorsData> cellTable;
//    CellTable<Contact> cellTable;

    List<DebtorsData> listDebtorsData;

    @Override
    public void onChange(HeaderWidgetEvent event) {
        LoadData(event.getOkpo(), event.getDate_info());
    }

    interface TabelViewUiBinder extends UiBinder<Widget, TabelView> {
    }

    public TabelView() {
           initWidget(uiBinder.createAndBindUi(this));
    }

//    public void onModuleLoad() {
//
//    }

    public void setList(List<DebtorsData> list) {
        this.listDebtorsData = list;
        // Create a CellTable.
        //CellTable<DebtorsData> table = new CellTable<DebtorsData>();
        //CellTable<DebtorsData> table = new CellTable<DebtorsData>();
        // cellTable.
        cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        cellTable.setWidth("100%", true);

        // Do not refresh the headers and footers every time the data is updated.
        cellTable.setAutoHeaderRefreshDisabled(true);
        cellTable.setAutoFooterRefreshDisabled(true);

        // Attach a column sort handler to the ListDataProvider to sort the list.
        ListHandler<DebtorsData> sortHandler = new ColumnSortEvent.ListHandler<DebtorsData>(
                this.listDebtorsData);
        cellTable.addColumnSortHandler(sortHandler);

//        // Create a Pager to control the table.
//        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
//        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
//        pager.setDisplay(cellTable);
        // Add a selection model so we can select cells.
        final SelectionModel<DebtorsData> selectionModel = new MultiSelectionModel<DebtorsData>(
                DebtorsData.KEY_PROVIDER);
        cellTable.setSelectionModel(selectionModel,
                DefaultSelectionEventManager.<DebtorsData>createCheckboxManager());

        // Initialize the columns.
        initTableColumns(selectionModel, sortHandler);

    }

    public void LoadData(String okpo, Date date_info) {
        DataServiceAsync ds = GWT.create(DataService.class);
        // (2) Create an asynchronous callback to handle the result.
        AsyncCallback acb = new AsyncCallback<List<DebtorsData>>() {
            @Override
            public void onFailure(Throwable error) {
            }

            @Override
            public void onSuccess(List<DebtorsData> result) {
                //logger.log(Level.SEVERE, "onSuccess ++++++++++++  ");
                if (null != result) {
                    setList(result);
                }
            }
        };
        // (3) Make the call. Control flow will continue immediately and later
        // 'callback' will be invoked when the RPC completes.
        ds.getDebtorsData(okpo, date_info, acb);
    }

    /**
     * Add the columns to the table.
     */
    private void initTableColumns(
            final SelectionModel<DebtorsData> selectionModel,
            ColumnSortEvent.ListHandler<DebtorsData> sortHandler) {
        // Checkbox column. This table will uses a checkbox column for selection.
        // Alternatively, you can call cellTable.setSelectionEnabled(true) to enable
        // mouse selection.
        Column<DebtorsData, Boolean> checkColumn = new Column<DebtorsData, Boolean>(
                new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(DebtorsData object) {
                // Get the value from the selection model.
                return selectionModel.isSelected(object);
            }
        };
        cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        cellTable.setColumnWidth(checkColumn, 40, Unit.PX);

        // Firm name.
        Column<DebtorsData, String> FirmNameColumn = new Column<DebtorsData, String>(
                new TextCell()) {
            @Override
            public String getValue(DebtorsData object) {
                return object.getName();
            }
        };
        FirmNameColumn.setSortable(true);
        sortHandler.setComparator(FirmNameColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        cellTable.addColumn(FirmNameColumn, "Фирма");
//        FirmNameColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setName(value);
//                //    ContactDatabase.get().refreshDisplays();
//            }
//        });
        cellTable.setColumnWidth(FirmNameColumn, 20, Unit.PCT);

        // OKPO
        Column<DebtorsData, String> okpoColumn = new Column<DebtorsData, String>(
                new TextCell()) {
            @Override
            public String getValue(DebtorsData object) {
                return object.getOkpo();
            }
        };
        okpoColumn.setSortable(true);
        sortHandler.setComparator(okpoColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getOkpo().compareTo(o2.getOkpo());
            }
        });
        cellTable.addColumn(okpoColumn, "ОКПО");
//        okpoColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setOkpo(value);
//                //    ContactDatabase.get().refreshDisplays();
//            }
//        });
        cellTable.setColumnWidth(okpoColumn, 20, Unit.PCT);

        // Name Buh
        Column<DebtorsData, String> nameBuhColumn = new Column<DebtorsData, String>(
                new TextCell()) {
            @Override
            public String getValue(DebtorsData object) {
                return object.getName_buh();
            }
        };
        nameBuhColumn.setSortable(true);
        sortHandler.setComparator(nameBuhColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getName_buh().compareTo(o2.getName_buh());
            }
        });
        cellTable.addColumn(nameBuhColumn, "Бухгалтер");
//        okpoColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setOkpo(value);
//                //    ContactDatabase.get().refreshDisplays();
//            }
//        });
        cellTable.setColumnWidth(nameBuhColumn, 20, Unit.PCT);

        // Phone Work Buh
        Column<DebtorsData, String> phoneWorkBuhColumn = new Column<DebtorsData, String>(
                new TextCell()) {
            @Override
            public String getValue(DebtorsData object) {
                return object.getPhone_work();
            }
        };
        phoneWorkBuhColumn.setSortable(true);
        sortHandler.setComparator(phoneWorkBuhColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getPhone_work().compareTo(o2.getPhone_work());
            }
        });
        cellTable.addColumn(phoneWorkBuhColumn, "Рабочий телефон");
//        okpoColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setOkpo(value);
//                //    ContactDatabase.get().refreshDisplays();
//            }
//        });
        cellTable.setColumnWidth(phoneWorkBuhColumn, 20, Unit.PCT);

        // Phone Mob Buh
        Column<DebtorsData, String> phoneMobBuhColumn = new Column<DebtorsData, String>(
                new TextCell()) {
            @Override
            public String getValue(DebtorsData object) {
                return object.getPhone_mob();
            }
        };
        phoneMobBuhColumn.setSortable(true);
        sortHandler.setComparator(phoneMobBuhColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getPhone_mob().compareTo(o2.getPhone_mob());
            }
        });
        cellTable.addColumn(phoneMobBuhColumn, "Мобильный телефон");
//        okpoColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setOkpo(value);
//                //    ContactDatabase.get().refreshDisplays();
//            }
//        });
        cellTable.setColumnWidth(phoneMobBuhColumn, 20, Unit.PCT);

        // Date Info
        DateCell dateCell = new DateCell();
        Column<DebtorsData, Date> dateColumn = new Column<DebtorsData, Date>(dateCell) {
            @Override
            public Date getValue(DebtorsData object) {
                return object.getDate_info();
            }
        };
        dateColumn.setSortable(true);
        dateColumn.setDefaultSortAscending(false);
        sortHandler.setComparator(dateColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getDate_info().compareTo(o2.getDate_info());
            }
        });
        cellTable.addColumn(dateColumn, "Дата информации");
        dateColumn.setFieldUpdater(new FieldUpdater<DebtorsData, Date>() {
            @Override
            public void update(int index, DebtorsData object, Date value) {
                // Called when the user changes the value.
                object.setDate_info(value);
                //    ContactDatabase.get().refreshDisplays();
            }
        });

        // DatePickerCell.
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM);
        DatePickerCell datePicCell = new DatePickerCell(dateFormat);

        Column<DebtorsData, Date> datePicColumn = new Column<DebtorsData, Date>(datePicCell) {
            @Override
            public Date getValue(DebtorsData object) {
                return object.getDate_info();
            }
        };
        dateColumn.setSortable(true);
        dateColumn.setDefaultSortAscending(false);
        sortHandler.setComparator(dateColumn, new Comparator<DebtorsData>() {
            @Override
            public int compare(DebtorsData o1, DebtorsData o2) {
                return o1.getDate_info().compareTo(o2.getDate_info());
            }
        });
        cellTable.addColumn(dateColumn, "Дата информации Pic");

//        addColumn(new DatePickerCell(dateFormat), "DatePicker", new GetValue<Date>() {
//            @Override
//            public Date getValue(ContactInfo contact) {
//                return contact.getBirthday();
//            }
//        }, new FieldUpdater<ContactInfo, Date>() {
//            @Override
//            public void update(int index, ContactInfo object, Date value) {
//                pendingChanges.add(new BirthdayChange(object, value));
//            }
//        });
//        /**
//         * Add a column with a header.
//         *
//         * @param <C> the cell type
//         * @param cell the cell used to render the column
//         * @param headerText the header string
//         * @param getter the value getter for the cell
//         */
//    private <C> Column<ContactInfo, C> addColumn(Cell<C> cell, String headerText,
//            final GetValue<C> getter, FieldUpdater<ContactInfo, C> fieldUpdater) {
//        Column<ContactInfo, C> column = new Column<ContactInfo, C>(cell) {
//            @Override
//            public C getValue(ContactInfo object) {
//                return getter.getValue(object);
//            }
//        };
//        column.setFieldUpdater(fieldUpdater);
//        if (cell instanceof AbstractEditableCell<?, ?>) {
//            editableCells.add((AbstractEditableCell<?, ?>) cell);
//        }
//        contactList.addColumn(column, headerText);
//        return column;
//    }
///////////////////////////////////////////////////////////////////////
//        // Address.
//        Column<DebtorsData, String> addressColumn = new Column<DebtorsData, String>(
//                new TextCell()) {
//            @Override
//            public String getValue(DebtorsData object) {
//                return object.getAddress();
//            }
//        };
//        addressColumn.setSortable(true);
//        addressColumn.setDefaultSortAscending(false);
//        sortHandler.setComparator(addressColumn, new Comparator<DebtorsData>() {
//            @Override
//            public int compare(DebtorsData o1, DebtorsData o2) {
//                return o1.getAddress().compareTo(o2.getAddress());
//            }
//        });
//        cellTable.addColumn(addressColumn, constants.cwCellTableColumnAddress());
//        cellTable.setColumnWidth(addressColumn, 60, Unit.PCT);
//
//        ///////////////////////////////////////////////////////////////////////
//        // Last name.
//        Column<DebtorsData, String> lastNameColumn = new Column<DebtorsData, String>(
//                new EditTextCell()) {
//            @Override
//            public String getValue(DebtorsData object) {
//                return object.getLastName();
//            }
//        };
//        lastNameColumn.setSortable(true);
//        sortHandler.setComparator(lastNameColumn, new Comparator<DebtorsData>() {
//            @Override
//            public int compare(DebtorsData o1, DebtorsData o2) {
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });
//        cellTable.addColumn(lastNameColumn, constants.cwCellTableColumnLastName());
//        lastNameColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                // Called when the user changes the value.
//                object.setLastName(value);
//                ContactDatabase.get().refreshDisplays();
//            }
//        });
//        cellTable.setColumnWidth(lastNameColumn, 20, Unit.PCT);
//
//        // Category.
//        final Category[] categories = ContactDatabase.get().queryCategories();
//        List<String> categoryNames = new ArrayList<String>();
//        for (Category category : categories) {
//            categoryNames.add(category.getDisplayName());
//        }
//        SelectionCell categoryCell = new SelectionCell(categoryNames);
//        Column<DebtorsData, String> categoryColumn = new Column<DebtorsData, String>(
//                categoryCell) {
//            @Override
//            public String getValue(DebtorsData object) {
//                return object.getCategory().getDisplayName();
//            }
//        };
//        cellTable.addColumn(categoryColumn, constants.cwCellTableColumnCategory());
//        categoryColumn.setFieldUpdater(new FieldUpdater<DebtorsData, String>() {
//            @Override
//            public void update(int index, DebtorsData object, String value) {
//                for (Category category : categories) {
//                    if (category.getDisplayName().equals(value)) {
//                        object.setCategory(category);
//                    }
//                }
//                ContactDatabase.get().refreshDisplays();
//            }
//        });
//        cellTable.setColumnWidth(categoryColumn, 130, Unit.PX);
//
//        // Address.
//        Column<DebtorsData, String> addressColumn = new Column<DebtorsData, String>(
//                new TextCell()) {
//            @Override
//            public String getValue(DebtorsData object) {
//                return object.getAddress();
//            }
//        };
//        addressColumn.setSortable(true);
//        addressColumn.setDefaultSortAscending(false);
//        sortHandler.setComparator(addressColumn, new Comparator<DebtorsData>() {
//            @Override
//            public int compare(DebtorsData o1, DebtorsData o2) {
//                return o1.getAddress().compareTo(o2.getAddress());
//            }
//        });
//        cellTable.addColumn(addressColumn, constants.cwCellTableColumnAddress());
//        cellTable.setColumnWidth(addressColumn, 60, Unit.PCT);
    }

}
