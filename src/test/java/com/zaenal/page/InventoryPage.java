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
        // Klik tombol add
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        // Tunggu tombol berubah menjadi "Remove"
        wait.until(driver -> {
            WebElement button = driver.findElement(addButton);
            return button.getText().equalsIgnoreCase("Remove");
        });

        // Tunggu badge muncul & update count
        wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            if (badges.isEmpty()) return false;
            try {
                return Integer.parseInt(badges.get(0).getText()) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        // Tunggu tombol Remove muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));

        // Klik tombol Remove
        driver.findElement(removeButton).click();

        // Tunggu tombol Add to Cart muncul kembali sebagai indikasi produk sudah dihapus
        By addButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));

        // Jika ada cart badge, tunggu count update (opsional)
        try {
            By cartBadge = By.className("shopping_cart_badge");
            wait.until(driver -> {
                List<WebElement> badge = driver.findElements(cartBadge);
                // Jika badge ada, pastikan jumlah di-update, jika tidak ada berarti cart kosong
                return badge.isEmpty() || Integer.parseInt(badge.get(0).getText()) < driver.findElements(By.xpath("//div[@class='inventory_item']//button[text()='Remove']")).size() + 1;
            });
        } catch (Exception ignored) {
            // jika badge tidak ada, lanjut
        }
    }

    //validasi item keranjang
    public void validateCartBadge(String expectedCount) {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        assertEquals(expectedCount, badge.getText(), "Cart badge count mismatch");
    }

    public void validateCartBadgeNotVisible() {
        boolean invisible = wait.until(driver -> {
            List<WebElement> badges = driver.findElements(cartBadge);
            return badges.isEmpty() || badges.get(0).getText().equals("0");
        });
        assertTrue(invisible, "Cart badge should not be visible or zero");
    }
}
