package com.brunner.social.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "User")
public class User {

    @ApiModelProperty(notes = "Roles that user is going to have.")
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;
    @ApiModelProperty(notes = "Generated User Id.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ApiModelProperty(notes = "Email of the user for logging in.")
    @NonNull
    @Size(max = 30)
    private String email;
}
