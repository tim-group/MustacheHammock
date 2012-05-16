package com.timgroup.mustache;

import java.io.Reader;
import java.io.Writer;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.MustacheVisitor;
import com.github.mustachejava.ObjectHandler;

public class HammockedMustacheFactory implements MustacheFactory {
    
    private final MustacheFactory delegate;
    
    public HammockedMustacheFactory(MustacheFactory delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public Mustache compile(String name) {
        return wrap(delegate.compile(name));
    }
    
    @Override
    public Mustache compile(Reader reader, String name) {
        return wrap(delegate.compile(reader, name));
    }
    
    private Mustache wrap(Mustache mustache) {
        return new HammockMustache(mustache, delegate);
    }

    @Override
    public MustacheVisitor createMustacheVisitor() {
        return delegate.createMustacheVisitor();
    }
    
    @Override
    public Reader getReader(String resourceName) {
        return delegate.getReader(resourceName);
    }
    
    @Override
    public void encode(String value, Writer writer) {
        delegate.encode(value, writer);
    }
    
    @Override
    public ObjectHandler getObjectHandler() {
        return delegate.getObjectHandler();
    }
    
    @Override
    public String translate(String from) {
        return delegate.translate(from);
    }
    
}
