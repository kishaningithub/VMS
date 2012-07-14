package com.vms.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class vmsJaxbContext
{
	private static JAXBContext jaxbContext ;
	private vmsJaxbContext(){}
	public static JAXBContext getVmsJaxbContext()
	{
		if(jaxbContext==null)
			try {
				jaxbContext=JAXBContext.newInstance("com.vms.dto");
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		return jaxbContext;
	}
}
