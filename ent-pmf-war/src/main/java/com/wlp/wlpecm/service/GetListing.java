//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.requestdto.GetListingReqDTO;


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
 *         &lt;element name="objRequestGetListingReqDTO" type="{http://requestdto.service.wlpecm.wlp.com}GetListingReqDTO"/>
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
    "objRequestGetListingReqDTO"
})
@XmlRootElement(name = "getListing")
public class GetListing {

    @XmlElement(required = true, nillable = true)
    protected GetListingReqDTO objRequestGetListingReqDTO;

    /**
     * Gets the value of the objRequestGetListingReqDTO property.
     * 
     * @return
     *     possible object is
     *     {@link GetListingReqDTO }
     *     
     */
    public GetListingReqDTO getObjRequestGetListingReqDTO() {
        return objRequestGetListingReqDTO;
    }

    /**
     * Sets the value of the objRequestGetListingReqDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetListingReqDTO }
     *     
     */
    public void setObjRequestGetListingReqDTO(GetListingReqDTO value) {
        this.objRequestGetListingReqDTO = value;
    }

}
