package br.com.fiap.trafego.service;

import br.com.fiap.trafego.model.SemaforoModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CadastroSemaforoService {

    final SemaforoModel semaforoModel = new SemaforoModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    String baseUrl = "http://localhost:8080";
    public Response response;
    String idSemaforo;

    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsSemaforo(String field, String value) {
        switch (field) {
            case "localizacao" -> semaforoModel.setLocalizacao(value);
            case "estadoAtual" -> semaforoModel.setEstadoAtual(value);
            case "tempoVerde" -> semaforoModel.setTempoVerde(Integer.valueOf(value));
            case "tempoVermelho" -> semaforoModel.setTempoVermelho(Integer.valueOf(value));
            case "condicoesClimaticas" -> semaforoModel.setCondicoesClimaticas(value);
            case "ultimaAtualizacao" -> semaforoModel.setUltimaAtualizacao(value);
            default -> throw new IllegalStateException("Unexpected field");
        }
    }

    public void createSemaforo(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(semaforoModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdSemaforo() {
        idSemaforo = String.valueOf(gson.fromJson(response.jsonPath().prettify(), SemaforoModel.class).getId());
    }

    public void deleteSemaforo(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idSemaforo);
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    // CORRIGIDO: leitura correta do arquivo JSON
    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String jsonContent = reader.lines().collect(Collectors.joining("\n"));
            return new JSONObject(jsonContent);
        } catch (JSONException e) {
            throw new RuntimeException("Erro ao converter o conteúdo do arquivo em JSON: " + filePath, e);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido do semaforo" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-do-semaforo.json");
            default ->
                    throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(response.getBody().asString());
        } catch (JSONException e) {
            throw new RuntimeException("Erro ao converter resposta em JSON", e);
        }

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());

        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());

        return schema.validate(jsonResponseNode);
    }
}
