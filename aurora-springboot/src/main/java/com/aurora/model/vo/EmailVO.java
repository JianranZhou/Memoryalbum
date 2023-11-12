package com.aurora.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "bind emil")
public class EmailVO {

    @NotBlank(message = "Email can not be none")
    @Email(message = "Email format is not valid")
    @ApiModelProperty(name = "email", value = "username", required = true, dataType = "String")
    private String email;

    @NotBlank(message = "Verification code can not be none")
    @ApiModelProperty(name = "code", value = "email verification code", required = true, dataType = "String")
    private String code;

}
