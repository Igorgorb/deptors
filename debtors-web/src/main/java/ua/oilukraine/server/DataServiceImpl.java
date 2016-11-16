/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

import ua.oilukraine.client.DataService;
import ua.oilukraine.shared.Firm;

/**
 *
 * @author u_gorbonos
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService {

    @Override
    public List<Firm> getFirms(String s) {
        // Do something interesting with 's' here on the server.
        List<Firm> list = null;
        DB db = new DB();
        try {
            list = db.getFirmsTable();
        } catch (SQLException ex) {
            Logger.getLogger(DataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            list = null;
        }
        return list;
    }
}
