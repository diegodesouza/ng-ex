//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service.responsedto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetContentRespDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetContentRespDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="contentKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="contentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentUri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entryDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetContentRespDTO", propOrder = {
    "contentData",
    "contentKey",
    "contentName",
    "contentSize",
    "contentType",
    "contentUri",
    "entryDate"
})
public class GetContentRespDTO {

    @XmlElement(required = true)
    protected byte[] contentData;
    @XmlElement(required = true, nillable = true)
    protected String contentKey;
    @XmlElement(required = true, nillable = true)
    protected String contentName;
    protected long contentSize;
    @XmlElement(required = true, nillable = true)
    protected String contentType;
    @XmlElement(required = true, nillable = true)
    protected String contentUri;
    @XmlElement(required = true, nillable = true)
    protected String entryDate;

    /**
     * Gets the value of the contentData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContentData() {
        return contentData;
    }

    /**
     * Sets the value of the contentData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContentData(byte[] value) {
        this.contentData = ((byte[]) value);
    }

    /**
     * Gets the value of the contentKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentKey() {
        return contentKey;
    }

    /**
     * Sets the value of the contentKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentKey(String value) {
        this.contentKey = value;
    }

    /**
     * Gets the value of the contentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentName() {
        return contentName;
    }

    /**
     * Sets the value of the contentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentName(String value) {
        this.contentName = value;
    }

    /**
     * Gets the value of the contentSize property.
     * 
     */
    public long getContentSize() {
        return contentSize;
    }

    /**
     * Sets the value of the contentSize property.
     * 
     */
    public void setContentSize(long value) {
        this.contentSize = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the contentUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentUri() {
        return contentUri;
    }

    /**
     * Sets the value of the contentUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentUri(String value) {
        this.contentUri = value;
    }

    /**
     * Gets the value of the entryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryDate(String value) {
        this.entryDate = value;
    }

}
