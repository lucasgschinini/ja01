package ar.com.telecom.shiva.base.soa.xmlAdapters;

import java.math.BigInteger;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigIntegerXmlAdapter 
	extends XmlAdapter<String, BigInteger> 
{
	public BigInteger unmarshal(String value) {
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return (javax.xml.bind.DatatypeConverter.parseInteger(value));
		}
	}

	public String marshal(BigInteger value) {
		if (value == null) {
			return null;
		}
		return (javax.xml.bind.DatatypeConverter.printInteger(value));
	}
}
