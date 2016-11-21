/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.ListBox;
import java.util.ArrayList;
import java.util.List;
import ua.oilukraine.shared.Firm;

/**
 *
 * @author u_gorbonos
 */
public class ListBoxExt extends ListBox implements TakesValue<String> {

    private List<Firm> listItems = new ArrayList<Firm>();

    @Override
    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void setValue(String value) {
        if (listItems.size() > 0) {
            int valueIndex = 0;
            if (listItems.contains(value)) {
                valueIndex = listItems.indexOf(value);
                // this.value = value;
            }
            setItemSelected(valueIndex, true);
        }
    }

    @Override
    public String getValue() {
        int selectedIndex = super.getSelectedIndex();
        String value = null;
        if (selectedIndex >= 0) {
            value = super.getValue(selectedIndex);
            if ("null".equals(value)) {
                value = null;
            }
        }

        return value;
    }

    public void setOptions(List<Firm> options) {
        listItems.clear();
        listItems.addAll(options);
        for (Firm item : listItems) {
//            addItem(item.getName() + "(" + item.getOkpo() + ")", Long.valueOf(item.getCode()).toString());
            addItem(item.getName() + "(" + item.getOkpo() + ")", item.getOkpo());
        }
    }

}
