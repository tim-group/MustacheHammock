package com.timgroup.mustache;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class MustacheCompositionDemo {
    
    public static void main(String[] args) throws IOException {
        render("hello.mustache", new PrintWriter(System.out));
    }
    
    private static void render(String template, PrintWriter output) throws IOException {
        MustacheFactory factory = new DefaultMustacheFactory();
        Mustache mustache = factory.compile(template);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("var", new VarFunction(map));
        map.put("def", new DefFunction(map, factory));
        
        mustache.execute(output, new Object[] {map}).flush();
    }
    
}
