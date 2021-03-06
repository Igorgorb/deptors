/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import ua.oilukraine.shared.Firm;
import java.util.List;
import ua.oilukraine.shared.DebtorsData;

/**
 *
 * @author u_gorbonos
 */
public interface DataServiceAsync {

    public void getFirms(String s, AsyncCallback<List<Firm> > callback);
    
    public void getDebtorsData(String okpo, Date date_info, AsyncCallback<List<DebtorsData> > callback);
}
