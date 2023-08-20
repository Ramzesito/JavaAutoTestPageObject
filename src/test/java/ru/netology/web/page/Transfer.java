package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Transfer {

    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInputNew = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

    public Transfer() {
        transferHead.shouldBe(visible);
    }

    public Dashboard makeValidTransfer(String amountToTransfer, DataGenerator.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new Dashboard();
    }

    public void makeTransfer(String amountToTransfer, DataGenerator.CardInfo cardInfo) {
        amountInputNew.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
