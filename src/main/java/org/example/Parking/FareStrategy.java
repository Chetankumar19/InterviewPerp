package org.example.Parking;

import java.math.BigDecimal;

public interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket,BigDecimal inputFare);
}
