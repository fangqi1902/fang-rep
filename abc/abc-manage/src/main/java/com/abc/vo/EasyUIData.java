package com.abc.vo;

import java.util.List;

import com.abc.pojo.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIData {
	private Integer total;
	private List<Item> rows;

}
