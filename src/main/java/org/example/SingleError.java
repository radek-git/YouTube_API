package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleError {

    private String message;
    private String domain;
    private String reason;
    private String location;
    private String locationType;


}
