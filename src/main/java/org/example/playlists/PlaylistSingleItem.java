package org.example.playlists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.channels.ContentDetails;
import org.example.channels.Statistics;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSingleItem {

    private String kind;
    private String etag;
    private String id;
    private PlaylistContentDetails contentDetails;
    private Statistics statistics;
}
