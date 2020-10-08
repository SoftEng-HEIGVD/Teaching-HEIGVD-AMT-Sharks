package ch.heigvd.amt.fishing.ui.web;

import ch.heigvd.amt.fishing.domain.services.BankingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchExpeditionEndpointIT {

  private static String host;
  private static String port;
  private static String baseUrl;

  @BeforeAll
  public static void initialSetup() {
    host = "localhost"; // System.getProperty("http.host");
    port = "9081"; // System.getProperty("http.port");
    baseUrl = "http://" + host + ":" + port + "/";
  }

  @Test
  public void testOverambitiousBudgetFromOutsideTheContainer() {
    MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
    formData.add("budget", Integer.toString(BankingService.MAX_WITHDRAWAL_AMOUNT + 1));
    formData.add("crewSize", "10");
    formData.add("boatType", "LARGE");
    Response response = ClientBuilder.newClient().target(baseUrl + "launchExpedition").request().post(Entity.form(formData));
    String html = response.readEntity(String.class);
    assertTrue(html.contains("Expedition aborted because your banker was not happy"));
  }

  @Test
  public void testOverSpendingFromOutsideTheContainer() {
    MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
    formData.add("budget", "1");
    formData.add("crewSize", "10");
    formData.add("boatType", "LARGE");
    Response response = ClientBuilder.newClient().target(baseUrl + "launchExpedition").request().post(Entity.form(formData));
    String html = response.readEntity(String.class);
    assertTrue(html.contains("Expedition aborted because of careless budgeting"));
  }

}