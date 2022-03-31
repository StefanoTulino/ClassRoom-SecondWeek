package com.corso.annotations;

public @interface SingleTestAnnotation {

    //per passare un parametro gli dichiariamo una semplice variabile di istanza(in questo caso)

    //viene invocata come metodo in questo caso
    TestAnnotationEnum value();

    String item();

}
