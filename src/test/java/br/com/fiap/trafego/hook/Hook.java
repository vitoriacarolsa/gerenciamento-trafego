package br.com.fiap.trafego.hook;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hook {

    @BeforeAll
    public static void setUpBeforeClass() {
        // Simulação de configuração global antes da execução de todos os testes
        System.out.println("Configuração global antes de todos os testes.");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        // Simulação de limpeza global após a execução de todos os testes
        System.out.println("Limpeza global após todos os testes.");
    }

    @Before
    public void setUp() {
        // Simulação de configuração antes da execução de cada cenário
        System.out.println("Iniciando um novo cenário de teste...");
    }

    @After
    public void tearDown() {
        // Simulação de limpeza após a execução de cada cenário
        System.out.println("Finalizando o cenário de teste...");
    }
}
