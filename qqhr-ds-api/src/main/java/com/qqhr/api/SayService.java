package com.qqhr.api;

import com.qqhr.dto.Xua01101RequestDto;
import com.qqhr.dto.Xua01101ResponseDto;

import java.util.List;
import java.util.Map;

public interface SayService {

    public String sayHello(String str);

    public List<Xua01101ResponseDto> listUser(Map param);
}
