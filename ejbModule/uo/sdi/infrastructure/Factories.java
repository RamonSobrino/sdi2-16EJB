package uo.sdi.infrastructure;

import uo.sdi.infrastructure.FactoriesHelper;

import uo.sdi.business.ServicesFactory;

public class Factories {
	private static String CONFIG_FILE = "/factories.properties";
	
	public static ServicesFactory services = (ServicesFactory)
			FactoriesHelper.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
}
