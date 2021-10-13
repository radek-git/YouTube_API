package org.example.channels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleItem {

    private String kind;
    private String etag;
    private String id;
    private ContentDetails contentDetails;
    private Statistics statistics;
}
