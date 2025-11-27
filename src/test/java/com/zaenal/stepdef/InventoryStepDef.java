package com.zaenal.stepdef;

import com.zaenal.BaseTest;
import com.zaenal.page.InventoryPage;
import com.zaenal.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InventoryStepDef extends BaseTest {

    InventoryPage inventoryPage;
    LoginPage loginPage;

    @Then("user is on inventory page")
    public void userIsOnInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        assertTrue("User is not on inventory page", inventoryPage.isOnInventoryPage());
    }

    @And("user can see product list")
    public void userCanSeeProductList() {
        assertTrue("Product list should be visible", inventoryPage.isProductListVisible());
//        inventoryPage.validateProductListVisible();
    }

    @Given("user is logged in successfully")
    public void userIsLoggedInSuccessfully() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();
        inventoryPage = new InventoryPage(driver);
        assertTrue("User failed to reach inventory page", inventoryPage.isOnInventoryPage());
//        inventoryPage.validateOnInventoryPage();
    }

    @When("user add product {string} to cart")
    public void userAddProductToCart(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @Then("cart badge should show {string}")
    public void cartBadgeShouldShow(String expectedCount) {
        String actual = inventoryPage.getCartBadgeText();
        assertEquals("Cart badge count mismatch", expectedCount, actual);
//        inventoryPage.validateCartBadge(expectedCount);
    }

    @Given("user has added product {string} to cart")
    public void userHasAddedProductToCart(String productName) {
        userIsLoggedInSuccessfully();
        inventoryPage.addProductToCart(productName);
    }

    @When("user remove product {string}")
    public void userRemoveProduct(String productName) {
        inventoryPage.removeProductFromCart(productName);
    }

    @Then("cart badge should not be visible")
    public void cartBadgeShouldNotBeVisible() {
        assertFalse(inventoryPage.isCartBadgeVisible(), "Cart badge should not be visible");
//        inventoryPage.validateCartBadgeNotVisible();
    }
}
