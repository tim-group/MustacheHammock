package com.timgroup.mustache;

import java.util.Map;

import com.google.common.base.Function;

public class DefFunction extends AssignmentFunction {
    
    public DefFunction(Map<String, Object> map) {
        super(map);
    }
    
    @Override
    protected void assign(String name, final String value) {
        map.put(name, new Function<String, String>() {
            @Override
            public String apply(String input) {
                return value;
            }
        });
    }
    
}
