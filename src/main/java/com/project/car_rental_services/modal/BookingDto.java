package com.project.car_rental_services.modal;

public class BookingDto {
    private String fullName;
    private String mobileNumber;
    private String email;
    private String startDate;
    private boolean includeAccommodations;
    private String placeName;
    private String packageName;
    private String packagePrice;

    public BookingDto() {
    }

    public BookingDto(String fullName, String mobileNumber, String email, String startDate, boolean includeAccommodations, String placeName, String packageName) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.startDate = startDate;
        this.includeAccommodations = includeAccommodations;
        this.placeName = placeName;
        this.packageName = packageName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isIncludeAccommodations() {
        return includeAccommodations;
    }

    public void setIncludeAccommodations(boolean includeAccommodations) {
        this.includeAccommodations = includeAccommodations;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }
}
