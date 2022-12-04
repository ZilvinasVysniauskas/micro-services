package com.thoughtmechanix.licenses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}
