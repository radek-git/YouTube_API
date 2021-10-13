package org.example.playlists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddVideoToPlaylistRequest {

    private Snippet snippet;
}
