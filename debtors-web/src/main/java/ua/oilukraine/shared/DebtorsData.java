/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;

/**
 *
 * @author u_gorbonos
 */
public class DebtorsData implements IsSerializable, Comparable<DebtorsData> {

    private long code;
    private long ref_firm_for_debtors;

    private String name;
    private String okpo;
    private String name_buh;
    private String phone_work;
    private String phone_mob;
    private Date date_info;
    private double crd_fin_aid;
    private double crd_buy_brokerage;
    private double crd_serv_prod;
    private double crd_total_sum;
    private double dbt_fin_aid;
    private double dbt_buy_brokerage;
    private double dbt_serv_prod;
    private double dbt_total_sum;
    private long fixed_sum;
    private long ref_user;
    private long ref_firm;

    public DebtorsData() {
    }

    public DebtorsData(long code, long ref_firm_for_debtors, String name, String okpo, String name_buh, String phone_work, String phone_mob, Date date_info, double crd_fin_aid, double crd_buy_brokerage, double crd_serv_prod, double crd_total_sum, double dbt_fin_aid, double dbt_buy_brokerage, double dbt_serv_prod, double dbt_total_sum, long fixed_sum, long ref_user, long ref_firm) {
        this.code = code;
        this.ref_firm_for_debtors = ref_firm_for_debtors;
        this.name = name;
        this.okpo = okpo;
        this.name_buh = name_buh;
        this.phone_work = phone_work;
        this.phone_mob = phone_mob;
        this.date_info = date_info;
        this.crd_fin_aid = crd_fin_aid;
        this.crd_buy_brokerage = crd_buy_brokerage;
        this.crd_serv_prod = crd_serv_prod;
        this.crd_total_sum = crd_total_sum;
        this.dbt_fin_aid = dbt_fin_aid;
        this.dbt_buy_brokerage = dbt_buy_brokerage;
        this.dbt_serv_prod = dbt_serv_prod;
        this.dbt_total_sum = dbt_total_sum;
        this.fixed_sum = fixed_sum;
        this.ref_user = ref_user;
        this.ref_firm = ref_firm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getName_buh() {
        return name_buh;
    }

    public void setName_buh(String name_buh) {
        this.name_buh = name_buh;
    }

    public String getPhone_work() {
        return phone_work;
    }

    public void setPhone_work(String phone_work) {
        this.phone_work = phone_work;
    }

    public String getPhone_mob() {
        return phone_mob;
    }

    public void setPhone_mob(String phone_mob) {
        this.phone_mob = phone_mob;
    }

    public Date getDate_info() {
        return date_info;
    }

    public void setDate_info(Date date_info) {
        this.date_info = date_info;
    }

    public double getCrd_fin_aid() {
        return crd_fin_aid;
    }

    public void setCrd_fin_aid(double crd_fin_aid) {
        this.crd_fin_aid = crd_fin_aid;
    }

    public double getCrd_buy_brokerage() {
        return crd_buy_brokerage;
    }

    public void setCrd_buy_brokerage(double crd_buy_brokerage) {
        this.crd_buy_brokerage = crd_buy_brokerage;
    }

    public double getCrd_serv_prod() {
        return crd_serv_prod;
    }

    public void setCrd_serv_prod(double crd_serv_prod) {
        this.crd_serv_prod = crd_serv_prod;
    }

    public double getCrd_total_sum() {
        return crd_total_sum;
    }

    public void setCrd_total_sum(double crd_total_sum) {
        this.crd_total_sum = crd_total_sum;
    }

    public double getDbt_fin_aid() {
        return dbt_fin_aid;
    }

    public void setDbt_fin_aid(double dbt_fin_aid) {
        this.dbt_fin_aid = dbt_fin_aid;
    }

    public double getDbt_buy_brokerage() {
        return dbt_buy_brokerage;
    }

    public void setDbt_buy_brokerage(double dbt_buy_brokerage) {
        this.dbt_buy_brokerage = dbt_buy_brokerage;
    }

    public double getDbt_serv_prod() {
        return dbt_serv_prod;
    }

    public void setDbt_serv_prod(double dbt_serv_prod) {
        this.dbt_serv_prod = dbt_serv_prod;
    }

    public double getDbt_total_sum() {
        return dbt_total_sum;
    }

    public void setDbt_total_sum(double dbt_total_sum) {
        this.dbt_total_sum = dbt_total_sum;
    }

    public long getFixed_sum() {
        return fixed_sum;
    }

    public void setFixed_sum(long fixed_sum) {
        this.fixed_sum = fixed_sum;
    }

    public long getRef_user() {
        return ref_user;
    }

    public void setRef_user(long ref_user) {
        this.ref_user = ref_user;
    }

    public long getRef_firm() {
        return ref_firm;
    }

    public void setRef_firm(long ref_firm) {
        this.ref_firm = ref_firm;
    }

    @Override
    public int compareTo(DebtorsData o) {
        int result;
        result = Long.valueOf(code).compareTo(o.code);
        if (result != 0) {
            return result;
        }
        result = Long.valueOf(ref_firm_for_debtors).compareTo(o.ref_firm_for_debtors);
        if (result != 0) {
            return result;
        }
        result = name.compareTo(o.name);
        if (result != 0) {
            return result;
        }
        result = okpo.compareTo(o.okpo);
        return result;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

}
