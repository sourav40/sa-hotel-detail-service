package edu.miu.cs590.hoteldetailsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.lang.annotation.Inherited;
import java.util.UUID;

@Document(collection = "hotels")
@Data
@Builder
@AllArgsConstructor
public class Hotel {
    @MongoId
    @Field(value = "_id", targetType = FieldType.STRING)
    private UUID id;
    private String name;
    private String description;
    private String address;
    private String location;
    private String imageName;
    private String bucketName;
    private UUID userId;
    private String feature;
    private String amenity;
    private String cancellationPolicy;
    @Transient
    private String imageURL;

    public Hotel() {
        if(this.id == null) { this.id = UUID.randomUUID();}
    }
}
