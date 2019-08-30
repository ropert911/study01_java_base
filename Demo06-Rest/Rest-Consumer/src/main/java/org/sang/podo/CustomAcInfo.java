package org.sang.podo;

public class CustomAcInfo {
    /**
     * ac ip
     */
    private String acBaseIp;

    /**
     * ac mac
     */
    private String acBaseMac;

    /**
     * ac 别名
     */
    private String alias;

    public String getAcBaseIp() {
        return acBaseIp;
    }

    public void setAcBaseIp(String acBaseIp) {
        this.acBaseIp = acBaseIp;
    }

    public String getAcBaseMac() {
        return acBaseMac;
    }

    public void setAcBaseMac(String acBaseMac) {
        this.acBaseMac = acBaseMac;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "CustomAcInfo{" +
                "acBaseIp='" + acBaseIp + '\'' +
                ", acBaseMac='" + acBaseMac + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}