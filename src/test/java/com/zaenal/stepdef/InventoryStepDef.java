package com.zaenal.stepdef;

import com.zaenal.BaseTest;
import com.zaenal.page.InventoryPage;
import com.zaenal.page.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventoryStepDef extends BaseTest {

    InventoryPage inventoryPage;
    LoginPage loginPage;

    @Then("user is on inventory page")
    public void userIsOnInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        inventoryPage.validateOnInventoryPage();
    }

    @And("user can see product list")
    public void userCanSeeProductList() {
        inventoryPage.validateProductListVisible();

    }

    @Given("user is logged in successfully")
    public void userIsLoggedInSuccessfully() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();
        inventoryPage = new InventoryPage(driver);
        inventoryPage.validateOnInventoryPage();

    }

    @When("user add product {string} to cart")
    public void userAddProductToCart(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @Then("cart badge should show {string}")
    public void cartBadgeShouldShow(String expectedCount) {
        inventoryPage.validateCartBadge(expectedCount);
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
        inventoryPage.validateCartBadgeNotVisible();
    }
}
