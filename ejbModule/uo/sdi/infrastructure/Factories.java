package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.LocalEjbServicesLocator;

public class Factories {
	public static ServicesFactory services = new LocalEjbServicesLocator();
}
