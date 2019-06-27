package com.abc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {
	private Integer error;  //0表示成功， 1表示失败
	private String url;
	private Integer width;
	private Integer height;

}
