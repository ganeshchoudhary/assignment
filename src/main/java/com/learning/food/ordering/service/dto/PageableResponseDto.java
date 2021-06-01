package com.learning.food.ordering.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponseDto<T> {
	
	private int currentPage;
	private int lastPage;
	private int pageSize;
	private List<T> details;
}
