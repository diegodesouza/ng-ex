//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.common.ObjectClassInfo;


/**
 * <p>Java class for ArrayOf_tns3_nillable_ObjectClassInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOf_tns3_nillable_ObjectClassInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectClassInfo" type="{http://common.service.wlpecm.wlp.com}ObjectClassInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns3_nillable_ObjectClassInfo", propOrder = {
    "objectClassInfo"
})
public class ArrayOfTns3NillableObjectClassInfo {

    @XmlElement(name = "ObjectClassInfo", nillable = true)
    protected List<ObjectClassInfo> objectClassInfo;

    /**
     * Gets the value of the objectClassInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectClassInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectClassInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectClassInfo }
     * 
     * 
     */
    public List<ObjectClassInfo> getObjectClassInfo() {
        if (objectClassInfo == null) {
            objectClassInfo = new ArrayList<ObjectClassInfo>();
        }
        return this.objectClassInfo;
    }

}
