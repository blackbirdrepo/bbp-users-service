package com.bbp.usersservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString(of = {"emailAddress", "emailType"})
public class EmailDto {

    private String emailAddress;

    private String emailType;

    private Map<String, Object> contentProperties;
}
