package org.example;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.channels.ChannelResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChannelTest {

    public static final String API_KEY = "AIzaSyCQaR9BvuT-yGha0FYXKBtNmlOVPnuD0KE";
    public static final String GET_CHANNELS = "https://www.googleapis.com/youtube/v3/channels";


    @Test
    @Order(1)
    public void whenRequestSentWithoutFiltersThenStatus400IsReturned() {
        YTErrorResponse response = given().contentType(ContentType.JSON)
                .when()
                .get(GET_CHANNELS + "?key=" + API_KEY)
                .then()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().as(YTErrorResponse.class);

        String expectedMessage = "No filter selected. Expected one of";
        assertThat(response.getError().getMessage()).contains(expectedMessage);
        assertThat(response.getError().getCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);

    }


    @Test
    @Order(2)
    public void whenRequestSentWithFilterContentDetailsThen200AndContentDetailsIsReturned() {
        ChannelResponse response = given().contentType(ContentType.JSON)
                .when()
                .get(GET_CHANNELS + "?part=contentDetails&forUsername=SPInkafilmstudio&key=" + API_KEY)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(ChannelResponse.class);

        assertThat(response.getPageInfo().getTotalResults()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void whenRequestSentWithFilterContentDetailsAndStatisticsThen200AndContentDetailsAndStatisticsReturned() {
        ChannelResponse response = given().contentType(ContentType.JSON)
                .when()
                .get(GET_CHANNELS + "?part=contentDetails&part=statistics&forUsername=SPInkafilmstudio&key=" + API_KEY)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(ChannelResponse.class);

        assertThat(response.getItems().get(0).getStatistics().getSubscriberCount()).isEqualTo("2090000");
    }
}
