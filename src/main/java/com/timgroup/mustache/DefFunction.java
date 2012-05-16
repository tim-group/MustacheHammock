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
    private final Object[] scopes;
    
    public DefFunction(Map<String, Object> map, MustacheFactory factory, Object[] scopes) {
        super(map);
        this.factory = factory;
        this.scopes = scopes;
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
                    mustache.execute(buf, HammockMustache.prepend(Collections.singletonMap("input", input), scopes)).flush();
                } catch (IOException e) {
                    throw new RuntimeException("error executing defined mustache: " + name + " = " + value, e);
                }
                return buf.toString();
            }
        });
    }
    
}
