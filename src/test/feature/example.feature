@MoneyTolia
Feature: MoneyTolia

  Background:
    Given Open the "http://automationexercise.com"
    Then I see home page

  @MoneyToliaTest
  Scenario: MoneyTolia
    Then I wait "main_button" element
    Then I click "products_button" element at index 1
    Then I see "products" page
    Then I save variable "first_product_price" of element value as "first_price"
    Then I save variable "first_product_name" of element value as "first_product_info"
    Then I save variable "second_product_price" of element value as "second_price"
    Then I save variable "second_product_name" of element value as "second_product_info"
    Then I mouse hover to "first_product" element and click to "add_to_cart_button" element
    Then I click "continue_shopping_button" element at index 1
    Then I mouse hover to "second_product" element and click to "second_add_to_cart_button" element
    Then I click "view_card_text_button" element at index 1

    Then I wait "first_price" element
    Then I wait "first_product_info" element
    Then I wait "second_price" element
    Then I wait "second_product_info" element





