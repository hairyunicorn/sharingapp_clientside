package shareapp.mobileapps.master.zhaw.ch.sharingapp_clientside;

import java.util.UUID;

public class Item {
    private String ownerId;
    private UUID itemId;
    private String title;
    private String category;
    private String description;
    private String city;
    private String zipCode;
    private String telephoneNumber;

    public Item(String ownerId, String title, String category, String description,
                String city, String zipCode, String telephoneNumber) {
        this.ownerId = ownerId;
        this.title = title;
        this.category = category;
        this.description = description;
        this.city = city;
        this.zipCode = zipCode;
        this.telephoneNumber = telephoneNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
