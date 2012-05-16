package com.timgroup.mustache;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;

public abstract class AssignmentFunction implements Function<String, String> {
    
    private static final Pattern ASSIGNMENT = Pattern.compile("^(\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)\\s*=\\s*(.*)$", Pattern.DOTALL);
    
    protected final Map<String, Object> map;
    
    public AssignmentFunction(Map<String, Object> map) {
        this.map = map;
    }
    
    @Override
    public String apply(String input) {
        if (input == null) throw new IllegalArgumentException("invalid variable assignment; no expression");
        Matcher matcher = ASSIGNMENT.matcher(input);
        if (!matcher.matches()) throw new IllegalArgumentException("invalid variable assignment: " + input);
        
        String name = matcher.group(1);
        String value = matcher.group(2);
        
        assign(name, value);
        return "";
    }
    
    protected abstract void assign(String name, String value);
    
}
