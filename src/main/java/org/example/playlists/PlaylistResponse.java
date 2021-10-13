package org.example.playlists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.channels.PageInfo;
import org.example.channels.SingleItem;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("prevPageToken")
public class PlaylistResponse {

    private String kind;
    private String etag;
    private String nextPageToken;
//    private String prevPageToken; - pole ignorowane
    private PageInfo pageInfo;
    private List<PlaylistSingleItem> items;
}


