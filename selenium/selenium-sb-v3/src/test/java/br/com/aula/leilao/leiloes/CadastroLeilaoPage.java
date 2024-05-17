package br.com.aula.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CadastroLeilaoPage {
    public static final String URL_CADASTRO = "http://localhost:8080/leiloes/new";
    

    private WebDriver browser;
    
    public CadastroLeilaoPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fechar() {
        browser.quit();
    }

    public void preencherFormulario(String nome, String valorInicial, String dataAbertura) {
        browser.findElement(By.id("nome")).sendKeys(nome);
        browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
    }

    public void submeterFormulario() {
        browser.findElement(By.id("button-submit")).click();
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        // Verifica se a última linha da tabela contém o nome, valor e data
        String cssUltimaLinhaDaTabela = "table tbody tr:last-child";
        String nomeTabela = browser.findElement(By.cssSelector(cssUltimaLinhaDaTabela + " td:nth-child(1)")).getText();
        String dataTabela = browser.findElement(By.cssSelector(cssUltimaLinhaDaTabela + " td:nth-child(2)")).getText();
        String valorTabela = browser.findElement(By.cssSelector(cssUltimaLinhaDaTabela + " td:nth-child(3)")).getText();

        return nomeTabela.equals(nome) && valorTabela.equals(valor) && dataTabela.equals(data);
    }

    public boolean contemErrosValidacao() {
        List<WebElement> elements = browser.findElements(By.cssSelector("p.alert-danger"));
        // check if all elemensts contains texts
        return !elements.isEmpty() && elements.stream().noneMatch(element -> element.getText().isEmpty());
    }
}
