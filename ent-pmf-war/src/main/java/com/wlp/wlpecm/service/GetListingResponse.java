//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.responsedto.GetListingRespDTO;


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
 *         &lt;element name="getListingReturn" type="{http://responsedto.service.wlpecm.wlp.com}GetListingRespDTO"/>
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
    "getListingReturn"
})
@XmlRootElement(name = "getListingResponse")
public class GetListingResponse {

    @XmlElement(required = true, nillable = true)
    protected GetListingRespDTO getListingReturn;

    /**
     * Gets the value of the getListingReturn property.
     * 
     * @return
     *     possible object is
     *     {@link GetListingRespDTO }
     *     
     */
    public GetListingRespDTO getGetListingReturn() {
        return getListingReturn;
    }

    /**
     * Sets the value of the getListingReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetListingRespDTO }
     *     
     */
    public void setGetListingReturn(GetListingRespDTO value) {
        this.getListingReturn = value;
    }

}
