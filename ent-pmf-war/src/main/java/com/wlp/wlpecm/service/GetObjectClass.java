//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.requestdto.GetObjectClassReqDTO;


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
 *         &lt;element name="objRequestGetObjectClassReqDTO" type="{http://requestdto.service.wlpecm.wlp.com}GetObjectClassReqDTO"/>
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
    "objRequestGetObjectClassReqDTO"
})
@XmlRootElement(name = "getObjectClass")
public class GetObjectClass {

    @XmlElement(required = true, nillable = true)
    protected GetObjectClassReqDTO objRequestGetObjectClassReqDTO;

    /**
     * Gets the value of the objRequestGetObjectClassReqDTO property.
     * 
     * @return
     *     possible object is
     *     {@link GetObjectClassReqDTO }
     *     
     */
    public GetObjectClassReqDTO getObjRequestGetObjectClassReqDTO() {
        return objRequestGetObjectClassReqDTO;
    }

    /**
     * Sets the value of the objRequestGetObjectClassReqDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetObjectClassReqDTO }
     *     
     */
    public void setObjRequestGetObjectClassReqDTO(GetObjectClassReqDTO value) {
        this.objRequestGetObjectClassReqDTO = value;
    }

}
