package com.kursova.assets;

import com.kursova.assets.finished.*;
import com.kursova.assets.sub.PurchaseInfo;
import com.kursova.exceptions.AssetNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AssetManager {
    private List<Stock> stocks = new ArrayList<>();
    private List<PassiveResource> passiveResources = new ArrayList<>();
    private List<Index> indexFunds = new ArrayList<>();
    private List<Commodities> commodities = new ArrayList<>();
    private List<Crypto> cryptoCurrencies = new ArrayList<>();

    public AssetManager(){}

    public List<Stock> getAllStocks(){
        return stocks;
    }
    public void addStock(Stock stock){
        stocks.add(stock);
    }
    public void addStocks(List<Stock> newStocks){
        if(newStocks != null) {
            stocks.addAll(newStocks);
        }
    }

    public List<PassiveResource> getAllPassiveResources(){
        return passiveResources;
    }
    public void addPassiveResource(PassiveResource passiveResource){
        passiveResources.add(passiveResource);
    }
    public void addPassiveResources(List<PassiveResource> newPassiveResources){
        if(newPassiveResources != null) {
            passiveResources.addAll(newPassiveResources);
        }
    }

    public List<Index> getAllIndex(){
        return indexFunds;
    }
    public void addIndex(Index index){
        indexFunds.add(index);
    }
    public void addIndexList(List<Index> newIndexFunds){
        if(newIndexFunds != null) {
            indexFunds.addAll(newIndexFunds);
        }
    }

    public List<Commodities> getCommodities(){
        return commodities;
    }
    public void addCommodity(Commodities commodity){
        commodities.add(commodity);
    }
    public void addCommodities(List<Commodities> newCommodities){
        if(newCommodities != null) {
            commodities.addAll(newCommodities);
        }
    }

    public List<Crypto> getCrypto(){
        return cryptoCurrencies;
    }
    public void addCrypto(Crypto crypto){
        cryptoCurrencies.add(crypto);
    }
    public void addCryptoList(List<Crypto> newCrypto){
        if(newCrypto != null) {
            cryptoCurrencies.addAll(newCrypto);
        }
    }



    public Stock getStockByName(String stockName){
        for(Stock stock: stocks){
            if(stock.getName().equals(stockName)){
                return stock;
            }
        }
        return null;
    }
    public Stock getStockBySymbol(String stockSymbol){
        for(Stock stock: stocks){
            if(stock.getSymbol().equals(stockSymbol)){
                return stock;
            }
        }
        return null;
    }
    public PassiveResource getPassiveResourceByName(String name){
        for(PassiveResource passiveResource: passiveResources){
            if(passiveResource.getName().equals(name)){
                return passiveResource;
            }
        }
        return null;
    }
    public Index getIndexByName(String name){
        for(Index index: indexFunds){
            if(index.getName().equals(name)){
                return index;
            }
        }
        return null;
    }
    public Index getIndexBySymbol(String indexSymbol){
        for(Index index: indexFunds){
            if(index.getSymbol().equals(indexSymbol)){
                return index;
            }
        }
        return null;
    }
    public Crypto getCryptoByName(String name){
        for(Crypto crypto: cryptoCurrencies){
            if(crypto.getName().equals(name)){
                return crypto;
            }
        }
        return null;
    }
    public Crypto getCryptoBySymbol(String cryptoSymbol){
        for(Crypto crypto: cryptoCurrencies){
            if(crypto.getSymbol().equals(cryptoSymbol)){
                return crypto;
            }
        }
        return null;
    }
    public Commodities getCommodityByName(String name){
        for(Commodities commodity: commodities){
            if(commodity.getName().equals(name)){
                return commodity;
            }
        }
        return null;
    }

    public void addPurchaseToResource(String resourceType, String resourceIdentifier, PurchaseInfo purchaseInfo){
        /*
            Adds purchase to user.
            resourceType - investment type (stock,index,crypto,commodity)
            resourceIdentifier - name or symbol of investment
            purchaseInfo - the purchase to be added
         */
        int i;
        switch (resourceType){
            case "stock":
                for(i=0; i<stocks.size(); i++){ //find the resource
                    if(stocks.get(i).getSymbol().equals(resourceIdentifier)){
                        break;
                    }
                }
                stocks.get(i).addPurchase(purchaseInfo);
                break;
            case "index":
                for(i=0; i<indexFunds.size(); i++){ //find the resource
                    if(indexFunds.get(i).getSymbol().equals(resourceIdentifier)){
                        break;
                    }
                }
                indexFunds.get(i).addPurchase(purchaseInfo);
                break;
            case "crypto":
                for(i=0; i<cryptoCurrencies.size(); i++){ //find the resource
                    if(cryptoCurrencies.get(i).getSymbol().equals(resourceIdentifier)){
                        break;
                    }
                }
                cryptoCurrencies.get(i).addPurchase(purchaseInfo);
                break;
            case "commodity":
                for(i=0; i<commodities.size(); i++){ //find the resource
                    if(commodities.get(i).getName().equals(resourceIdentifier)){
                        break;
                    }
                }
                commodities.get(i).addPurchase(purchaseInfo);
                break;
        }
    }

    public boolean isAssetInList(String assetType, String asset){
        /*
            returns true if investment is in object
            assetType - investment type (stock,index,crypto,commodity)
            asset - name or symbol of investment
         */
        int i;
        boolean isPresent = false;
        switch (assetType){
            case "stock":
                for(i=0; i<stocks.size(); i++){ //find the resource
                    if(stocks.get(i).getSymbol().equals(asset)){
                        isPresent = true;
                        break;
                    }
                }
                break;
            case "index":
                for(i=0; i<indexFunds.size(); i++){ //find the resource
                    if(indexFunds.get(i).getSymbol().equals(asset)){
                        isPresent = true;
                        break;
                    }
                }
                break;
            case "crypto":
                for(i=0; i<cryptoCurrencies.size(); i++){ //find the resource
                    if(cryptoCurrencies.get(i).getSymbol().equals(asset)){
                        isPresent = true;
                        break;
                    }
                }
                break;
            case "commodity":
                for(i=0; i<commodities.size(); i++){ //find the resource
                    if(commodities.get(i).getName().equals(asset)){
                        isPresent = true;
                        break;
                    }
                }
                break;
            case "passive-resource":
                for(i=0; i<passiveResources.size(); i++){ //find the resource
                    if(passiveResources.get(i).getName().equals(asset)){
                        isPresent = true;
                        break;
                    }
                }
                break;
        }
        return isPresent;
    }

    public void removeAsset(String assetType, String asset) throws AssetNotFoundException {
        /*
            returns true if user has ivestment
            assetType - investment type (stock,index,crypto,commodity)
            asset - name or symbol of investment
         */
        if(!isAssetInList(assetType, asset)){
            throw new AssetNotFoundException(assetType + ": " + asset + " not found");
        }
        switch (assetType){
            case "stock":
                Stock tempStock = getStockBySymbol(asset);
                stocks.remove(tempStock);
                break;
            case "index":
                Index tempIndex = getIndexBySymbol(asset);
                indexFunds.remove(tempIndex);
                break;
            case "crypto":
                Crypto cryptoTemp = getCryptoByName(asset);
                cryptoCurrencies.remove(cryptoTemp);
                break;
            case "commodity":
                Commodities commodityTemp = getCommodityByName(asset);
                commodities.remove(commodityTemp);
                break;
            case "passive-resource":
                PassiveResource passiveResourceTemp = getPassiveResourceByName(asset);
                passiveResources.remove(passiveResourceTemp);
                break;
        }
    }

    public double calculateAllStocksWorth(){
        //return value of all owned stocks
        double total = 0;
        for(Stock stock: stocks){
            total = total + stock.getCurrentMarketPrice() * stock.getQuantityOwned();
        }
        return total;
    }
    public double calculateAllIndexWorth(){
        //return value of all owned indexes
        double total = 0;
        for(Index index: indexFunds){
            total = total + index.getCurrentMarketPrice() * index.getQuantityOwned();
        }
        return total;
    }
    public double calculateAllCryptoWorth(){
        //return value of all owned cryptocurrencies
        double total = 0;
        for(Crypto crypto: cryptoCurrencies){
            total = total + crypto.getCurrentMarketPrice() * crypto.getQuantityOwned();
        }
        return total;
    }
    public double calculateAllCommodityWorth(){
        //return value of all owned commodities
        double total = 0;
        for(Commodities commodity: commodities){
            total = total + commodity.getCurrentMarketPrice() * commodity.getQuantityOwned();
        }
        return total;
    }
    public double calculateAllPassiveResourceWorth(){
        //return value of all owned passive resources
        double total = 0;
        for(PassiveResource passiveResource: passiveResources){
            total = total + passiveResource.getCurrentMarketPrice();
        }
        return total;
    }
    public double calculateWholePortfolio(){
        //return value of whole portfolio
        return calculateAllStocksWorth() + calculateAllIndexWorth() + calculateAllCryptoWorth() +
                calculateAllCommodityWorth() + calculateAllPassiveResourceWorth();
    }

    public void updateStocks(List<Stock> updatedStocks){
        stocks.clear();
        stocks.addAll(updatedStocks);
    }
    public void updateIndex(List<Index> updatedIndexList){
        indexFunds.clear();
        indexFunds.addAll(updatedIndexList);
    }
    public void updateCrypto(List<Crypto> updatedCryptoList){
        cryptoCurrencies.clear();
        cryptoCurrencies.addAll(updatedCryptoList);
    }
    public void updateCommodities(List<Commodities> updatedCommodityList){
        commodities.clear();
        commodities.addAll(updatedCommodityList);
    }

}
