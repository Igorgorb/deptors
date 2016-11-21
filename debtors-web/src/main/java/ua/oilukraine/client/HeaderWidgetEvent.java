/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.client;

import java.util.Date;

/**
 *
 * @author u_gorbonos
 */
public class HeaderWidgetEvent {

    long code;
    String okpo;
    Date date_info;

    public HeaderWidgetEvent(long code, Date date_info) {
        this.code = code;
        // this.okpo = okpo;
        this.date_info = date_info;
    }

    public HeaderWidgetEvent(String okpo, Date date_info) {
        //this.code = code;
        this.okpo = okpo;
        this.date_info = date_info;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public Date getDate_info() {
        return date_info;
    }

    public void setDate_info(Date date_info) {
        this.date_info = date_info;
    }

}
