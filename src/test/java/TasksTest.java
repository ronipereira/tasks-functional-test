import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessandoAplicacao() {
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessandoAplicacao();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via selenium");
        driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");
        driver.findElement(By.id("saveButton")).click();
        String mensagemSucesso = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Success!", mensagemSucesso);
        driver.quit();
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = acessandoAplicacao();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via selenium");
        driver.findElement(By.id("dueDate")).sendKeys("01/01/2020");
        driver.findElement(By.id("saveButton")).click();
        String mensagemSucesso = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Due date must not be in past", mensagemSucesso);
        driver.quit();
    }

    @Test
    public void deveSalvarTarefaSemDescricao() {
        WebDriver driver = acessandoAplicacao();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("");
        driver.findElement(By.id("dueDate")).sendKeys("01/01/2020");
        driver.findElement(By.id("saveButton")).click();
        String mensagemSucesso = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the task description", mensagemSucesso);
        driver.quit();
    }

    @Test
    public void deveSalvarTarefaSemData() {
        WebDriver driver = acessandoAplicacao();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("addTodo")).click();
        driver.findElement(By.id("task")).sendKeys("Teste via selenium");
        driver.findElement(By.id("dueDate")).sendKeys("");
        driver.findElement(By.id("saveButton")).click();
        String mensagemSucesso = driver.findElement(By.id("message")).getText();
        Assert.assertEquals("Fill the due date", mensagemSucesso);
        driver.quit();
    }
}
