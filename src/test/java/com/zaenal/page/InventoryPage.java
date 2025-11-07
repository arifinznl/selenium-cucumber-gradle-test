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
    private WebDriverWait wait;

    // Locator
    private By productList = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    //validasi item keranjang
    public void validateCartBadge(String expectedCount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        WebElement badge = driver.findElement(cartBadge);
        assertEquals(expectedCount, badge.getText(), "Jumlah item di cart badge tidak sesuai ekspektasi");
    }

    public void validateCartBadgeNotVisible() {
        // TImeout untuk menghindari false negative di UI
        wait.withTimeout(Duration.ofSeconds(2));
        assertTrue(driver.findElements(cartBadge).isEmpty(), "Cart badge should not be visible");
    }
}
