package theKazantsev.RESTful_Calulator_02.logic;

import theKazantsev.RESTful_Calulator_02.logic.exception.DivideByZeroException;

public class Divide extends General {

    public Divide(double param1, double param2) {
        result = param1 / param2;
    }
}
