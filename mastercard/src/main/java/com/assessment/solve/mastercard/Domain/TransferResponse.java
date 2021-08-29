package com.assessment.solve.mastercard.Domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
/**
 * POJO class for sending the message on successful money transfer.
 * @auhor Abhijit Giri
 */
@Getter
@Setter
@RequiredArgsConstructor
public class TransferResponse {
    private String message ;
}
