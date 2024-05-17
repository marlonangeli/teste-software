package br.com.aula.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroLoginPage {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    public static final String URL_LEILOES = "http://localhost:8080/leiloes";
    public static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";
    private WebDriver wd;

    public CadastroLoginPage(WebDriver driver) {
        this.wd = driver;

        wd.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.wd.quit();
    }

    public void preencheFormulario(String username, String password) {
        wd.findElement(By.id("username")).sendKeys(username);
        wd.findElement(By.id("password")).sendKeys(password);
    }

    public void submeterFormulario() {
        wd.findElement(By.id("button-submit")).click();
    }

    public boolean isPaginaDeLogin() {
        return wd.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean isPaginaDeLoginComErro() {
        return wd.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    public boolean isPaginaDeLeiloes() {
        return wd.getCurrentUrl().equals(URL_LEILOES);
    }

    public String getNomeUsuarioLogado() {
        return wd.findElement(By.id("usuario-logado")).getText();
    }

    public boolean contemTexto(String texto) {
        return wd.getPageSource().contains(texto);
    }
}
