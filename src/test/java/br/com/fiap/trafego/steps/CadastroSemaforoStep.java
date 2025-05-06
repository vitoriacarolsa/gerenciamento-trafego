package br.com.fiap.trafego.steps;

import br.com.fiap.trafego.model.ErrorMessageModel;
import br.com.fiap.trafego.service.CadastroSemaforoService;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONException;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroSemaforoStep {

    CadastroSemaforoService cadastroSemaforoService = new CadastroSemaforoService();

    @Dado("que eu tenha os seguintes dados do semáforo:")
    public void queEuTenhaOsSeguintesDadosDoSemáforo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroSemaforoService.setFieldsSemaforo(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string}")
    public void euEnviarARequisicaoParaOEndpoint(String endPoint) {
        cadastroSemaforoService.createSemaforo(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroSemaforoService.response.statusCode());
    }

    @E("o corpo da resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDaRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroSemaforoService.gson.fromJson(
                cadastroSemaforoService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }


    @Dado("que eu recupere o ID do semaforo criado no contexto")
    public void queEuRecupereOIDDoSemaforoCriadoNoContexto() {
        cadastroSemaforoService.retrieveIdSemaforo();
    }

    @Quando("eu enviar a requisicao com o ID para o endpoint {string} de delecao de semaforos")
    public void euEnviarARequisicaoComOIDParaOEndpointDeDelecaoDeSemaforos(String endPoint) {
        cadastroSemaforoService.deleteSemaforo(endPoint);
    }


    @E("que o arquivo de contrato esperado e o {string}")
    public void queOArquivoDeContratoEsperadoEO(String contract) throws IOException {
        cadastroSemaforoService.setContract(contract);
    }

    @Então("a resposta da requisicao deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroSemaforoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }


}
