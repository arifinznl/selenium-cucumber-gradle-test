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

    private By productList = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Validasi halaman inventory
    public void validateOnInventoryPage() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    // Validasi produk muncul
    public void validateProductListVisible() {
        List<WebElement> products = driver.findElements(productList);
        assertTrue(products.size() > 0, "Product list should not be empty");
    }

    // Tambah satu produk ke cart
    public void addProductToCart(String productName) {
        By addButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    // Tambah banyak produk sekaligus
    public void addProductsToCart(List<String> productNames) {
        for (String product : productNames) {
            addProductToCart(product);
        }
        // Tunggu badge update sesuai jumlah total produk
        waitForCartBadgeCount(productNames.size());
    }

    // Hapus satu produk dari cart
    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    // Hapus banyak produk sekaligus
    public void removeProductsFromCart(List<String> productNames) {
        for (String product : productNames) {
            removeProductFromCart(product);
        }
        // Tunggu badge update sesuai jumlah sisa produk
        int remaining = getCurrentBadgeCount();
        waitForCartBadgeCount(remaining);
    }

    // Validasi jumlah badge sesuai expected
    public void validateCartBadge(int expectedCount) {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        assertEquals(String.valueOf(expectedCount), badge.getText(), "Cart badge count mismatch");
    }

    // Validasi badge hilang atau nol
    public void validateCartBadgeNotVisible() {
        boolean invisible = wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            return badges.isEmpty() || badges.get(0).getText().equals("0");
        });
        assertTrue(invisible, "Cart badge should not be visible or zero");
    }

    // Utility: tunggu sampai badge mencapai jumlah tertentu
    private void waitForCartBadgeCount(int expectedCount) {
        wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            if (badges.isEmpty()) return expectedCount == 0;
            try {
                int value = Integer.parseInt(badges.get(0).getText());
                return value == expectedCount;
            } catch (NumberFormatException e) {
                return expectedCount == 0;
            }
        });
    }

    // Utility: ambil jumlah badge saat ini
    private int getCurrentBadgeCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) return 0;
        try {
            return Integer.parseInt(badges.get(0).getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
