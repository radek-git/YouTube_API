package org.example.channels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResponse {

    private String kind;
    private String etag;
    private PageInfo pageInfo;
    private List<SingleItem> items;
}
