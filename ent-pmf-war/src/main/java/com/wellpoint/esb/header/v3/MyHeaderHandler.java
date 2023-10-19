package com.wellpoint.esb.header.v3;



import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.anthem.common.PMFResourceUtils;
import com.anthem.util.Constants;
import com.ibm.ws.webservices.engine.xmlsoap.SOAPHeaderElement;
import com.sun.mail.imap.protocol.Namespaces.Namespace;
import com.wellpoint.esb.header.v3.ESBHeaderType;
import com.wellpoint.esb.header.v3.ESBPropsType;
import com.wellpoint.esb.header.v3.ObjectFactory;


public class MyHeaderHandler implements SOAPHandler<SOAPMessageContext> {

private String username;
private String password;



public boolean handleMessage(SOAPMessageContext smc) {
	
	


    Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    if (outboundProperty.booleanValue()) {
    	Properties webConfigProp=PMFResourceUtils.getProperties();                      
    	String securityUsername = webConfigProp.getProperty("wlp.pmf.datapower.username");
    	String securityPassword = webConfigProp.getProperty("wlp.pmf.datapower.password");
    	
    	 SOAPMessage message = smc.getMessage();
         SOAPHeader header = null;
         SOAPEnvelope envelope = null;
		try {
			header = message.getSOAPHeader();
			envelope = message.getSOAPPart().getEnvelope();
			  if (header == null)
		          header = envelope.addHeader();
		} catch (SOAPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
  		
  		
         
   
 		
         try { 
         
         SOAPElement security =
                 header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
         SOAPElement usernameToken =		security.addChildElement("UsernameToken", "wsse");
         
         SOAPElement username =
                 usernameToken.addChildElement("Username", "wsse");
         username.addTextNode(securityUsername);

         SOAPElement password =
                 usernameToken.addChildElement("Password", "wsse");
        password.addTextNode(securityPassword);     
        message.writeTo(System.out);
        } catch (Exception e) {
             e.printStackTrace();
         }
         
         System.out.println("Outbound-Message in proper format!!");
     /*    try {
 			Source source = message.getSOAPPart().getContent();
 			javax.xml.transform.Transformer transformer;
 			System.out.println("Outbound Message formatted:");
 			try {
 				transformer = TransformerFactory.newInstance().newTransformer();
 				  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
 			        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
 			         
 			        try {
 						transformer.transform(source, new StreamResult(System.out));
 					} catch (TransformerException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
 			} catch (TransformerConfigurationException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (TransformerFactoryConfigurationError e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	         
 	      
 		} catch (SOAPException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
            
*/
         
   
           
            System.out.println("");

     
         
         

    } else {
        try {

            //This handler does nothing with the response from the Web Service so
            //we just print out the SOAP message.
            SOAPMessage message = smc.getMessage();
            System.out.println("Inbound Message");
            try {
    			Source source = message.getSOAPPart().getContent();
    			javax.xml.transform.Transformer transformer;
    			System.out.println("Inbound Message formatted:");
    			try {
    				transformer = TransformerFactory.newInstance().newTransformer();
    				  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    			        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    			         
    			        try {
    						transformer.transform(source, new StreamResult(System.out));
    					} catch (TransformerException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    			} catch (TransformerConfigurationException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (TransformerFactoryConfigurationError e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	         
    	      
    		} catch (SOAPException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}

        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    return outboundProperty;
}

@Override
public void close(MessageContext arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public boolean handleFault(SOAPMessageContext arg0) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Set<QName> getHeaders() {
	// TODO Auto-generated method stub
	return null;
}

}