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
public class QuoteDTO implements Serializable
{
    @ApiModelProperty(value = "Id", required = true)
    private Long id;

    @ApiModelProperty(value = "Text quote", required = true)
    private String text;

    @ApiModelProperty("Source quote")
    private String source;

    @ApiModelProperty("Year quote")
    private String year;

    @ApiModelProperty(value = "Category quote", required = true)
    private CategoryDTO category;

    @ApiModelProperty(value = "Author quote", required = true)
    private AuthorDTO author;
}
