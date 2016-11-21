/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.view.client.ProvidesKey;
import java.util.Objects;

/**
 *
 * @author u_gorbonos
 */
public class Firm implements IsSerializable, Comparable<Firm> {

    private String name;
    private String okpo;
    private long code;

    public static final ProvidesKey<Firm> KEY_PROVIDER = new ProvidesKey<Firm>() {
        @Override
        public Object getKey(Firm item) {
            return item == null ? null : item.getCode();
        }
    };

    public Firm() {
    }

    public Firm(long code, String name, String okpo) {
        this.name = name;
        this.okpo = okpo;
        this.code = code;
    }

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public long getCode() {
        return code;
    }

    /**
     * Set the value of code
     *
     * @param code new value of code
     */
    public void setCode(long code) {
        this.code = code;
    }

    /**
     * Get the value of okpo
     *
     * @return the value of okpo
     */
    public String getOkpo() {
        return okpo;
    }

    /**
     * Set the value of okpo
     *
     * @param okpo new value of okpo
     */
    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Firm o) {
        int result;
        result = name.compareTo(o.name);
        if (result != 0) {
            return result;
        }
        result = okpo.compareTo(o.okpo);
        if (result != 0) {
            return result;
        }
        result = Long.valueOf(code).compareTo(o.code);
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Firm other = (Firm) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.okpo, other.okpo)) {
            return false;
        }
        return true;
    }

}
