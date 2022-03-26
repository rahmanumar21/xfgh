package vistula.ar.vocationtourapp.model;

public class TopPlacesData {

    String placeName;
    String countryName;
    String price;
    Integer imageUrl;

    public TopPlacesData(String placeName, String countryName, String price, Integer imageUrl) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getPrice() {
        return price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }
}
