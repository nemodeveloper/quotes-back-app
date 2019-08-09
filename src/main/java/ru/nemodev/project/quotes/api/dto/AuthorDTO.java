package ru.nemodev.project.quotes.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AuthorDTO implements Serializable
{
    @ApiModelProperty(value = "Id", required = true)
    private Long id;

    @ApiModelProperty(value = "Full name author", required = true)
    private String fullName;

    @ApiModelProperty("Full path to static image author")
    private String imageURL;
}
