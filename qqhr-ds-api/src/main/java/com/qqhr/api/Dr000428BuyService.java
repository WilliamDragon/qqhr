package com.qqhr.api;

import com.qqhr.common.utils.R;
import com.qqhr.dto.Dr000428RequestDto;
import com.qqhr.dto.Xua01101ResponseDto;

import java.util.List;
import java.util.Map;

public interface Dr000428BuyService {

    //public R execute(Map param);

    public R execute(Dr000428RequestDto dr00428RequestDto);
}
