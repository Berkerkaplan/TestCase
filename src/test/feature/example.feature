@MoneyTolia
Feature: MoneyTolia

  Background:
    Given Open the "http://automationexercise.com"
    Then I see home page

#Faturali Mnp port in - E2E

#PASS
#  @Test @FaturaliDisSKodlu @Parallel1
#  Scenario Outline: Dış akış S Kodlu Faturali aktivasyon tarife seçimi ve sipariş tamamlama - E2E
#
#    Then I wait "main_button" element
#    Then OTP step passed
#    When I see musteriPortali page
#    Then I click "hamburger menu" element at index 1
#    And I click "aktivasyon text" element at index 1
#    When I click "faturali aktivasyon" element at index 1
#
#    Then I wait for 5 seconds
#
##    Then I fill id information "84637127996" element with click inquire
#    Then I take tc id for postpaid flows
#
#    Then I wait for 5 seconds
#
#    Then I click "faturali kullanici bilgileri sayfasi ileri button" element at index 1
#    Then I wait for 5 seconds
#    Then I check if there is a "kayitli adres" or not
#
#    Then I must able to see "faturali adres bilgileri sayfasi ileri button" element at index 1
#    Then I click "faturali adres bilgileri sayfasi ileri button" element at index 1
#
#    Then I wait for 10 seconds
# #   Then I see "faturali adres bilgileri fatura kesim tarihi" element at index 1
#    Then I click "faturali adres bilgileri sayfasi second ileri button" element at index 1
##    Then I click element: "faturali adres bilgileri sayfasi second ileri button" if it exists at index 1
#
#    Then I see numaraSecimi page
#
#    Then I wait for 10 seconds
#
#    Then I click "prefix section" element at index 1
#    Then I click "543 text" element at index 1
##    Then I click "gsm no text box" element at index 1
#    Then I enter "302" text to gsm no text box at index 1
#    Then I click "gsm control button" element at index 1
#    Then I choose to p status gsm number
#
#    Then I click "click gsm number from list" element at index 1
#    Then I click "gsm list sec button" element at index 1
#    Then external postpaid icci
#    Then I click "sim kart kontrol button" element at index 1
#    Then I click "numara page ok button" element at index 1
#
#    Then I click "numara secim sayfasi ileri button" element at index 1
#    Then I wait for 5 seconds
#
#    Then I see tarifeServis page
#
#    Then I must able to see "tarife ara text" element at index 1
#    Then I click "tarife sec card 4" element at index 1
#    Then I click "tarife sayfasi next button" element at index 1
#
#    Then I wait for 5 seconds
#
#    Then I see siparisTamamlama page
#    And I must able to see "complete text" element at index 1
#    Then I click element: "vodafone logo" if it exists at index 1
#    Then I wait for 5 seconds
#    Then I click element: "ok button" if it exists at index 1
#    #bunu ekle en son
##    When I click "PrePaidSummarySend" element at index 1
#
##    Then I see musteriPortali page
##    Then I must able to see "tebrikler text" element at index 1
##    And I must able to see "isleminiz basarıyla text" element at index 1
##    When I click "ok button" element at index 1
##    And I must able to see "bireysel text" element at index 1
#
#    Examples:
#      | username-s   | password-s   |
#      | mdirlik      | Voda1234567* |


    #PASS
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





