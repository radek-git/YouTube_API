package org.example;

import com.google.common.truth.Truth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apiguardian.api.API;
import org.example.playlists.*;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaylistsTest {


    public static final String API_KEY = "AIzaSyCQaR9BvuT-yGha0FYXKBtNmlOVPnuD0KE";
    public static final String GET_PLAYLISTS = "https://www.googleapis.com/youtube/v3/playlists";
    public static final String POST_PLAYLISTS = "https://www.googleapis.com/youtube/v3/playlists";
    public static final String ADD_VIDEO_TO_PLAYLIST = "https://www.googleapis.com/youtube/v3/playlistItems";
    public static final String TOKEN = "ya29.a0AfH6SMA8wgo62_mvZ_zYTzgaSitlgJbURX5u_3gxpfTuxdbfBq9f1te16G1mGvHchrZQwvEnlnaIvUwkBZuOwZ15rUuaMmULpUgqvs5qwo_lnDwV3i__Kj0jiGw1J1Oy-QLLM6pa976l97CaEKgEslFWThkxPp1c6sMS__2JyLs";

    @Test
    @Order(1)
    public void whenRequestIsSentWithContentDetailsAndChannelIdThenPlaylistsInfoIsReturned() {
        List<PlaylistSingleItem> playlists = new ArrayList<>();
        String nextPageToken;

        String baseParams = String.format("?part=contentDetails&channelId=UCxJDH_2HXzwUtT62HgWJqCg&key=%s", API_KEY);
        String params = baseParams;
        int pageCount = 1;

        while (true) {
            PlaylistResponse response = given().contentType(ContentType.JSON)
                    .when()
                    .get(GET_PLAYLISTS + params)
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .extract().as(PlaylistResponse.class);

            playlists.addAll(response.getItems());

            nextPageToken = response.getNextPageToken();
            if (nextPageToken == null) {
                break;
            } else {
                params = baseParams + String.format("&pageToken=%s", nextPageToken);
            }
            pageCount++;
        }

        System.out.println(playlists);

        assertThat(playlists.size()).isEqualTo(24);
        assertThat(pageCount).isEqualTo(5);


//        Truth.assertThat()

    //zrobić test że są 24 playlisty, 5 stron i ilość filmów w poszczególnych jsonach
    }


//    @Test
//    public void whenRequestIsSentWithSnippetThenNewPlaylistIsCreated() {
//        Snippet snippet = new Snippet("asdf", "UCNXfDtEKS4fod9laDi__bPg");
//        CreatePlaylistRequest createPlaylistRequest = new CreatePlaylistRequest(snippet);
//        String response = given()
//                .urlEncodingEnabled(false)
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + TOKEN)
//                .body(createPlaylistRequest)
//                .when()
//                .post(POST_PLAYLISTS+"?part=snippet").asString();
////                .then()
////                .assertThat().statusCode(HttpStatus.SC_OK);
//
//        System.out.println(response);
//    }

    @Test
    public void addVideoToPlaylist() {
        String s = given()
                .urlEncodingEnabled(true)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TOKEN)
                .body(new CreatePlaylistRequest(new Snippet("", "UCNXfDtEKS4fod9laDi__bPg",
                        "PLJu3BFSDuyNkxxBKa41L-eF-b0Qx66cQC", new ResourceId("W3ik2X1N6e0", "youtube#video"))))
                .when()
                .post(ADD_VIDEO_TO_PLAYLIST + "?part=snippet").asString();

        System.out.println(s);
    }




}
