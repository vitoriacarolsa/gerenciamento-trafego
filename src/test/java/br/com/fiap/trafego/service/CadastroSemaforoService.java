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


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static io.restassured.RestAssured.given;

public class CadastroSemaforoService {

    final SemaforoModel semaforoModel = new SemaforoModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().
            create();

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
            case "ultimaAtualizacao" -> semaforoModel.setUltimaAtualizacao(LocalDateTime.parse(value));
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

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido do semaforo" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-do-semaforo.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {

        // Obter o corpo da resposta como String e converter para JSONObject
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        // Configurar o JsonSchemaFactory e criar o JsonSchema
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        // Converter o JSON de resposta para JsonNode
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        // Validar o JSON de resposta contra o esquema
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);

        return schemaValidationErrors;

    }



}
