package org.geektimes.projects.user.management;

import javax.management.*;
import java.util.HashMap;
import java.util.Map;

public class UserMXBean implements DynamicMBean {

    private Map<String, Object> attributes  = new HashMap<>();

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (!attribute.contains(attribute)) {
            return null;
        }

        return attributes.get(attribute);
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        attributes.put(attribute.getName(), attribute.getValue());
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList attributeList = new AttributeList();
        for (String s : attributes) {
            try {
                Object attributeValue = getAttribute(s);
                attributeList.add(new Attribute(s, attributeValue));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return attributeList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
