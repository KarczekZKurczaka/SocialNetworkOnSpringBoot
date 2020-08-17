package com.brunner.social.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SqlResultSetMapping(
        name = "findAllDataMapping",
        classes = @ConstructorResult(
                targetClass = com.brunner.social.dto.PostDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "userId", type = Long.class),
                }
        )
)

@Data
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post {

    @ApiModelProperty(notes = "Generated Post Id.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Title of the post.")
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @ApiModelProperty(notes = "Optional description of the post.")
    @Size(max = 250)
    private String description;

    @ApiModelProperty(notes = "Content of the post.")
    @NotNull
    @Lob
    private String content;

    @ApiModelProperty(notes = "This is the user who wrote the post.")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;
}

