package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable {
    final String name;
    private final Map<String, LoxFunction> methods;
    private final Map<String, LoxGetter> getters;


    public LoxClass(String name, Map<String, LoxFunction> methods, Map<String, LoxGetter> getters) {
        this.name = name;
        this.methods = methods;
        this.getters = getters;
    }

    public LoxFunction findMethod(String name) {
        if (methods.containsKey(name))
            return methods.get(name);
        return null;
    }

    public LoxGetter findGetter(String name) {
        if(getters.containsKey(name)) return getters.get(name);
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int arity() {
        LoxFunction init = findMethod("init");
        if(init == null) return 0;
        return init.arity();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction init = findMethod("init");
        if(init != null) {
            init.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }
}
