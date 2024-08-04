package com.inotePoc.InotePoc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ADDAI")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressEntity {
    @Id
    private String addressId;

    @JsonProperty("Structured")
    private StructuredAddress structured;

    @JsonProperty("Unstructured")
    private UnstructuredAddress unstructured;
}
