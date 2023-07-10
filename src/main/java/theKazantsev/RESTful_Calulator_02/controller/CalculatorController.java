package theKazantsev.RESTful_Calulator_02.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import theKazantsev.RESTful_Calulator_02.logic.*;
import theKazantsev.RESTful_Calulator_02.logic.exception.DivideByZeroException;

@RestController
public class CalculatorController {

    @GetMapping(value = "/add")
    public Add add(@RequestParam(name = "arg1", defaultValue = "1") double arg1,
                   @RequestParam(name = "arg2", defaultValue = "1") double arg2) {
        return new Add(arg1, arg2);
    }

    @GetMapping(value = "/subtract")
    public Subtract subtract(@RequestParam(name = "arg1", defaultValue = "1") double arg1,
                             @RequestParam(name = "arg2", defaultValue = "1") double arg2) {
        return new Subtract(arg1, arg2);
    }

    @GetMapping(value = "/divide")
    public ResponseEntity<?> divide(@RequestParam(name = "arg1", defaultValue = "1") double arg1,
                         @RequestParam(name = "arg2", defaultValue = "1") double arg2) {
        try {
            if (arg2 == 0) {
                throw new DivideByZeroException("Can't divide by zero");
            }
            Divide divide = new Divide(arg1, arg2);
            return ResponseEntity.ok(divide);
        } catch (DivideByZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"error\": \"Can't divide by zero\"}");
        }
    }

    @GetMapping(value = "/multiply")
    public Multiply multiply(@RequestParam(name = "arg1", defaultValue = "1") double arg1,
                             @RequestParam(name = "arg2", defaultValue = "1") double arg2) {
        return new Multiply(arg1, arg2);
    }

    @GetMapping(value = "/pow")
    public Pow pow(@RequestParam(name = "arg1", defaultValue = "1") double arg1,
                   @RequestParam(name = "arg2", defaultValue = "1") double arg2) {
        return new Pow(arg1, arg2);
    }

    @GetMapping(value = "/sqrt")
    public Sqrt sqrt(@RequestParam(name = "arg1", defaultValue = "1") double arg1) {
        return new Sqrt(arg1);
    }
}
