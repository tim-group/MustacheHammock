package com.timgroup.mustache;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.Code;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class HammockMustache implements Mustache {
    
    private final Mustache mustache;
    private final MustacheFactory factory;

    public HammockMustache(Mustache mustache, MustacheFactory factory) {
        this.mustache = mustache;
        this.factory = factory;
    }

    @Override
    public Writer execute(Writer writer, Object[] scopes) {
        return mustache.execute(writer, augment(scopes));
    }

    private Object[] augment(Object[] scopes) {
        Map<String, Object> augmentation = new HashMap<String, Object>();
        Object[] augmentedScopes = prepend(augmentation, scopes);
        
        augmentation.put("var", new VarFunction(augmentation));
        augmentation.put("def", new DefFunction(augmentation, factory, augmentedScopes));
        
        return augmentedScopes;
    }

    public static Object[] prepend(Object object, Object[] array) {
        Object[] prepended = new Object[array.length + 1];
        prepended[0] = object;
        System.arraycopy(array, 0, prepended, 1, array.length);
        return prepended;
    }

    @Override
    public Writer execute(Writer writer, Object scope) {
        return execute(writer, new Object[] {scope});
    }
    
    @Override
    public void identity(Writer writer) {
        mustache.identity(writer);
    }

    @Override
    public void append(String text) {
        mustache.append(text);
    }

    @Override
    public Code[] getCodes() {
        return mustache.getCodes();
    }

    @Override
    public void setCodes(Code[] codes) {
        mustache.setCodes(codes);
    }

    @Override
    public void init() {
        mustache.init();
    }

}
