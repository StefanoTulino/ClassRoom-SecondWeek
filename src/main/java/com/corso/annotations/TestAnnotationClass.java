package com.corso.annotations;

//Annotation vuota, se eseguo il codice non da pero errore
@TestAnnotation

@SingleTestAnnotation(item= "Ciao",
        value= TestAnnotationEnum.SUCCESS)
public class TestAnnotationClass {

    public void testAnnotationPresent(){
        TestAnnotationClass t= new TestAnnotationClass();

    }


    public static void main(String[] args) {
        //TestAnnotationClass testAnnotationClass= new TestAnnotationClass();

        Class test= TestAnnotationClass.class;
        if(test.isAnnotationPresent(TestAnnotationClass.class)) System.out.println("There is annotations");
        else   System.out.println("There is not a annotations");

    }




}

