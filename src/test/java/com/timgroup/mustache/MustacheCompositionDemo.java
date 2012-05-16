package com.timgroup.mustache;

import java.io.IOException;
import java.io.PrintWriter;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class MustacheCompositionDemo {
    
    public static void main(String[] args) throws IOException {
        render("hello.mustache", new PrintWriter(System.out));
    }
    
    private static void render(String template, PrintWriter output) throws IOException {
        MustacheFactory factory = new HammockedMustacheFactory(new DefaultMustacheFactory());
        Mustache mustache = factory.compile(template);
        mustache.execute(output, new Object[] {}).flush();
    }
    
}
