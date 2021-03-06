/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;
import ua.oilukraine.shared.Firm;
import java.util.List;
import ua.oilukraine.shared.DebtorsData;

/**
 *
 * @author u_gorbonos
 */
@RemoteServiceRelativePath("dataservice")
public interface DataService extends RemoteService {

    public List<Firm> getFirms(String s);

    public List<DebtorsData> getDebtorsData(String okpo, Date date_info);
}
