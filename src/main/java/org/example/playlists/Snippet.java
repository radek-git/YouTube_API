package org.example.playlists;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Snippet {

    private String title;
    private String channelId;
    private String playlistId;
    private ResourceId resourceId;
}
