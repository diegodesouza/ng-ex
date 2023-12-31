//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.wlp.wlpecm.service;

import javax.xml.ws.WebFault;
import com.wellpoint.service.exception.v2.ExceptionListType;

@WebFault(name = "ExceptionList", targetNamespace = "http://wellpoint.com/service/exception/v2")
public class Fault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExceptionListType faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public Fault(String message, ExceptionListType faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public Fault(String message, ExceptionListType faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.wellpoint.service.exception.v2.ExceptionListType
     */
    public ExceptionListType getFaultInfo() {
        return faultInfo;
    }

}
