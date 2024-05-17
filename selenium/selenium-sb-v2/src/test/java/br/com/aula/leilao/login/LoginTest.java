package br.com.aula.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CadastroLeilaoTest {

    CadastroLeilaoPage page;
    @BeforeEach
    void beforeEach() {
        page = new CadastroLeilaoPage();
    }

    @AfterEach
    void afterEach() {
        page.fechar();
    }

    @Test
    void deveriaFazerLoginComSucesso() {
        page.preencheFormulario("fulano", "pass");
        page.submeterFormulario();

        // Asserts
        // Verificar se a URL mudou para .../leiloes
        Assert.assertTrue(
                page.isPaginaDeLeiloes());

        // Verificar se o nome do usuário logado é apresentado na Div
        Assert.assertEquals("fulano", page.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaFazerLoginComSucesso() {
        page.preencheFormulario("fulano", "senhaerrada");
        page.submeterFormulario();

        // Asserts
        // Verificar se a URL está em .../login
        Assert.assertTrue(
                page.isPaginaDeLoginComErro());

        // Verificar se a URL não é a .../leiloes
        Assert.assertFalse(
                page.isPaginaDeLeiloes());

        // Verificar se a página contém o texto "Usuário e senha inválidos."
        Assert.assertTrue(
                page.contemTexto("Usuário e senha inválidos."));
    }

}
