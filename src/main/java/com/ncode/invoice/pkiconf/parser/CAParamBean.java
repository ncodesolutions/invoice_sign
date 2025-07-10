/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.invoice.pkiconf.parser;

/**
 *

 */
public class CAParamBean implements java.io.Serializable {

    /**
     * Holds Id property for the Bean.
     */
    private String id;
    /**
     * Holds CRL property for the Bean.
     */
    private String crl;
    /**
     * Holds Blocked Classes property for the Bean.
     */
    private String classesBlocked[];
    /**
     * Holds Name property for the Bean.
     */
    private String name;
    /**
     * Holds Root Subject Key Identifier property for the Bean.
     */
    private String rootSki;

    /**
     * Parameterized constructor to set id.
     *
     * @param id String object passed to set Id through constructor.
     */
    public CAParamBean(String id) {
        this.id = id;
    }

    public CAParamBean() {

    }

    /**
     * get Id form Bean
     *
     * @return Id as String object.
     */
    public String getId() {
        return id;
    }

    /**
     * set Id to Bean.
     *
     * @param id Set id to bean.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get Name from Bean.
     *
     * @return Name as String object.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Name to Bean.
     *
     * @param name Set name to bean.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get CRL from Bean
     *
     * @return CRL as String object.
     */
    public String getCrl() {
        return crl;
    }

    /**
     * set CRl to Bean
     *
     * @param crl Set CRL to bean.
     */
    public void setCrl(String crl) {
        this.crl = crl;
    }

    /**
     * get Blocked Class from Bean.
     *
     * @return Blocked class as String array.
     */
    public String[] getClassBlocked() {
        return classesBlocked;
    }

    /**
     * set Blocked classes to Bean.
     *
     * @param classesBlocked set Classes Blocked to bean.
     */
    public void setClassBlocked(String classesBlocked[]) {
        this.classesBlocked = classesBlocked;
    }

    /**
     * get Root subject key identifier from bean.
     *
     * @return Root SKI as String object.
     */
    public String getRootSki() {
        return rootSki;
    }

    /**
     * set Root subject key identifier to bean.
     *
     * @param rootSki Set root ski to bean.
     */
    public void setRootSki(String rootSki) {
        this.rootSki = rootSki;
    }

    /**
     * Compare with CAParam bean object.
     *
     * @param obj to compair with.
     * @return true if both objecs are equal and false otherwise.
     */
    public boolean equals(Object obj) {
        CAParamBean slf = null;
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CAParamBean)) {
            return false;
        }
        slf = (CAParamBean) obj;
        return slf.getId().equalsIgnoreCase(this.getId());
    }

    /**
     * retuen hash code.
     *
     * @return Hash Code.
     */
    public int hashCode() {
        return this.getId().hashCode();
    }

}
