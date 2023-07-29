package com.example.mdbspringboot.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NatwestApiClient {

    private final String clientId;
    private final String clientSecret;
    private String accessToken;
    private String consentId;

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public NatwestApiClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public static String getByAccountData(String username) throws URISyntaxException, IOException, InterruptedException {
        String clientId = "PkAJ3oVcGGZlUTPKFJAK1X2EVL3xzVMMAG_EJh_KzOc=";
        String clientSecret = "suFn5Mvb9ACHPw5SJdMz0eIiScJCZtXxDi29ue2r_ZE=";

        NatwestApiClient client = new NatwestApiClient(clientId, clientSecret);
        client.initialize();
        if (client.getAccessToken() != null && client.getConsentId() != null) {
            var full_url = client.generateRedirectURL(username);
            var access_token = findData(full_url);
            return getAccountDataHelper(access_token);
        }
        return "None";
    }

    public static String getByAccountTransacationData(String username, String accountID) throws URISyntaxException, IOException, InterruptedException {
        String clientId = "PkAJ3oVcGGZlUTPKFJAK1X2EVL3xzVMMAG_EJh_KzOc=";
        String clientSecret = "suFn5Mvb9ACHPw5SJdMz0eIiScJCZtXxDi29ue2r_ZE=";

        NatwestApiClient client = new NatwestApiClient(clientId, clientSecret);
        client.initialize();
        if (client.getAccessToken() != null && client.getConsentId() != null) {
            var full_url = client.generateRedirectURL(username);
            var access_token = findData(full_url);
            return getTransationDataHelper(access_token, accountID);
        }
        return "None";
    }

    public void initialize() {
        try {
            accessToken = getAccessToken();
            if (accessToken != null) {
                consentId = createAccountAccessConsent();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getAccessToken() throws IOException, InterruptedException {
        String url = "https://ob.sandbox.natwest.com/token";

        Map<String, String> data = new HashMap<>();
        data.put("grant_type", "client_credentials");
        data.put("client_id", clientId);
        data.put("client_secret", clientSecret);
        data.put("scope", "accounts");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormDataFromMap(data))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            return extractValueFromJson(responseBody, "access_token");
        } else {
            System.out.println("Request failed with status code " + response.statusCode());
            System.out.println(response.body());
            return null;
        }
    }

    private String createAccountAccessConsent() throws IOException, InterruptedException {
        String url = "https://ob.sandbox.natwest.com/open-banking/v3.1/aisp/account-access-consents";

        String requestData = "{\"Data\":{\"Permissions\":[\"ReadAccountsDetail\",\"ReadBalances\",\"ReadTransactionsCredits\",\"ReadTransactionsDebits\",\"ReadTransactionsDetail\"]},\"Risk\":{}}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestData))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            String responseBody = response.body();
            return extractValueFromJson(responseBody, "ConsentId");
        } else {
            System.out.println("Request failed with status code " + response.statusCode());
            System.out.println(response.body());
            return null;
        }
    }

    private String generateRedirectURL(String username) throws URISyntaxException {
        String url = "https://api.sandbox.natwest.com/authorize";
        String response_type = "code id_token";
        String scope = "openid accounts";
        String redirect_uri = "https://bbe465c4-b930-4bf9-a07b-3d1bafd440b2.example.org/redirect";
        String authorization_mode = "AUTO_POSTMAN";
        String authorization_result = "APPROVED";
        String test_user_username = username+"@bbe465c4-b930-4bf9-a07b-3d1bafd440b2.example.org";
        String authorization_accounts = "*";

        String urldone = (url + "?client_id=" + clientId +
                "&response_type=" + response_type +
                "&scope=" + scope +
                "&redirect_uri=" + redirect_uri +
                "&request=c600650c-0e49-419e-ae6e-0c015dcc3d3c" +
                "&authorization_mode=" + authorization_mode +
                "&authorization_result=" + authorization_result +
                "&authorization_username=" + test_user_username +
                "&authorization_accounts=" + authorization_accounts +
                "&consent_id=" + consentId);

        System.out.println("Full Request URL: " + urldone);
        return urldone;
    }

    private String extractValueFromJson(String jsonString, String key) {
        int startIndex = jsonString.indexOf("\"" + key + "\":\"") + key.length() + 4;
        int endIndex = jsonString.indexOf("\"", startIndex);
        return jsonString.substring(startIndex, endIndex);
    }

    private HttpRequest.BodyPublisher buildFormDataFromMap(Map<String, String> data) {
        String params = data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");
        return HttpRequest.BodyPublishers.ofString(params, StandardCharsets.UTF_8);
    }

    public String getConsentId() {
        return consentId;
    }

    public static String findData(String full_request_url) throws IOException {
        // Make the GET request
        URL url = new URL(full_request_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseStringBuilder.append(line);
        }
        reader.close();

        // Extract the 'code' from the JSON response
        String responseJson = responseStringBuilder.toString();
        String code = responseJson.split("redirectUri")[1].split("#code=")[1].split("&id_token")[0];

        // Step 2
        String tokenUrl = "https://ob.sandbox.natwest.com/token";
        String clientId = "PkAJ3oVcGGZlUTPKFJAK1X2EVL3xzVMMAG_EJh_KzOc=";
        String clientSecret = "suFn5Mvb9ACHPw5SJdMz0eIiScJCZtXxDi29ue2r_ZE=";
        String redirectUri = "https://bbe465c4-b930-4bf9-a07b-3d1bafd440b2.example.org/redirect";
        String grantType = "authorization_code";

        // Prepare the POST data
        String data = "client_id=" + URLEncoder.encode(clientId, "UTF-8") +
                "&client_secret=" + URLEncoder.encode(clientSecret, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                "&grant_type=" + URLEncoder.encode(grantType, "UTF-8") +
                "&code=" + URLEncoder.encode(code, "UTF-8");

        // Make the POST request
        URL tokenEndpoint = new URL(tokenUrl);
        HttpURLConnection tokenConnection = (HttpURLConnection) tokenEndpoint.openConnection();
        tokenConnection.setRequestMethod("POST");
        tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        tokenConnection.setDoOutput(true);

        // Send the POST data
        tokenConnection.getOutputStream().write(data.getBytes());

        // Read the response
        BufferedReader tokenReader = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
        StringBuilder tokenResponseStringBuilder = new StringBuilder();
        while ((line = tokenReader.readLine()) != null) {
            tokenResponseStringBuilder.append(line);
        }
        tokenReader.close();

        // Extract the access token from the JSON response
        String tokenResponseJson = tokenResponseStringBuilder.toString();
        String accessToken = tokenResponseJson.split("\"access_token\":\"")[1].split("\"")[0];

//        split here
        return accessToken;
    }

    public static String getTransationDataHelper(String accessToken, String accountID) throws IOException {
        // Step 5
        String accountUrl = "https://ob.sandbox.natwest.com/open-banking/v3.1/aisp/accounts/"+accountID+"/transactions";
        String line;

        // Make the GET request with the access token
        URL accountEndpoint = new URL(accountUrl);
        HttpURLConnection accountConnection = (HttpURLConnection) accountEndpoint.openConnection();
        accountConnection.setRequestMethod("GET");
        accountConnection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Read the response
        BufferedReader accountReader = new BufferedReader(new InputStreamReader(accountConnection.getInputStream()));
        StringBuilder accountResponseStringBuilder = new StringBuilder();
        while ((line = accountReader.readLine()) != null) {
            accountResponseStringBuilder.append(line);
        }
        accountReader.close();

        // Process the account data here (in accountResponseStringBuilder.toString())
        String accountData = accountResponseStringBuilder.toString();
        return accountData;
    }

    public static String getAccountDataHelper(String accessToken) throws IOException {
        // Step 5
        String accountUrl = "https://ob.sandbox.natwest.com/open-banking/v3.1/aisp/accounts";
        String line;

        // Make the GET request with the access token
        URL accountEndpoint = new URL(accountUrl);
        HttpURLConnection accountConnection = (HttpURLConnection) accountEndpoint.openConnection();
        accountConnection.setRequestMethod("GET");
        accountConnection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Read the response
        BufferedReader accountReader = new BufferedReader(new InputStreamReader(accountConnection.getInputStream()));
        StringBuilder accountResponseStringBuilder = new StringBuilder();
        while ((line = accountReader.readLine()) != null) {
            accountResponseStringBuilder.append(line);
        }
        accountReader.close();

        // Process the account data here (in accountResponseStringBuilder.toString())
        String accountData = accountResponseStringBuilder.toString();
        return accountData;
    }
}
