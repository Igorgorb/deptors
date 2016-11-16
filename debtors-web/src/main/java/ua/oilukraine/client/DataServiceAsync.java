/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.oilukraine.shared.Firm;
import java.util.List;

/**
 *
 * @author u_gorbonos
 */
public interface DataServiceAsync {

    public void getFirms(String s, AsyncCallback<List<Firm> > callback);
}
