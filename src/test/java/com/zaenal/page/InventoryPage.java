package com.zaenal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPage {
    private WebDriver driver;

    // Locator
    private By productList = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    //validasi
    public void validateOnInventoryPage() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    //validasi produk muncul
    public void validateProductListVisible() {
        List<WebElement> products = driver.findElements(productList);
        assertTrue(products.size() > 0, "Product list should not be empty");
    }

    //locator dinamis
    public void addProductToCart(String productName) {
        By addButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.findElement(addButton).click();
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.findElement(removeButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            // Jika badge sudah hilang atau jumlah badge sesuai ekspektasi, lanjut
            return badges.isEmpty() || Integer.parseInt(badges.get(0).getText()) >= 0;
        });

    }

    //validasi item keranjang
    public void validateCartBadge(String expectedCount) {
        WebElement badge = driver.findElement(cartBadge);
        assertEquals(expectedCount, badge.getText());
    }

    public void validateCartBadgeNotVisible() {
        assertTrue(driver.findElements(cartBadge).isEmpty(), "Cart badge should not be visible");
    }
}
