package com.timgroup.mustache;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;

final class VarFunction implements Function<String, String> {
    
    private static final Pattern ASSIGNMENT = Pattern.compile("^(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)\\s*=\\s*(.*)$", Pattern.DOTALL);
    
    private final Map<String, Object> map;
    
    VarFunction(Map<String, Object> map) {
        this.map = map;
    }
    
    @Override
    public String apply(String input) {
        Matcher matcher = ASSIGNMENT.matcher(input);
        if (!matcher.matches()) throw new IllegalArgumentException("invalid variable assignment: " + input);
        
        String name = matcher.group(1);
        String value = matcher.group(2);
        
        map.put(name, value);
        return "";
    }
    
}