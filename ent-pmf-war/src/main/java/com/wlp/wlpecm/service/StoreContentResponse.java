//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.responsedto.StoreContentRespDTO;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeContentReturn" type="{http://responsedto.service.wlpecm.wlp.com}StoreContentRespDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "storeContentReturn"
})
@XmlRootElement(name = "storeContentResponse")
public class StoreContentResponse {

    @XmlElement(required = true, nillable = true)
    protected StoreContentRespDTO storeContentReturn;

    /**
     * Gets the value of the storeContentReturn property.
     * 
     * @return
     *     possible object is
     *     {@link StoreContentRespDTO }
     *     
     */
    public StoreContentRespDTO getStoreContentReturn() {
        return storeContentReturn;
    }

    /**
     * Sets the value of the storeContentReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreContentRespDTO }
     *     
     */
    public void setStoreContentReturn(StoreContentRespDTO value) {
        this.storeContentReturn = value;
    }

}