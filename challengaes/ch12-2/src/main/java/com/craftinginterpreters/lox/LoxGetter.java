package com.craftinginterpreters.lox;

import java.util.List;

public class LoxGetter implements LoxCallable{
    private final Stmt.Getter declaration;
    private final Environment closure;

    public LoxGetter(Stmt.Getter declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
      Environment environment = new Environment(closure);
      try{
        interpreter.executeBlock(declaration.body, environment);
      } catch (Return returnValue){
        return returnValue.value;
      }
      return null;
    }

    public LoxGetter bind(LoxInstance instance) {
        closure.define("this", instance);
        return this;
//        Environment environment = new Environment(closure);
//        environment.define("this", instance);
//        return new LoxGetter(declaration, environment);
    }
}
