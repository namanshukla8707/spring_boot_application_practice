package com.code.free.responses;

import java.util.List;

import lombok.Data;

@Data
public class ValidUsernameResponseDto {
   private Boolean isExist;
   private List<String> suggestions;
}  
