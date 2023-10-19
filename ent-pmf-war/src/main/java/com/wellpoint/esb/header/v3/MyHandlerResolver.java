package com.wellpoint.esb.header.v3;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class MyHandlerResolver implements HandlerResolver{

	@Override
	public List<Handler> getHandlerChain(PortInfo arg0) {
		// TODO Auto-generated method stub
		 List handlerChain = new ArrayList();
		 
	      MyHeaderHandler hh = new MyHeaderHandler();
	      handlerChain.add(hh);
	      return handlerChain;
	}

}
