package com.dojo;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

public class SumReduce {


    public Optional<BigDecimal> sampleReduce(Optional<BigDecimal> numberOne, Optional<BigDecimal> numberTwo){

        return Stream.of(numberOne, numberTwo)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .reduce(BigDecimal::add);

    }


}
