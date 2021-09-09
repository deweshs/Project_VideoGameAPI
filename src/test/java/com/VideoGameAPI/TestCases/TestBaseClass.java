package com.VideoGameAPI.TestCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBaseClass {
	public static Logger logger;
	
	@BeforeClass
	public void setUp() {
	logger = Logger.getLogger("VideoGameAPI");
	PropertyConfigurator.configure("log4j.properties");
	logger.setLevel(Level.ALL);
	}
	

	public String randomString() {
		String randomStr = RandomStringUtils.randomNumeric(3);
		return randomStr;
	}
}
