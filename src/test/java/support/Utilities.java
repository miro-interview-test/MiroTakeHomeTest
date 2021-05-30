package support;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;


public class Utilities {

    public static Properties loadUserProperties() {
        String projectPath = System.getProperty("user.dir");
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(projectPath + "/src/test/resources/data/users.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static void hitEscapeKey(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
    }

    public static void rightClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
    }

    public static void takeScreesnhot(WebDriver driver, String filenname) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String newFilePath = projectPath+"/src/test/resources/screenshots/"+filenname+".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshotFile.toPath(), new File(newFilePath).toPath());
    }

    public static void takeElementScreenshot(WebDriver driver, WebElement element, String filenname) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String newFilePath = projectPath+"/src/test/resources/screenshots/"+filenname+".png";
        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
        Files.copy(screenshotFile.toPath(), new File(newFilePath).toPath());
    }

    public static double compareImagePixels() throws IOException {
        String projectPath = System.getProperty("user.dir");
        BufferedImage image1 = ImageIO.read(new File(projectPath+"/src/test/resources/screenshots/user1Board.png"));
        BufferedImage image2 = ImageIO.read(new File(projectPath+"/src/test/resources/screenshots/user2Board.png"));
        int width = image1.getWidth();
        int height = image1.getHeight();
        long diff = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int pixel1 = image1.getRGB(i, j);
                Color color1 = new Color(pixel1, true);
                int r1 = color1.getRed();
                int g1 = color1.getGreen();
                int b1 = color1.getBlue();
                int pixel2 = image2.getRGB(i, j);
                Color color2 = new Color(pixel2, true);
                int r2 = color2.getRed();
                int g2 = color2.getGreen();
                int b2= color2.getBlue();
                long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
                diff = diff+data;
            }
        }
        double avg = diff/(width*height*3);
        return (avg/255)*100;
    }
}
