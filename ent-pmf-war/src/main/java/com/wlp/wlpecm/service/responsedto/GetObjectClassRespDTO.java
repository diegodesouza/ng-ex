//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service.responsedto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.wlp.wlpecm.service.ArrayOfTns3NillableObjectClassInfo;


/**
 * <p>Java class for GetObjectClassRespDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetObjectClassRespDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="objClassInfo" type="{http://service.wlpecm.wlp.com}ArrayOf_tns3_nillable_ObjectClassInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetObjectClassRespDTO", propOrder = {
    "objClassInfo"
})
public class GetObjectClassRespDTO {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfTns3NillableObjectClassInfo objClassInfo;

    /**
     * Gets the value of the objClassInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns3NillableObjectClassInfo }
     *     
     */
    public ArrayOfTns3NillableObjectClassInfo getObjClassInfo() {
        return objClassInfo;
    }

    /**
     * Sets the value of the objClassInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns3NillableObjectClassInfo }
     *     
     */
    public void setObjClassInfo(ArrayOfTns3NillableObjectClassInfo value) {
        this.objClassInfo = value;
    }

}
