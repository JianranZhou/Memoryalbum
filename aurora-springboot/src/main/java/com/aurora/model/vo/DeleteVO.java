package com.aurora.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVO {

    @NotNull(message = "id can not be none")
    @ApiModelProperty(name = "ids", value = "delete id", required = true, dataType = "List<Integer>")
    private List<Integer> ids;

    @NotNull(message = "Status can not be none")
    @ApiModelProperty(name = "isDelete", value = "delete status", required = true, dataType = "Integer")
    private Integer isDelete;
}
