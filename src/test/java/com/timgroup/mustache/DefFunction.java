package com.timgroup.mustache;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Function;

public class DefFunction extends AssignmentFunction {
    
    private final MustacheFactory factory;

    public DefFunction(Map<String, Object> map, MustacheFactory factory) {
        super(map);
        this.factory = factory;
    }
    
    @Override
    protected void assign(final String name, final String value) {
        final Mustache mustache = factory.compile(new StringReader(value), name);
        map.put(name, new Function<String, String>() {
            @Override
            public String apply(String input) {
                // sadly, there is no way to write directly to the main writer
                StringWriter buf = new StringWriter();
                try {
                    mustache.execute(buf, new Object[] {Collections.singletonMap("input", input), map}).flush();
                } catch (IOException e) {
                    throw new RuntimeException("error executing defined mustache: " + name + " = " + value, e);
                }
                return buf.toString();
            }
        });
    }
    
}
