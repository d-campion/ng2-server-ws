package com.dimitri.angular.core.property;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * Extension du place holder Spring <br>
 * Utile pour la maitrise totale et facile des properties.<br>
 * Elle sont stockées dans une {@link HashMap}
 * 
 * @author Dimitri <campion.dimitri@gmail.com>
 * @version 0.0.1
 *
 */
public class PropertyPlaceHolder extends PropertyPlaceholderConfigurer {

	/**
	 * Contient les properties.
	 * 
	 * @see applicationContext.xml
	 */
	private static Map<String, String> propertiesMap;

	// Default as in PropertyPlaceholderConfigurer
	private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

	@Override
	public void setSystemPropertiesMode(final int systemPropertiesMode) {
		super.setSystemPropertiesMode(systemPropertiesMode);
		springSystemPropertiesMode = systemPropertiesMode;
	}

	@Override
	protected void processProperties(final ConfigurableListableBeanFactory beanFactory, final Properties props) throws BeansException {
		super.processProperties(beanFactory, props);

		propertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
			propertiesMap.put(keyStr, valueStr);
		}
	}

	public static String getProperty(final String name) {
		return propertiesMap.get(name).toString();
	}

	public static String getProperty(final String prefix, final String suffix) {
		return propertiesMap.get(new StringBuffer(prefix).append(suffix).toString()).toString();
	}

	public static List<String> getPropertyValues(final String name, final String separator) {
		String values = propertiesMap.get(name).toString();
		// remove all space
		values = values.replaceAll("\\s+", "");
		return Arrays.asList(values.split(separator));
	}

}