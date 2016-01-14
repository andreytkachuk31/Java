package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.sample.stockwatcher.client.widget.UserProfile;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class StockWatcherEntryPoint implements EntryPoint {

    private static final int REFRESH_INTERVAL = 5000; // ms

    private StockPriceServiceAsync stockPriceSvc;

    private FlexTable stockTable;
    private TextBox newStockTextBox;
    private Label lastUpdateLabel;
    private Label errorMsgLabel;

    private List<String> stocks = new ArrayList<String>();

    public void onModuleLoad() {
        VerticalPanel verticalPanel = new VerticalPanel();

        stockTable = createStockTable();
        HorizontalPanel horizontalPanel = createHorizontalPanel();
        lastUpdateLabel = createLastUpdateLabel();
        errorMsgLabel = createErrorMessage();

        verticalPanel.add(errorMsgLabel);
        verticalPanel.add(stockTable);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(lastUpdateLabel);

        refreshPrice();

        RootPanel.get("stockList").add(verticalPanel);

        /*** Ui Binder ***/
        UserProfile userProfile = new UserProfile();
        RootPanel.get("userProfile").add(userProfile);
    }

    private FlexTable createStockTable() {
        FlexTable stockTable = new FlexTable();

        stockTable.setText(0, 0, "Symbol");
        stockTable.setText(0, 1, "Price");
        stockTable.setText(0, 2, "Change");
        stockTable.setText(0, 3, "Remove");

        return stockTable;
    }

    private HorizontalPanel createHorizontalPanel() {
        HorizontalPanel panel = new HorizontalPanel();

        newStockTextBox = new TextBox();
        Button addButton = new Button("Add");
        addButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                addStock();
            }
        });


        panel.add(newStockTextBox);
        panel.add(addButton);

        return panel;
    }

    private Label createLastUpdateLabel() {
        return new Label("Last update:");
    }

    private Label createErrorMessage() {
        Label label = new Label();

        label.addStyleName("errorMessage");
        label.setVisible(false);

        return label;
    }

    private void refreshPrice() {
        Timer refreshTimer = new Timer() {
            @Override
            public void run() {
                refreshPriceStockList();
            }
        };
        refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
        newStockTextBox.setFocus(true);
    }

    private void addStock() {
        final int row = stockTable.getRowCount();
        final String stockName = newStockTextBox.getText().toUpperCase().trim();

        newStockTextBox.setFocus(true);

        if (!stockName.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
            Window.alert("'" + stockName + "' is not a valid stockName.");
            newStockTextBox.selectAll();
            return;
        } else if (stocks.contains(stockName)) {
            Window.alert("'" + stockName + "' is already exist.");
            newStockTextBox.selectAll();
            return;
        }

        stocks.add(stockName);
        Button removeStock = new Button("x");
        removeStock.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int removeIndex = stocks.indexOf(stockName);
                stocks.remove(removeIndex);
                stockTable.removeRow(removeIndex + 1);

            }
        });

        stockTable.setText(row, 0, stockName);
        stockTable.setWidget(row, 3, removeStock);
        refreshPriceStockList();

        newStockTextBox.setText("");
    }

    private void refreshPriceStockList() {

        // «Ленивая» инициализация прокси сервиса
        if (stockPriceSvc == null) {
            stockPriceSvc = GWT.create(StockPriceService.class);
        }

        AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>() {

            public void onFailure(Throwable caught) {
                errorMsgLabel.setText("Error: " + caught.getMessage());
                errorMsgLabel.setVisible(true);
            }

            public void onSuccess(StockPrice[] result) {
                updateTable(result);
            }
        };

        stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);
    }

    private void updateTable(StockPrice[] prices) {

        for (int i = 0; i < prices.length; i++) {
            final StockPrice price = prices[i];
            final String stockName = price.getStockName();

            if (stocks.contains(stockName)) {
                int row = stocks.indexOf(stockName) + 1;
                String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
                String changeText = NumberFormat.getFormat("+#,##0.00;-#,##0.00").format(price.getChange());
                String changePercentText = NumberFormat.getFormat("+#,##0.00;-#,##0.00").format(price.getChangePercent());

                stockTable.setText(row, 1, priceText);
                stockTable.setText(row, 2, changeText + " (" + changePercentText + "%)");

            }
        }

        lastUpdateLabel.setText("Last update : " + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));
        errorMsgLabel.setVisible(false);
    }
}
