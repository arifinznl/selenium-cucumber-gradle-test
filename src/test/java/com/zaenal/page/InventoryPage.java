package com.zaenal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    // Locator
    private By productList = By.className("inventory_item");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    //validasi
//    public void validateOnInventoryPage() {
//        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
//    }
//
//    //validasi produk muncul
//    public void validateProductListVisible() {
//        List<WebElement> products = driver.findElements(productList);
//        assertTrue(products.size() > 0, "Product list should not be empty");
//    }
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public boolean isProductListVisible() {
        List<WebElement> products = driver.findElements(productList);
        return products.size() > 0;
    }
    //locator dinamis
    public void addProductToCart(String productName) {
        By addButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.findElement(addButton).click();
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.findElement(removeButton).click();

    }
    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartBadgeVisible() {
        return driver.findElements(cartBadge).size() > 0;
    }

    //validasi item keranjang
//    public void validateCartBadge(String expectedCount) {
//        WebElement badge = driver.findElement(cartBadge);
//        assertEquals(expectedCount, badge.getText());
//    }
//
//    public void validateCartBadgeNotVisible() {
//        assertTrue(driver.findElements(cartBadge).isEmpty(), "Cart badge should not be visible");
//    }
}
