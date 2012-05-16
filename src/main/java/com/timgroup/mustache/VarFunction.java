package com.timgroup.mustache;

import java.util.Map;

public class VarFunction extends AssignmentFunction {
    
    public VarFunction(Map<String, Object> map) {
        super(map);
    }
    
    @Override
    protected void assign(String name, String value) {
        map.put(name, value);
    }
    
}
