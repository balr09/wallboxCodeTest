package jfc.wallbox.codetest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * Unit test for simple App.
 */

public class AppTest {
	private static String apiKey = "dc6zaTOxFJmzC";
	private static Properties props;
	
	private static String urlbase;
	// private static String urlbase = "api.giphy.com/v1/stickers";
	// there's a second url for search endpoint that responds with message: "You cannot consume this service"; 
	// so will only use stickers search...
	// private static String urlbase = "api.giphy.com/v1/gifs";

	/**
	 * Perform some simple seraches:
	 */
	@Tag(value = "search")
	@DisplayName(value = "search endpoint")
	@ParameterizedTest(name = "{index} => querystring={0}, statuscode={1}, datasize={2}")
	@CsvFileSource(delimiter = ',', encoding = "utf-8", resources = "/search.csv", numLinesToSkip = 1)
	public void simplesearch(String querystring, int expectedStatusCode, int dataSize) {
		
		given()
			.params("api_key", apiKey, "q", querystring)
		.expect()
			.statusCode(expectedStatusCode)
			.body("data", hasSize(dataSize))
		.when()
			.post(getUrl("search"))
		.then()
			.log().body();
	}

	/**
	 * Test bad requests using parameters with wrong type:
	 */
	@Tag(value = "wrongparams")
	@DisplayName(value = "send wrong params to endpoint")
	@ParameterizedTest(name = "{index} => querystring={0}, limit={1}, offset={2}")
	@CsvFileSource(delimiter = ',', encoding = "utf-8", resources = "/wrong.csv", numLinesToSkip = 1)
	public void wrongparams(Object querystring, Object limit, Object offset) {
		// always expect Bad Request:
		final int expectedStatusCode = 400;
		
		given()
			.params("api_key", apiKey, "q", querystring, "limit", limit, "offset", offset)
		.expect()
			.statusCode(expectedStatusCode)
		.when()
			.post(getUrl("search"))
		.then()
			.log().body();
	}

	/**
	 * Explore boundaries:
	 */
	@Tag(value = "boundaries")
	@DisplayName(value = "send wrong near or off the edge")
	@ParameterizedTest(name = "{index} => querystring={0}, limit={1}, offset={2}, datasize={3}, statuscode={4}")
	@CsvFileSource(delimiter = ',', encoding = "utf-8", resources = "/boundaries.csv", numLinesToSkip = 1)
	public void boundaries(String querystring, int limit, int offset, int dataSize, int expectedStatusCode ) {
		
		if (dataSize >= 0) { 
			given()
				.params("api_key", apiKey, "q", querystring, "limit", limit, "offset", offset)
			.expect()
				.statusCode(expectedStatusCode)
				.body("data", hasSize(dataSize))
			.when()
				.post(getUrl("search"))
			.then()
				.log().body();
		} else {
			given()
				.params("api_key", apiKey, "q", querystring, "limit", limit, "offset", offset)
			.expect()
				.statusCode(expectedStatusCode)
			.when()
				.post(getUrl("search"))
			.then()
				.log().body();
		}
	}

	
	
	////////////////////////////////////////////////////////////
	// helper
	public String getUrl(String path) {
		return String.format("https://%s/%s", urlbase, path);
	}
	
	@BeforeAll
	public static void loadProperties() {
		props = new Properties();
		try {
			props.load(AppTest.class.getResourceAsStream("/test.properties"));
			
			urlbase = props.getProperty("testHost");
		} catch (IOException e) {
			// should add logger here:
			e.printStackTrace();
		}
	}
}
