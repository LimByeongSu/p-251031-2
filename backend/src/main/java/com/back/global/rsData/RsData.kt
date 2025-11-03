package com.back.global.rsData

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
class RsData<T> @JvmOverloads constructor(
    val resultCode: String,
    val msg: String,
    val data: T? = null  //디폴트 파라미터(여기선 값이없으면 null로 설정)
                         //컨트롤러에서 응답을 resultCode, msg 2개만 하는 경우가 있기때문에 data가 없는경우가 있다.
                        //그래서 코틀린에선 디폴트 파라미터를 사용하였는데
                         // -> 자바에선 이걸 이해못하기 때문에 @JvmOverloads constructor를 붙임
                        //이게 쓰기 싫으면 부생성자를 쓰자.
) {
    @get:JsonIgnore
    val statusCode: Int
        get() = resultCode.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt()
}
