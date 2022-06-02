package models;

/**
 * Building model. It has an ID and two one to one relationships with Public Elevator and FreightElevator.
 * It has empty and all args constructor, getters for all the attributes and no setters.
 */
public class Building {

    //Attributes
    private Long id;
    private Integer storiesHigh = 50; //50 stories high and a basement.
    private PublicElevator publicElevator;
    private FreightElevator freightElevator;

    //Empty Constructor
    public Building(){}

    //AllArgs Constructor


    public Building(Long id, Integer storiesHigh, PublicElevator publicElevator, FreightElevator freightElevator) {
        this.id = id;
        this.storiesHigh = storiesHigh;
        this.publicElevator = publicElevator;
        this.freightElevator = freightElevator;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public Integer getStoriesHigh() {
        return storiesHigh;
    }

    public PublicElevator getPublicElevator() {
        return publicElevator;
    }

    public FreightElevator getFreightElevator() {
        return freightElevator;
    }
}
