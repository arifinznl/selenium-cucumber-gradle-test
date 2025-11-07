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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        try {
            Thread.sleep(500); // waktu rendering tambahan
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();

        wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            if (badges.isEmpty()) return true;
            try {
                int value = Integer.parseInt(badges.get(0).getText());
                return value >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    //validasi item keranjang
    public void validateCartBadge(String expectedCount) {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        assertEquals(expectedCount, badge.getText(), "Cart badge count mismatch");
    }

    public void validateCartBadgeNotVisible() {
        boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        assertTrue(invisible, "Cart badge should not be visible");
    }
}
