//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service.requestdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetContentReqDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetContentReqDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginPwd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="applicationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="plan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="repositoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetContentReqDTO", propOrder = {
    "loginPwd",
    "applicationID",
    "contentKey",
    "operationType",
    "plan",
    "repositoryName"
})
public class GetContentReqDTO {

    @XmlElement(required = true, nillable = true)
    protected String loginPwd;
    @XmlElement(required = true, nillable = true)
    protected String applicationID;
    @XmlElement(required = true, nillable = true)
    protected String contentKey;
    @XmlElement(required = true, nillable = true)
    protected String operationType;
    @XmlElement(required = true, nillable = true)
    protected String plan;
    @XmlElement(required = true, nillable = true)
    protected String repositoryName;

    /**
     * Gets the value of the loginPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * Sets the value of the loginPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginPwd(String value) {
        this.loginPwd = value;
    }

    /**
     * Gets the value of the applicationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * Sets the value of the applicationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationID(String value) {
        this.applicationID = value;
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
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationType(String value) {
        this.operationType = value;
    }

    /**
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlan(String value) {
        this.plan = value;
    }

    /**
     * Gets the value of the repositoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * Sets the value of the repositoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepositoryName(String value) {
        this.repositoryName = value;
    }

}
