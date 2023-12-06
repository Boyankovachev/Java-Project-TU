package com.kursova.kursovajavatu.users.sub;

public class Notification {
    private AssetType assetType;
    private boolean assetTypeSettings; // true - all  false - only 1
    private String notificationTarget;
    private double notificationPrice;
    private String notificationName;
    public Notification(){}
    public Notification(AssetType assetType, String notificationName){
        this.assetType = assetType;
        this.notificationName = notificationName;
    }
    public Notification(AssetType assetType, double notificationPrice, String notificationName){
        this.assetType = assetType;
        this.notificationPrice = notificationPrice;
        this.notificationName = notificationName;
    }

    public double getNotificationPrice(){
        return notificationPrice;
    }

    public void setNotificationPrice(Double price){
        this.notificationPrice = price;
    }

    public String getNotificationName(){
        return notificationName;
    }

    public void setNotificationName(String name){
        this.notificationName = name;
    }

    public AssetType getAssetType(){
        return assetType;
    }

    public void setAssetType(AssetType assetType){
        this.assetType = assetType;
    }

    public void setAssetTypeSettings(boolean assetTypeSettings) {
        this.assetTypeSettings = assetTypeSettings;
    }
    public boolean getAssetTypeSettings() {
        return assetTypeSettings;
    }
    public void setNotificationTarget(String notificationTarget){
        this.notificationTarget = notificationTarget;
    }
    public String getNotificationTarget(){
        return notificationTarget;
    }


    public boolean isNotificationSimilar(Notification newNotification){
        /*
        checks if a new notification created by the user is similar
         */
        if(newNotification.getNotificationName().equals(this.notificationName)){
            return true;
        }
        return false;
    }

}
