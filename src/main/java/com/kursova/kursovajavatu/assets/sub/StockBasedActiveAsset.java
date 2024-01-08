package com.kursova.assets.sub;

public class StockBasedActiveAsset extends ActiveAsset {
    protected String symbol;

    public StockBasedActiveAsset(){
        super();
    }
    public StockBasedActiveAsset(String name){
        super(name);
    }
    public StockBasedActiveAsset(String name, double currentMarketPrice){
        super(name,currentMarketPrice);
    }
    public StockBasedActiveAsset(String name, double currentMarketPrice, double quantityOwned){
        super(name,currentMarketPrice,quantityOwned);
    }
    public StockBasedActiveAsset(String name, double currentMarketPrice, double quantityOwned, String symbol){
        super(name,currentMarketPrice,quantityOwned);
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
}
